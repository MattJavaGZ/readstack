<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ page import="java.time.format.DateTimeFormatter" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <title>ReadStack</title>
    <%@include file="../segments/stylesheets.jspf"%>
</head>
<body>
<div class="container">
   <%@include file="../segments/header.jspf"%>

    <aside class="categories">
        <ul>
            <c:forEach var="category" items="${requestScope.allCategoryNames}">
                <li> <a href="${pageContext.request.contextPath.concat('/category?id=').concat(category.id)}">${category.name}</a> </li>
            </c:forEach>
        </ul>
    </aside>
    <p class="block">
    <c:if test="${requestScope.block}">
        Twoje konto zostało zablokowane!
    </c:if>
    </p>
    <main>
       <%@include file="../segments/discovery-list.jspf"%>
    </main>
       <%@include file="../segments/footer.jspf"%>
</div>
</body>
</html>