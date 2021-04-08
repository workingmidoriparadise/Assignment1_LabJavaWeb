<%-- 
    Document   : login
    Created on : Jan 14, 2021, 9:01:12 AM
    Author     : KHAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="css/login_css.css">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <meta name="google-signin-client_id" content="870975079006-eil60m44hq9jgll9sat3sptcsl49gmmt.apps.googleusercontent.com">
    </head>
    <body>
        <div id="logreg-forms">
            <form class="form-signin" action="MainController" method="POST">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign in</h1>

                <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/Assignment1_DangDuongKham/login-google&response_type=code&client_id=870975079006-eil60m44hq9jgll9sat3sptcsl49gmmt.apps.googleusercontent.com&approval_prompt=force">Login With Google</a> 
                <p style="text-align:center"> OR  </p>
                <input type="UserID" id="inputEmail" name="txtUserID" class="form-control" placeholder="UserID" autofocus="">
                <font color="red">
                ${requestScope.INVALID.userIDError}
                </font>
                <input type="password" id="inputPassword" name="txtPassword" class="form-control" placeholder="Password" >
                <font color="red">
                ${requestScope.INVALID.passwordError}
                </font>
                <font color="red">
                ${requestScope.LOGINERROR}
                </font>
                <button class="btn btn-success btn-block" type="submit" name="action" value="Login"><i class="fas fa-sign-in-alt"></i> Sign in</button>
                <hr>
            </form>
            <br>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="/script.js"></script>
    </body>
</html>
