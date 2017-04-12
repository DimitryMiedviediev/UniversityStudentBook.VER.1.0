package controller.student;

import beans.order.BeansOrderInformation;
import beans.student.BeansStudentInformation;
import entity.order.Order;
import entity.student.Student;

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
        name = "Student information",
        description = "This is student information servlet",
        urlPatterns = {"/student_create", "/student_info"}
)
public class StudentInformation extends HttpServlet {
    BeansStudentInformation beans = new BeansStudentInformation();

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
            if (req.getParameter("student_create") != null) {
                req.setAttribute("studentStatusList", beans.getStudentStatusList());
                req.setAttribute("studentFinancingList", beans.getStudentFinancingList());
                req.setAttribute("groupList", beans.getGroupList());
//                req.setAttribute("orderList", beans.getOrderList());
                req.setAttribute("studentSubgroupList", beans.getSubgroupList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_create.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("back_to_list") != null) {
                resp.sendRedirect("student_list");
//
            } else if (req.getParameter("clear_new_student") != null) {
                req.setAttribute("studentStatusList", beans.getStudentStatusList());
                req.setAttribute("studentFinancingList", beans.getStudentFinancingList());
                req.setAttribute("groupList", beans.getGroupList());
//                req.setAttribute("orderList", beans.getOrderList());
                req.setAttribute("studentSubgroupList", beans.getSubgroupList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_create.jsp");
                dispatcher.forward(req, resp);
//
            } else if (req.getParameter("save_new_student") != null) {
                if (req.getParameter("studentName") != null &&
                        !req.getParameter("studentName").equals("") &&
                        req.getParameter("studentSurname") != null &&
                        !req.getParameter("studentSurname").equals("") &&
                        req.getParameter("studentPatronimic") != null &&
                        !req.getParameter("studentPatronimic").equals("") &&
                        req.getParameter("groupId") != null &&
//                        req.getParameter("orderId") != null &&
                        req.getParameter("studentSubgroupId") != null &&
                        req.getParameter("studentFinancingId") != null &&
                        req.getParameter("studentBook") != null &&
                        req.getParameter("studentBook") != null) {

                    beans.createNewStudent(
                            req.getParameter("studentName"),
                            req.getParameter("studentSurname"),
                            req.getParameter("studentPatronimic"),
                            req.getParameter("studentPhone1"),
                            req.getParameter("studentPhone2"),
                            req.getParameter("studentEmail"),
                            Integer.parseInt(req.getParameter("groupId")),
                            Integer.parseInt(req.getParameter("studentSubgroupId")),
                            Integer.parseInt(req.getParameter("studentFinancingId")),
                            req.getParameter("studentBook"),
                            req.getParameter("studentBirthDate"),
                            req.getParameter("studentPassportNumber"),
                            req.getParameter("studentPassportOffice"),
                            req.getParameter("studentPassportReleaseDate"),
                            req.getParameter("studentIdentityCode"),
                            req.getParameter("studentHouse"),
                            req.getParameter("studentStreet"),
                            req.getParameter("studentCity"),
                            req.getParameter("studentState"),
                            req.getParameter("studentZip"),
                            req.getParameter("studentCountry"),
                            req.getParameter("fatherName"),
                            req.getParameter("fatherSurname"),
                            req.getParameter("fatherPatronimic"),
                            req.getParameter("fatherPhone1"),
                            req.getParameter("fatherPhone2"),
                            req.getParameter("fatherEmail"),
                            req.getParameter("motherName"),
                            req.getParameter("motherSurname"),
                            req.getParameter("motherPatronimic"),
                            req.getParameter("motherPhone1"),
                            req.getParameter("motherPhone2"),
                            req.getParameter("motherEmail"),
                            req.getParameter("parentHouse"),
                            req.getParameter("parentStreet"),
                            req.getParameter("parentCity"),
                            req.getParameter("parentState"),
                            req.getParameter("parentZip"),
                            req.getParameter("parentCountry")
                    );

                    resp.sendRedirect("student_list");

                } else {
                    req.setAttribute("error", "error");
                    req.setAttribute("studentStatusList", beans.getStudentStatusList());
                    req.setAttribute("studentFinancingList", beans.getStudentFinancingList());
                    req.setAttribute("groupList", beans.getGroupList());
//                    req.setAttribute("orderList", beans.getOrderList());
                    req.setAttribute("studentSubgroupList", beans.getSubgroupList());

                    req.setAttribute("studentName", req.getParameter("studentName"));
                    req.setAttribute("studentSurname", req.getParameter("studentSurname"));
                    req.setAttribute("studentPatronimic", req.getParameter("studentPatronimic"));
                    req.setAttribute("studentPhone1", req.getParameter("studentPhone1"));
                    req.setAttribute("studentPhone2", req.getParameter("studentPhone2"));
                    req.setAttribute("studentEmail", req.getParameter("studentEmail"));

                    req.setAttribute("groupId", req.getParameter("groupId"));
//                    req.setAttribute("orderId", req.getParameter("orderId"));
                    req.setAttribute("studentSubgroupId", req.getParameter("studentSubgroupId"));
                    req.setAttribute("studentFinancingId", req.getParameter("studentFinancingId"));
                    req.setAttribute("studentBook", req.getParameter("studentBook"));
                    req.setAttribute("studentBirthDate", req.getParameter("studentBirthDate"));
                    req.setAttribute("studentPassportNumber", req.getParameter("studentPassportNumber"));
                    req.setAttribute("studentPassportOffice", req.getParameter("studentPassportOffice"));
                    req.setAttribute("studentPassportReleaseDate", req.getParameter("studentPassportReleaseDate"));
                    req.setAttribute("studentIdentityCode", req.getParameter("studentIdentityCode"));

                    req.setAttribute("studentAddress", req.getParameter("studentAddress"));
                    req.setAttribute("studentHouse", req.getParameter("studentHouse"));
                    req.setAttribute("studentStreet", req.getParameter("studentStreet"));
                    req.setAttribute("studentCity", req.getParameter("studentCity"));
                    req.setAttribute("studentState", req.getParameter("studentState"));
                    req.setAttribute("studentZip", req.getParameter("studentZip"));
                    req.setAttribute("studentCountry", req.getParameter("studentCountry"));

                    req.setAttribute("fatherName", req.getParameter("fatherName"));
                    req.setAttribute("fatherSurname", req.getParameter("fatherSurname"));
                    req.setAttribute("fatherPatronimic", req.getParameter("fatherPatronimic"));
                    req.setAttribute("fatherPhone1", req.getParameter("fatherPhone1"));
                    req.setAttribute("fatherPhone2", req.getParameter("fatherPhone2"));
                    req.setAttribute("fatherEmail", req.getParameter("fatherEmail"));

                    req.setAttribute("motherName", req.getParameter("motherName"));
                    req.setAttribute("motherSurname", req.getParameter("motherSurname"));
                    req.setAttribute("motherPatronimic", req.getParameter("motherPatronimic"));
                    req.setAttribute("motherPhone1", req.getParameter("motherPhone1"));
                    req.setAttribute("motherPhone2", req.getParameter("motherPhone2"));
                    req.setAttribute("motherEmail", req.getParameter("motherEmail"));

                    req.setAttribute("parentAddress", req.getParameter("parentAddress"));
                    req.setAttribute("parentHouse", req.getParameter("parentHouse"));
                    req.setAttribute("parentStreet", req.getParameter("parentStreet"));
                    req.setAttribute("parentCity", req.getParameter("parentCity"));
                    req.setAttribute("parentState", req.getParameter("parentState"));
                    req.setAttribute("parentZip", req.getParameter("parentZip"));
                    req.setAttribute("parentCountry", req.getParameter("parentCountry"));

                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_create.jsp");
                    dispatcher.forward(req, resp);
                }

            } else if (req.getParameter("delete_btn") != null) {
                beans.deleteStudent(Integer.parseInt(req.getParameter("student_id")));
                resp.sendRedirect("student_list");

            } else if (req.getParameter("back_to_info_btn") != null) {
                Student student = beans.getStudent(Integer.parseInt(req.getParameter("student_id")));
                req.setAttribute("studentObject", student);
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_info.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("update_btn") != null) {
                Student student = beans.getStudent(Integer.parseInt(req.getParameter("student_id")));
                req.setAttribute("studentObject", student);
                req.setAttribute("studentStatusList", beans.getStudentStatusList());
                req.setAttribute("studentFinancingList", beans.getStudentFinancingList());
                req.setAttribute("groupList", beans.getGroupList());
//                req.setAttribute("orderList", beans.getOrderList());
                req.setAttribute("studentSubgroupList", beans.getSubgroupList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_edit.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("save_update_btn") != null) {
                if (req.getParameter("studentName") != null &&
                        !req.getParameter("studentName").equals("") &&
                        req.getParameter("studentSurname") != null &&
                        !req.getParameter("studentSurname").equals("") &&
                        req.getParameter("studentPatronimic") != null &&
                        !req.getParameter("studentPatronimic").equals("") &&
                        req.getParameter("groupId") != null &&
//                        req.getParameter("orderId") != null &&
                        req.getParameter("studentSubgroupId") != null &&
                        req.getParameter("studentFinancingId") != null &&
                        req.getParameter("studentBook") != null &&
                        req.getParameter("studentBook") != null) {

                    beans.updateStudent(
                            Integer.parseInt(req.getParameter("student_id")),
                            req.getParameter("studentName"),
                            req.getParameter("studentSurname"),
                            req.getParameter("studentPatronimic"),
                            req.getParameter("studentPhone1"),
                            req.getParameter("studentPhone2"),
                            req.getParameter("studentEmail"),
                            Integer.parseInt(req.getParameter("groupId")),
                            Integer.parseInt(req.getParameter("studentSubgroupId")),
                            Integer.parseInt(req.getParameter("studentFinancingId")),
                            req.getParameter("studentBook"),
                            req.getParameter("studentBirthDate"),
                            req.getParameter("studentPassportNumber"),
                            req.getParameter("studentPassportOffice"),
                            req.getParameter("studentPassportReleaseDate"),
                            req.getParameter("studentIdentityCode"),
                            req.getParameter("studentHouse"),
                            req.getParameter("studentStreet"),
                            req.getParameter("studentCity"),
                            req.getParameter("studentState"),
                            req.getParameter("studentZip"),
                            req.getParameter("studentCountry"),
                            req.getParameter("fatherName"),
                            req.getParameter("fatherSurname"),
                            req.getParameter("fatherPatronimic"),
                            req.getParameter("fatherPhone1"),
                            req.getParameter("fatherPhone2"),
                            req.getParameter("fatherEmail"),
                            req.getParameter("motherName"),
                            req.getParameter("motherSurname"),
                            req.getParameter("motherPatronimic"),
                            req.getParameter("motherPhone1"),
                            req.getParameter("motherPhone2"),
                            req.getParameter("motherEmail"),
                            req.getParameter("parentHouse"),
                            req.getParameter("parentStreet"),
                            req.getParameter("parentCity"),
                            req.getParameter("parentState"),
                            req.getParameter("parentZip"),
                            req.getParameter("parentCountry")
                    );

                    Student student = beans.getStudent(Integer.parseInt(req.getParameter("student_id")));
                    req.setAttribute("studentObject", student);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_info.jsp");
                    dispatcher.forward(req, resp);

                } else {
                    req.setAttribute("error", "error");
                    req.setAttribute("studentStatusList", beans.getStudentStatusList());
                    req.setAttribute("studentFinancingList", beans.getStudentFinancingList());
                    req.setAttribute("groupList", beans.getGroupList());
//                    req.setAttribute("orderList", beans.getOrderList());
                    req.setAttribute("studentSubgroupList", beans.getSubgroupList());

                    req.setAttribute("studentName", req.getParameter("studentName"));
                    req.setAttribute("studentSurname", req.getParameter("studentSurname"));
                    req.setAttribute("studentPatronimic", req.getParameter("studentPatronimic"));
                    req.setAttribute("studentPhone1", req.getParameter("studentPhone1"));
                    req.setAttribute("studentPhone2", req.getParameter("studentPhone2"));
                    req.setAttribute("studentEmail", req.getParameter("studentEmail"));

                    req.setAttribute("groupId", req.getParameter("groupId"));
//                    req.setAttribute("orderId", req.getParameter("orderId"));
                    req.setAttribute("studentSubgroupId", req.getParameter("studentSubgroupId"));
                    req.setAttribute("studentFinancingId", req.getParameter("studentFinancingId"));
                    req.setAttribute("studentBook", req.getParameter("studentBook"));
                    req.setAttribute("studentBirthDate", req.getParameter("studentBirthDate"));
                    req.setAttribute("studentPassportNumber", req.getParameter("studentPassportNumber"));
                    req.setAttribute("studentPassportOffice", req.getParameter("studentPassportOffice"));
                    req.setAttribute("studentPassportReleaseDate", req.getParameter("studentPassportReleaseDate"));
                    req.setAttribute("studentIdentityCode", req.getParameter("studentIdentityCode"));

                    req.setAttribute("studentAddress", req.getParameter("studentAddress"));
                    req.setAttribute("studentHouse", req.getParameter("studentHouse"));
                    req.setAttribute("studentStreet", req.getParameter("studentStreet"));
                    req.setAttribute("studentCity", req.getParameter("studentCity"));
                    req.setAttribute("studentState", req.getParameter("studentState"));
                    req.setAttribute("studentZip", req.getParameter("studentZip"));
                    req.setAttribute("studentCountry", req.getParameter("studentCountry"));

                    req.setAttribute("fatherName", req.getParameter("fatherName"));
                    req.setAttribute("fatherSurname", req.getParameter("fatherSurname"));
                    req.setAttribute("fatherPatronimic", req.getParameter("fatherPatronimic"));
                    req.setAttribute("fatherPhone1", req.getParameter("fatherPhone1"));
                    req.setAttribute("fatherPhone2", req.getParameter("fatherPhone2"));
                    req.setAttribute("fatherEmail", req.getParameter("fatherEmail"));

                    req.setAttribute("motherName", req.getParameter("motherName"));
                    req.setAttribute("motherSurname", req.getParameter("motherSurname"));
                    req.setAttribute("motherPatronimic", req.getParameter("motherPatronimic"));
                    req.setAttribute("motherPhone1", req.getParameter("motherPhone1"));
                    req.setAttribute("motherPhone2", req.getParameter("motherPhone2"));
                    req.setAttribute("motherEmail", req.getParameter("motherEmail"));

                    req.setAttribute("parentAddress", req.getParameter("parentAddress"));
                    req.setAttribute("parentHouse", req.getParameter("parentHouse"));
                    req.setAttribute("parentStreet", req.getParameter("parentStreet"));
                    req.setAttribute("parentCity", req.getParameter("parentCity"));
                    req.setAttribute("parentState", req.getParameter("parentState"));
                    req.setAttribute("parentZip", req.getParameter("parentZip"));
                    req.setAttribute("parentCountry", req.getParameter("parentCountry"));

                    Student student = beans.getStudent(Integer.parseInt(req.getParameter("student_id")));
                    req.setAttribute("studentObject", student);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_edit.jsp");
                    dispatcher.forward(req, resp);
                }


            } else {
                if (req.getParameter("student_id") != null) {
                    Student student = beans.getStudent(Integer.parseInt(req.getParameter("student_id")));
                    req.setAttribute("studentObject", student);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/student/student_info.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    resp.sendRedirect("student_list");
                }
            }
        }
    }
}
