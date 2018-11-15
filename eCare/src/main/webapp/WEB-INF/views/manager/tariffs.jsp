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
    </nav>
    <h5 class="my-0 mr-md-auto font-weight-normal"></h5>
    <a class="p-2 text-dark" href="#">Manager</a>
    <a class="btn btn-outline-primary" href="#">Выйти</a>
</div>

<a href="/ecare/manager/tariff-add">Добавить тариф</a>

<div class="container">
    <div class="card-deck mb-3 text-center">

        <c:forEach items="${tariffsList}" var="tariff">
            <div class="row">
                <div class="col">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-header">
                            <h4 class="my-0 font-weight-normal">${tariff.name}</h4>
                        </div>
                        <div class="card-body">
                            <h1 class="card-title pricing-card-title">$${tariff.cost}
                                <small class="text-muted">/ mo</small>
                            </h1>
                            <ul class="list-unstyled mt-3 mb-4">
                                <li>-</li>
                            </ul>
                            <a href="/ecare/manager/tariff-${tariff.id}-more" class="btn btn-lg btn-block btn-outline-primary" role="button" aria-disabled="true">More</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>


    <footer class="pt-4 my-md-5 pt-md-5 border-top">
        <div class="row">
            <div class="col-12 col-md">
                <small class="d-block mb-3 text-muted">nastinio-2018</small>
            </div>
        </div>
    </footer>
</div>

</body>
</html>