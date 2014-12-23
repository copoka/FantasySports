<%-- 
    Document   : team
    Created on : Nov 3, 2014, 8:01:11 PM
    Author     : steven.muschler
--%>
<%@page import="com.fantasysports.player.Player"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="CoreCSS.css">
        <title>Team</title>
    </head>
    <body>
        <h1>${leagueMember.id}</h1>
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
                <th>Score</th>
                <th>Click to Drop</th>
            </tr>
            <c:forEach var="player" items="${leagueMember.roster}">
                <tr>
                    <td>${player.name}</td>
                    <td>${player.position}</td>
                    <td>${player.stats.passingyards}</td>
                    <td>${player.stats.passingtd}</td>
                    <td>${player.stats.scrimmageyards}</td>
                    <td>${player.stats.scrimmagetd}</td>
                    <td>${player.stats.interceptions}</td>
                    <td>${player.stats.fumbles}</td>
                    <% 
                        Player player = (Player) pageContext.getAttribute("player");
                        double score = player.calculateScore();
                    %>
                    <td><%= score %></td>
                    <td><a href="DropPlayer?teamId=${leagueMember.id}&playerId=${player.id}">Drop Player</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="ListAvailablePlayers?teamId=${leagueMember.id}">Add Player</a>
    </body>
</html>
