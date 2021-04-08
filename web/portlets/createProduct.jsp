<%-- 
    Document   : createProduct
    Created on : Jan 20, 2021, 9:20:25 AM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            ProductID: <input type="text" name="txtProductID" value="${param.txtProductID}" />
            <font color="red">
            ${requestScope.INVALID.errorProductID}
            </font><br/>
            Product Name: <input type="text" name="txtProductName" value="${param.txtProductName}"/>
            <font color="red">
            ${requestScope.INVALID.errorProductName}
            </font><br/>
            Price: <input type="text" name="txtPrice" value="${param.txtPrice}"/>
            <font color="red">
            ${requestScope.INVALID.errorPrice}
            </font><br/>
            Description: <input type="text" name="txtDescription" value="${param.txtDescription}"/><br/>
            Category: 
            <select name="txtCategory">
                <c:forEach items="${sessionScope.listCategory}" var="dto" varStatus="counter">
                    <option value="${dto.category}">${dto.category}</option>
                </c:forEach>
            </select>
            <br/>
            Quantity: <input type="text" name="txtQuantity" value="${param.txtQuantity}"/>
            <font color="red">
            ${requestScope.INVALID.errorQuantity}
            </font><br/>
            Choose image: <input type="file" name="txtImage"/>
            <font color="red">
            ${requestScope.INVALID.errorImage}
            </font><br/>
            <input type="submit" name="action" value="CreateProduct"/><br/>

            <font color="green">
            ${requestScope.INSERTSUCCESS}
            </font>
        </form>
    </body>
</html>
