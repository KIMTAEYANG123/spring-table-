
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<style type="text/css">
	.active {
		color : red !important; 
	}
</style>

<!-- css와 script는 매번 사용하기 떄문에 공통 레이아웃에 넣음  -->
<script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU=" crossorigin="anonymous"></script>
<sitemesh:write property = "head"/>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">김태양</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link " aria-current="page" href="#">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class=" ${menuType.name() == 'community' ? 'active' : '' } nav-link" href="/board/community"><spring:message code="menu.community"/></a>
	        </li>
	        <li>
	        	<a class=" ${menuType.name() == 'notice' ? 'active' : ''} nav-link" href="/board/notice"><spring:message code="menu.notice"/></a>
	        </li>
	        <li>
	        	<a class=" ${menuType.name() == 'faq' ? 'active' : '' } nav-link" href="/board/faq"><spring:message code="menu.faq"/></a>
	        </li> 
	        <li>
	         	<a class=" ${menuType.name() == 'inquiry' ? 'active' : '' } nav-link" href="/board/inquiry"><spring:message code="menu.inquiry"/></a>
	        </li>	         
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>
	
	<sitemesh:write property = "body"/>
	
</body>
</html>