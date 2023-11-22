<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.lb2.Models.Point" %>
<%@ page import="com.example.lb2.Models.Points" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LB 2</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="background">
<header class="header">
    <div class="header-container">
        <a class="header-item" href="https://se.ifmo.ru/~s353349/lb1/index.htm" target="_blank"> <h2>Lab Work 1</h2></a>
        <a class="header-item" href="https://isu.ifmo.ru/pls/apex/f?p=2437:7:109555636440371:::::" target="_blank"> <h2>Zarubov E.N</h2></a>
        <a class="header-item" href="https://isu.ifmo.ru/pls/apex/f?p=2143:GR:109555636440371::NO:RP:GR_GR,GR_DATE:P3218," target="_blank"> <h2>Group P3218</h2></a>
        <a class="header-item" href="https://se.ifmo.ru/courses/web" target="_blank"> <h2>Variant 2802</h2></a>
    </div>
    <div class="git-logo">
        <a href="https://github.com/byrybdyk/Web" target="_blank">
            <img class="git-img" src="source/GitHublogo.png"  alt="git-logo">
        </a>
    </div>
</header>
<main class="main">
    <div class="row">
        <div class="results">
            <H3>Результаты проверки</H3>
            <%
                Points points = (Points) request.getSession().getAttribute("points");
                if (points == null) {
            %>
            <span>Нет результатов</span>
            <%} else {%>
            <table>
                <thead>
                <thead>
                <tr>
                    <th>r</th>
                    <th>x</th>
                    <th>y</th>
                    <th>статус</th>
                </tr>
                </thead>
                <tbody id="resultsTable">
                <%for(Point point : points.getPoints()) {%>
                <tr>
                    <td><%=point.getR()%></td>
                    <td><%=point.getX()%></td>
                    <td><%=point.getY()%></td>
                    <td><%=point.isInArea()%></td>
                </tr>
                <%}%>
                </tbody>
                </thead>
            </table>
            <%}%>
            <a href="./">Вернуться к форме</a>
        </div>
    </div>
</main>
<script type='text/javascript' src="js/graph.js"></script>
<script src="js/script.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</div>
</body>
</html>
