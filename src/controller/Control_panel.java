package controller;

import model.Beans;
import model.filters.FormEncodingSetterFilter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dimitry on 12.02.17.
 */
public class Control_panel extends HttpServlet {

    Beans beans = new Beans();
    FormEncodingSetterFilter filter = new FormEncodingSetterFilter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

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
