<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>ЛК</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <style>
        .container {
            width: 600px;
        }
    </style>
</head>

<body>

<c:if test="${!empty person.id}">
    Добро пожаловать, ${person.firstname} ${person.lastname}! <br>
</c:if>


<div class="container">
    Информация о пользователе:<br><br>

    <spring:form method="post" action="/ecare/${person.id}/edit" modelAttribute="person">
        <div class="form-group row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Пароль</label>
            <div class="col-sm-10">
                <spring:input path="password" type="password" class="form-control" id="inputPassword"
                              placeholder="${person.password}"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputFirstname" class="col-sm-2 col-form-label">Имя</label>
            <div class="col-sm-10">
                <spring:input path="firstname" type="text" class="form-control" id="inputFirstname"
                              placeholder="${person.firstname}"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputLastname" class="col-sm-2 col-form-label">Фамилия</label>
            <div class="col-sm-10">
                <spring:input path="lastname" type="text" class="form-control" id="inputLastname"
                              placeholder="${person.lastname}"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassport" class="col-sm-2 col-form-label">Пасспорт</label>
            <div class="col-sm-10">
                <spring:input path="passport" type="text" class="form-control" id="inputPassport"
                              placeholder="${person.passport}"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputAddress" class="col-sm-2 col-form-label">Адрес</label>
            <div class="col-sm-10">
                <spring:input path="address" type="text" class="form-control" id="inputAddress"
                              placeholder="${person.address}"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <spring:input path="email" type="email" class="form-control" id="inputEmail"
                              placeholder="${person.email}"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputDob" class="col-sm-2 col-form-label">Дата рождения</label>
            <div class="col-sm-10">
                <spring:input path="dob" type="text" class="form-control" id="inputDob" placeholder="${person.dob}"/>
            </div>
        </div>

        <div class="form-group row">
            <div class="offset-sm-2 col-sm-10">
                <spring:button type="submit" class="btn btn-primary">Редактировать</spring:button>
            </div>
        </div>
    </spring:form>
</div>


Информация о контрактах:<br>
<%--<c:forEach items="${tariffList}" var="tariff">
    <tr>
        <td>${tariff.id}</td>
        <td>${tariff.name}</td>

    </tr>
</c:forEach>--%>
<c:forEach items="${contractSet}" var="contract">

    <br><br>
    Номер телефона:   ${contract.number}<br>
    Статус контракта:

    <c:choose>
        <c:when test = "${contract.isBlockedByManager eq '1'}">
            Заблокирован менеджером
        </c:when>

        <c:when test = "${contract.isBlockedByPerson eq '1'}">
            Заблокирован пользователем
            <a href="<c:url value='/ecare/${person.id}/unlock/${contract.id}'/>" class="c">Разблокировать</a>
            <br>
        </c:when>

        <c:otherwise>
            Активен
            <a href="<c:url value='/ecare/${person.id}/block/${contract.id}'/>" class="c">Заблокировать</a>
            <br>
        </c:otherwise>
    </c:choose>

    <br>
    Подключенный тариф: ${contract.tariff.name} <br>


</c:forEach>

</body>
</html>