<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="formStyle.css"/>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Update Department</title>
    <script language="javaScript"
            type="text/javascript">
        function validateDepartment(){
            var departmentName = document.getElementById("departmentName").value;
            if(departmentName.length < 3){
                alert("Name must be more then 2 symbols");
                return false;
            }
            if(departmentName.length > 100){
                alert("Name must be less then 100 symbols");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="link">
    <a href="<c:url value="/listDepartment"/>"><-- Back</a>
    <a href="<c:url value="/logout"/>">logout</a>
</div>
<div class="outerWrapper">
    <h2>Update Department</h2>
    <div class="innerWrapperDept">
        <form class="formDept" method="post" action="updateDepartment" onsubmit="return validateDepartment()">
            <c:set value="${department}" var="dept"/>
            <input type="hidden" name="departmentId" value="${dept.departmentId}">

            <p>Enter department name: <span>${errors['nameLength']}</span> <span>${errors['NotUniqueName']}</span></p>
            <p><input type="text" id="departmentName" name="departmentName" value="${dept.name}" class="inputSize"/></p>

            <div class="submitDept"><p><input type="submit"
                                       value="Update" /></p></div>

        </form>
    </div>
</div>
</body>
</html>