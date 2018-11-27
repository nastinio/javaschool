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
    <h2>${option.name}</h2>


    <%--/ecare/person-${person.id}/contract-${contract.id}/option-${option.id}/basket-disable--%>


    <p>Статус опции: ${option.status}</p>
    <c:if test="${option.canBeConnectedByPerson}">
        <a href="/ecare/person-${person.id}/contract-${contract.id}/option-${option.id}/basket-add">Добавить в корзину</a>
    </c:if>
    <c:if test="${!option.canBeConnectedByPerson}">
        <p>Невозможно подключить, т.к. не все необходимые опции подключены или подключены несоместимые опции</p>
    </c:if>

    </p>
    <p>${option.description}</p>
    <p><b>Стоимость услуги: </b> ${option.cost}</p>
    <p><b>Стоимость подключения:</b> ${option.costConnection}</p>

    <c:if test="${empty listAllJointlyOptions}">
        <h5>Нет необходимых для подключения опций</h5>
    </c:if>
    <c:if test="${!empty listAllJointlyOptions}">
        <h5>Необходимые для подключения опции</h5>
        <table class="table">
            <tr>
                <th width="80">ID</th>
                <th width="180">Название</th>
                <th width="120">Стоимость</th>
                <th width="280">Стоимость подключения</th>
                <th width="60">Статус подключения</th>
                <th width="60"></th>
            </tr>
            <c:forEach items="${listAllJointlyOptions}" var="option">
                <tr>
                    <td>${option.id}</td>
                    <td>${option.name}</td>
                    <td>${option.cost}</td>
                    <td>${option.costConnection}</td>
                    <td>${option.status}</td>
                    <td><a href="<c:url value="/ecare/person-${person.id}/contract-${contract.id}/option-${option.id}-more"/>">More</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty listAllExcludeOptions}">
        <h5>Нет несовместимых опций</h5>
    </c:if>
    <c:if test="${!empty listAllExcludeOptions}">
        <h5>Несовместимые опции</h5>
        <table class="table">
            <tr>
                <th width="80">ID</th>
                <th width="180">Название</th>
                <th width="120">Стоимость</th>
                <th width="280">Стоимость подключения</th>
                <th width="60">Статус подключения</th>
                <th width="60"></th>
            </tr>
            <c:forEach items="${listAllExcludeOptions}" var="option">
                <tr>
                    <td>${option.id}</td>
                    <td>${option.name}</td>
                    <td>${option.cost}</td>
                    <td>${option.costConnection}</td>
                    <td>${option.status}</td>
                    <td><a href="<c:url value="/ecare/person-${person.id}/contract-${contract.id}/option-${option.id}-more"/>">More</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>


</div>


</body>
</html>
