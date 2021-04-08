<%-- 
    Document   : view
    Created on : Jan 17, 2021, 6:42:00 PM
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
        <c:set var="sum" value="0" scope="request"/>

        <div class="container">
            <div class="row">
                <div class="text-center"><h1>${sessionScope.fullname}'s Shopping Cart</h1></div>
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                    <form action="MainController" method="POST" id="myForm">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Quantity</th>
                                    <th class="text-center">Price</th>
                                    <th class="text-center">Total</th>
                                    <th>  </th>
                                    <th>  </th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.cart.cart.values()}" var="myCart" varStatus="counter">
                                <form action="MainController" method="POST">
                                    <tr>
                                        <td class="col-sm-8 col-md-6">
                                            <div class="media">
                                                <a class="thumbnail pull-left" href="#"> <img class="media-object" src="${myCart.image}" style="width: 72px; height: 72px;"> </a>
                                                <div class="media-body">
                                                    <h4 class="media-heading"><a href="#">${myCart.productName}</a></h4>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="col-sm-1 col-md-1" style="text-align: center">
                                            <input type="text" value="${myCart.quantity}" name="txtQuantity">

                                            <input type="hidden" name="txtProductID" value="${myCart.productID}"/>
                                        </td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>${myCart.price}đ</strong></td>
                                        <td class="col-sm-1 col-md-1 text-center"><strong>${myCart.price * myCart.quantity}đ</strong></td>
                                        <c:set var="sum" value="${sum + myCart.price * myCart.quantity}"/>
                                        <td class="col-sm-1 col-md-1">
                                            <button type="submit" class="btn btn-danger" name="action" value="DeleteCart" onclick="return confirm('Are you sure you want to delete?')">
                                                <span class="glyphicon glyphicon-remove"></span> Remove
                                            </button>
                                        </td>
                                        <td class="col-sm-1 col-md-1">
                                            <button type="submit" class="btn btn-dark" name="action" value="UpdateCart">
                                                Update
                                            </button>
                                        </td>
                                    </tr>
                                </form>
                            </c:forEach>
                            <tr>
                                <td> <font color="red">
                                    ${requestScope.INVALIDQUANTITY}
                                    </font>  </td>
                                <td>   </td>
                                <td>   </td>
                                <td><h3>Total</h3></td>
                                <td class="text-right"><h3><strong><c:out value="${sum}đ"/></strong></h3></td>

                            </tr>
                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td> ${requestScope.success}  </td>
                                <td>
                                    <button type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-shopping-cart"></span> <a href="MainController?action=ConfirmCart&sum=${sum}">Confirm</a>
                                    </button>
                                </td>
                            </tr>
                            <tr>
                                <td>   </td>
                                <td>   </td>
                                <td>   </td>
                                <td>
                                    <button type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-shopping-cart"></span> <a href="MainController?action=ContinueShopping">Continue Shopping</a>
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <%--<c:set scope="session" var="totalPrice" value="${sum}"/>--%>
    </body>
</html>
