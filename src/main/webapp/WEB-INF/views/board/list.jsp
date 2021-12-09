<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!-- 날짜 데이트를 사용하기 위해서  -->
<%@ taglib prefix="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 함수사용을 위해 -->
<%@ taglib prefix="fn" uri ="http://java.sun.com/jsp/jstl/functions" %>
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
			<form id="form" method="get" action="/list" >
			  <div class="row mb-3">
				    <label for="keyword" class="col-sm-2 col-form-label"><spring:message code="search.keyword"/></label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name ="keyword" value="${param.keyword}" id="keyword" placeholder="검색어를 입력하세요.">
				    </div>
			  </div>
			  <button type="submit" class="btn btn-primary"><spring:message code="button.search"/></button>
			</form>
			<table class="table table-hover">
			  <thead>
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">제목</th>
			      <th scope="col">조회수</th>
			      <th scope="col">등록일</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach items="${boardList}" var="board"  varStatus="status">
			  	 	<tr>
				      <th scope="row">${status.count}</th>
				      <td><a href ="/board/detail/${board.boardSeq}"> ${board.title} </a></td>    
				      <td>${board.viewCount}</td>
				      <td><fmt:formatDate value="${board.regDate}" pattern="yyyy.MM.dd HH:mm"/></td>
				    </tr>
			  	</c:forEach>
				<c:if test="${fn:length(boardList)==0}">
					<tr>
						<td colspan="4">게시판이 존재하지 않습니다.</td>
					</tr>
				</c:if>
			  </tbody>
			</table>
			<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
			  <a href="/board/form" class="btn btn-primary me-md-2" type="button"><spring:message code="button.form"/></a>
			</div>
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