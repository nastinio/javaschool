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

</body>
</html>

