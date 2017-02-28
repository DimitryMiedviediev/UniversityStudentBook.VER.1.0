package controller;

import com.mysql.cj.api.Session;
import model.Beans;
import model.classes.Group;
import model.classes.Speciality;
import model.classes.Student;
import model.classes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dimitry on 25.02.17.
 */
public class Authorization extends HttpServlet {
    Beans beans = new Beans();

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

        if (req.getParameter("signIn") != null) {
            Boolean bool = beans.getUser(req.getParameter("email"), req.getParameter("password"));
            if (bool) {
                session.setAttribute("UserInfo", beans.getOneUser(req.getParameter("email"), req.getParameter("password")));
                session.setAttribute("Logged In", true);
                resp.sendRedirect("students_list");
            } else {
                HashMap<String, String> userMap = new HashMap<>();
                userMap.put(req.getParameter("email"), req.getParameter("password"));
                req.setAttribute("userMap", userMap);
                RequestDispatcher dispatcher = req.getRequestDispatcher("sign_in_error.jsp");
                dispatcher.forward(req, resp);
            }
        } else if (req.getParameter("signUp") != null) {
            if (beans.testEmail(req.getParameter("email")) && beans.testPassword(req.getParameter("password"))) {
                beans.createNewUser(req.getParameter("email"), req.getParameter("password"));
                RequestDispatcher dispatcher = req.getRequestDispatcher("sign_in.jsp");
                dispatcher.forward(req, resp);
            } else {
                HashMap<String, String> userMap = new HashMap<>();
                userMap.put(req.getParameter("email"), req.getParameter("password"));
                req.setAttribute("userMap", userMap);

                RequestDispatcher dispatcher = req.getRequestDispatcher("sign_up_error.jsp");
                dispatcher.forward(req, resp);
            }
        } else if (req.getParameter("exit") != null) {
            session = req.getSession();
            session.invalidate();
            resp.sendRedirect("sign_in.jsp");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("sign_in.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
