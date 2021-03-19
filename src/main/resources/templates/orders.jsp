<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<table border="1">
    <th>Order id</th>
    <th>User id</th>
    <th>Order status</th>
    <th>Created at</th>
    <th>Order items</th>
    <c:forEach items="${orders}" var="o">
        <tr>
            <td><c:out value="${o.id}"/></td>
            <td><c:out value="${o.userId}"/></td>
            <td><c:out value="${o.status}"/></td>
            <td><c:out value="${o.createdAt}"/></td>
            <td><c:out value="${o.orderItems}"/></td>
        </tr>
    </c:forEach>
</table>
<br/><a href="/orders?additionalInfo=true">Show orders additional info</a>
<br/><a href="/">Home page</a>
</body>
</html>
