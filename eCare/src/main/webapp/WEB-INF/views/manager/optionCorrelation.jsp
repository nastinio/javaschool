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
    <p>
    <h2>${option.name}</h2>
    </p>
    <p>${option.description}</p>
    <p><b>Стоимость услуги: </b> ${option.cost}</p>
    <p><b>Стоимость подключения:</b> ${option.costConnection}</p>


    <h5>Взаимосвязи между опциями</h5>
    <table class="table">
        <tr>
            <th width="100">ID</th>
            <th width="180">Название</th>
            <th width="120">Стоимость</th>
            <th width="280">Стоимость подключения</th>
            <th width="300">Взаимосвязь</th>
            <th width="60"></th>
            <th width="60"></th>
        </tr>
        <c:forEach items="${listOptions}" var="option">
            <tr>
                <spring:form method="post" action="/ecare/manager/option-${option.id}-update-correlation"
                             modelAttribute="outputOption">
                    <td>${option.id}</td>
                    <td>${option.name}</td>
                    <td>${option.cost}</td>
                    <td>${option.costConnection}</td>
                    <td>
                        <c:if test="${option.correlation eq 'JOINTLY'}">
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions" checked="checked"
                                                    id="inlineRadio1" value="jointly"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio1">Необходима</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions"
                                                    id="inlineRadio2" value="exclude"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio2">Несовместима</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions"
                                                    id="inlineRadio3" value="none"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio3">Нет связи</label>
                            </div>
                        </c:if>
                        <c:if test="${option.correlation eq 'EXCLUDE'}">
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions"
                                                    id="inlineRadio1" value="jointly"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio1">Необходима</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions" checked="checked"
                                                    id="inlineRadio2" value="exclude"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio2">Несовместима</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions"
                                                    id="inlineRadio3" value="none"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio3">Нет связи</label>
                            </div>
                        </c:if>
                        <c:if test="${option.correlation eq 'NONE'}">
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions"
                                                    id="inlineRadio1" value="jointly"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio1">Необходима</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions"
                                                    id="inlineRadio2" value="exclude"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio2">Несовместима</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <spring:radiobutton path="include" class="form-check-input"
                                                    name="inlineRadioOptions" checked="checked"
                                                    id="inlineRadio3" value="none"></spring:radiobutton>
                                <label class="form-check-label" for="inlineRadio3">Нет связи</label>
                            </div>
                        </c:if>
                    </td>

                    <td><a href="<c:url value="/ecare/manager/option-${option.id}-more"/>">More</a></td>
                    <td>
                        <div class="form-group row">
                            <div class="offset-sm-2 col-sm-10">
                                <c:if test="${!empty option.id}">
                                    <spring:button type="submit" class="btn btn-primary">Редактировать</spring:button>
                                </c:if>
                            </div>
                        </div>
                    </td>
                </spring:form>
            </tr>
        </c:forEach>
    </table>


</div>

</body>
</html>
