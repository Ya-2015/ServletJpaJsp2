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

<title>Detail</title>
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
        <li class="active"><a href="#">Detail</a></li>
    	<c:if test="${not empty username and username != 'admin'}">
        <li><a href="Checkout">Check Out</a></li>
        </c:if>
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

<table class="table table-bordered">
<tr>
    <th>Name</th> 
    <th>Description</th>
    <th>Price</th>
    <c:if test="${not empty username and username != 'admin'}">
    <th>Add to Cart</th>
    </c:if>
  </tr>
    <tr>      
        <td>${product.prodname}</td>
        <td><!-- ${product.description}<br><img src="img/${product.image}" alt="HTML5 Icon" width="128" height="128"> -->
        	<img src="img/${product.image}" alt="HTML5 Icon" width="200" height="200">
        	<br>
        	<br>
        	<p>${product.description}</p>
        <!-- <div class="row">
  			<div class="col-md-4">
  			<img src="img/${product.image}" alt="HTML5 Icon" width="200" height="200">
  			</div>
  			<div class="col-md-8">
  			<p>${product.description}</p>
  			</div> 
		</div>-->
        </td>
        <td><fmt:setLocale value="en_US"/><fmt:formatNumber value="${product.price}" type="currency"/></td>
        <c:if test="${not empty username and username != 'admin'}">
			<td>
			<form action="ConfirmationServlet" method="post">
			<select name="quantity" class="selectpicker">
			  <% for(int i = 1; i < 100; i+=1) { %>
			  	<option value=<%=i %>><%=i %></option>
			    <% } %>
			</select> 
			<br>
			<br>
			<button type="submit" class="btn btn-primary">Add to Cart</button>
			</form>
			</td>
		</c:if>
    </tr>
</table>

<br>
 
 <!-- display all reviews -->
  <c:if test="${not empty reviews}">
  <h3>Reviews</h3>
  <br>
  <table class="table table-bordered">
<tr>
    <th>Rating</th> 
    <th>Review</th>
    <th>Customer</th>
    <th>Date</th>
  </tr>
    <c:forEach items="${reviews}" var="rew">
    <tr>      
        <td>${rew.poststar }</td>
        <td>${rew.post }</td>
        <td>${rew.username }</td>
        <td>${rew.postdate }</td>
    </tr>
	</c:forEach>
</table>
  </c:if>
 
 <!-- post reviews for logged in user -->
 <c:if test="${not empty username and username != 'admin'}">
 <br>
 <h3>Write A Review</h3>
 <br>
 <form action="AddToCartServlet" method="post">
 		<input type="hidden" name="ProductId" value=${product.prodid}>
 		<div class="row">
  			<div class="col-md-1">Rating</div>
  			<div class="col-md-11">
  				<select name="rating" class="selectpicker">
  					<% for(int i = 5; i > 0; i-=1) { %>
  					<option value=<%=i %>><%=i %></option>
    				<% } %>
				</select> 
  			</div>
		</div>
		<br>
		<div class="form-group">
    		 <textarea class="form-control" rows="5" id="post" name="post"></textarea>
  		</div>
		<button type="submit" class="btn btn-primary">Post</button>
 </form>
 </c:if>


</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>

</body>
</html>