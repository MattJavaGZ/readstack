<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Znalezisko - ReadStack</title>
    <%@include file="../segments/stylesheets.jspf"%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/discovery.css">
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jspf"%>

    <article class="discovery" >
    <h2 class="discovery-header"><c:out value="${discovery.title}"/></h2>
    <p class="discovery-details">Dodane przez: ${discovery.author}, Dnia: ${discovery.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
    <a href="<c:out value="${discovery.url}"/>" target="_blank" class="discovery-link"><c:out value="${discovery.url}"/></a>
    <p><c:out value="${discovery.description}"/></p>
    <section class="discovery-bar">
        <a href="${pageContext.request.contextPath.concat('/discovery/vote?id=').concat(discovery.id).concat('&type=UP')}"
           class="discovery-link upvote">
            <i class="fas fa-arrow-alt-circle-up discovery-upvote"></i>
        </a>
        <p class="discovery-votes">${discovery.voteCount}</p>
        <a href="${pageContext.request.contextPath.concat('/discovery/vote?id=').concat(discovery.id).concat('&type=DOWN')}"
           class="discovery-link downvote">
            <i class="fas fa-arrow-alt-circle-down discovery-downvote"></i>
        </a>
        <p class="visits">  Odwiedziny: ${discovery.visitCounter}</p>
    </section>
    </article>
    <article class="comments">
    <section class="comment_add">
        <h2>Dodaj komentarz</h2>
        <form action="${pageContext.request.contextPath}/discovery/comment?id=${discovery.id}" method="post">
        <textarea name="comment" placeholder="Treść komentarza" required cols="80" rows="5"></textarea>
<%--            <input hidden="hidden" name="id" value="${discovery.id}">--%>
            <p><button>Dodaj komentarz</button></p>
        </form>
    </section>
    <section class="comments_print">
        <h2>Komentarze</h2>
        <ul>
            <c:forEach var="comment" items="${requestScope.commentsByDiscoveryId}">
                <li class="singleComment">
                    <p>Autor: ${comment.userName} &nbsp&nbsp&nbsp *&nbsp&nbsp&nbsp
                    Data dodania: ${comment.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
                    <p><b><c:out value="${comment.commentContent}"/></b></p>
                    <c:if test="${requestScope.admin}">
                        <a href="${pageContext.request.contextPath}/userpanel/admin/editcomment?id=${comment.id}&discoveryid=${discovery.id}">Edytuj</a>
                        <a href="${pageContext.request.contextPath}/userpanel/admin/deletecomment?id=${comment.id}&discoveryid=${discovery.id}">Usuń</a>
                    </c:if>
                </li>
            </c:forEach>
        </ul>

    </section>
    </article>
        <%@include file="../segments/footer.jspf"%>
    </div>
</body>
</html>
