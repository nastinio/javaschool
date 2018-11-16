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
    <a class="p-2 text-dark" href="/ecare/person-${person.id}/contract-${contract.id}/basket">Корзина</a>
    <a class="btn btn-outline-primary" href="#">Выйти</a>
</div>

<div class="container">
    <p>
    <h1>${contract.number}</h1>

    <c:choose>
        <c:when test="${contract.isBlockedByManager eq '1'}">
            Заблокирован менеджером
        </c:when>

        <c:when test="${contract.isBlockedByPerson eq '1'}">
            <p>Контракт заблокирован пользователем <a href="/ecare/person-${person.id}/contract-${contract.id}-unlock">Unlock</a></p>
        </c:when>

        <c:otherwise>
            <p>Контракт активен <a href="/ecare/person-${person.id}/contract-${contract.id}-block">Block</a></p>
        </c:otherwise>
    </c:choose>
    </p>

    <h5>Тариф: <a href="/ecare/person-${person.id}/contract-${contract.id}/tariff-${contract.tariffInContract.id}-more">${contract.tariffInContract.name}</a></h5>
    <p>${contract.tariffInContract.description}</p>

    <%--Ссылка активна, если контракт незаблокирован--%>
    <c:if test="${contract.isBlockedByManager eq '0' && contract.isBlockedByPerson eq '0'}">
        <a href="/ecare/person-${person.id}/contract-${contract.id}/tariff-all">Сменить тариф</a></h5>
    </c:if>

    <c:if test="${!empty contract.tariffInContract.optionsOnTariff}">
        <table class="table">
            <tr>
                <th width="80">ID</th>
                <th width="120">Name</th>
                <th width="120">Стоимость</th>
                <th width="280">Стоимость подключения</th>
                <th width="60"></th>
            </tr>
            <c:forEach items="${contract.tariffInContract.optionsOnTariff}" var="option">
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

    <h5>Дополнительные опции</h5>
    <%--Ссылка активна, если контракт незаблокирован--%>
    <c:if test="${contract.isBlockedByManager eq '0' && contract.isBlockedByPerson eq '0'}">
        <a href="/ecare/person-${person.id}/contract-${contract.id}/option-all">Выбрать дополнительные опции</a></h5>
    </c:if>


    <div class="container">
        <div class="card-deck mb-3 text-center">
            <c:forEach items="${contract.optionsOnContract}" var="option">
                <div class="row">
                    <div class="col">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header">
                                <h4 class="my-0 font-weight-normal">${option.name}</h4>
                            </div>
                            <div class="card-body">
                                <h1 class="card-title pricing-card-title">$${option.cost}
                                    <small class="text-muted">/ mo</small>
                                </h1>
                                <ul class="list-unstyled mt-3 mb-4">
                                    <li>$${option.costConnection} стоимость подключения</li>
                                </ul>
                                <a href="/ecare/person-${person.id}/contract-${contract.id}/option-${option.id}-more" class="btn btn-lg btn-block btn-outline-primary" role="button"
                                   aria-disabled="true">More</a>
                                <a href="/ecare/manager/person-${person.id}/contract-${contract.id}/extraoption-${option.id}-disable" class="btn btn-lg btn-block btn-outline-primary" role="button"
                                   aria-disabled="true">Отключить</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


</div>

</body>
</html>
