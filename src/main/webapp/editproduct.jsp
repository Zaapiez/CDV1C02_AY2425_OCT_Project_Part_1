<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Inventory Management Application</title>
<link rel="stylesheet"
href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">
<meta charset="UTF-8">
</head>
<body>


<nav class="navbar navbar-expand-md navbar-light">
<div>
<a class="navbar-brand">Edit Product</a>
</div>
<ul class="navbar-nav">
<li><a
href="<%=request.getContextPath()%>/inventoryservlet/dashboard"
class="nav-link">Back to Dashboard</a></li>
</ul>
</nav>
<div class="container col-md-6">
<div class="card">
<div class="card-body">
<c:if test="${!empty product.prodname}">
<form action="update" method="post">
</c:if>
<c:if test="${empty product.prodname}">
<form action="insert" method="post">
</c:if>
<caption>
<h2>
<c:if test="${!empty product.prodname}">
Edit Product
</c:if>
<c:if test="${empty product.prodname}">
Add New Product
</c:if>
</h2>
</caption>
<br>
<br>
<c:if test="${product != null}">
<input type="hidden" name="oriName"
value="<c:out
value='${product.prodname}' />" />
</c:if>
<fieldset class="form-group">
<label>Product Name</label> <input type="text"
value="<c:out
value='${product.prodname}' />" class="form-control"
name="prodname" required="required">
</fieldset>
<fieldset class="form-group">
<label>Quantity</label> <input type="text"
value="<c:out
value='${product.prodquantity}' />" class="form-control"
name="prodquantity">
</fieldset>

<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>
</body>
</html>