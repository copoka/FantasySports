<%-- 
    Document   : homepage
    Created on : Nov 1, 2014, 5:41:10 PM
    Author     : steven.muschler
--%>
<%@page import="com.fantasysports.league.LeagueMember"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page  language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link REL="StyleSheet" TYPE="text/css" HREF="CoreCSS.css">
        <title>${league.leaguename}'s Standings</title>
    </head>
    <body>
        <h1>${league.leaguename}</h1>
        <a href="homepage.jsp">Homepage</a>
        <a href="createleague.jsp">Create a League!</a>
        <a href="ListAvailableLeaguesServlet">Join a League!</a>
        <a href="LogoutServlet">Logout</a>
        <table id="players">
            <c:forEach var="leagueMember" items="${league.leagueMemberCollection}">
                <tr>
                    <th colspan="2">Team ID: ${leagueMember.id}</th>                    
                    <th colspan="6">Score: ${scores[leagueMember.id]}</th>
                </tr>
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
                <c:choose>
                    <c:when test="${not empty leagueMember.roster}">
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
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="8">User has no players on Roster!!!</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
       
            </c:forEach>
        </table>
    </body>
</html>
