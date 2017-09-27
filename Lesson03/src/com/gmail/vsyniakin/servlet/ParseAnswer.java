package com.gmail.vsyniakin.servlet;

import com.gmail.vsyniakin.Base;
import com.gmail.vsyniakin.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ParseAnswer", urlPatterns = "/parseAnswer")
public class ParseAnswer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("login");
        Base base = (Base) req.getSession().getAttribute("base");
        String questionOne = req.getParameter("questionOne");
        String questionTwo = req.getParameter("questionTwo");
        String questionThree = req.getParameter("questionThree");

        user.getStatisticAnswer().addAnswers(questionOne, questionTwo, questionThree);
        base.getStatisticAllUser().addAnswers(questionOne, questionTwo, questionThree);

        req = statisticAll(base, req);
        req = statisticUser(user, req);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/statistic.jsp");
        dispatcher.forward(req, resp);

    }

    public HttpServletRequest statisticAll(Base base, HttpServletRequest request) {
        request.setAttribute("q0", base.getSomeQuestions().get(0).getQuestion());
        request.setAttribute("q1", base.getSomeQuestions().get(1).getQuestion());
        request.setAttribute("q2", base.getSomeQuestions().get(2).getQuestion());

        request.setAttribute("q0a0", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(0).getAnswers().get(0)).get());
        request.setAttribute("q0a1", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(0).getAnswers().get(1)).get());
        request.setAttribute("q0a2", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(0).getAnswers().get(2)).get());

        request.setAttribute("q1a0", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(1).getAnswers().get(0)).get());
        request.setAttribute("q1a1", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(1).getAnswers().get(1)).get());
        request.setAttribute("q1a2", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(1).getAnswers().get(2)).get());

        request.setAttribute("q2a0", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(2).getAnswers().get(0)).get());
        request.setAttribute("q2a1", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(2).getAnswers().get(1)).get());
        request.setAttribute("q2a2", base.getStatisticAllUser().getStatisticUserAnswer().get(base.getSomeQuestions().get(2).getAnswers().get(2)).get());

        return request;
    }

    public HttpServletRequest statisticUser(User user, HttpServletRequest request) {

        request.setAttribute("q0a0User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(0).getAnswers().get(0)).get());
        request.setAttribute("q0a1User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(0).getAnswers().get(1)).get());
        request.setAttribute("q0a2User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(0).getAnswers().get(2)).get());

        request.setAttribute("q1a0User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(1).getAnswers().get(0)).get());
        request.setAttribute("q1a1User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(1).getAnswers().get(1)).get());
        request.setAttribute("q1a2User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(1).getAnswers().get(2)).get());

        request.setAttribute("q2a0User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(2).getAnswers().get(0)).get());
        request.setAttribute("q2a1User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(2).getAnswers().get(1)).get());
        request.setAttribute("q2a2User", user.getStatisticAnswer().getStatisticUserAnswer().get(user.getStatisticAnswer().getSomeQuestions().get(2).getAnswers().get(2)).get());

        return request;
    }


}
