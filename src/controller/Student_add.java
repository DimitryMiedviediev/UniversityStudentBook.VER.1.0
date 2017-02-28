package controller;

import model.Beans;
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

//import static controller.Mainservlet.beans;

/**
 * Created by Dimitry on 13.02.17.
 */
public class Student_add extends HttpServlet {
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
        Boolean b = (Boolean) session.getAttribute("Logged In");
        if (b == null || !b) {
            resp.sendRedirect("sign_in.jsp");
        } else {

            ArrayList<User> thisUser = (ArrayList<User>) session.getAttribute("UserInfo");
            String userSchema = thisUser.get(0).getUser_database();

            if (req.getParameter("clear_btn") != null) {
                req.setAttribute("groupList", beans.getGroupList(userSchema));
                RequestDispatcher dispatcher = req.getRequestDispatcher("profile_add.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("save_btn") != null) {
                beans.createNewStudent(userSchema, req.getParameter("name_student"), req.getParameter("surname_student"),
                        req.getParameter("lastname_student"), req.getParameter("date_entry"),
                        req.getParameter("status"), req.getParameter("group"), req.getParameter("subgroup"),
                        req.getParameter("financing"), req.getParameter("stud_book"), req.getParameter("date_birth"),
                        req.getParameter("passp_serial"), req.getParameter("passp_office"), req.getParameter("date_release"),
                        req.getParameter("identity_code"), req.getParameter("student_house"), req.getParameter("student_street"),
                        req.getParameter("student_city"), req.getParameter("student_state"), req.getParameter("student_zip"),
                        req.getParameter("student_country"), req.getParameter("student_phone_1"), req.getParameter("student_phone_2"),
                        req.getParameter("name_father"), req.getParameter("surname_father"), req.getParameter("lastname_father"),
                        req.getParameter("father_phone_1"), req.getParameter("father_phone_2"), req.getParameter("name_mother"),
                        req.getParameter("surname_mother"), req.getParameter("lastname_mother"), req.getParameter("mother_phone_1"),
                        req.getParameter("mother_phone_2"), req.getParameter("parent_house"), req.getParameter("parent_street"),
                        req.getParameter("parent_city"), req.getParameter("parent_state"), req.getParameter("parent_zip"),
                        req.getParameter("parent_country"));
                req.setAttribute("groupList", beans.getGroupList(userSchema));
                RequestDispatcher dispatcher = req.getRequestDispatcher("profile_add.jsp");
                dispatcher.forward(req, resp);
            } else {
                req.setAttribute("groupList", beans.getGroupList(userSchema));
                RequestDispatcher dispatcher = req.getRequestDispatcher("profile_add.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
