<%-- 
    Document   : editScores
    Created on : Nov 10, 2014, 2:37:25 PM
    Author     : steven.muschler
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page  language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="CoreCSS.css">
        <title>Edit Player's Scores</title>
    </head>
    <body>
        <h1>Edit Player's Scores</h1>
        <a href="homepage.jsp">Homepage</a>
        <a href="createleague.jsp">Create a League!</a>
        <a href="ListAvailableLeaguesServlet">Join a League!</a>
        <a href="LogoutServlet">Logout</a>
        <form action="/FantasySports/SetScores">
        <table id="players">
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Passing Yards</th>
                <th>Passing TD</th>
                <th>Scrimmage Yards</th>
                <th>Scrimmage TD</th>
                <th>Interceptions</th>
                <th>Fumbles</th>
            </tr>    
            <tr>
                <td>${player.name}</a></td>
                <td>${player.position}</td>
                <td><input type="number" size="5" name="passingyards" value="${player.stats.passingyards}" required></td>
                <td><input type="number" size="2" name="passingtd" value="${player.stats.passingtd}" required></td>
                <td><input type="number" size="5" name="scrimmageyards" value="${player.stats.scrimmageyards}" required></td>
                <td><input type="number" size="2" name="scrimmagetd" value="${player.stats.scrimmagetd}" required></td>
                <td><input type="number" size="2" name="interceptions" value="${player.stats.interceptions}" required></td>
                <td><input type="number" size="6" name="fumbles" value="${player.stats.fumbles}" required></td>
            </tr>           
        </table>
        <input type="hidden" name="id" value="${player.id}">
        <input type="submit" value="Submit">
        </form>
    </body>
</html>
