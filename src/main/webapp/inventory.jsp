<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Inventory Management</title>
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Products</h3>
			<hr>
			<div class="container text-left">
				<a href="<%=request.getContextPath()%>/addproduct.jsp"
					class="btn btn-success">Add New product</a>
			</div>
			<br>

			<table class="table">
				<thead>
					<tr>
						<th>Product Name</th>
						<th>Quantity</th>
						<th>Actions</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="product" items="${listProduct}">
						<tr>
							<td><c:out value="${product.prodname}" /></td>
							<td><c:out value="${product.prodquantity}" /></td>
							<td> <a href="<c:url value='/inventoryservlet/edit'><c:param name='prodname' value='${product.prodname}' /></c:url>">Edit</a>
<a href="<c:url value='/inventoryservlet/delete'><c:param name='prodname' value='${product.prodname}' /></c:url>">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>