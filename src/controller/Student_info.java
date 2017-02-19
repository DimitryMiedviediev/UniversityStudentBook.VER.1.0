package controller;

import model.Beans;
import model.classes.Group;
import model.classes.Speciality;
import model.classes.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Dimitry on 14.02.17.
 */
public class Student_info extends HttpServlet {
    Beans beans = new Beans();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        if (req.getParameter("edit_btn") != null) {

            ArrayList<Student> studentInfo = beans.getOneStudent(req.getParameter("stud_id"), true);
            req.setAttribute("studentInfo", studentInfo);

            ArrayList<Group> groupInfo = beans.getOneStudentGroup(req.getParameter("stud_id"));
            req.setAttribute("groupInfo", groupInfo);

            ArrayList<Speciality> specInfo = beans.getOneStudentSpec(req.getParameter("stud_id"));
            req.setAttribute("specInfo", specInfo);

            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("profile_edit.jsp");
            dispatcher.forward(req, resp);

        } else if (req.getParameter("delete_btn") != null) {

            String id = req.getParameter("stud_id");
            System.out.println("Delete student with iD = " + id);
            beans.deleteStudent(id);

            RequestDispatcher dispatcher = req.getRequestDispatcher("students_list");
            dispatcher.forward(req, resp);

        } else if (req.getParameter("save_btn") != null) {

            beans.editStudent(req.getParameter("stud_id"), req.getParameter("name_student"), req.getParameter("surname_student"),
                    req.getParameter("lastname_student"), req.getParameter("date_entry"), req.getParameter("status"),
                    req.getParameter("group"), req.getParameter("subgroup"), req.getParameter("financing"),
                    req.getParameter("stud_book"), req.getParameter("date_birth"), req.getParameter("passp_serial"),
                    req.getParameter("passp_office"), req.getParameter("date_release"), req.getParameter("identity_code"),
                    req.getParameter("student_house"), req.getParameter("student_street"), req.getParameter("student_city"),
                    req.getParameter("student_state"), req.getParameter("student_zip"), req.getParameter("student_country"),
                    req.getParameter("student_phone_1"), req.getParameter("student_phone_2"), req.getParameter("name_father"),
                    req.getParameter("surname_father"), req.getParameter("lastname_father"), req.getParameter("father_phone_1"),
                    req.getParameter("father_phone_2"), req.getParameter("name_mother"), req.getParameter("surname_mother"),
                    req.getParameter("lastname_mother"), req.getParameter("mother_phone_1"), req.getParameter("mother_phone_2"),
                    req.getParameter("parent_house"), req.getParameter("parent_street"), req.getParameter("parent_city"),
                    req.getParameter("parent_state"), req.getParameter("parent_zip"), req.getParameter("parent_country"));


            ArrayList<Student> studentInfo = beans.getOneStudent(req.getParameter("stud_id"), false);
            req.setAttribute("studentInfo", studentInfo);

            ArrayList<Group> groupInfo = beans.getOneStudentGroup(req.getParameter("stud_id"));
            req.setAttribute("groupInfo", groupInfo);

            ArrayList<Speciality> specInfo = beans.getOneStudentSpec(req.getParameter("stud_id"));
            req.setAttribute("specInfo", specInfo);

            RequestDispatcher dispatcher = req.getRequestDispatcher("profile_info.jsp");
            dispatcher.forward(req, resp);

        } else {

            ArrayList<Student> studentInfo = beans.getOneStudent(req.getParameter("stud_id"), false);
            req.setAttribute("studentInfo", studentInfo);

            ArrayList<Group> groupInfo = beans.getOneStudentGroup(req.getParameter("stud_id"));
            req.setAttribute("groupInfo", groupInfo);

            ArrayList<Speciality> specInfo = beans.getOneStudentSpec(req.getParameter("stud_id"));
            req.setAttribute("specInfo", specInfo);

            RequestDispatcher dispatcher = req.getRequestDispatcher("profile_info.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
