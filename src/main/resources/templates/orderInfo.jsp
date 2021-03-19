<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <th>Order id</th>
    <th>Order total sum</th>
    <th>Product name</th>
    <th>Product quantity</th>
    <th>Order date creation</th>
    <c:forEach items="${orderInfos}" var="o">
        <tr>
            <td><c:out value="${o.orderId}"/></td>
            <td><c:out value="${o.totalSum}"/></td>
            <td><c:out value="${o.productName}"/></td>
            <td><c:out value="${o.quantity}"/></td>
            <td><c:out value="${o.createdAt}"/></td>
        </tr>
    </c:forEach>
</table>
<br/><a href="/orders">Show all orders</a>
<br/><a href="/">Home page</a>
</body>
</html>
