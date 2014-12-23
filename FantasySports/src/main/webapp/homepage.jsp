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
        <title>${user.username}'s Home Page</title>
    </head>
    <body>
        <h1>${user.username}</h1>
        <a href="homepage.jsp">Homepage</a>
        <a href="createleague.jsp">Create a League!</a>
        <a href="ListAvailableLeaguesServlet">Join a League!</a>
        <c:if test="${user.username=='admin'}">
            <a href="addEligiblePlayer.jsp">Add Eligible Player</a>
            <a href="EditScores">Edit Player Scores</a>
        </c:if>
        <a href="LogoutServlet">Logout</a>
        <table>
            <c:forEach var="leagueMember" items="${leagueMembers}">
                <tr>
                    <td>
                        <% 
                            LeagueMember l = (LeagueMember) pageContext.getAttribute("leagueMember");
                            String leagueName = l.getLeague().getLeaguename();
                            Long leagueId = l.getLeague().getId();
                        %>
                        <b><%= leagueName %></b>
                        <a href="TeamRosterServlet?teamId=${leagueMember.id}">${leagueMember.id}</a>
                        <a href="Standings?leagueId=<%= leagueId %>">View Standings</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
