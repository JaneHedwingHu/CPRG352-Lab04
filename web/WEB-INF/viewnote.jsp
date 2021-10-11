

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
            
            <h3>Title:</h3>
            <span>${CONTENTS.title}</span>
            <h3>Contents:</h3>
            <p>${CONTENTS.contents}</p>
            
            <p><a href="note?edit">Edit</a></p>
        </form>
    </body>
</html>
