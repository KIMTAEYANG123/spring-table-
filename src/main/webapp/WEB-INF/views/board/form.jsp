<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<div class="container pt-5">
			<form id="form" method="post" action="/save" >
				<input type="hidden" name="boardType" value="COMMUNITY"/>
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
	<script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script>
	<script>
		const $form = $('#form');
		$form.bind('submit' , ()=>{
			$.ajax({
				url : '/board/save',
				type : 'post',
				data : $form.serialize(),
				dataType : 'json',
				success: (data)=>{
					if(data.code == 'SUCCESS'){
					
					}else{
						alert(data.message);
					}
					console.log(data)
				}
			})
			return false;
		});
	</script>
</body>
</html>