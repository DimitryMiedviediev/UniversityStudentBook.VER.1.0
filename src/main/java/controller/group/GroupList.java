package controller.group;

import beans.group.BeansGroupList;

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
 * Created by Dimitry on 19.03.17.
 */
@WebServlet(
        name = "Group list",
        description = "This is group list servlet",
        urlPatterns = "/group_list"
)
public class GroupList extends HttpServlet {
    BeansGroupList beans = new BeansGroupList();

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
            if (req.getParameter("group_sort") != null) {
                HashMap<String, Boolean> groupSpecialityList = beans.getGroupSpecialityListForTitle();
                for (String key : groupSpecialityList.keySet()) {
                    if (req.getParameter("groupSpec=" + key) != null) {
                        groupSpecialityList.put(key, true);
                    }
                }
                req.setAttribute("groupSpecialityList", groupSpecialityList);

                HashMap<String, Boolean> groupEducationFormList = beans.getGroupEducationFormListForTitle();
                for (String key : groupEducationFormList.keySet()) {
                    if (req.getParameter("groupEducForm=" + key) != null) {
                        groupEducationFormList.put(key, true);
                    }
                }
                req.setAttribute("groupEducationFormList", groupEducationFormList);

                HashMap<String, Boolean> groupQualificationLevelList = beans.getGroupQualificationLevelListForTitle();
                for (String key : groupQualificationLevelList.keySet()) {
                    if (req.getParameter("groupQualLevel=" + key) != null) {
                        groupQualificationLevelList.put(key, true);
                    }
                }
                req.setAttribute("groupQualificationLevelList", groupQualificationLevelList);

                HashMap<Integer, Boolean> groupNumberList = beans.getGroupNumbersListForTitle();
                for (Integer key : groupNumberList.keySet()) {
                    if (req.getParameter("groupNumber=" + key) != null) {
                        groupNumberList.put(key, true);
                    }
                }
                req.setAttribute("groupNumberList", groupNumberList);

                HashMap<Integer, Boolean> groupCourseList = beans.getGroupCourseListForTitle();
                for (Integer key : groupCourseList.keySet()) {
                    if (req.getParameter("groupCourse=" + key) != null) {
                        groupCourseList.put(key, true);
                    }
                }
                req.setAttribute("groupCourseList", groupCourseList);

                HashMap<String, Boolean> groupStatusList = beans.getGroupStatusListForTitle();
                for (String key : groupStatusList.keySet()) {
                    if (req.getParameter("groupStatus=" + key) != null) {
                        groupStatusList.put(key, true);
                    }
                }
                req.setAttribute("groupStatusList", groupStatusList);


                req.setAttribute("groupList", beans.getGroupList(groupSpecialityList, groupEducationFormList, groupQualificationLevelList, groupNumberList, groupCourseList, groupStatusList));

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/list/group_list.jsp");
                dispatcher.forward(req, resp);
            } else if(req.getParameter("group_create") != null){
                resp.sendRedirect("group_create");
            } else {
                HashMap<String, Boolean> groupSpecialityList = beans.getGroupSpecialityListForTitle();
                req.setAttribute("groupSpecialityList", groupSpecialityList);

                HashMap<String, Boolean> groupEducationFormList = beans.getGroupEducationFormListForTitle();
                req.setAttribute("groupEducationFormList", groupEducationFormList);

                HashMap<String, Boolean> groupQualificationLevelList = beans.getGroupQualificationLevelListForTitle();
                req.setAttribute("groupQualificationLevelList", groupQualificationLevelList);

                HashMap<Integer, Boolean> groupNumberList = beans.getGroupNumbersListForTitle();
                req.setAttribute("groupNumberList", groupNumberList);

                HashMap<Integer, Boolean> groupCourseList = beans.getGroupCourseListForTitle();
                req.setAttribute("groupCourseList", groupCourseList);

                HashMap<String, Boolean> groupStatusList = beans.getGroupStatusListForTitle();
                req.setAttribute("groupStatusList", groupStatusList);

                req.setAttribute("groupList", beans.getGroupList(groupSpecialityList, groupEducationFormList, groupQualificationLevelList, groupNumberList, groupCourseList, groupStatusList));

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/group/list/group_list.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
