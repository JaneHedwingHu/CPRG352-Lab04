

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\n"); %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Note - Simple Note Keeper</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>

        <form action="note" method="GET">
            <h2>View Note</h2>
            <p><a href="note?create">Create</a></p>

        <c:forEach items="${CONTENTS}" var="note" varStatus="loop">
            <h3>Title:</h3>
            <c:out value="${note.title}"/>
            <h3>Contents:</h3>
            <c:out value="${fn:replace(note.contents, newLineChar, '<br />')}" escapeXml="false"/>
            <p><a href="note?edit=${loop.index}">Edit</a>&nbsp;<a href="note?delete=${loop.index}">Delete</a></p>
            <hr />
        </c:forEach>
            
        </form>
    </body>
</html>
