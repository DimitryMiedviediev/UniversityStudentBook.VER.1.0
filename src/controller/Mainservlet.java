//package controller;
//
//import model.Beans;
//import model.SortParams;
//import model.classes.Student;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class Mainservlet extends HttpServlet {
//    static Beans beans = new Beans();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //Create variables with request parameters
//        String mechanics = req.getParameter("mechanics");
//        String engineers = req.getParameter("engineers");
//        String educate = req.getParameter("educate");
//        String archive = req.getParameter("archive");
//        String bachelor = req.getParameter("bachelor");
//        String specialist = req.getParameter("specialist");
//        String master = req.getParameter("master");
//        String course1 = req.getParameter("1 course");
//        String course2 = req.getParameter("2 course");
//        String course3 = req.getParameter("3 course");
//        String course4 = req.getParameter("4 course");
//        String course5 = req.getParameter("5 course");
//        String course6 = req.getParameter("6 course");
//        String group = req.getParameter("group");
//        String subgroup = req.getParameter("subgroup");
//        String government = req.getParameter("government");
//        String commercial = req.getParameter("commercial");
//        String full_time = req.getParameter("full_time");
//        String distance = req.getParameter("distance");
//        String state = req.getParameter("state");
//        String department = req.getParameter("department");
//        String orphan = req.getParameter("orphan");
//        String disabled = req.getParameter("disabled");
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
//        //Create ArrayList of students using SQLquery and Requested parameters of sort
//        //ADD QUERY
//        String query = "SELECT stud_name, stud_surname, stud_lastname, stud_status FROM students stud";
//
//        System.out.println(query);
//        //ADD JOINS
//        query = beans.qJoins(query, params);
//
//        //ADD WHERE
//        //1.Specialization
//        //2.Type
//        query = beans.addQueryPart(query, "stud.stud_status", archive);
//        query = beans.addQueryPart(query, "stud.stud_status", educate);
//        //3.Qualification
//        query = beans.addQueryPart(query, "un.qual_level", bachelor);
//        query = beans.addQueryPart(query, "un.qual_level", specialist);
//        query = beans.addQueryPart(query, "un.qual_level", master);
//        //4.Courses
//        query = beans.addQueryPart(query, "un.course", course1);
//        query = beans.addQueryPart(query, "un.course", course2);
//        query = beans.addQueryPart(query, "un.course", course3);
//        query = beans.addQueryPart(query, "un.course", course4);
//        query = beans.addQueryPart(query, "un.course", course5);
//        query = beans.addQueryPart(query, "un.course", course6);
//        //5.Group
//        query = beans.addQueryPartText(query, "gr.group_num", group);
//        query = beans.addQueryPartText(query, "gr.subgroup_num", subgroup);
//        //6.Financing
//        query = beans.addQueryPart(query, "un.funding", government);
//        query = beans.addQueryPart(query, "un.funding", commercial);
//        //7.Education form
//        query = beans.addQueryPart(query, "un.educ_form", full_time);
//        query = beans.addQueryPart(query, "un.educ_form", distance);
//        //8.Birthplace
//        //9.Exemptions
//        query = beans.addQueryPart(query, "ex.exemption_type", orphan);
//        query = beans.addQueryPart(query, "ex.exemption_type", disabled);
//
//        //ADD BRACKETS or ')' '('
//        query = beans.qParamGroup(query);
//
//        System.out.println(query);
//        ArrayList<Student> storage = beans.getStudList(query);
//        req.setAttribute("studList", storage);
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/main_page.jsp");
//        dispatcher.forward(req, resp);
//    }
//
//}
