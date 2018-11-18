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

<div class="container">
    <spring:form method="post" action="/ecare/manager/person-update" modelAttribute="person">

        <c:if test="${!empty person.id}">
            <div class="form-group row">
                <label for="inputId" class="col-sm-2 col-form-label">ID</label>
                <div class="col-sm-10">
                    <spring:input path="id" type="text" class="form-control" id="inputId"
                                  readonly="true" disabled="true" placeholder="${person.id}"/>
                    <spring:hidden path="id"></spring:hidden>
                </div>
            </div>
        </c:if>


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
                <c:if test="${!empty person.id}">
                    <spring:button type="submit" class="btn btn-primary">Редактировать</spring:button>
                </c:if>
                <c:if test="${empty person.id}">
                    <spring:button type="submit" class="btn btn-primary">Добавить</spring:button>
                </c:if>
            </div>
        </div>
    </spring:form>
</div>

</body>
</html>
