package controller.group;

import beans.group.BeansGroupInformation;
import entity.group.Group;
import entity.order.Order;

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
 * Created by Dimitry on 03.04.17.
 */
@WebServlet(
        name = "Group information",
        description = "This is group information servlet",
        urlPatterns = {"/group_create", "/group_info"}
)
public class GroupInformation extends HttpServlet {
    BeansGroupInformation beans = new BeansGroupInformation();

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
            if (req.getParameter("group_create") != null) {
                req.setAttribute("specialityList", beans.getSpecialityList());
                req.setAttribute("groupEducationFormList", beans.getGroupEducationFormList());
                req.setAttribute("groupQualificationLevelList", beans.getGroupQualificationLevelList());
                req.setAttribute("groupStatusList", beans.getGroupStatusList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_create.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("back_to_list") != null) {
                resp.sendRedirect("group_list");

            } else if (req.getParameter("clear_new_group") != null) {
                req.setAttribute("specialityList", beans.getSpecialityList());
                req.setAttribute("groupEducationFormList", beans.getGroupEducationFormList());
                req.setAttribute("groupQualificationLevelList", beans.getGroupQualificationLevelList());
                req.setAttribute("groupStatusList", beans.getGroupStatusList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_create.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("save_new_group") != null) {
                if (req.getParameter("groupNumber") != null &&
                        !req.getParameter("groupNumber").equals("") &&
                        req.getParameter("speciality") != null &&
                        req.getParameter("groupEducationForm") != null &&
                        req.getParameter("groupQualificationLevel") != null &&
                        req.getParameter("groupCourse") != null &&
                        !req.getParameter("groupCourse").equals("") &&
                        req.getParameter("groupStatus") != null
                        ) {

                    beans.createNewGroup(
                            Integer.parseInt(req.getParameter("groupNumber")),
                            Integer.parseInt(req.getParameter("speciality")),
                            Integer.parseInt(req.getParameter("groupEducationForm")),
                            Integer.parseInt(req.getParameter("groupQualificationLevel")),
                            Integer.parseInt(req.getParameter("groupCourse")),
                            Integer.parseInt(req.getParameter("groupStatus"))
                    );
                    resp.sendRedirect("group_list");

                } else {
                    req.setAttribute("error", "error");
                    req.setAttribute("groupNumber", req.getParameter("groupNumber"));
                    req.setAttribute("speciality", req.getParameter("speciality"));
                    req.setAttribute("groupEducationForm", req.getParameter("groupEducationForm"));
                    req.setAttribute("groupQualificationLevel", req.getParameter("groupQualificationLevel"));
                    req.setAttribute("groupCourse", req.getParameter("groupCourse"));
                    req.setAttribute("groupStatus", req.getParameter("groupStatus"));

                    req.setAttribute("specialityList", beans.getSpecialityList());
                    req.setAttribute("groupEducationFormList", beans.getGroupEducationFormList());
                    req.setAttribute("groupQualificationLevelList", beans.getGroupQualificationLevelList());
                    req.setAttribute("groupStatusList", beans.getGroupStatusList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_create.jsp");
                    dispatcher.forward(req, resp);
                }

            } else if (req.getParameter("delete_btn") != null) {
                beans.deleteGroup(Integer.parseInt(req.getParameter("group_id")));
                resp.sendRedirect("group_list");

            } else if (req.getParameter("back_to_info_btn") != null) {
                Group group = beans.getGroup(Integer.parseInt(req.getParameter("group_id")));
                req.setAttribute("groupObject", group);
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_info.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("update_btn") != null) {
                Group group = beans.getGroup(Integer.parseInt(req.getParameter("group_id")));
                req.setAttribute("groupObject", group);
                req.setAttribute("specialityList", beans.getSpecialityList());
                req.setAttribute("groupEducationFormList", beans.getGroupEducationFormList());
                req.setAttribute("groupQualificationLevelList", beans.getGroupQualificationLevelList());
                req.setAttribute("groupStatusList", beans.getGroupStatusList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_edit.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("save_update_btn") != null) {
                if (req.getParameter("groupNumber") != null &&
                        !req.getParameter("groupNumber").equals("") &&
                        req.getParameter("speciality") != null &&
                        req.getParameter("groupEducationForm") != null &&
                        req.getParameter("groupQualificationLevel") != null &&
                        req.getParameter("groupCourse") != null &&
                        !req.getParameter("groupCourse").equals("") &&
                        req.getParameter("groupStatus") != null
                        ) {
                    beans.updateGroup(
                            Integer.parseInt(req.getParameter("group_id")),
                            Integer.parseInt(req.getParameter("groupNumber")),
                            Integer.parseInt(req.getParameter("speciality")),
                            Integer.parseInt(req.getParameter("groupEducationForm")),
                            Integer.parseInt(req.getParameter("groupQualificationLevel")),
                            Integer.parseInt(req.getParameter("groupCourse")),
                            Integer.parseInt(req.getParameter("groupStatus"))
                    );
                    Group group = beans.getGroup(Integer.parseInt(req.getParameter("group_id")));
                    req.setAttribute("groupObject", group);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_info.jsp");
                    dispatcher.forward(req, resp);

                } else {
                    req.setAttribute("error", "error");
                    req.setAttribute("groupNumber", req.getParameter("groupNumber"));
                    if (req.getParameter("speciality") != null) {
                        req.setAttribute("speciality", Integer.parseInt(req.getParameter("speciality")));
                    } else{
                        req.setAttribute("speciality", null);
                    }
                    if (req.getParameter("groupEducationForm") != null) {
                        req.setAttribute("groupEducationForm", Integer.parseInt(req.getParameter("groupEducationForm")));
                    } else{
                        req.setAttribute("groupEducationForm", null);
                    }
                    if (req.getParameter("groupQualificationLevel") != null) {
                        req.setAttribute("groupQualificationLevel", Integer.parseInt(req.getParameter("groupQualificationLevel")));
                    } else{
                        req.setAttribute("groupQualificationLevel", null);
                    }
                    req.setAttribute("groupCourse", req.getParameter("groupCourse"));
                    if (req.getParameter("groupStatus") != null) {
                        req.setAttribute("groupStatus", Integer.parseInt(req.getParameter("groupStatus")));
                    } else{
                        req.setAttribute("groupStatus", null);
                    }

                    Group group = beans.getGroup(Integer.parseInt(req.getParameter("group_id")));
                    req.setAttribute("groupObject", group);
                    req.setAttribute("specialityList", beans.getSpecialityList());
                    req.setAttribute("groupEducationFormList", beans.getGroupEducationFormList());
                    req.setAttribute("groupQualificationLevelList", beans.getGroupQualificationLevelList());
                    req.setAttribute("groupStatusList", beans.getGroupStatusList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_edit.jsp");
                    dispatcher.forward(req, resp);
                }
            } else {
                if (req.getParameter("group_id") != null) {
                    Group group = beans.getGroup(Integer.parseInt(req.getParameter("group_id")));
                    req.setAttribute("groupObject", group);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/group_info.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    resp.sendRedirect("group_list");
                }

            }
        }
    }
}
