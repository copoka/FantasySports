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
        <title>Edit Scores</title>
    </head>
    <body>
        <h1>Edit Scores</h1>
        <a href="homepage.jsp">Homepage</a>
        <a href="createleague.jsp">Create a League!</a>
        <a href="ListAvailableLeaguesServlet">Join a League!</a>
        <a href="LogoutServlet">Logout</a>
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
            <c:forEach var="player" items="${players}">
                <c:choose>
                    <c:when test="${not empty player.stats}">
                        <tr>
                            <td><a href="EditPlayerScores?playerId=${player.id}">${player.name}</a></td>
                            <td>${player.position}</td>
                            <td>${player.stats.passingyards}</td>
                            <td>${player.stats.passingtd}</td>
                            <td>${player.stats.scrimmageyards}</td>
                            <td>${player.stats.scrimmagetd}</td>
                            <td>${player.stats.interceptions}</td>
                            <td>${player.stats.fumbles}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="8">Player has not stats!!!</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </table>
    </body>
</html>
