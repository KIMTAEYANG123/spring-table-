<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<div class="container pt-5">
			<form id="form" method="post" action="/save" >
				<input type="hidden" name="boardSeq" value="${board == null ? 0 : board.boardSeq }"/>
				
			  <div class="row mb-3">
				    <label for="title" class="col-sm-2 col-form-label"><spring:message code="board.title"/></label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name ="title" value="${board.title}" id="title">
				    </div>
			  </div>
			  <div class="row mb-3">
				    <label for="contents" class="col-sm-2 col-form-label"><spring:message code="board.contents"/></label>
				    <div class="col-sm-10">
				      <textarea class="form-control" name="contents" id="contents" placeholder="<spring:message code="placeholder.required"/>">${board.contents}</textarea>
				    </div>
			  </div>
			  <button type="submit" class="btn btn-primary"><spring:message code="button.save"/></button>
			</form>
	</div>
	
	<script>
		const $form = $('#form');
		$form.bind('submit' , ()=>{
			$.ajax({
				url : '/board/${menuType}/save',
				type : 'post',
				data : $form.serialize(),
				dataType : 'json',
				success: (res)=>{
					if(res.code == 'SUCCESS'){
						window.location.href = "/board/${menuType}/"+res.data;	
					}else{
						alert(res.message);
					}
					console.log(res)
				}
			})
			return false;
		});
	</script>
</body>
</html>