<%-- 
    Document   : deleteProduct
    Created on : Jan 17, 2021, 9:56:04 AM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <form action="MainController" method="POST">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th class="text-center">Status</th>
                                    <th class="text-center">Category</th>
                                    <th class="text-center">Quantity</th>
                                    <th class="text-center">Price</th>
                                    <th class="text-center">Delete</th>
                                    <th class="text-center">Update</th>
                                    <th> </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.listUpdate}" var="dto" varStatus="counter">

                                    <tr>
                                <input type="hidden" name="txtProductID" value="${dto.productID}"/>
                                <td class="col-sm-8 col-md-6">
                                    <div class="media">
                                        <a class="thumbnail pull-left" href="#"> <img class="media-object" src="${dto.image}" style="width: 72px; height: 72px;"> </a>
                                        <input type="hidden" name="txtUpdateImage" value="${dto.image}"/>
                                        <div class="media-body">
                                            <h4 class="media-heading"><input type="text" name="txtUpdateProductName" value="${dto.productName}"/></h4>
                                        </div>
                                    </div>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center">
                                    <strong>
                                        <select name="status">
                                            <c:forEach begin="0" end="1" var="count" varStatus="counter">
                                                <option value="${count}" <c:if test="${count == dto.status}">selected=true</c:if>>${count}</option>
                                            </c:forEach>
                                        </select>
                                    </strong>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center">
                                    <strong>
                                        <select name="txtUpdateCategory">
                                            <option value=""></option>
                                            <c:forEach items="${sessionScope.listCategory}" var="category" varStatus="counter">
                                                <option value="${dto.category}" <c:if test="${category.category == dto.category}">selected=true</c:if>>${dto.category}</option>
                                            </c:forEach>
                                        </select>
                                    </strong>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center"><strong><input type="text" name="txtUpdateQuantity" value="${dto.quantity}"/></strong></td>
                                <td class="col-sm-1 col-md-1 text-center"><strong><input type="text" name="txtUpdatePrice" value="${dto.price}"/></strong></td>
                                <td class="col-sm-1 col-md-1">
                                    <div class="text-center"><input type="checkbox" name="txtDeleteCB" value="${dto.productID}"/></div>
                                </td>
                                <td class="col-sm-1 col-md-1">
<!--                                    <button type="submit" name="action" value="Update" class="btn btn-default">
                                        Update
                                    </button>-->
                                </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <button type="submit" class="btn btn-danger" name="action" value="Delete" onclick="return confirm('Are you sure you want to delete?')">
                            <span class="glyphicon glyphicon-remove"></span> Delete
                        </button>
                    </form>
                </div>
                <form action="MainController" method="POST">

                    <div class="text-center">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${sessionScope.pageCount}" varStatus="counter">
                                <li class="page-item <c:if test="${counter.count == sessionScope.updatePage}">active</c:if>">
                                    <a class="page-link" href="MainController?action=FirstUpdate&updatePage=${counter.count}">${counter.count}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
