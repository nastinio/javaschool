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
    <p>
    <h2>
        <a href="/ecare/manager/person-${contract.personInContract.id}-more">${contract.personInContract.firstname} ${contract.personInContract.lastname}</a>
    </h2>
    <h1>${contract.number}</h1>
    <a href="/ecare/manager/contract-${contract.id}-more">More</a>
    </p>

    <h5>Тариф: <a
            href="/ecare/manager/tariff-${contract.tariffInContract.id}-more">${contract.tariffInContract.name}</a></h5>
    <p>${contract.tariffInContract.description}</p>

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
                    <td><a href="<c:url value="/ecare/manager/option-${option.id}-more"/>">More</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <br>

    <c:if test="${!empty optionsOnContract}">
        <div class="container">
            <h5>Дополнительные опции</h5>
            <table class="table">
                <tr>
                    <th width="80">ID</th>
                    <th width="120">Name</th>
                    <th width="120">Стоимость</th>
                    <th width="280">Стоимость подключения</th>
                    <th width="380">Необходимые для подключения опции</th>
                    <th width="280">Несовместимые</th>
                    <th width="60"></th>
                    <th width="60"></th>
                </tr>
                <c:forEach items="${optionsOnContract}" var="extraOption">
                    <tr>
                        <td>${extraOption.id}</td>
                        <td>${extraOption.name}</td>
                        <td>${extraOption.cost}</td>
                        <td>${extraOption.costConnection}</td>
                        <td>
                            <c:forEach items="${extraOption.allJointlyOptions}" var="extraOptionsJointlyOption">
                                #${extraOptionsJointlyOption.id} ${extraOptionsJointlyOption.name}<br>
                            </c:forEach>
                                <%--${extraOption.allJointlyOptions}--%>
                        </td>
                        <td>
                            <c:forEach items="${extraOption.allExcludeOptions}" var="extraOptionsExcludeOption">
                                #${extraOptionsExcludeOption.id} ${extraOptionsExcludeOption.name}<br>
                            </c:forEach>
                                <%--${extraOption.allExcludeOptions}--%>
                        </td>
                        <td><a href="<c:url value="/ecare/manager/option-${extraOption.id}-more"/>">More</a></td>
                        <td>
                                ${extraOption.canBeDisabled}
                            <c:if test="${extraOption.canBeDisabled}">
                                <a href="<c:url value="/ecare/manager/contract-${contract.id}-exrtaoptions-delete/option-${extraOption.id}"/>">Отключить</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <br>

    <div class="container">
        <h5>Подключить дополнительные опции</h5>
        <table class="table">
            <tr>
                <th width="80">ID</th>
                <th width="120">Name</th>
                <th width="120">Стоимость</th>
                <th width="280">Стоимость подключения</th>
                <th width="380">Необходимые для подключения опции</th>
                <th width="280">Несовместимые</th>
                <th width="60"></th>
                <th width="60"></th>
            </tr>
            <c:forEach items="${extraOptionsList}" var="extraOptionForAdd">
                <tr>
                    <td>${extraOptionForAdd.id}</td>
                    <td>${extraOptionForAdd.name}</td>
                    <td>${extraOptionForAdd.cost}</td>
                    <td>${extraOptionForAdd.costConnection}</td>
                    <td>
                        <c:forEach items="${extraOptionForAdd.allJointlyOptions}" var="extraOptionsJointlyOption">
                            #${extraOptionsJointlyOption.id} ${extraOptionsJointlyOption.name}<br>
                        </c:forEach>
                            <%--${extraOption.allJointlyOptions}--%>
                    </td>
                    <td>
                        <c:forEach items="${extraOptionForAdd.allExcludeOptions}" var="extraOptionsExcludeOption">
                            #${extraOptionsExcludeOption.id} ${extraOptionsExcludeOption.name}<br>
                        </c:forEach>
                            <%--${extraOption.allExcludeOptions}--%>
                    </td>
                    <td><a href="<c:url value="/ecare/manager/option-${extraOptionForAdd.id}-more"/>">More</a></td>
                    <td>
                            ${extraOptionForAdd.canBeAdded}
                        <c:if test="${extraOptionForAdd.canBeAdded}">
                            <a href="<c:url value="/ecare/manager/contract-${contract.id}-exrtaoptions-add/option-${extraOptionForAdd.id}"/>">Подключить</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>


</div>

</body>
</html>
