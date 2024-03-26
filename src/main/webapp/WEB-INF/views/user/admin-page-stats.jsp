
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Admin Page - statystyki</title>
    <%@include file="../../segments/stylesheets.jspf"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/user-panel.css">
</head>
<body>
<div class="container">
    <%@include file="../../segments/header.jspf"%>
    <aside class="categories">
        <ul>
            <li><a href="${pageContext.request.contextPath}/userpanel/admin">Statystyki</a> </li>
            <li><a href="${pageContext.request.contextPath}/userpanel/admin?page=users">Użytkownicy</a> </li>
        </ul>
    </aside>
    <main class="stats">
        <h1>Statystyki portalu:</h1>
        <p>Ilość dodanych znalezisk: ${requestScope.statsFullInfo.discoveryCount}</p>
        <p>Ilość dodanych komentarzy: ${requestScope.statsFullInfo.commentsCount}</p>
        <p>Ilość ocen znalezisk: ${requestScope.statsFullInfo.voteCount}</p>
        <p>Ilość użytkowników: ${requestScope.statsFullInfo.usersCount}</p>

    </main>

</div>
</body>
</html>
