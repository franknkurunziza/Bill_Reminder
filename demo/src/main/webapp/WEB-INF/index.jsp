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
    <title>Register Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/master.css">
</head>
<body>
		    <div class="container">
        <div class="row content-reg">
          <div class="col-md-6">
            <h3 class="signin-text-reg mb-3">Register</h3>
            <form:form method="POST" action="registration" modelAttribute="user">
              <div class="form-group">
                <form:label path="firstname">First Name</form:label>
                <form:input type="text" path="firstname" class="form-control"/>
              </div>
              <div class="form-group">
                <form:label path="lastname">Last Name</form:label>
                <form:input type="text" path="lastname" class="form-control"/>
              </div>
              <div class="form-group">
                <form:label path="email">Email</form:label>
                <form:input type="email" path="email" class="form-control"/>
              </div>
              <div class="form-group">
                <form:label path="password">Password</form:label>
                <form:input type="password" path="password" class="form-control"/>
              </div>
              <div class="form-group">
                <form:label path="passwordConfirmation">Confirm Password</form:label>
                <form:input type="password" path="passwordConfirmation" class="form-control"/>
              </div>
              <input type="submit" value="Register" class="btn btn-class btn-reg"/>
<!--               <button type="submit" name="button" class="btn btn-class btn-reg">Register</button> -->
              <a href="/login"> Login </a>
            </form:form>
          </div>
          <div class="col-md-6" >
          	<form:errors path="user.*" />
          </div>
        </div>
      </div>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/
    js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA
    13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous">
    </script>
</body>
</html>




