<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Information</title>
</head>
<body>
<table border="1">
    <th>Product id</th>
    <th>Product name</th>
    <th>Product price</th>
    <th>Product status</th>
    <th>Product date creation</th>
    <th>Product total ordered quantity</th>
    <c:forEach items="${productInfos}" var="p">
        <tr>
            <td><c:out value="${p.product.id}"/></td>
            <td><c:out value="${p.product.name}"/></td>
            <td><c:out value="${p.product.price}"/></td>
            <td><c:out value="${p.product.status}"/></td>
            <td><c:out value="${p.product.createdAt}"/></td>
            <td><c:out value="${p.totalQuantity}"/></td>
        </tr>
    </c:forEach>
</table>
<br/><a href="/products">Show products</a>
<br/><a href="/">Home page</a>
</body>
</html>
