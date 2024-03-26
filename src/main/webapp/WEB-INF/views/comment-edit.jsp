<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="pl">
<head>
    <title>Edycja komentarza</title>
    <%@include file="../segments/stylesheets.jspf" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/user-panel.css">
</head>
<body>
<div class="container">
    <%@include file="../segments/header.jspf" %>
    <div class="edit-comment">
        <h1>Edycja komentarza</h1>

        <p>Autor: ${comment.userName}</p>
        <p>Data dodania: ${comment.dateAdded.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))}</p>
        <form action="${pageContext.request.contextPath}/userpanel/admin/editcomment?id=${comment.id}&discoveryid=${discoveryid}"
              method="post">
            <textarea name="commentContent" cols="80" rows="5"><c:out value="${comment.commentContent}"/></textarea>
            <p>
                <button>Zapisz zmianę</button>
            </p>
        </form>
    </div>
</div>
</body>
</html>
