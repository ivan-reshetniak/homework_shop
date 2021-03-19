<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<table border="1">
    <th>Product id</th>
    <th>Product name</th>
    <th>Product price</th>
    <th>Product status</th>
    <th>Product date creation</th>
    <c:forEach items="${products}" var="p">
        <tr>
            <td><c:out value="${p.id}"/></td>
            <td><c:out value="${p.name}"/></td>
            <td><c:out value="${p.price}"/></td>
            <td><c:out value="${p.status}"/></td>
            <td><c:out value="${p.createdAt}"/></td>
            <td>
                <form method="post" action="/products">
                    <button>Delete</button>
                    <input type="hidden" name="id" value="${p.id}"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="/products" method="post">
    <button type="submit" name="delete" value="true">Delete all products</button>
</form>
<br/><a href="/products?additionalInfo=true">Additional information</a>
<br/><a href="/">Home page</a>
</body>
</html>
