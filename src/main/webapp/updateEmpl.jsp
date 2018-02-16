<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="formStyle.css"/>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">

    <script language="javaScript"
            type="text/javascript" src="<c:url value="datePicker/datepicker.js"/>"></script>
    <link href="<c:url value="style_datepicker.css"/>" rel="stylesheet" type="text/css">

    <script language="javaScript"
            type="text/javascript">
        function validateEployee(){
            var firstname = document.getElementById('firstname').value;
            var lastname = document.getElementById('lastname').value;
            var dateOfBirth = document.getElementById('dateOfBirth').value;
            var absenteeism = document.getElementById('absenteeism').value;

            if(firstname.length < 3){
                alert("First name must be more then 2 symbols!");
                return false;
            }
            if(firstname.length > 15){
                alert("First name must be less then 15 symbols!");
                return false;
            }
            if(lastname.length < 3){
                alert("Last name must be more then 2 symbols!");
                return false;
            }
            if(lastname.length > 15){
                alert("Last name must be less then 15 symbols!");
                return false;
            }
            regex = /^\d{1,2}\-\d{1,2}\-\d{4}$/;

            if(! dateOfBirth.match(regex)) {
                alert("Invalid date format: " + dateOfBirth);
                return false;
            }
            if(absenteeism == "" || !(0 <= absenteeism && absenteeism < 100)){
                alert("Please provide correct number (from 0 to 100)");
                return false;
            }
            return true;
        }

    </script>

    <title></title>
</head>
<body>
<div class="link">
    <a href="<c:url value="/listEmployee"/>"><-- Back</a>
    <a href="<c:url value="/logout"/>">logout</a>
</div>

<div class="outerWrapper">
    <h2>Update Employee</h2>
    <div class="innerWrapperEmpl">

        <form class="formEmpl"  method="POST" action="updateEmployee" onsubmit="return validateEployee()">
            <c:set value="${employee}" var="employee"/>

            <input type="hidden" name="employeeId" value="${employee.employeeId}"/>

            <input type="hidden" name="command" value="${command}"/>

            <p>  First Name : <span>${errors['firstname']}</span></p>
            <p><input type="text" id="firstname" name="firstname" value="${employee.firstname}" class="inputSize"/></p>

            <p>Last Name: <span>${errors['lastname']}</span></p>
            <p><input type="text" id="lastname" name="lastname" value="${employee.lastname}" class="inputSize"/></p>

            <p>Date of birth: <span>${errors['dateOfBirth']}</span> </p>
            <p><input type="text" id="dateOfBirth" name="dateOfBirth"  value="${employee.dateAsString}"/>
                <a href="#" onClick="setYears(1920, 2009);
                showCalender(this, 'dateOfBirth');">
                    <img src="<c:url value="datePicker/datepickerPicture.png"/>"></a></p>

            <p>Absenteeism: <span>${errors['absenteeism']}</span></p>
            <p><input type="number" id="absenteeism" name="absenteeism" value="${employee.absenteeism}"/></p>

            <p>Department: <select id="departmentId" name="departmentId">
                <c:forEach items="${departmentList}" var="allDepartment">
                    <option value="${allDepartment.departmentId}"
                        ${allDepartment.departmentId == employee.department.departmentId ? 'selected="selected"' : ''}>${allDepartment.name}</option>
                </c:forEach>
            </select><span>${errors['departmentId']}</span></p>

            <div class="submit"><p><input type="submit"
                                          value="Update"/></p></div>

        </form>
    </div>
</div>

<!-- Date Picker Table  -->

<table id="calenderTable">
    <tbody id="calenderTableHead">
    <tr>
        <td colspan="4" align="center">
            <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));" id="selectMonth">
                <option value="0">January</option>
                <option value="1">February</option>
                <option value="2">March</option>
                <option value="3">April</option>
                <option value="4">May</option>
                <option value="5">June</option>
                <option value="6">July</option>
                <option value="7">August</option>
                <option value="8">September</option>
                <option value="9">October</option>
                <option value="10">November</option>
                <option value="11">December</option>
            </select>
        </td>
        <td colspan="2" align="center">
            <select onChange="showCalenderBody(createCalender(this.value,
				document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
            </select>
        </td>
        <td align="center">
            <a href="#" onClick="closeCalender();"><font color="#003333" size="+1">X</font></a>
        </td>
    </tr>
    </tbody>
    <tbody id="calenderTableDays">
    <tr style="">
        <td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>
    </tr>
    </tbody>
    <tbody id="calender"></tbody>
</table>
</body>
</html>