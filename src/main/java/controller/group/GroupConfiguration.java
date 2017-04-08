package controller.group;

import beans.group.BeansGroupConfiguration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dimitry on 08.04.17.
 */
@WebServlet(
        name = "Group configuration",
        description = "This is group configuration servlet",
        urlPatterns = {"/group_configuration"}
)
public class GroupConfiguration extends HttpServlet{
    BeansGroupConfiguration beans = new BeansGroupConfiguration();

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
            resp.sendRedirect("authorization");
        } else {
            if (req.getParameter("group_config") != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
//
//
//
//
//                Education form
            } else if (req.getParameter("education_form_create") != null) {
                req.setAttribute("createEducationForm", "createEducationForm");
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("education_form_create_option") != null) {
                if (req.getParameter("education_form") != null && !req.getParameter("education_form").equals("")) {
                    beans.createNewEducationForm(req.getParameter("education_form"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("createEducationForm", "createEducationForm");
                    req.setAttribute("wrongEducationForm", req.getParameter("education_form"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("education_form_update") != null) {
                req.setAttribute("updateEducationForm", "updateEducationForm");
                req.setAttribute("groupEducationFormsList", beans.getGroupEducationFormsList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("education_form_update_option") != null) {
                req.setAttribute("updateEducationForm", "updateEducationForm");
                req.setAttribute("updateEducationFormArea", "updateEducationFormArea");
                req.setAttribute("educationFormToUpdate", beans.readOneEducationForm(Integer.parseInt(req.getParameter("update_education_form"))));
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("education_form_update_save") != null) {
                if (req.getParameter("education_form_title") != null && !req.getParameter("education_form_title").equals("")) {
                    beans.updateEducationForm(Integer.parseInt(req.getParameter("education_form_id")), req.getParameter("education_form_title"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("updateEducationForm", "updateEducationForm");
                    req.setAttribute("updateEducationFormArea", "updateEducationFormArea");
                    req.setAttribute("updateEducationFormAreaError", "updateEducationFormAreaError");
                    req.setAttribute("educationFormTitleError", req.getParameter("education_form_title"));
                    req.setAttribute("educationFormIdError", req.getParameter("education_form_id"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("education_form_delete") != null) {
                req.setAttribute("deleteEducationForm", "deleteEducationForm");
                req.setAttribute("groupEducationFormsList", beans.getGroupEducationFormsList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("education_form_delete_option") != null) {
                beans.deleteEducationForm(Integer.parseInt(req.getParameter("delete_education_form")));
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
//
//
//
//                 Qualification level
            } else if (req.getParameter("qualification_level_create") != null) {
                req.setAttribute("createQualificationLevel", "createQualificationLevel");
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("qualification_level_create_option") != null) {
                if (req.getParameter("qualification_level") != null && !req.getParameter("qualification_level").equals("")) {
                    beans.createNewQualificationLevel(req.getParameter("qualification_level"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("createQualificationLevel", "createQualificationLevel");
                    req.setAttribute("wrongQualificationLevel", req.getParameter("qualification_level"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("qualification_level_update") != null) {
                req.setAttribute("updateQualificationLevel", "updateQualificationLevel");
                req.setAttribute("groupQualificationLevelList", beans.getGroupQualificationLevelsList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("qualification_level_update_option") != null) {
                req.setAttribute("updateQualificationLevel", "updateQualificationLevel");
                req.setAttribute("updateQualificationLevelArea", "updateQualificationLevelArea");
                req.setAttribute("qualificationLevelToUpdate", beans.readOneQualificationLevel(Integer.parseInt(req.getParameter("update_qualification_level"))));
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("qualification_level_update_save") != null) {
                if (req.getParameter("qualification_level_title") != null && !req.getParameter("qualification_level_title").equals("")) {
                    beans.updateQualificationLevel(Integer.parseInt(req.getParameter("qualification_level_id")), req.getParameter("qualification_level_title"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("updateQualificationLevel", "updateQualificationLevel");
                    req.setAttribute("updateQualificationLevelArea", "updateQualificationLevelArea");
                    req.setAttribute("updateQualificationLevelAreaError", "updateQualificationLevelAreaError");
                    req.setAttribute("qualificationLevelTitleError", req.getParameter("qualification_level_title"));
                    req.setAttribute("qualificationLevelIdError", req.getParameter("qualification_level_id"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("qualification_level_delete") != null) {
                req.setAttribute("deleteQualificationLevel", "deleteQualificationLevel");
                req.setAttribute("groupQualificationLevelList", beans.getGroupQualificationLevelsList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("qualification_level_delete_option") != null) {
                beans.deleteQualificationLevel(Integer.parseInt(req.getParameter("delete_qualification_level")));
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
                //
//
//
//                 Qualification level
            } else if (req.getParameter("status_create") != null) {
                req.setAttribute("createStatus", "createStatus");
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("status_create_option") != null) {
                if (req.getParameter("status") != null && !req.getParameter("status").equals("")) {
                    beans.createNewStatus(req.getParameter("status"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("createStatus", "createStatus");
                    req.setAttribute("wrongStatus", req.getParameter("status"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("status_update") != null) {
                req.setAttribute("updateStatus", "updateStatus");
                req.setAttribute("groupStatusList", beans.getGroupStatusList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("status_update_option") != null) {
                req.setAttribute("updateStatus", "updateStatus");
                req.setAttribute("updateStatusArea", "updateStatusArea");
                req.setAttribute("statusToUpdate", beans.readOneStatus(Integer.parseInt(req.getParameter("update_status"))));
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("status_update_save") != null) {
                if (req.getParameter("status_title") != null && !req.getParameter("status_title").equals("")) {
                    beans.updateStatus(Integer.parseInt(req.getParameter("status_id")), req.getParameter("status_title"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("updateStatus", "updateStatus");
                    req.setAttribute("updateStatusArea", "updateStatusArea");
                    req.setAttribute("updateStatusAreaError", "updateStatusAreaError");
                    req.setAttribute("statusTitleError", req.getParameter("status_title"));
                    req.setAttribute("statusIdError", req.getParameter("status_id"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("status_delete") != null) {
                req.setAttribute("deleteStatus", "deleteStatus");
                req.setAttribute("groupStatusList", beans.getGroupStatusList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("status_delete_option") != null) {
                beans.deleteStatus(Integer.parseInt(req.getParameter("delete_status")));
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);



            } else if (req.getParameter("cancel") != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("group_list");
            }
        }
    }
}
