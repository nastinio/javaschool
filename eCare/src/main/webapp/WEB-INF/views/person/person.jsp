<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Person Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<h1>
    Add a Person
</h1>

<c:url var="addAction" value="/person/add" ></c:url>

<form:form action="${addAction}" commandName="person">
    <table>
        <c:if test="${!empty person.firstname}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="id" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="password">
                    <spring:message text="Password"/>
                </form:label>
            </td>
            <td>
                <form:input path="password" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="firstname">
                    <spring:message text="Firstname"/>
                </form:label>
            </td>
            <td>
                <form:input path="firstname" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastname">
                    <spring:message text="Lastname"/>
                </form:label>
            </td>
            <td>
                <form:input path="lastname" />
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="dob">
                    <spring:message text="DOB"/>
                </form:label>
            </td>
            <td>
                <form:input path="dob" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="passport">
                    <spring:message text="Passport"/>
                </form:label>
            </td>
            <td>
                <form:input path="passport" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="address">
                    <spring:message text="Address"/>
                </form:label>
            </td>
            <td>
                <form:input path="address" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">
                    <spring:message text="Email"/>
                </form:label>
            </td>
            <td>
                <form:input path="email" />
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${!empty person.firstname}">
                    <input type="submit"
                           value="<spring:message text="Edit Person"/>" />
                </c:if>
                <c:if test="${empty person.firstname}">
                    <input type="submit"
                           value="<spring:message text="Add Person"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<br>
<h3>Persons List</h3>
<c:if test="${!empty listPersons}">
    <table class="tg">
        <tr>
            <th width="80">Person ID</th>
            <th width="120">Person password</th>
            <th width="120">Person FirstName</th>
            <th width="120">Person LastName</th>
            <th width="120">Person date of birthday</th>
            <th width="120">Person passport</th>
            <th width="120">Person Address</th>
            <th width="120">Person email</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
            <th width="60">More</th>
        </tr>
        <c:forEach items="${listPersons}" var="person">
            <tr>
                <td>${person.id}</td>
                <td>${person.password}</td>
                <td>${person.firstname}</td>
                <td>${person.lastname}</td>
                <td>${person.dob}</td>
                <td>${person.passport}</td>
                <td>${person.address}</td>
                <td>${person.email}</td>
                <td><a href="<c:url value='/person/edit/${person.id}' />" >Edit</a></td>
                <td><a href="<c:url value='/person/remove/${person.id}' />" >Delete</a></td>
                <td><a href="<c:url value='/person/${person.id}' />" >More</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
