package controller.student;

import beans.group.BeansGroupConfiguration;
import beans.student.BeansStudentConfiguration;

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
 * Created by Dimitry on 16.04.17.
 */
@WebServlet(
        name = "Student Configuration",
        description = "This is student configuration servlet",
        urlPatterns = {"/student_configuration"}
)
public class StudentConfiguration extends HttpServlet {
    //    BeansGroupConfiguration beans = new BeansGroupConfiguration();
    BeansStudentConfiguration beans = new BeansStudentConfiguration();

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
            if (req.getParameter("student_config") != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                dispatcher.forward(req, resp);
//
//
//
//
//                Education form
            } else if (req.getParameter("subgroup_create") != null) {
                req.setAttribute("createSubgroup", "createSubgroup");
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("subgroup_create_option") != null) {
                if (req.getParameter("subgroup") != null && !req.getParameter("subgroup").equals("")) {
                    beans.createNewSubgroup(req.getParameter("subgroup"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("createSubgroup", "createSubgroup");
                    req.setAttribute("wrongSubgroup", req.getParameter("subgroup"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }

            } else if (req.getParameter("subgroup_update") != null) {
                req.setAttribute("updateSubgroup", "updateSubgroup");
                req.setAttribute("subgroupsList", beans.getSubgroupList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("subgroup_update_option") != null) {
                if (req.getParameter("update_subgroup") != null) {
                    req.setAttribute("updateSubgroup", "updateSubgroup");
                    req.setAttribute("updateSubgroupArea", "updateSubgroupArea");
                    req.setAttribute("subgroupToUpdate", beans.readOneSubgroup(Integer.parseInt(req.getParameter("update_subgroup"))));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("updateSubgroup", "updateSubgroup");
                    req.setAttribute("subgroupsList", beans.getSubgroupList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }

            } else if (req.getParameter("subgroup_update_save") != null) {
                if (req.getParameter("subgroup_title") != null && !req.getParameter("subgroup_title").equals("")) {
                    beans.updateSubgroup(Integer.parseInt(req.getParameter("subgroup_id")), req.getParameter("subgroup_title"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("updateSubgroup", "updateSubgroup");
                    req.setAttribute("updateSubgroupArea", "updateSubgroupArea");
                    req.setAttribute("updateSubgroupAreaError", "updateSubgroupAreaError");
                    req.setAttribute("subgroupTitleError", req.getParameter("subgroup_title"));
                    req.setAttribute("subgroupIdError", req.getParameter("subgroup_id"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("subgroup_delete") != null) {
                req.setAttribute("deleteSubgroup", "deleteSubgroup");
                req.setAttribute("subgroupsList", beans.getSubgroupList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("subgroup_delete_option") != null) {
                if (req.getParameter("delete_subgroup") != null) {
                    beans.deleteSubgroup(Integer.parseInt(req.getParameter("delete_subgroup")));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("deleteSubgroup", "deleteSubgroup");
                    req.setAttribute("subgroupsList", beans.getSubgroupList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }
//
//
//
//                 Qualification level
            } else if (req.getParameter("financing_create") != null) {
                req.setAttribute("createFinancing", "createFinancing");
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("financing_create_option") != null) {
                if (req.getParameter("financing") != null && !req.getParameter("financing").equals("")) {
                    beans.createNewFinancing(req.getParameter("financing"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("createFinancing", "createFinancing");
                    req.setAttribute("wrongFinancing", req.getParameter("financing"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("financing_update") != null) {
                req.setAttribute("updateFinancing", "updateFinancing");
                req.setAttribute("financingList", beans.getFinancingList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("financing_update_option") != null) {
                if(req.getParameter("update_financing") != null){
                    req.setAttribute("updateFinancing", "updateFinancing");
                    req.setAttribute("updateFinancingArea", "updateFinancingArea");
                    req.setAttribute("financingToUpdate", beans.readOneFinancing(Integer.parseInt(req.getParameter("update_financing"))));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }else{
                    req.setAttribute("updateFinancing", "updateFinancing");
                    req.setAttribute("financingList", beans.getFinancingList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }
//
            } else if (req.getParameter("financing_update_save") != null) {
                if (req.getParameter("financing_title") != null && !req.getParameter("financing_title").equals("")) {
                    beans.updateFinancing(Integer.parseInt(req.getParameter("financing_id")), req.getParameter("financing_title"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("updateFinancing", "updateFinancing");
                    req.setAttribute("updateFinancingArea", "updateFinancingArea");
                    req.setAttribute("updateFinancingAreaError", "updateFinancingAreaError");
                    req.setAttribute("financingTitleError", req.getParameter("financing_title"));
                    req.setAttribute("financingIdError", req.getParameter("financing_id"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("financing_delete") != null) {
                req.setAttribute("deleteFinancing", "deleteFinancing");
                req.setAttribute("financingList", beans.getFinancingList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("financing_delete_option") != null) {
                if(req.getParameter("delete_financing") !=null){
                    beans.deleteFinancing(Integer.parseInt(req.getParameter("delete_financing")));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                } else{
                    req.setAttribute("deleteFinancing", "deleteFinancing");
                    req.setAttribute("financingList", beans.getFinancingList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
                    dispatcher.forward(req, resp);
                }

            //
//
//
//                 Qualification level
//            } else if (req.getParameter("status_create") != null) {
//                req.setAttribute("createStatus", "createStatus");
//                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                dispatcher.forward(req, resp);
//            } else if (req.getParameter("status_create_option") != null) {
//                if (req.getParameter("status") != null && !req.getParameter("status").equals("")) {
//                    beans.createNewStatus(req.getParameter("status"));
//                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                    dispatcher.forward(req, resp);
//                } else {
//                    req.setAttribute("createStatus", "createStatus");
//                    req.setAttribute("wrongStatus", req.getParameter("status"));
//                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                    dispatcher.forward(req, resp);
//                }
//            } else if (req.getParameter("status_update") != null) {
//                req.setAttribute("updateStatus", "updateStatus");
//                req.setAttribute("groupStatusList", beans.getGroupStatusList());
//                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                dispatcher.forward(req, resp);
//            } else if (req.getParameter("status_update_option") != null) {
//                if(req.getParameter("update_status") != null){
//                    req.setAttribute("updateStatus", "updateStatus");
//                    req.setAttribute("updateStatusArea", "updateStatusArea");
//                    req.setAttribute("statusToUpdate", beans.readOneStatus(Integer.parseInt(req.getParameter("update_status"))));
//                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                    dispatcher.forward(req, resp);
//                } else {
//                    req.setAttribute("updateStatus", "updateStatus");
//                    req.setAttribute("groupStatusList", beans.getGroupStatusList());
//                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                    dispatcher.forward(req, resp);
//                }
//
//            } else if (req.getParameter("status_update_save") != null) {
//                if (req.getParameter("status_title") != null && !req.getParameter("status_title").equals("")) {
//                    beans.updateStatus(Integer.parseInt(req.getParameter("status_id")), req.getParameter("status_title"));
//                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                    dispatcher.forward(req, resp);
//                } else {
//                    req.setAttribute("updateStatus", "updateStatus");
//                    req.setAttribute("updateStatusArea", "updateStatusArea");
//                    req.setAttribute("updateStatusAreaError", "updateStatusAreaError");
//                    req.setAttribute("statusTitleError", req.getParameter("status_title"));
//                    req.setAttribute("statusIdError", req.getParameter("status_id"));
//                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                    dispatcher.forward(req, resp);
//                }
//            } else if (req.getParameter("status_delete") != null) {
//                req.setAttribute("deleteStatus", "deleteStatus");
//                req.setAttribute("groupStatusList", beans.getGroupStatusList());
//                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                dispatcher.forward(req, resp);
//            } else if (req.getParameter("status_delete_option") != null) {
//                if(req.getParameter("delete_status") != null){
//                    beans.deleteStatus(Integer.parseInt(req.getParameter("delete_status")));
//                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                    dispatcher.forward(req, resp);
//                } else{
//                    req.setAttribute("deleteStatus", "deleteStatus");
//                    req.setAttribute("groupStatusList", beans.getGroupStatusList());
//                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_config.jsp");
//                    dispatcher.forward(req, resp);
//                }
//
        } else if (req.getParameter("cancel") != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_config.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("student_list");
        }
    }
}
}
