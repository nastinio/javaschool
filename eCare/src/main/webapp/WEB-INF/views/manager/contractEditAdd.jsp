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

        <h5>Тарифы</h5>
        <table class="table">
            <tr>
                <th width="100">ID</th>
                <th width="180">Название</th>
                <th width="260">Выбрать тариф</th>
                <th width="60"></th>
            </tr>
            <c:forEach items="${tariffs}" var="tariff">
                <tr>
                    <td>${tariff.id}</td>
                    <td>${tariff.name}</td>
                    <td>
                        <c:if test="${!empty contract.id}">
                            <c:if test="${contract.tariffInContract.id eq tariff.id}">
                                <div class="form-check form-check-inline">
                                    <spring:radiobutton path="idTariff" class="form-check-input"
                                                        name="inlineRadioOptions" checked="checked"
                                                        id="inlineRadio1" value="${tariff.id}"></spring:radiobutton>
                                </div>
                            </c:if>
                            <c:if test="${!(contract.tariffInContract.id eq tariff.id)}">
                                <div class="form-check form-check-inline">
                                    <spring:radiobutton path="idTariff" class="form-check-input"
                                                        name="inlineRadioOptions"
                                                        id="inlineRadio1" value="${tariff.id}"></spring:radiobutton>
                                </div>
                            </c:if>
                        </c:if>
                        <c:if test="${empty contract.id}">
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="idTariff" class="form-check-input"
                                                    name="inlineRadioOptions" checked="checked"
                                                    id="inlineRadio1" value="${tariff.id}"></spring:radiobutton>
                            </div>
                        </c:if>
                    </td>

                    <td><a href="<c:url value="/ecare/manager/tariff-${tariff.id}-more"/>">More</a></td>
                </tr>
            </c:forEach>
        </table>


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
