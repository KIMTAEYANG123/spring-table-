<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="container">
		<div class="card">
			  <div class="card-header">
			    <h3>${board.title}</h3>
			  </div>
			  <div class="card-body">
			    <blockquote class="blockquote mb-0">
			      <p>${board.contents}</p>
			    </blockquote>
			  </div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
			  <a href="/board/${menuType}" class="btn btn-primary me-md-2" type="button"><spring:message code="button.list"/></a>
			  <a href="/board/${menuType}/edit/${board.boardSeq}" class="btn btn-primary" type="button"><spring:message code="button.edit"/></a>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script>
</body>
</html>