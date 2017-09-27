package com.gmail.vsyniakin.servlet;

import com.gmail.vsyniakin.Base;
import com.gmail.vsyniakin.BaseToXML;
import com.gmail.vsyniakin.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "UserDataServlet", urlPatterns = "/userDataServlet")
public class UserDataServlet extends HttpServlet {

    private Base base;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        loadBase();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession userSession;
        userSession = req.getSession();

        User user;

        if ((req.getParameter("logIn")) == null) {
            user = base.addUser(login, password);
            if (user != null) {
                userSession.setAttribute("login", user);
                userSession.setAttribute("base", base);
                resp.sendRedirect("questions.jsp");
            } else {
                resp.sendRedirect("index.html");
            }
        } else {
            user = base.searchUser(login);
            if (base.checkPassword(login, password)) {
                userSession.setAttribute("login", user);
                userSession.setAttribute("base", base);
                resp.sendRedirect("questions.jsp");
            } else {
                resp.sendRedirect("index.html");
            }
        }
    }

    public void loadBase() {
        if (this.base == null) {
            File file = new File("W:\\MyJavaProject\\prog.kiev.ua Homework PRO\\Lesson03\\base.xml");
            if (file.exists()) {
                this.base = BaseToXML.unmarshalBase(file);
            } else {
                this.base = new Base();
                this.base.createNewBase();
            }
        }
    }

}
