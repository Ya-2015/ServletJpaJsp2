<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">

<title>Order List</title>
</head>
<body>

<div class="container">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Shopping Cart</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
        <li ><a href="HomeServlet">Home</a></li>
        <li class="active"><a href="#">Order List</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <c:if test="${not empty username}">
      		<li><a href="#"></span> Welcome ${username }</a></li>
    		 <li><a href="LogoutServlet"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</c:if>
       <c:if test="${empty username}">
    		 <li><a href="Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		</c:if>
      </ul>
    </div>
  </div>
</nav>
</div>


<c:if test="${not empty username}">
<div class="container" style ="background:url('https://newevolutiondesigns.com/images/freebies/white-wallpaper-9.jpg')">
</c:if>
<c:if test="${empty username}">
<div class="container">
</c:if>

<c:if test="${empty items}">
<h2>Currently No Oder</h2>
</c:if>

<c:if test="${not empty items}">
<h2>Order List</h2>
    <table class="table table-bordered">
<tr>
    <th>Name</th>
    <th>Price</th> 
    <th>Quantity</th>
    <th>Cost</th>
    <th>Customer</th>
    <th>Date</th>
  </tr>
  <c:forEach items="${items}" var="item">
    <tr>      
        <td>${item.productname}</td>
        <td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${item.price}" type="currency"/></td>
        <td>${item.quantity}</td>
        <td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${item.productcost}" type="currency"/></td>
        <td>${item.username}</td>
        <td>${item.purchasedate}</td>
    </tr>
	</c:forEach>
</table>

</c:if>

</div>



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>

</body>
</html>