<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Test JSP</title>
</head>
<body>
    <h1>Test Page</h1>
    <%-- Hardcoded product for testing --%>
    <% 
        com.assignment.product testProduct = new com.assignment.product("Test Product", "99");
        request.setAttribute("product", testProduct);
    %>
    <p>Product Name: ${product.prodname}</p>
    <p>Product Quantity: ${product.prodquantity}</p>
</body>
</html>