package controller.student;

import beans.student.BeansStudentList;

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
 * Created by Dimitry on 17.03.17.
 */
@WebServlet(
        name = "Student list",
        description = "This is student list servlet",
        urlPatterns = "/student_list"
)
public class StudentList extends HttpServlet {
    BeansStudentList beans = new BeansStudentList();

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
            if (req.getParameter("student_sort") != null) {

                HashMap<String, Boolean> specList = beans.getSpecListForTitle();
                for (String key : specList.keySet()) {
                    if (req.getParameter("spec=" + key) != null) {
                        specList.put(key, true);
                    }
                }
                req.setAttribute("specList", specList);

                HashMap<String, Boolean> statusList = beans.getStatusListForTitle();
                for (String key : statusList.keySet()) {
                    if (req.getParameter("stat=" + key) != null) {
                        statusList.put(key, true);
                    }
                }
                req.setAttribute("statusList", statusList);

                HashMap<String, Boolean> qualList = beans.getQualificationListForTitle();
                for (String key : qualList.keySet()) {
                    if (req.getParameter("qual=" + key) != null) {
                        qualList.put(key, true);
                    }
                }
                req.setAttribute("qualList", qualList);

                HashMap<Integer, Boolean> courseList = beans.getCourseListForTitle();
                for (Integer key : courseList.keySet()) {
                    if (req.getParameter("course=" + key) != null) {
                        courseList.put(key, true);
                    }
                }
                req.setAttribute("courseList", courseList);

                HashMap<Integer, Boolean> groupList = beans.getGroupListForTitle();
                for (Integer key : groupList.keySet()) {
                    if (req.getParameter("gr=" + key) != null) {
                        groupList.put(key, true);
                    }
                }
                req.setAttribute("groupList", groupList);

                HashMap<String, Boolean> subgroupList = beans.getSubgroupListForTitle();
                for (String key : subgroupList.keySet()) {
                    if (req.getParameter("sub=" + key) != null) {
                        subgroupList.put(key, true);
                    }
                }
                req.setAttribute("subgroupList", subgroupList);

                HashMap<String, Boolean> educFormList = beans.getEducFormListForTitle();
                for (String key : educFormList.keySet()) {
                    if (req.getParameter("edform=" + key) != null) {
                        educFormList.put(key, true);
                    }
                }
                req.setAttribute("educFormList", educFormList);

                HashMap<String, Boolean> financeList = beans.getFinanceListForTitle();
                for (String key : financeList.keySet()) {
                    if (req.getParameter("fin=" + key) != null) {
                        financeList.put(key, true);
                    }
                }
                req.setAttribute("financeList", financeList);

                HashMap<String, Boolean> cityList = beans.getCityListForTitle();
                req.setAttribute("cityList", cityList);

                HashMap<String, Boolean> stateList = beans.getStateListForTitle();
                req.setAttribute("stateList", stateList);

                String cityParam = req.getParameter("city");
                req.setAttribute("cityParam", cityParam);

                String stateParam = req.getParameter("state");
                req.setAttribute("stateParam", stateParam);

                req.setAttribute("studList", beans.getStudentList(specList, statusList, qualList,
                        courseList, groupList, subgroupList, financeList, educFormList, cityParam, stateParam));

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/students_list.jsp");

                dispatcher.forward(req, resp);
            } else if (req.getParameter("student_create") != null){
                resp.sendRedirect("student_create");
            } else {
                HashMap<String, Boolean> specList = beans.getSpecListForTitle();
                req.setAttribute("specList", specList);

                HashMap<String, Boolean> statusList = beans.getStatusListForTitle();
                req.setAttribute("statusList", statusList);

                HashMap<String, Boolean> qualList = beans.getQualificationListForTitle();
                req.setAttribute("qualList", qualList);

                HashMap<Integer, Boolean> courseList = beans.getCourseListForTitle();
                req.setAttribute("courseList", courseList);

                HashMap<Integer, Boolean> groupList = beans.getGroupListForTitle();
                req.setAttribute("groupList", groupList);

                HashMap<String, Boolean> subgroupList = beans.getSubgroupListForTitle();
                req.setAttribute("subgroupList", subgroupList);

                HashMap<String, Boolean> educFormList = beans.getEducFormListForTitle();
                req.setAttribute("educFormList", educFormList);

                HashMap<String, Boolean> financeList = beans.getFinanceListForTitle();
                req.setAttribute("financeList", financeList);

                HashMap<String, Boolean> cityList = beans.getCityListForTitle();
                req.setAttribute("cityList", cityList);

                HashMap<String, Boolean> stateList = beans.getStateListForTitle();
                req.setAttribute("stateList", stateList);

                String cityParam = req.getParameter("city");
                req.setAttribute("cityParam", cityParam);

                String stateParam = req.getParameter("state");
                req.setAttribute("stateParam", stateParam);

                req.setAttribute("studList", beans.getStudentList(specList, statusList, qualList,
                        courseList, groupList, subgroupList, financeList, educFormList, null, stateParam));

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/students_list.jsp");

                dispatcher.forward(req, resp);
            }
        }
    }
}
