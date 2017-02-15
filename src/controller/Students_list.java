package controller;

import model.Beans;
import model.classes.Speciality;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        HashMap<String, Boolean> specList = beans.getSpecListForTitle();
        for (String key : specList.keySet()) {
            if (req.getParameter(key) != null) {
                specList.put(key, true);
            }
        }
        req.setAttribute("specList", specList);

        HashMap<String, Boolean> statusList = beans.getStatusListForTitle();
        for (String key : statusList.keySet()) {
            if (req.getParameter(key) != null) {
                statusList.put(key, true);
            }
        }
        req.setAttribute("statusList", statusList);

        HashMap<String, Boolean> qualList = beans.getQualificationListForTitle();
        for (String key : qualList.keySet()) {
            if (req.getParameter(key) != null) {
                qualList.put(key, true);
            }
        }
        req.setAttribute("qualList", qualList);

        HashMap<String, Boolean> courseList = beans.getCourseListForTitle();
        for (String key : courseList.keySet()) {
            if (req.getParameter(key) != null) {
                courseList.put(key, true);
            }
        }
        req.setAttribute("courseList", courseList);

        HashMap<String, Boolean> educFormList = beans.getEducFormListForTitle();
        for (String key : educFormList.keySet()) {
            if (req.getParameter(key) != null) {
                educFormList.put(key, true);
            }
        }
        req.setAttribute("educFormList", educFormList);

        HashMap<String, Boolean> financeList = beans.getFinanceListForTitle();
        for (String key : financeList.keySet()) {
            if (req.getParameter(key) != null) {
                financeList.put(key, true);
            }
        }
        req.setAttribute("financeList", financeList);

        HashMap<String, String> groupParam = new HashMap<>();
        groupParam.put("group", req.getParameter("group"));
        req.setAttribute("groupParam", groupParam);

        HashMap<String, String> subgroupParam = new HashMap<>();
        subgroupParam.put("subgroup", req.getParameter("subgroup"));
        req.setAttribute("subgroupParam", subgroupParam);

        HashMap<String, String> cityParam = new HashMap<>();
        cityParam.put("city", req.getParameter("city"));
        req.setAttribute("cityParam", cityParam);

        HashMap<String, String> stateParam = new HashMap<>();
        stateParam.put("state", req.getParameter("state"));
        req.setAttribute("stateParam", stateParam);


////        ParametersSort parametersSort = new ParametersSort(req.getParameter("educate"), req.getParameter("expelled"),
////                req.getParameter("academicVacation"), req.getParameter("graduate"), req.getParameter("bachelor"),
////                req.getParameter("specialist"), req.getParameter("master"), req.getParameter("course1"),
////                req.getParameter("course2"), req.getParameter("course3"), req.getParameter("course4"),
////                req.getParameter("course5"), req.getParameter("course6"), req.getParameter("group"),
////                req.getParameter("subgroup"), req.getParameter("government"), req.getParameter("commercial"),
////                req.getParameter("full_time"), req.getParameter("distance"), req.getParameter("state"),
////                req.getParameter("department"), req.getParameter("orphan"), req.getParameter("disabled"));
////        ArrayList<ParametersSort> parametersSorts = new ArrayList<>();
////        parametersSorts.add(parametersSort);
//
//        // Created ArrayList with parameters of sort
//        SortParams sortParams = new SortParams(mechanics, engineers, educate, archive, bachelor,
//                specialist, master, course1, course2, course3, course4, course5,
//                course6, group, subgroup, government, commercial, full_time, distance, state,
//                department, orphan, disabled);
//        ArrayList<SortParams> params = new ArrayList<>();
//        params.add(sortParams);
//        req.setAttribute("paramsList", params);
//
////        //Create ArrayList of students using SQLquery and Requested parameters of sort
////        //ADD QUERY
////        String query = "SELECT stud_name, stud_surname, stud_lastname, stud_status FROM students stud";
////
////        System.out.println(query);
////        //ADD JOINS
////        query = beans.qJoins(query, params);
////
////        //ADD WHERE
////        //1.Specialization
////        //2.Type
////        query = beans.addQueryPart(query, "stud.stud_status", archive);
////        query = beans.addQueryPart(query, "stud.stud_status", educate);
////        //3.Qualification
////        query = beans.addQueryPart(query, "un.qual_level", bachelor);
////        query = beans.addQueryPart(query, "un.qual_level", specialist);
////        query = beans.addQueryPart(query, "un.qual_level", master);
////        //4.Courses
////        query = beans.addQueryPart(query, "un.course", course1);
////        query = beans.addQueryPart(query, "un.course", course2);
////        query = beans.addQueryPart(query, "un.course", course3);
////        query = beans.addQueryPart(query, "un.course", course4);
////        query = beans.addQueryPart(query, "un.course", course5);
////        query = beans.addQueryPart(query, "un.course", course6);
////        //5.Group
////        query = beans.addQueryPartText(query, "gr.group_num", group);
////        query = beans.addQueryPartText(query, "gr.subgroup_num", subgroup);
////        //6.Financing
////        query = beans.addQueryPart(query, "un.funding", government);
////        query = beans.addQueryPart(query, "un.funding", commercial);
////        //7.Education form
////        query = beans.addQueryPart(query, "un.educ_form", full_time);
////        query = beans.addQueryPart(query, "un.educ_form", distance);
////        //8.Birthplace
////        //9.Exemptions
////        query = beans.addQueryPart(query, "ex.exemption_type", orphan);
////        query = beans.addQueryPart(query, "ex.exemption_type", disabled);
////
////        //ADD BRACKETS or ')' '('
////        query = beans.qParamGroup(query);
////
////        System.out.println(query);
////        ArrayList<Student> storage = beans.getStudList(query);
////        req.setAttribute("studList", storage);

        req.setAttribute("studList", beans.getStudList());
        RequestDispatcher dispatcher = req.getRequestDispatcher("students_list.jsp");

        dispatcher.forward(req, resp);
    }
}
