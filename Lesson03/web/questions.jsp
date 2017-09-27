<%@ page import="com.gmail.vsyniakin.Base" %>
<%@ page import="com.gmail.vsyniakin.User" %><%--
  Created by IntelliJ IDEA.
  User: V2
  Date: 24.09.2017
  Time: 23:35
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
<img src="image.jpg" alt="картинка"/>
<h1 align="left" title="Ответьте на пару вопросов"> Some questions </h1>

<ol>
    <form action="/parseAnswer" method="post">
        <fieldset>
            <% Base base = (Base)request.getSession().getAttribute("base"); %>
            <li><%=base.getSomeQuestions().get(0).getQuestion() %>
                <ol type="A">
                    <li><label> <input type="radio" name="questionOne"
                                       value="0"> <%= base.getSomeQuestions().get(0).getAnswers().get(0).getAnswer() %>
                    </label></li>
                    <li><label> <input type="radio" name="questionOne"
                                       value="1"> <%= base.getSomeQuestions().get(0).getAnswers().get(1).getAnswer() %>
                    </label></li>
                    <li><label> <input type="radio" name="questionOne"
                                       value="2"> <%= base.getSomeQuestions().get(0).getAnswers().get(2).getAnswer() %>
                    </label></li>
                </ol>
            </li>
        </fieldset>
        <fieldset>
            <li><%= base.getSomeQuestions().get(1).getQuestion() %>
                <ol type="A">
                    <li><label> <input type="radio" name="questionTwo"
                                       value="0"> <%= base.getSomeQuestions().get(1).getAnswers().get(0).getAnswer() %>
                    </label></li>
                    <li><label> <input type="radio" name="questionTwo"
                                       value="1"> <%= base.getSomeQuestions().get(1).getAnswers().get(1).getAnswer() %>
                    </label></li>
                    <li><label> <input type="radio" name="questionTwo"
                                       value="2"> <%= base.getSomeQuestions().get(1).getAnswers().get(2).getAnswer() %>
                    </label></li>
                </ol>
            </li>
        </fieldset>
        <fieldset>
            <li><%= base.getSomeQuestions().get(2).getQuestion() %>
                <ol type="A">
                    <li><label> <input type="radio" name="questionThree"
                                       value="0"> <%= base.getSomeQuestions().get(2).getAnswers().get(0).getAnswer() %>
                    </label></li>
                    <li><label> <input type="radio" name="questionThree"
                                       value="1"> <%= base.getSomeQuestions().get(2).getAnswers().get(1).getAnswer() %>
                    </label></li>
                    <li><label> <input type="radio" name="questionThree"
                                       value="2"> <%= base.getSomeQuestions().get(2).getAnswers().get(2).getAnswer() %>
                    </label></li>
                </ol>
            </li>
        </fieldset>

        <input type="submit" name="send" value="Send answers">

    </form>


</ol>

</body>


</html>
