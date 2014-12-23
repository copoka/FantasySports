<%-- 
    Document   : addEligiblePlayer
    Created on : Nov 10, 2014, 5:29:11 PM
    Author     : steven.muschler
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Eligible Player</title>
    </head>
    <body>
        <h1>Add Eligible Player</h1>
        <form action="/FantasySports/AddEligiblePlayer" method="post">
            <table>
                <tr>
                    <td>Player Name: <input type="text" name="playerName" required></td>
                </tr>
                <tr>
                    <td>Position: 
                        <select name="playerPosition">
                            <option value="QB">QB</option>
                            <option value="WR">WR</option>
                            <option value="RB">RB</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </body>
</html>