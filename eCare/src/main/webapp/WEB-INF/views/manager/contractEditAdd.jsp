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

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/ecare/manager/all-options">Опции</a>
        <a class="p-2 text-dark" href="/ecare/manager/all-tariffs">Тарифы</a>
        <a class="p-2 text-dark" href="/ecare/manager/all-persons">Клиенты</a>
        <a class="p-2 text-dark" href="/ecare/manager/all-contracts">Контракты</a>
    </nav>
    <h5 class="my-0 mr-md-auto font-weight-normal"></h5>
    <a class="p-2 text-dark" href="#">Manager</a>
    <a class="btn btn-outline-primary" href="#">Выйти</a>
</div>

<div class="container">
    <spring:form method="post" action="/ecare/manager/person-${idPerson}/contract-update" modelAttribute="contract">

        ${idPerson}

        <c:if test="${!empty contract.id}">
            <div class="form-group row">
                <label for="inputId" class="col-sm-2 col-form-label">ID</label>
                <div class="col-sm-10">
                    <spring:input path="id" type="text" class="form-control" id="inputId"
                                  readonly="true" disabled="true" placeholder="${contract.id}"/>
                    <spring:hidden path="id"></spring:hidden>
                </div>
            </div>
        </c:if>


        <div class="form-group row">
            <label for="inputNumber" class="col-sm-2 col-form-label">Номер</label>
            <div class="col-sm-10">
                <spring:input path="number" type="text" class="form-control" id="inputNumber"
                              placeholder="${contract.number}"/>
            </div>
        </div>

        <div class="form-group row">
            <div class="offset-sm-2 col-sm-10">
                <c:if test="${!empty contract.id}">
                    <spring:button type="submit" class="btn btn-primary">Редактировать</spring:button>
                </c:if>
                <c:if test="${empty contract.id}">
                    <spring:button type="submit" class="btn btn-primary">Добавить</spring:button>
                </c:if>
            </div>
        </div>



    </spring:form>
</div>

</body>
</html>
