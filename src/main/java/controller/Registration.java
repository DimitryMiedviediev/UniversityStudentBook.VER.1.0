package controller;

import beans.BeansAuthorizationRegistration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by Dimitry on 16.03.17.
 */
public class Registration extends HttpServlet {
    BeansAuthorizationRegistration beans = new BeansAuthorizationRegistration();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(true);
        session.setAttribute("Logged in", false);


        if (req.getParameter("signUp") != null) {
            if (req.getParameter("email") != null &&
                    !req.getParameter("email").equals("") &&
                    req.getParameter("password") != null &&
                    !req.getParameter("password").equals("")) {
                if (!beans.testUser(req.getParameter("email"), req.getParameter("password")) &&
                        !beans.testEmailExist(req.getParameter("email")) &&
                        beans.testEmailFormat(req.getParameter("email")) &&
                        beans.testPasswordLength(req.getParameter("password"))) {
                    beans.createNewUser(req.getParameter("email"), req.getParameter("password"));
                    resp.sendRedirect("authorization");
                } else {
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put(req.getParameter("email"), req.getParameter("password"));
                    req.setAttribute("userMap", userMap);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/sign_up_error.jsp");
                    dispatcher.forward(req, resp);
                }
            }
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/sign_up.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
