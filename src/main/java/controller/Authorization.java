package controller;

import beans.BeansAuthorizationRegistration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(
        name = "Authorization",
        description = "This is authorization servlet",
        urlPatterns = "/authorization"
)
public class Authorization extends HttpServlet {
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
        Boolean b = (Boolean) session.getAttribute("Logged In");
        System.out.println(b);
        if (b != null && b) {
            resp.sendRedirect("student_list");
        } else {
            if (req.getParameter("signIn") != null) {
                if (beans.testUser(req.getParameter("email"), req.getParameter("password")) &&
                        beans.testEmailFormat(req.getParameter("email")) &&
                        beans.testPasswordLength(req.getParameter("password"))) {
                    session.setAttribute("UserInfo", beans.getUserInfo(req.getParameter("email"), req.getParameter("password")));
                    session.setAttribute("Logged In", true);
                    resp.sendRedirect("student_list");
                } else {
                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put(req.getParameter("email"), req.getParameter("password"));
                    req.setAttribute("userMap", userMap);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/sign_in_error.jsp");
                    dispatcher.forward(req, resp);
                }

            } else if (req.getParameter("exit") != null) {
                session.invalidate();
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/sign_in.jsp");
                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/sign_in.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
