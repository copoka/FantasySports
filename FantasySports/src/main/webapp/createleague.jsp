<%-- 
    Document   : homepage
    Created on : Nov 1, 2014, 5:41:10 PM
    Author     : steven.muschler
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>League Creation</title>
    </head>
    <body>
        <h1>Create a League!</h1>
        <form action="/FantasySports/CreateLeagueServlet" method="post">
            <table>
                <tr>
                    <td>League Name: <input type="text" name="leagueName"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
    </body>
</html>