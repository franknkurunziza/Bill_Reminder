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
    <title>Login</title>
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/master.css">
</head>
<body>
	
	<div class="container">
    <div class="row content">
      <div class="col-md-6 mb-3">
        <img src="image/phonepay.jpg" alt="image" class="img-fluid">
      </div>
      
      <div class="col-md-6">
        <h3 class="signin-text mb-3">Sign In</h3>
        <form method="POST" action="/authanticate">
          <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" class="form-control"/>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control"/>
          </div>
          <input type="submit" value="Login" class="btn btn-class btn-reg"/>
          <a href="/"> Sign Up</a>
        </form>
        <c:out value="${error}" />
      </div>
      
    </div>
    
  </div>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/
    js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA
    13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous">
    </script>
    
</body>
<html>

