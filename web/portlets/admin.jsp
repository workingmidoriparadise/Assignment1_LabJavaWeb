<%-- 
    Document   : admin
    Created on : Jan 7, 2021, 9:10:04 AM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hana Shop</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="css/shop-homepage.css" rel="stylesheet">
        <script src="js/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link href="css/css.css" rel="stylesheet">

    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="MainController?action=BackHomeAdmin">Hana Shop</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="#">Welcome, ${sessionScope.fullname}</a>
                        </li>
                        <li>
                            <a href="MainController?action=BackHomeAdmin">Home</a>
                        </li>
                        <li>
                            <a href="MainController?action=Logout">Logout</a>
                        </li>
                        <li>
                            <a href="MainController?action=GoCreateProduct">Create Product</a>
                        </li>
                        <li>
                            <a href="MainController?action=FirstUpdate">Update Product</a>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <!-- Page Content -->
        <form action="MainController" method="POST">
            <div class="container">

                <div class="row">

                    <div class="col-md-3">
                        <p class="lead">Search</p>
                        <div class="list-group">
                            <input type="text" name="txtSearchByName" placeholder="Product's name" value="${param.txtSearchByName}"/><br/>
                            <input type="text" name="txtFromPrice" placeholder="Min Price" value="${param.txtFromPrice}"/><br/>
                            <input type="text" name="txtToPrice" placeholder="Max Price" value="${param.txtToPrice}"/><br/>
                            <select name="txtSearchCategory">
                                <option value=""></option>
                                <c:forEach items="${sessionScope.listCategory}" var="dto" varStatus="counter">
                                    <option value="${dto.category}" <c:if test="${param.txtSearchCategory == dto.category}">selected=true</c:if>>${dto.category}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="page" value="1"/>
                            <input type="submit" name="action" value="Search"/>
                            <c:if test="${sessionScope.adminErrorInput}">
                                <script> alertErrorInput()</script>
                            </c:if>
                        </div>
                    </div>

                    <div class="col-md-9">
                        <div class="row">
                            <c:forEach items="${sessionScope.listSearched}" var="list" varStatus="counter">
                                <div class="col-sm-4 col-lg-4 col-md-4">
                                    <div class="thumbnail">
                                        <img src="${list.image}" alt="">
                                        <div class="caption">
                                            <p class="productName">${list.productName}</p>
                                            <h4>${list.price}đ</h4>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="container">
                            <ul class="pagination">
                                <c:forEach begin="1" end="${sessionScope.pageCount}" varStatus="counter">
                                    <li class="page-item <c:if test="${counter.count == sessionScope.page}">active</c:if>">
                                        <a class="page-link" href="MainController?action=Search&page=${counter.count}&txtSearchByName=${param.txtSearchByName}&txtFromPrice=${param.txtFromPrice}&txtToPrice=${param.txtToPrice}&txtSearchCategory=${param.txtSearchCategory}">${counter.count}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
