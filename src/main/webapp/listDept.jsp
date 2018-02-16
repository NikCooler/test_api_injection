<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="tableStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="formStyle.css"/>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>List departments</title>
</head>
<body>
<div class="linkList">
    <a href="<c:url value="/startPage.jsp"/>"><-- Home</a>
    <a href="<c:url value="/logout"/>">logout</a>
</div>
<div class="tableWrapper">
    <h2>List Department</h2>
    <display:table name="departmentList" id="department"  >
        <display:setProperty name="basic.msg.empty_list" value="Departments not found" />
        <display:column property="name" title="Name"/>
        <display:column title="Action">

            <a href="<c:url value="/deleteDepartment?departmentId=${department.departmentId}"/>">Delete</a>
            <a href="<c:url value="/updateDepartment?departmentId=${department.departmentId}"/>">Update</a>
            <a href="<c:url value="/employeesOfDepartment?departmentId=${department.departmentId}"/>">List Employee</a>

        </display:column>
    </display:table>
    <div class="sub"><a href="<c:url value="addDept.jsp"/>"><input type="submit" value="Add department"></a></div>
</div>
</body>
</html>