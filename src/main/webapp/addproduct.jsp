<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Inventory Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="UTF-8">
</head>

<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Add New Product </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/inventoryservlet/dashboard"
				class="nav-link">Back to Product Page</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<form action="AddProductServlet" method="Post">
					<fieldset class="form-group">
						<caption>
							<h2>Add product</h2>
						</caption>
						<br> <br> <label>Product Name :</label><input
							type="text" class="form-control" name="prodname">
					</fieldset>
					<fieldset class="form-group">
						<label>Quantity : </label><input type="text" class="form-control"
							name="prodquantity">
					</fieldset>
					<input type="submit" value="Add Product">
				</form>
			</div>
		</div>
	</div>

</body>
</html>