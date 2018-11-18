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
    <spring:form method="post" action="/ecare/manager/tariff-update" modelAttribute="tariff">

        <c:if test="${!empty tariff.id}">
            <div class="form-group row">
                <label for="inputId" class="col-sm-2 col-form-label">ID</label>
                <div class="col-sm-10">
                    <spring:input path="id" type="text" class="form-control" id="inputId"
                                  readonly="true" disabled="true" placeholder="${tariff.id}"/>
                    <spring:hidden path="id"></spring:hidden>
                </div>
            </div>
        </c:if>


        <div class="form-group row">
            <label for="inputName" class="col-sm-2 col-form-label">Наименование</label>
            <div class="col-sm-10">
                <spring:input path="name" type="text" class="form-control" id="inputName"
                              placeholder="${tariff.name}"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputCost" class="col-sm-2 col-form-label">Стоимость</label>
            <div class="col-sm-10">
                <spring:input path="cost" type="text" class="form-control" id="inputCost"
                              placeholder="${tariff.cost}"/>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputDescription" class="col-sm-2 col-form-label">Описание</label>
            <div class="col-sm-10">
                <spring:textarea path="description" type="text" class="form-control" id="inputDescription " rows="5"
                                 placeholder="${tariff.description}"/>
            </div>
        </div>

        <div class="form-group row">
            <div class="offset-sm-2 col-sm-10">
                <c:if test="${!empty tariff.id}">
                    <spring:button type="submit" class="btn btn-primary">Редактировать</spring:button>
                </c:if>
                <c:if test="${empty tariff.id}">
                    <spring:button type="submit" class="btn btn-primary">Добавить</spring:button>
                </c:if>
            </div>
        </div>
    </spring:form>
</div>

</body>
</html>
