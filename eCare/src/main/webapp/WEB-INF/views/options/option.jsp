<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Add option page</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<h1>
    Add Option
</h1>

<a href="<c:url value = '/option/edit/${option.id}' />">Редактировать опцию </a>

<table>
    <tr>
        <td>ID</td>
        <td>${option.id}</td>
    </tr>
    <tr>
        <td>Name</td>
        <td>${option.name}</td>
    </tr>
    <tr>
        <td>
            connectionCostOption
        </td>
        <td>
            ${option.connectionCostOption}
        </td>
    </tr>
    <tr>
        <td>
            costOption
        </td>
        <td>
            ${option.costOption}
        </td>
    </tr>

</table>

Информация о взаимосвязях с другими опциями <br><<br>

<c:if test="${empty listJointlyOptions}">

    Нет информации о взаимосвязях с другими опциями <br>
    Может использоваться совместно с любыми другими опциями <br><br>

</c:if>

<c:forEach items="${listJointlyOptions}" var="optionJointly">
    Обязательные опции <br><br>
    Id =   ${optionJointly.id}<br>
    Name = ${optionJointly.name}<br>
</c:forEach>

<c:forEach items="${listExcludeOptions}" var="optionExclude">
    Ичключающие опции <br><br>
    Id =   ${optionExclude.id}<br>
    Name = ${optionExclude.name}<br>
</c:forEach>


<c:if test="${!empty listOptions}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Connection cost</th>
            <th width="120">Cost</th>

            <th width="120">Jointly</th>
            <th width="120">Exclude</th>

        </tr>
        <c:forEach items="${listOptions}" var="option">
            <tr>
                <td>${option.id}</td>
                <td>${option.name}</td>
                <td>${option.connectionCostOption}</td>
                <td>${option.costOption}</td>

                <td><input type="checkbox" name=${option.id}</td>
                <td><input type="checkbox" name=${option.id}></td>

            </tr>
        </c:forEach>
    </table>
</c:if>


</body>
</html>
