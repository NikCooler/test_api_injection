<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="formStyle.css"/>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title></title>
    <script language="javaScript"
            type="text/javascript">
        function validateLogin(){
            var email = document.getElementById('email').value;
            if (!email.match(/^[a-zA-Z0-9_\-\.]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/)) {
                alert("Incorrect E-mail!");
                return false;
            }
            var password = document.getElementById('password').value;
            if(password.length<6){
                alert("Password must be more then 5 symbols!");
                return false;
            }
            if(password.length > 60){
                alert("Password must be less then 60 symbols!");
                return false;
            }
            return true;
        }
    </script>

</head>
<body>

<div class="link">
    <a href="<c:url value="/registration.jsp"/>">Registration</a>
</div>

<div class="outerWrapper">
    <h1>Login</h1>
    <div class="login">
        <form name="form" class="formLogin" method="post" action="login" onsubmit="return validateLogin()">

            <table>

                <tr>
                    <td></td>
                    <td><span>${error}</span></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="text" id="email" name="email" value="${email}" class="inputSize"></td>

                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" id="password" name="password" class="inputSize"></td>

                </tr>

            </table>
            <div class="submit">

                <p><input type="submit"
                       value="Login" /></p>

            </div>
        </form>
    </div>

</div>
</body>
</html>