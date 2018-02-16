<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="formStyle.css"/>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <script language="javaScript"
            type="text/javascript">
        function validateRegister(){

            var email = document.getElementById("email").value;
            if (!email.match(/^[a-zA-Z0-9_\-\.]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$/)) {
                alert("Incorrect E-mail!");
                return false;
            }
            var password1 = document.getElementById("password1").value;
            var password2 = document.getElementById("password2").value;
            if(password1.length<6){
                alert("Password must be more then 5 symbols!");
                return false;
            }
            if(password1.length > 60){
                alert("Password must be less then 60 symbols!");
                return false;
            }
            if(password1 != password2){
                alert("Passwords are not equals!");
                return false;
            }
            return true;

        }
            </script>
    <title></title>

</head>
<body>

<div class="link">
    <a href="<c:url value="login.jsp"/>"><-- Login</a>
</div>

<div class="outerWrapper">
    <h1>Register</h1>
    <div class="login">
        <form class="formLogin" method="post" action="registration" onsubmit="return validateRegister()">

            <table>

                <tr>
                    <td></td>
                    <td><span>${error}</span></td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td><input type="email" id="email" name="email" value="${email}" class="inputSize"></td>

                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" id="password1" name="password1" class="inputSize"></td>

                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" id="password2" name="password2" class="inputSize"></td>
                </tr>
            </table>
            <div class="submit"><input type="submit"
                                       value="Register" /></div>
        </form>
    </div>
</div>
</body>
</html>