package controller;

import model.Beans;
import model.SortParams;
import model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ControlJSP extends HttpServlet {
    static Beans beans = new Beans();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Create variables with request parameters
        String mechanics = req.getParameter("mechanics");
        String engineers = req.getParameter("engineers");
        String archive = req.getParameter("archive");
        String bachelor = req.getParameter("bachelor");
        String specialist = req.getParameter("specialist");
        String master = req.getParameter("master");
        String course1 = req.getParameter("1 course");
        String course2 = req.getParameter("2 course");
        String course3 = req.getParameter("3 course");
        String course4 = req.getParameter("4 course");
        String course5 = req.getParameter("5 course");
        String course6 = req.getParameter("6 course");
        String group = req.getParameter("group");
        String government = req.getParameter("government");
        String commercial = req.getParameter("commercial");
        String full_time = req.getParameter("full_time");
        String distance = req.getParameter("distance");
        String state = req.getParameter("state");
        String department = req.getParameter("department");
        String military = req.getParameter("military");
        String orphan = req.getParameter("orphan");
        String disabled = req.getParameter("disabled");

        // Created ArrayList with parameters of sort
        SortParams sortParams = new SortParams(mechanics, engineers, archive, bachelor,
                specialist, master, course1, course2, course3, course4, course5,
                course6, group, government, commercial, full_time, distance, state,
                department, military, orphan, disabled);
        ArrayList<SortParams> params = new ArrayList<>();
        params.add(sortParams);
        req.setAttribute("paramsList", params);

        //Create ArrayList of students using SQLquery and Requested parameters of sort
        String query = "SELECT stud_name, stud_surname, stud_lastname, stud_status FROM students stud INNER JOIN unfiles un ON stud.stud_id = un.stud_id";

        System.out.println(query);

        query = beans.addQueryPart(query, "un.qual_level", bachelor);
        query = beans.addQueryPart(query, "un.qual_level", specialist);
        query = beans.addQueryPart(query, "un.qual_level", master);
        query = beans.addQueryPart(query, "stud.stud_status", archive);

        System.out.println(query);
        ArrayList<Student> storage = beans.getStudList(query);
        req.setAttribute("studList", storage);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/main_page.jsp");
        dispatcher.forward(req, resp);

    }
}
