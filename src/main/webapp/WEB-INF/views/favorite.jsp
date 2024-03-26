
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Ulubione znaleziska</title>
    <%@include file="../segments/stylesheets.jspf"%>
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jspf"%>
    <h1>Ulubione znaleziska</h1>
    <c:choose>
        <c:when test="${empty sessionScope.discoveries}">
            <h2>Brak ulubionych</h2>
        </c:when>
        <c:otherwise>
            <%@include file="../segments/discovery-list.jspf"%>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
