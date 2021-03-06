<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>All options</title>
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


<a href="<c:url value = "/option/add" />">Добавить опцию </a>
<br>

<table class="tg">
    <tr>
        <th width="80" disabled="true">ID</th>
        <th width="120">Name</th>
        <th width="120">Connection cost</th>
        <th width="120">Cost</th>
    </tr>

    <c:forEach items="${listOptions}" var="option">
        <tr>
            <td>${option.id}</td>
            <td><a href="<c:url value='/option/${option.id}'/>">${option.name}</a></td>
            <td>${option.connectionCostOption}</td>
            <td>${option.costOption}</td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
