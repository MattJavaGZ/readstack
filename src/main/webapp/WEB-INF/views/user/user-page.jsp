
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Panel użytkownika</title>
    <%@include file="../../segments/stylesheets.jspf"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/user-panel.css">
</head>
<body>
<div class="container">
    <%@include file="../../segments/header.jspf"%>
    <aside class="categories">
        <ul>
            <li><a href="${pageContext.request.contextPath}/userpanel/user">Ocenione znaleziska</a> </li>
            <li><a href="${pageContext.request.contextPath}/userpanel/user?type=comment">Skomentowane znaleziska</a> </li>
        </ul>
    </aside>
    <h2 class="user-panel-title">${requestScope.title}</h2>
    <%@include file="../../segments/discovery-list.jspf"%>
</div>
</body>
</html>
