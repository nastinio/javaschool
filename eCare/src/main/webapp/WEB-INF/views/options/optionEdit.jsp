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

<%--<c:url var="addAction" value="/option/add"></c:url>--%>


<form:form action="/option/create" commandName="option">
    <table>
        <c:if test="${!empty option.id}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name"><spring:message text="Name"/></form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="costConnection">
                    <spring:message text="Connection cost option"/>
                </form:label>
            </td>
            <td>
                <form:input path="costConnection"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="cost">
                    <spring:message text="Cost option"/>
                </form:label>
            </td>
            <td>
                <form:input path="cost"/>
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${!empty option.id}">
                    <input type="submit"
                           value="<spring:message text="Edit Option"/>"/>
                </c:if>
                <c:if test="${empty option.id}">
                    <input type="submit"
                           value="<spring:message text="Add Option"/>"/>
                </c:if>
            </td>
        </tr>
    </table>

</form:form>


Add relationships between options <br>


<%--<c:set var="currentID"  value="${option.id}" />--%>
<c:if test="${!empty option.id}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Connection cost</th>
            <th width="120">Cost</th>

            <th width="120">Jointly</th>
            <th width="120">Exclude</th>

        </tr>
            <%--Сначала выведем опции с зависимостями, с проставленными галочками, затем все остальные--%>
            <%--Все обязательные опции--%>
        <c:forEach items="${listJointlyOptions}" var="joinOption">
            <tr>
                <td>${joinOption.id}</td>
                <td>${joinOption.name}</td>
                <td>${joinOption.costConnection}</td>
                <td>${joinOption.cost}</td>

                <td><input type="checkbox" name=${joinOption.id} checked></td>
                <td><input type="checkbox" name=${joinOption.id}></td>
            </tr>
        </c:forEach>

            <%--Все исключающие опции--%>
        <c:forEach items="${listExcludeOptions}" var="excludeOption">
            <tr>
                <td>${excludeOption.id}</td>
                <td>${excludeOption.name}</td>
                <td>${excludeOption.costConnection}</td>
                <td>${excludeOption.cost}</td>

                <td><input type="checkbox" name=${excludeOption.id}></td>
                <td><input type="checkbox" name=${excludeOption.id} checked></td>
            </tr>
        </c:forEach>

            <%--Все остальные опции--%>
            <%--Не выводим опции с зависимостями и саму редактируемую опцию--%>
        <c:forEach items="${listOptionsWithoutRules}" var="optionWithoutRule">
            <tr>
                <td>${optionWithoutRule.id}</td>
                <td>${optionWithoutRule.name}</td>
                <td>${optionWithoutRule.costConnection}</td>
                <td>${optionWithoutRule.cost}</td>


                <%--<td>
                    <form:form action="/option/edit/${option.id}/addJointlyOp${optionWithoutRule.id}">
                        <input type="submit" value="<spring:message text="Make jointly"/>"/>

                    </form:form>
                </td>--%>
                <td><input type="checkbox" name=${optionWithoutRule.id}</td>
                <td><input type="checkbox" name=${optionWithoutRule.id}</td>
            </tr>
        </c:forEach>

    </table>
</c:if>


<%--Если выбрано добавление опции, выводим все возможные опции для добавления зависимостей--%>
<c:if test="${empty option.id}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Connection cost</th>
            <th width="120">Cost</th>

            <th width="120">Jointly</th>
            <th width="120">Exclude</th>

        </tr>

        <c:forEach items="${listOptions}" var="optionFromListAll">
            <tr>
                <td>${optionFromListAll.id}</td>
                <td>${optionFromListAll.name}</td>
                <td>${optionFromListAll.costConnection}</td>
                <td>${optionFromListAll.cost}</td>

                <td><input type="checkbox" name=${optionFromListAll.id}</td>
                <td><input type="checkbox" name=${optionFromListAll.id}</td>
            </tr>
        </c:forEach>

    </table>
</c:if>


<br> Здесь будет информация о тарифах<br>


</body>
</html>
