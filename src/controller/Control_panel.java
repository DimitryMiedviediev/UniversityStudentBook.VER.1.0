package controller;

import model.Group;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dimitry on 12.02.17.
 */
public class Control_panel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Create variables with request parameters
        String createSpeciality = req.getParameter("create_speciality");
        String editSpeciality = req.getParameter("edit_speciality");
        String deleteSpeciality = req.getParameter("delete_speciality");

        String createGroupSpeciality = req.getParameter("cr_gr_spec");
        String createGroupQualifications = req.getParameter("cr_gr_qual");
        String createGroupEducForm = req.getParameter("cr_gr_form");
        String createGroupNumber = req.getParameter("cr_gr_num");
        String createGroupCourse = req.getParameter("cr_gr_cource");

        String editGroup = req.getParameter("group_edit");
        String deleteGroup = req.getParameter("group_delete");

        System.out.println("If create cpec: " + createSpeciality);
        System.out.println("If edit cpec: " + editSpeciality);
        System.out.println("If delete cpec: " + deleteSpeciality);

        System.out.println("If create group - speciality: " + createGroupSpeciality);
        System.out.println("If create group - qualifications: " + createGroupQualifications);
        System.out.println("If create group - education form: " + createGroupEducForm);
        System.out.println("If create group - number: " + createGroupNumber);
        System.out.println("If create group - course: " + createGroupCourse);

        System.out.println("If edit group: " + editGroup);
        System.out.println("If delete group: " + deleteGroup);
        System.out.println("///////////////////////////");

//        req.setAttribute("studList", storage);
        if (createSpeciality != null) {

        } else if (editGroup != null) {
            ArrayList<Group> storage = new ArrayList<>();

            storage.add(new Group("Engineers", "Master", "Distance", "A16.11", "2"));
            req.setAttribute("group", storage);
            RequestDispatcher dispatcher = req.getRequestDispatcher("group_edit.jsp");
            dispatcher.forward(req, resp);

        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
        dispatcher.forward(req, resp);

    }
}
