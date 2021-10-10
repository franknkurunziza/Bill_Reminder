<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/master.css">
    <title>Tacos</title>

</head>
<body>

	<div class="container">
	
		<div class="content">
		<h1>WellCome<c:out value="${user.firstname }"/></h1>
		<c:forEach items="${user.bills}" var="bill">
		<c:if test="${bill.payed==false}">
			<p>Bill Name: ${bill.name}</p>
			<p>Bill Name: ${bill.amount}</p>
			<p>Bill Name: ${bill.payDate}</p>
			<p>-------------------------------</p>
		
		</c:if>

		</c:forEach>
		<a href="/dashboard"> Home </a>
		</div>
	</div>




	      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/
    js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA
    13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous">
    </script>
</body>
<html>

