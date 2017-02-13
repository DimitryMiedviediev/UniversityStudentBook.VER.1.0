package controller;

import model.Beans;
import model.Group;
import model.Speciality;

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

    Beans beans = new Beans();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        //Create variables with request parameters
//        String createSpeciality = req.getParameter("create_speciality");
//        String editSpeciality = req.getParameter("edit_speciality");
//        String deleteSpeciality = req.getParameter("delete_speciality");
//
//        String createGroupSpeciality = req.getParameter("cr_gr_spec");
//        String createGroupQualifications = req.getParameter("cr_gr_qual");
//        String createGroupEducForm = req.getParameter("cr_gr_form");
//        String createGroupNumber = req.getParameter("cr_gr_num");
//        String createGroupCourse = req.getParameter("cr_gr_cource");
//
//        String editGroup = req.getParameter("group_edit");
//        String deleteGroup = req.getParameter("group_delete");
//
//        System.out.println("If create cpec: " + createSpeciality);
//        System.out.println("If edit cpec: " + editSpeciality);
//        System.out.println("If delete cpec: " + deleteSpeciality);
//
//        System.out.println("If create group - speciality: " + createGroupSpeciality);
//        System.out.println("If create group - qualifications: " + createGroupQualifications);
//        System.out.println("If create group - education form: " + createGroupEducForm);
//        System.out.println("If create group - number: " + createGroupNumber);
//        System.out.println("If create group - course: " + createGroupCourse);
//
//        System.out.println("If edit group: " + editGroup);
//        System.out.println("If delete group: " + deleteGroup);
//
//        String btnCreateSpec = (req.getParameter("cr_new_spec"));
//        System.out.println("Button create speciality active: " + btnCreateSpec);
//        String btnEditSpec = (req.getParameter("edit_spec"));
//        System.out.println("Button edit speciality active: " + btnEditSpec);
//        String btnDelSpec = (req.getParameter("del_spec"));
//        System.out.println("Button delete speciality active: " + btnDelSpec);
//
//        String btnCreateGroup = (req.getParameter("btn_gr_create"));
//        System.out.println("Button group create active: " + btnCreateGroup);
//        String btnEditGroup = (req.getParameter("edit_group"));
//        System.out.println("Button group edit active: " + btnEditGroup);
//        String btnDelGroup = (req.getParameter("del_group"));
//        System.out.println("Button group delete active: " + btnDelGroup);
//
//        String btnEditGroupSubmit = (req.getParameter("edit_group_submit"));
//        System.out.println("Button group submit from editing active: " + btnEditGroupSubmit);
//        String btnEditSpecSubmit = (req.getParameter("edit_spec_submit"));
//
//        String specUpdate = (req.getParameter("spec_update"));
//        System.out.println("Speciality update : " + specUpdate);
//        System.out.println("Button speciality submit from editing active: " + btnEditSpecSubmit);
//        String btnCancel = (req.getParameter("cancel"));
//        System.out.println("Button cancel active: " + btnCancel);
//        System.out.println("///////////////////////////");

            //Create new specialities
        if(req.getParameter("cr_new_spec") != null){
            beans.createNewSpec(req.getParameter("create_speciality"));
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
            dispatcher.forward(req, resp);

            //Edit speciality
        } else if (req.getParameter("edit_spec") != null){
            req.setAttribute("oneSpec", beans.getOneSpec(req.getParameter("edit_speciality")));
            RequestDispatcher dispatcher = req.getRequestDispatcher("spec_edit.jsp");
            dispatcher.forward(req, resp);

            //Delete speciality
        } else if (req.getParameter("del_spec") != null){
            beans.deleteSpec(req.getParameter("delete_speciality"));
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
            dispatcher.forward(req, resp);

            //Create new group
        } else if (req.getParameter("btn_gr_create") != null){
            beans.createNewGroup(req.getParameter("cr_gr_spec"), req.getParameter("cr_gr_num"), req.getParameter("cr_gr_form"),
                    req.getParameter("cr_gr_qual"), req.getParameter("cr_gr_cource"));
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
            dispatcher.forward(req, resp);

            //Egit group information
        } else if (req.getParameter("edit_group") != null){
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("group", beans.getOneGroup(req.getParameter("group_edit")));
            RequestDispatcher dispatcher = req.getRequestDispatcher("group_edit.jsp");
            dispatcher.forward(req, resp);

            //Delete group
        } else if (req.getParameter("del_group") != null){
            beans.deleteGroup(req.getParameter("group_delete"));
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
            dispatcher.forward(req, resp);

            //Forvard from editing groups
        } else if (req.getParameter("edit_group_submit") != null){
            beans.editGroup(req.getParameter("cr_gr_id"), req.getParameter("cr_gr_spec"), req.getParameter("cr_gr_num"),
                    req.getParameter("cr_gr_form"), req.getParameter("cr_gr_qual"), req.getParameter("cr_gr_cource"));
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
            dispatcher.forward(req, resp);

            //Forvard from editing specialities
        } else if (req.getParameter("edit_spec_submit") != null){
            beans.editSpec(req.getParameter("spec_id"), req.getParameter("spec_name"));
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
            dispatcher.forward(req, resp);

            //Forvard with cancel button
        } else if (req.getParameter("cancel") != null){
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
            dispatcher.forward(req, resp);

            //Defolt forvard to control panel
        } else {
            req.setAttribute("specList", beans.getSpecList());
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("control_panel.jsp");
            dispatcher.forward(req, resp);
        }

    }
}
