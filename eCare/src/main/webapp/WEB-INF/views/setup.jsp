<%--<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Form</title>
</head>
<body>
<h1>Registration Form</h1>
<spring:form method="post" action="check-person" modelAttribute="person">

    Login: <spring:input path="id"/>   <br/>
    Password: <spring:input path="password"/>   <br/>

    <spring:button>Check</spring:button>

</spring:form>
<%--<c:url value="/setup"></c:url>
<form action="setup" method="post">
    <table style="with: 50%">
        <tr>
            <td>login</td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>password</td>
            <td><input type="text" name="password"/></td>
        </tr>
    </table>
    <input type="submit" value="Submit"/></form>--%>
</body>
</html>