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
    <h2>${person.firstname} ${person.lastname}</h2>
    <a href="/ecare/manager/person-${person.id}-edit">Edit</a> <a href="/ecare/manager/person-${person.id}-remove">Remove</a>
    </p>
    <p><b>Дата рождения: </b>${person.dob}</p>
    <p><b>Email: </b>${person.email}</p>
    <p><b>Адрес: </b>${person.address}</p>
    <p><b>Пасторт: </b>${person.passport}</p>

    <%--Добавить контракт--%>
    <a href="/ecare/manager/person-${person.id}/contract-add">Добавить контракт</a><br>

    <div class="card-deck mb-3 text-center">
        <%--Все контракты пользователя--%>
        <c:forEach items="${person.contracts}" var="contract">
            <div class="row">
                <div class="col">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-header">
                            <h4 class="my-0 font-weight-normal">${contract.number}</h4>
                        </div>
                        <div class="card-body">
                            <h1 class="card-title pricing-card-title">
                                <small class="text-muted">Баланс</small>
                                    ${contract.score}$
                            </h1>
                            <ul class="list-unstyled mt-3 mb-4">
                                <li>Тариф: ${contract.tariffInContract.name}</li>
                            </ul>
                            <a href="/ecare/manager/contract-${contract.id}-more"
                               class="btn btn-lg btn-block btn-outline-primary" role="button"
                               aria-disabled="true">More</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>


</div>

</body>
</html>
