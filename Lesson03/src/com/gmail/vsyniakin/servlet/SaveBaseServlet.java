package com.gmail.vsyniakin.servlet;

import com.gmail.vsyniakin.Base;
import com.gmail.vsyniakin.BaseToXML;
import com.gmail.vsyniakin.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "SaveBaseServlet", urlPatterns = "/saveBaseServlet")
public class SaveBaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("save") != null) {
            if (((User) req.getSession().getAttribute("login")).getLogin().equals("admin")) {
                BaseToXML.marshalBase((Base) req.getSession().getAttribute("base"), new File("W:\\MyJavaProject\\prog.kiev.ua Homework PRO\\Lesson03\\base.xml"));
            } else {
                resp.sendRedirect("index.html");
                req.getSession().invalidate();
            }
        } else if (req.getParameter("first") != null) {
            resp.sendRedirect("index.html");
            req.getSession().invalidate();
        }
    }
}
