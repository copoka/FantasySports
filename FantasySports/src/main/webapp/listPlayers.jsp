<%-- 
    Document   : homepage
    Created on : Nov 1, 2014, 5:41:10 PM
    Author     : steven.muschler
--%>
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
        <a href="LogoutServlet">Logout</a>
        <table>
            <c:forEach var="player" items="${players}">
                <tr>
                    <td>
                        <a href="AddPlayer?teamId=${teamId}&playerId=${player.id}">${player.name}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
