<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="tableStyle.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="formStyle.css"/>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Employees</title>
</head>
<body>

<div class="linkList">
    <a href="<c:url value="/startPage.jsp"/>"><-- Home</a>
    <a href="<c:url value="/logout"/>">logout</a>
</div>

<div class="tableWrapper">
    <h2>List Employee</h2>
<display:table name="employeeList" id="employee">
    <display:setProperty name="basic.msg.empty_list" value="Employees not found" />
    <display:column property="firstname" title="First Name"/>
    <display:column property="lastname" title="Last Name"/>
    <display:column property="dateOfBirth" format="{0,date,dd-MM-yyyy}" title="Date of birth"/>
    <display:column property="absenteeism" title="Absenteeism"/>
    <display:column property="department.name" title="Department name"/>
    <display:column title="Action">

        <a href="<c:url value="/deleteEmployee?employeeId=${employee.employeeId}&departmentId=${employee.department.departmentId}"/>">Delete</a>
        <a href="<c:url value="/updateEmployee?employeeId=${employee.employeeId}&departmentId=${employee.department.departmentId}"/>">Update</a>

    </display:column>
</display:table>
    <div class="sub"><a href="<c:url value="/addEmployee"/>"><input type="submit" value="Add Employee"></a></div>
    </div>
</body>
</html>