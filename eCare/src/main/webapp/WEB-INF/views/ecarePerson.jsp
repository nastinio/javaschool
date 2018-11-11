<%@ page contentType="text/html;charset=utf-8" language="java" %>
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
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/ecare/${person.id}/info">Информация о профиле</a></li>
                <li><a href="/ecare/${person.id}/contracts">Контракты</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">${person.firstname} ${person.lastname}</a></li>
                <li><a href="/ecare/${person.id}/logout">Выйти</a></li>
                <%--<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                    </ul>
                </li>--%>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>




<div class="container">
    <c:if test="${!empty person.password}">
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
    </c:if>
</div>

<c:if test="${empty person.password}">
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
        <c:when test="${contract.isBlockedByManager eq '1'}">
            Заблокирован менеджером
        </c:when>

        <c:when test="${contract.isBlockedByPerson eq '1'}">
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

    <a href="<c:url value='/ecare/${person.id}/contract/${contract.id}/more'/>" class="c">Подробнее</a>

    <br>
    Подключенный тариф: ${contract.tariff.name} <br>
    Опции тарифа:<br>

    <table class="table table-sm">
        <thead>
        <tr>
            <th>Название</th>
            <th>Стоимость подключения</th>
            <th>Стоимость</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contract.tariff.optionSet}" var="optionTariff">
            <tr>
                <td>${optionTariff.name}</td>
                <td>${optionTariff.connectionCostOption}</td>
                <td>${optionTariff.costOption}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    Дополнительные опции:<br>

    <table class="table table-sm">
        <thead>
        <tr>
            <th>Название</th>
            <th>Стоимость подключения</th>
            <th>Стоимость</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contract.optionExtraSet}" var="optionExtra">
            <tr>
                <td>${optionExtra.name}</td>
                <td>${optionExtra.connectionCostOption}</td>
                <td>${optionExtra.costOption}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</c:forEach>
</c:if>
<%--<c:forEach items="${optionSet}" var="optionTariff">
    ${optionTariff.name}<br>
</c:forEach>--%>

</body>
</html>