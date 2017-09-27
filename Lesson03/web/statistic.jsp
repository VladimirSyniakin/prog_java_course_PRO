<%@ page import="com.gmail.vsyniakin.User" %>
<%@ page import="com.gmail.vsyniakin.Base" %><%--
  Created by IntelliJ IDEA.
  User: V2
  Date: 26.09.2017
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form>
    <p><% User user = (User) request.getSession().getAttribute("login");%>
        Your login: <%= user.getLogin()%>
    </p>
</form>

<h1 align="center" title="Статистика ответов"> Answers </h1>

<form>
    <% Base base = (Base) request.getSession().getAttribute("base");%>


    <table border="1" width="650">
        <h3 title="Ответы на вопросы всех пользователей:">Answers to questions from all users:</h3>
        <tr>
            <th> Question</th>
            <th> A</th>
            <th> B</th>
            <th> C</th>
        </tr>
        <tr>
            <td><%= request.getAttribute("q0") %>
            </td>
            <td><%= request.getAttribute("q0a0") %>
            </td>
            <td><%= request.getAttribute("q0a1") %>
            </td>
            <td><%= request.getAttribute("q0a2") %>
            </td>
        </tr>
        <tr>
            <td><%= request.getAttribute("q1") %>
            </td>
            <td><%= request.getAttribute("q1a0") %>
            </td>
            <td><%= request.getAttribute("q1a1") %>
            </td>
            <td><%= request.getAttribute("q1a2") %>
            </td>

        </tr>
        <tr>
            <td><%= request.getAttribute("q2") %>
            </td>
            <td><%= request.getAttribute("q2a0") %>
            </td>
            <td><%= request.getAttribute("q2a1") %>
            </td>
            <td><%= request.getAttribute("q2a2") %>
            </td>

        </tr>
    </table>

    <table border="1">
        <h3 title="Ваши ответы на вопросы:">Your answers to questions:</h3>
        <tr>
            <th> Quations</th>
            <th> A</th>
            <th> B</th>
            <th> C</th>
        </tr>
        <tr>
            <td><%= request.getAttribute("q0") %>
            </td>
            <td><%= request.getAttribute("q0a0User") %>
            </td>
            <td><%= request.getAttribute("q0a1User") %>
            </td>
            <td><%= request.getAttribute("q0a2User") %>
            </td>
        </tr>
        <tr>
            <td><%= request.getAttribute("q1") %>
            </td>
            <td><%= request.getAttribute("q1a0User") %>
            </td>
            <td><%= request.getAttribute("q1a1User") %>
            </td>
            <td><%= request.getAttribute("q1a2User") %>
            </td>
        </tr>
        <tr>
            <td><%= request.getAttribute("q2") %>
            </td>
            <td><%= request.getAttribute("q2a0User") %>
            </td>
            <td><%= request.getAttribute("q2a1User") %>
            </td>
            <td><%= request.getAttribute("q2a2User") %>
            </td>
        </tr>
    </table>
</form>

<form action="/saveBaseServlet" method="post">
    <input type="submit" name="save" value="Admin: Save base">
    <input type="submit" name="first" value="In first page">
</form>


<a href="index.html"> здесь ссылка на первую страницу <br/> </a>
<img src="image.jpg" alt="картинка"/>
</body>
</html>
