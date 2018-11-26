<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="ru">
<head>
    <title>ЛК-client</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>

<body>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/ecare/person-${person.id}/contract-all">Контракты</a>
        <a class="p-2 text-dark" href="/ecare/person-${person.id}/contract-${contract.id}/tariff-all">Тарифы</a>
        <a class="p-2 text-dark" href="/ecare/person-${person.id}/contract-${contract.id}/option-all">Опции</a>
    </nav>
    <h5 class="my-0 mr-md-auto font-weight-normal"></h5>
    <a class="p-2 text-dark" href="#">${contract.number}</a>
    <a class="p-2 text-dark" href="#">${person.firstname} ${person.lastname}</a>
    <a class="btn btn-outline-primary" href="#">Выйти</a>
</div>

<div class="container">
    <p>
    <h1>Корзина для <a href="/ecare/person-${person.id}/contract-${contract.id}-more">${contract.number}</a></h1>

    </p>

    <%--<spring:form method="post" action="/ecare/person-${person.id}/info-update" modelAttribute="basket">

        <c:if test="${!empty basket.id}">
            <div class="form-group row">
                <label for="inputId" class="col-sm-2 col-form-label">ID</label>
                <div class="col-sm-10">
                    <spring:input path="id" type="text" class="form-control" id="inputId"
                                  readonly="true" disabled="true" placeholder="${basket.id}"/>
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
            <div class="offset-sm-2 col-sm-10">
                <c:if test="${!empty basket.id}">
                    <spring:button type="submit" class="btn btn-primary">Редактировать</spring:button>
                </c:if>
            </div>
        </div>
    </spring:form>--%>

        <c:if test="${contract.tariffInContract eq contract.tariffInContractForChange}">
            <h5>Новый тариф не был выбран</h5>
        </c:if>
        <c:if test="${!(contract.tariffInContract eq contract.tariffInContractForChange)}">
            <h5>Выбран новый тариф: <a href="/ecare/person-${person.id}/contract-${contract.id}/tariff-${contract.tariffInContractForChange.id}-more">${contract.tariffInContractForChange.name}</a></h5>
            <p>${basket.tariffInBasket.description}</p>

            <c:if test="${!empty contract.tariffInContractForChange.optionsOnTariff}">
                <table class="table">
                    <tr>
                        <th width="80">ID</th>
                        <th width="120">Name</th>
                        <th width="120">Стоимость</th>
                        <th width="280">Стоимость подключения</th>
                        <th width="60"></th>
                    </tr>
                    <c:forEach items="${contract.tariffInContractForChange.optionsOnTariff}" var="option">
                        <tr>
                            <td>${option.id}</td>
                            <td>${option.name}</td>
                            <td>${option.cost}</td>
                            <td>${option.costConnection}</td>
                            <td><a href="<c:url value="/ecare/person-${person.id}/contract-${contract.id}/option-${option.id}-more"/>">More</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

            <a href="/ecare/person-${person.id}/contract-${contract.id}/update">Редактировать</a>
            <a href="/ecare/person-${person.id}/contract-${contract.id}/basket-reset">Отменить изменения</a>


        </c:if>




</div>

</body>
</html>
