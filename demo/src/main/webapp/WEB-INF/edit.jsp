<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
   	<link rel="stylesheet" type="text/css" href="/css/master.css">
	<title>Edit</title>


</head>
<body>
	<div class="container">

		<div class="content">
			<h3>
				Welcome
				<c:out value="${userLogged.firstname}" />
			</h3>
			<h3 class="signin-text-reg mb-3">Create A Bill</h3>
			<form:form method="POST" action="/update/${id}" modelAttribute="bill">
				<div class="form-group">
					<form:label path="name">Bill Name</form:label>
					<form:input type="text" path="name" class="form-control" />
				</div>
				<div class="form-group">
					<form:label path="amount">Amount Due</form:label>
					<form:input type="number" path="amount" class="form-control" />
				</div>
				<div class="form-group">
					<form:label path="payDate">Date Due</form:label>
					<form:input type="date"  path="payDate" class="form-control" />
					
				</div>
				
				<input type="submit" value="Create" class="btn btn-class btn-reg" />
			</form:form>
			<a href="/dashboard"> Home </a>
			<div>
				<form:errors path="bill.*" />
			</div>
		</div>

	</div>

      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/
    js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA
    13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous">
    </script>

</body>
<html>