<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Order</title>
</head>
<body>
<form name="createOrder" method="post" action="/orders">
    <table border="1">
        <th>Product name</th>
        <th>Quantity</th>
        <c:forEach items="${productAndQuantity}" var="p">
            <tr>
                <td>
                    <c:out value="${p.key.name}"/>
                </td>
                <td>
                    <c:out value="${p.key.price}"/>
                </td>
                <td>
                    <input type="number" name="${p.key.id}" value="${p.value}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="Create order">
</form>
<br/><a href="/">Home Page</a>
</body>
</html>
