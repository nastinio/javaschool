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
    <div class="card-deck mb-3 text-center">

        <h5>Результаты поиска:</h5>
        <c:if test="${empty listContracts}">
            Контракты не найдены
        </c:if>
        <c:if test="${!empty listContracts}">
            <table class="table">
                <tr>
                    <th width="80">ID</th>
                    <th width="80">Number</th>
                    <th width="120">Score</th>
                    <th width="180">Тариф</th>
                    <th width="180">Владелец</th>
                    <th width="60"></th>
                </tr>
                <c:forEach items="${listContracts}" var="contract">
                    <tr>
                        <td>${contract.id}</td>
                        <td>${contract.number}</td>
                        <td>${contract.score}</td>
                        <td>
                            <a href="<c:url value="/ecare/manager/tariff-${contract.tariffInContract.id}-more"/>">${contract.tariffInContract.name}</a>
                        </td>
                        <td>
                            <a href="<c:url value="/ecare/manager/person-${contract.personInContract.id}-more"/>">${contract.personInContract.firstname} ${contract.personInContract.lastname}</a>
                        </td>
                        <td><a href="<c:url value="/ecare/manager/contract-${contract.id}-more"/>">More</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </div>
</div>

</body>
</html>
