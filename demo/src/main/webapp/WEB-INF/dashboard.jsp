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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/master.css">
</head>
<body>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/
    js/bootstrap.bundle.min.js"
		integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA
    13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ"
		crossorigin="anonymous">
		
	</script>


	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="/bill">Create Bill </a>
				<div class="collapse navbar-collapse" id="navbarNav">|
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/unpayed"> UpComing Bill</a></li>
							
					</ul>
				</div>
				<a class="navbar-brand" href="/logout"> Logout </a>
			</div>
		</nav>
	</div>
		<div class="container">
    <div class="row content">

<table class="table">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">Amount</th>
      <th scope="col">Date</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${user.bills }" var="bill">
  	  <tr>
      <th scope="row"> <a href="/edit/${bill.id}">${bill.name}</a> </th>
      <td>${bill.amount}</td>
      <td>${bill.payDate}</td>
      <td>
      <c:if test="${bill.payed==false}">
      	<a href="/pay/${bill.id}">Pay</a> |
      </c:if>
      <a href="/delete/${bill.id }">Delete</a> </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
      
    </div>
    
  </div>

</body>
<html>