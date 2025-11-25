<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.Cookie" %>

<%
    String theme = "light";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("theme".equals(c.getName()))
            {
                theme = c.getValue();
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Вибір теми</title>
    <style>
        body.light { background-color: white; color: black; }
        body.dark { background-color: #222; color: #eee; }
        .theme-selector { margin: 20px; font-size: 18px; }
    </style>
</head>
<body class="<%=theme%>">
<div class="theme-selector">
    <form method="post" action="theme">
        <label>
            <input type="radio" name="theme" value="light" <%= "light".equals(theme) ? "checked" : "" %> >
            Світла тема
        </label>
        <label>
            <input type="radio" name="theme" value="dark" <%= "dark".equals(theme) ? "checked" : "" %> >
            Темна тема
        </label>
        <button type="submit">Застосувати</button>
    </form>
</div>
</body>
</html>
