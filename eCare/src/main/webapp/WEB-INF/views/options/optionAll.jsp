<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>All options</title>
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
