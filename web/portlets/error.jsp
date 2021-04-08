<%-- 
    Document   : error
    Created on : Jan 5, 2021, 8:48:18 AM
    Author     : KHAM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    
        <h1>Error Page</h1>
        <font color="red">
        ${requestScope.ERROR}
        </font>
        <br/>
        <a href ="MainController?action=BackHome">Back to login page</a>
</html>
