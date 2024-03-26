
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>Admin Page - użytkownicy</title>
    <%@include file="../../segments/stylesheets.jspf"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/user-panel.css">
<body>
<div class="container">
    <%@include file="../../segments/header.jspf"%>
    <aside class="categories">
        <ul>
            <li><a href="${pageContext.request.contextPath}/userpanel/admin">Statystyki</a> </li>
            <li><a href="${pageContext.request.contextPath}/userpanel/admin?page=users">Użytkownicy</a> </li>
        </ul>
    </aside>
    <main class="users">
        <table class="users-table" border="1">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nazwa użytkownika</th>
                <th>Data rejestracji</th>
                <th>Zablokuj użytkownika</th>
                <th>Usuń użytkownika</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.allUsers}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.registrationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}</td>
                <c:if test="${user.role eq 'USER'}">
                    <td><a href="${pageContext.request.contextPath}/userpanel/admin/block?username=${user.username}">Zablokuj</a></td>
                </c:if>
                <c:if test="${user.role eq 'BLOCK'}">
                    <td><a href="${pageContext.request.contextPath}/userpanel/admin/unlock?username=${user.username}">Odblokuj</a></td>
                </c:if>
                <td><a href="${pageContext.request.contextPath}/userpanel/admin/delete?username=${user.username}&id=${user.id}">Usuń</a></td>
            </tr>
            </c:forEach>
            </tbody>

        </table>

    </main>

</body>
</html>
