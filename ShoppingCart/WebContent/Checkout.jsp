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

<title>Checkout</title>
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
        <li><a href="HomeServlet">Home</a></li>
        <li class="active"><a href="#">Check Out</a></li>
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
<h2>No Item in Shopping Cart</h2>
</c:if>

<c:if test="${not empty items}">
<c:if test="${items.size() == 1}">
<h2>You have 1 item in your cart</h2>
</c:if>
<c:if test="${items.size() > 1}">
<h2>You have ${items.size()} items in your cart</h2>
</c:if>

    <table class="table table-bordered">
<tr>
    <th>Name</th>
    <th>Description</th>
    <th>Price</th> 
    <th>Quantity</th>
    <th>Cost</th>
    <th></th>
  </tr>
  <c:forEach items="${items}" var="item" varStatus="loop">
    <tr>      
        <td>${item.productname}</td>
        <td><img src="img/${item.image}" alt="HTML5 Icon" width="128" height="128"></td>
        <td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${item.price}" type="currency"/></td>
        <!--  <td>
        	${item.quantity}
        </td>-->
        <td>
        	<form action="Checkout" method="post">
        	<div class="row">
        		<input type="hidden" name="chgid" value=${loop.index}>
        		<input type="hidden" name="chgid2" value=${item.purchaseno}>
				<div class="pull-left col-md-3">
					<p>${item.quantity}</p>
				</div>
				<div class="pull-left col-md-9">
					<select name="chgquantity" class="selectpicker" onchange='this.form.submit()'>
  					<% for(int i = 1; i < 100; i+=1) { %>
  						<option value=<%=i %>><%=i %></option>
    				<% } %>
					</select> 
				</div>
			</div>
        	</form>
        </td>
        <td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${item.productcost}" type="currency"/></td>
        <td><a class="btn btn-default" href="Checkout?PurchaseId=${item.purchaseno}" role="button">Remove</a></td>
    </tr>
	</c:forEach>
</table>

<br>
<h3>Total Amount: <fmt:setLocale value="en_US"/><fmt:formatNumber value="${totalcost}" type="currency"/></h3>
<br>

<c:if test="${not empty username}">
      		<a class="btn btn-success" href="#" role="button">Payment</a>
		</c:if>
<c:if test="${ empty username}">
      		<a class="btn btn-primary" href="Login.jsp" role="button">Check Out</a>
		</c:if>
</c:if>


</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
</body>
</html>

<!-- ${prod.image} -->