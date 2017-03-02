package controller;

import model.Beans;
import model.classes.Speciality;
import model.classes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Dimitry on 14.02.17.
 */
public class Students_list extends HttpServlet {
    Beans beans = new Beans();

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
            resp.sendRedirect("sign_in.jsp");
        } else {

            ArrayList<User> thisUser = (ArrayList<User>) session.getAttribute("UserInfo");
            String userSchema = thisUser.get(0).getUser_database();

            HashMap<String, Boolean> specList = beans.getSpecListForTitle(userSchema);
            for (String key : specList.keySet()) {
                if (req.getParameter("spec=" + key) != null) {
                    specList.put(key, true);
                }
            }
            req.setAttribute("specList", specList);

            HashMap<String, Boolean> statusList = beans.getStatusListForTitle(userSchema);
            for (String key : statusList.keySet()) {
                if (req.getParameter("stat=" + key) != null) {
                    statusList.put(key, true);
                }
            }
            req.setAttribute("statusList", statusList);

            HashMap<String, Boolean> qualList = beans.getQualificationListForTitle(userSchema);
            for (String key : qualList.keySet()) {
                if (req.getParameter("qual=" + key) != null) {
                    qualList.put(key, true);
                }
            }
            req.setAttribute("qualList", qualList);

            HashMap<String, Boolean> courseList = beans.getCourseListForTitle(userSchema);
            for (String key : courseList.keySet()) {
                if (req.getParameter("course=" + key) != null) {
                    courseList.put(key, true);
                }
            }
            req.setAttribute("courseList", courseList);

            HashMap<String, Boolean> groupList = beans.getGroupListForTitle(userSchema);
            for (String key : groupList.keySet()) {
                if (req.getParameter("gr=" + key) != null) {
                    groupList.put(key, true);
                }
            }
            req.setAttribute("groupList", groupList);

            HashMap<String, Boolean> subgroupList = beans.getSubgroupListForTitle(userSchema);
            for (String key : subgroupList.keySet()) {
                if (req.getParameter("sub=" + key) != null) {
                    subgroupList.put(key, true);
                }
            }
            req.setAttribute("subgroupList", subgroupList);

            HashMap<String, Boolean> educFormList = beans.getEducFormListForTitle(userSchema);
            for (String key : educFormList.keySet()) {
                if (req.getParameter("edform=" + key) != null) {
                    educFormList.put(key, true);
                }
            }
            req.setAttribute("educFormList", educFormList);

            HashMap<String, Boolean> financeList = beans.getFinanceListForTitle(userSchema);
            for (String key : financeList.keySet()) {
                if (req.getParameter("fin=" + key) != null) {
                    financeList.put(key, true);
                }
            }
            req.setAttribute("financeList", financeList);

            HashMap<String, Boolean> cityList = beans.getCityListForTitle(userSchema);
            for (String key : cityList.keySet()) {
                if (req.getParameter("city=" + key) != null) {
                    cityList.put(key, true);
                }
            }
            req.setAttribute("cityList", cityList);

            HashMap<String, Boolean> stateList = beans.getStateListForTitle(userSchema);
            for (String key : stateList.keySet()) {
                if (req.getParameter("state=" + key) != null) {
                    stateList.put(key, true);
                }
            }
            req.setAttribute("stateList", stateList);

            HashMap<String, String> cityParam = new HashMap<>();
            cityParam.put("city", req.getParameter("city"));
            req.setAttribute("cityParam", cityParam);

            HashMap<String, String> stateParam = new HashMap<>();
            stateParam.put("state", req.getParameter("state"));
            req.setAttribute("stateParam", stateParam);


            String query = "SELECT stud_id, stud_name, stud_surname, stud_lastname, status FROM students st";
            String modQuery = beans.retSortedQuery(query, specList, statusList, qualList, courseList,
                    educFormList, financeList, groupList, subgroupList, cityParam, stateParam);
            System.out.println(modQuery);

            req.setAttribute("studList", beans.getStudList(userSchema, modQuery));
            RequestDispatcher dispatcher = req.getRequestDispatcher("students_list.jsp");

            dispatcher.forward(req, resp);
        }
    }
}
