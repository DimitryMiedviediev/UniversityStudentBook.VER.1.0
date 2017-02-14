package controller;

import model.Beans;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//import static controller.Mainservlet.beans;

/**
 * Created by Dimitry on 13.02.17.
 */
public class Student_add extends HttpServlet {
    Beans beans = new Beans();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();


        System.out.println("/////////");
        String nameStud = req.getParameter("name_student");
        String surnameStud = req.getParameter("surname_student");
        String lastnameStud = req.getParameter("lastname_student");
        String dateEntry = req.getParameter("date_entry");
        String status = req.getParameter("status");
        String group = req.getParameter("group");
        String subGroup = req.getParameter("subgroup");
        String financing = req.getParameter("financing");
        String studBook = req.getParameter("stud_book");
        String dateBirth = req.getParameter("date_birth");
        String passpSerial = req.getParameter("passp_serial");
        String passpOffice = req.getParameter("passp_office");
        String dateRelease = req.getParameter("date_release");
        String identityCode = req.getParameter("identity_code");
        String studentHouse = req.getParameter("student_house");
        String studentStreet = req.getParameter("student_street");
        String studentCity = req.getParameter("student_city");
        String studentState = req.getParameter("student_state");
        String studentZip = req.getParameter("student_zip");
        String studentCountry = req.getParameter("student_country");
        String studentPhone1 = req.getParameter("student_phone_1");
        String studentPhone2 = req.getParameter("student_phone_2");

        String fatherName = req.getParameter("name_father");
        String fatherSurname = req.getParameter("surname_father");
        String fatherLastname = req.getParameter("lastname_father");
        String fatherPhone1 = req.getParameter("father_phone_1");
        String fatherPhone2 = req.getParameter("father_phone_2");
        String motherName = req.getParameter("name_mother");
        String motherSurname = req.getParameter("surname_mother");
        String motherLastname = req.getParameter("lastname_mother");
        String motherPhone1 = req.getParameter("mother_phone_1");
        String motherPhone2 = req.getParameter("mother_phone_2");
        String parentHouse = req.getParameter("parent_house");
        String parentStreet = req.getParameter("parent_street");
        String parentCity = req.getParameter("parent_city");
        String parentState = req.getParameter("parent_state");
        String parentZip = req.getParameter("parent_zip");
        String parentCountry = req.getParameter("parent_country");


        System.out.println("Student name: " + nameStud);
        System.out.println("Student surname: " + surnameStud);
        System.out.println("Student lastname" + lastnameStud);
        System.out.println("Date entry: " + dateEntry);
        System.out.println("Student status: " + status);
        System.out.println("Student group: " + group);
        System.out.println("Student subgroup: " + subGroup);
        System.out.println("Student financing: " + financing);
        System.out.println("Number studbook: " + studBook);
        System.out.println("Student dateBirth: " + dateBirth);
        System.out.println("PasspSrial: " + passpSerial);
        System.out.println("PasspOffice: " + passpOffice);
        System.out.println("Passp date release: " + dateRelease);
        System.out.println("Student identity code: " + identityCode);
        System.out.println("Student house: " + studentHouse);
        System.out.println("Student streeet: " + studentStreet);
        System.out.println("Student city: " + studentCity);
        System.out.println("Student state: " + studentState);
        System.out.println("Student zip: " + studentZip);
        System.out.println("Student country: " + studentCountry);
        System.out.println("Student phone1" + studentPhone1);
        System.out.println("Student phone2" + studentPhone2);

        System.out.println("Father name: " + fatherName);
        System.out.println("Father surname: " + fatherSurname);
        System.out.println("Father lastname: " + fatherLastname);
        System.out.println("Father phone1: " + fatherPhone1);
        System.out.println("Father phone2: " + fatherPhone2);
        System.out.println("Mother name: " + motherName);
        System.out.println("Mother surname: " + motherSurname);
        System.out.println("Mother lastname: " + motherLastname);
        System.out.println("Mother phone1: " + motherPhone1);
        System.out.println("Mother phone2: " + motherPhone2);
        System.out.println("Parent house: " + parentHouse);
        System.out.println("Parent street: " + parentStreet);
        System.out.println("Parent city: " + parentCity);
        System.out.println("Parent state: " + parentState);
        System.out.println("Parent zip: " + parentZip);
        System.out.println("Parent country: " + parentCountry);

        if (req.getParameter("clear_btn") != null) {
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("profile_add.jsp");
            dispatcher.forward(req, resp);
        } else if (req.getParameter("save_btn") != null) {
            beans.createNewStudent(nameStud, surnameStud, lastnameStud, dateEntry, status, group, subGroup, financing,
                    studBook, dateBirth, passpSerial, passpOffice, dateRelease, identityCode, studentHouse, studentStreet,
                    studentCity, studentState, studentZip, studentCountry, studentPhone1, studentPhone2, fatherName,
                    fatherSurname, fatherLastname, fatherPhone1, fatherPhone2, motherName, motherSurname, motherLastname,
                    motherPhone1, motherPhone2, parentHouse, parentStreet, parentCity, parentState, parentZip, parentCountry);
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("profile_add.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("groupList", beans.getGroupList());
            RequestDispatcher dispatcher = req.getRequestDispatcher("profile_add.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
