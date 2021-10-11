<%-- 
    Document   : createnote
    Created on : Oct 10, 2021, 8:34:14 PM
    Author     : jinwei
--%>

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

        <form action="note?create" method="POST">
            <h2>Create Note</h2>
            
            <h3>Title:</h3>
            <input type="text" name="EDIT_TITLE" value="">
            <h3>Contents:</h3>
            <textarea type="text" name="EDIT_CONTENTS" style="height:100px; width:300px;"></textarea>
           
            <input type="submit" value="Save">   
    </body>
</html>
