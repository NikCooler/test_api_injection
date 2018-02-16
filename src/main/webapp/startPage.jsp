
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
      <link rel="stylesheet" type="text/css" href="<c:url value="formStyle.css"/>">
      <meta http-equiv="Content-Type" content="text/html; charset=utf8">
      <title>Enter List</title>
  </head>
  <body>
  <div class="wrapperStartPage">
      <h2>Home Page</h2>
  <div class="linkHomePage">
  <a href="<c:url value="/listDepartment"/>">List Department</a>
  <a href="<c:url value="/listEmployee"/>">List Employee</a>
      </div>
      </div>
  </body>
</html>