<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ru">
<head>
    <title>ЛК-manager</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>

<body>

<%@include file="navbar.jsp" %>

<a href="/ecare/manager/person-add">Добавить пользователя</a>

<div class="container">
    <div class="card-deck mb-3 text-center">

        <c:if test="${!empty personsList}">
            <table class="table">
                <tr>
                    <th width="80">ID</th>
                    <th width="120">FirstName</th>
                    <th width="120">LastName</th>
                    <th width="180">Date of birthday</th>
                    <th width="120">Passport</th>
                    <th width="120">Address</th>
                    <th width="120">Email</th>
                    <th width="60"></th>
                </tr>
                <c:forEach items="${personsList}" var="person">
                    <tr>
                        <td>${person.id}</td>
                        <td>${person.firstname}</td>
                        <td>${person.lastname}</td>
                        <td>${person.dob}</td>
                        <td>${person.passport}</td>
                        <td>${person.address}</td>
                        <td>${person.email}</td>
                        <td><a href="<c:url value="/ecare/manager/person-${person.id}-more"/>">More</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </div>


</div>

</body>
</html>
