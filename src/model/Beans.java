package model;

import model.classes.Group;
import model.classes.Speciality;
import model.classes.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Dimitry on 07.02.17.
 */
public class Beans {
    GetTime time = new GetTime();
    String catalog = "studDBfin";

    private Connection startConnection() {
        Connection connection = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            String username = "";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useUnicode=yes&characterEncoding=UTF-8", "root", "");
            if (!connection.isClosed()) {
                System.out.println(time.getTime() + " --Connection to DB is active...");
            } else {
                System.err.println(time.getTime() + " --Cannot get DB access...");
                System.err.println(time.getTime() + " --Connection to DB is not active...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private void stopConnection(Connection connection) {
        try {
            connection.close();
            if (connection.isClosed()) {
                System.out.println(time.getTime() + " --Connection to DB is disabled...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Working with specialities in control panel and calling pages
     */
    public void createNewSpec(String newSpecName) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();
            String query = "INSERT INTO specialities (spec_name) VALUES (\"" + newSpecName + "\")";
            if (!newSpecName.equals("")) {
                statement.executeUpdate(query);
                System.out.println("Query create speciality: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public ArrayList<Speciality> getSpecList() {
        ArrayList<Speciality> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialities");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String id = resultSet.getString(meta.getColumnName(1));
                String speciality = resultSet.getString(meta.getColumnName(2));
                storage.add(new Speciality(id, speciality));
            }

            System.out.println("List of speciality:" + storage);

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public ArrayList<Speciality> getOneSpec(String nameSpec) {
        ArrayList<Speciality> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialities WHERE spec_name = \"" + nameSpec + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String id = resultSet.getString(meta.getColumnName(1));
                String speciality = resultSet.getString(meta.getColumnName(2));
                storage.add(new Speciality(id, speciality));
            }

            System.out.println("List of editspec:" + storage);

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public void editSpec(String specId, String newName) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();
            String query = "UPDATE specialities SET spec_name = \"" + newName + "\" WHERE id_spec = \"" + specId + "\"";
            if (!newName.equals("")) {
                statement.executeUpdate(query);
                System.out.println("Query create speciality: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public void deleteSpec(String nameSpec) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();
//            String query = "DELETE FROM specialities WHERE spec_name = \"" + nameSpec + "\")";
            String query = "DELETE FROM specialities WHERE spec_name = \"" + nameSpec + "\"";
            System.out.println("Query create speciality: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    /**
     * Working with groups in control panel and calling pages
     */
    public void createNewGroup(String speciality, String groupNum, String groupEducForm, String groupEducQual, String groupCourse) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();

            String specId = null;
            ResultSet resultSet = statement.executeQuery("SELECT id_spec FROM specialities WHERE spec_name = \"" + speciality + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();
            while (resultSet.next()) {
                specId = resultSet.getString(meta.getColumnName(1));
            }

            String query = "INSERT INTO groups (spec_id, group_num, group_educ_form, group_qual, group_course) " +
                    "VALUES (\"" + specId + "\" , \"" + groupNum + "\" , \"" + groupEducForm + "\" , \"" + groupEducQual + "\" , \"" + groupCourse + "\")";
            if ((!specId.equals("")) && (!groupNum.equals("")) && (!groupCourse.equals(""))) {
                statement.executeUpdate(query);
                System.out.println("Query create speciality: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public ArrayList<Group> getGroupList() {
        ArrayList<Group> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groups");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String groupId = resultSet.getString(meta.getColumnName(1));
                String specId = resultSet.getString(meta.getColumnName(2));
                String groupNum = resultSet.getString(meta.getColumnName(3));
                String groupEducForm = resultSet.getString(meta.getColumnName(4));
                String groupQual = resultSet.getString(meta.getColumnName(5));
                String groupCourse = resultSet.getString(meta.getColumnName(6));
                storage.add(new Group(groupId, specId, groupNum, groupEducForm, groupQual, groupCourse));
            }

            System.out.println("List of groups:" + storage);

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public ArrayList<Group> getOneGroup(String groupNum) {
        ArrayList<Group> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groups WHERE group_num = \"" + groupNum + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String groupId = resultSet.getString(meta.getColumnName(1));
                String specId = resultSet.getString(meta.getColumnName(2));
                String number = resultSet.getString(meta.getColumnName(3));
                String educationForm = resultSet.getString(meta.getColumnName(4));
                String qualificationLevel = resultSet.getString(meta.getColumnName(5));
                String course = resultSet.getString(meta.getColumnName(6));
                storage.add(new Group(groupId, specId, number, educationForm, qualificationLevel, course));
            }

            System.out.println("List of editGroup:" + storage);

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public void editGroup(String groupId, String specName, String groupNum, String groupEducForm, String groupEducQual, String groupCourse) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();
            String specId = null;
            ResultSet resultSet = statement.executeQuery("SELECT id_spec FROM specialities WHERE spec_name = \"" + specName + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();
            while (resultSet.next()) {
                specId = resultSet.getString(meta.getColumnName(1));
            }
            String query = "UPDATE groups SET spec_id = \"" + specId + "\", group_num = \"" + groupNum + "\", group_educ_form = \""
                    + groupEducForm + "\", group_qual = \"" + groupEducQual + "\", group_course = \"" + groupCourse + "\" WHERE group_id = \"" + groupId + "\"";
            if ((!groupNum.equals("")) && (!groupCourse.equals(""))) {
                statement.executeUpdate(query);
                System.out.println("Query edit group: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public void deleteGroup(String groupNum) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();
            String query = "DELETE FROM groups WHERE group_num = \"" + groupNum + "\"";
            System.out.println("Query delete group: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    /**
     * Working with students
     */
    public void createNewStudent(String studentName, String studentSurname, String studentLastname, String entryDate,
                                 String studentStatus, String studentGroup, String studentSubgroup, String studentFinancing,
                                 String studentBook, String dateBirth, String passpSerial, String passpOffice, String passpDateRelease,
                                 String identityCode, String studentHouse, String studentStreet, String studentCity,
                                 String studentState, String studentZip, String studentCountry, String studentPhone1,
                                 String studentPhone2, String fatherName, String fatherSurname, String fatherLastname,
                                 String fatherPhone1, String fatherPhone2, String motherName, String motherSurname, String motherLastname,
                                 String motherPhone1, String motherPhone2, String parentHouse, String parentStreet, String parentCity,
                                 String parentState, String parentZip, String parentCountry) {

        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();

            String queryStudent = createNewStudentQuery(studentName, studentSurname, studentLastname, entryDate,
                    studentStatus, studentGroup, studentSubgroup, studentFinancing, studentBook, dateBirth, passpSerial,
                    passpOffice, passpDateRelease, identityCode, studentHouse, studentStreet, studentCity, studentState,
                    studentZip, studentCountry, studentPhone1, studentPhone2, fatherName, fatherSurname, fatherLastname,
                    fatherPhone1, fatherPhone2, motherName, motherSurname, motherLastname, motherPhone1, motherPhone2,
                    parentHouse, parentStreet, parentCity, parentState, parentZip, parentCountry);
            System.out.println(queryStudent);
            if (studentName != null && studentSurname != null && studentLastname != null && studentStatus != null && studentGroup != null && studentFinancing != null && studentBook != null) {
                statement.executeUpdate(queryStudent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);

    }

    public String createNewStudentQuery(String studentName, String studentSurname, String studentLastname, String entryDate,
                                        String studentStatus, String studentGroup, String studentSubgroup, String studentFinancing,
                                        String studentBook, String dateBirth, String passpSerial, String passpOffice, String passpDateRelease,
                                        String identityCode, String studentHouse, String studentStreet, String studentCity,
                                        String studentState, String studentZip, String studentCountry, String studentPhone1,
                                        String studentPhone2, String fatherName, String fatherSurname, String fatherLastname,
                                        String fatherPhone1, String fatherPhone2, String motherName, String motherSurname, String motherLastname,
                                        String motherPhone1, String motherPhone2, String parentHouse, String parentStreet, String parentCity,
                                        String parentState, String parentZip, String parentCountry) {

        Connection conn = startConnection();
        String groupId = null;
        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();
            //Return groupID
            ResultSet resultSet1 = statement.executeQuery("SELECT group_id FROM groups WHERE group_num = \"" + studentGroup + "\"");
            ResultSetMetaData meta1 = resultSet1.getMetaData();
            while (resultSet1.next()) {
                groupId = resultSet1.getString(meta1.getColumnName(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn);


        String queryParam = "stud_name, stud_surname, stud_lastname, entry_date, status, group_id, financing, stud_book";
        String queryValues = "\"" + studentName + "\" , \"" + studentSurname + "\" , \"" + studentLastname +
                "\" , \"" + entryDate + "\" , \"" + studentStatus + "\" , \"" + groupId +
                "\" , \"" + studentFinancing + "\" , \"" + studentBook +
                "\"";
        if (studentSubgroup != null && !studentSubgroup.equals("")) {
            queryParam = queryParam + ", subgroup";
            queryValues = queryValues + " , \"" + studentSubgroup + "\"";
        }
        if (dateBirth != null && !dateBirth.equals("")) {
            queryParam = queryParam + ", birth_date";
            queryValues = queryValues + " , \"" + dateBirth + "\"";
        }
        if (passpSerial != null && !passpSerial.equals("")) {
            queryParam = queryParam + ", passport";
            queryValues = queryValues + " , \"" + passpSerial + "\"";
        }
        if (passpOffice != null && !passpOffice.equals("")) {
            queryParam = queryParam + ", passp_office";
            queryValues = queryValues + " , \"" + passpOffice + "\"";
        }
        if (passpDateRelease != null && !passpDateRelease.equals("")) {
            queryParam = queryParam + ", passp_date";
            queryValues = queryValues + " , \"" + passpDateRelease + "\"";
        }
        if (identityCode != null && !identityCode.equals("")) {
            queryParam = queryParam + ", identity_code";
            queryValues = queryValues + " , \"" + identityCode + "\"";
        }
        if (studentHouse != null && !studentHouse.equals("")) {
            queryParam = queryParam + ", student_house";
            queryValues = queryValues + " , \"" + studentHouse + "\"";
        }
        if (studentStreet != null && !studentStreet.equals("")) {
            queryParam = queryParam + ", student_street";
            queryValues = queryValues + " , \"" + studentStreet + "\"";
        }
        if (studentCity != null && !studentCity.equals("")) {
            queryParam = queryParam + ", student_city";
            queryValues = queryValues + " , \"" + studentCity + "\"";
        }
        if (studentState != null && !studentState.equals("")) {
            queryParam = queryParam + ", student_state";
            queryValues = queryValues + " , \"" + studentState + "\"";
        }
        if (studentZip != null && !studentZip.equals("")) {
            queryParam = queryParam + ", student_zip";
            queryValues = queryValues + " , \"" + studentZip + "\"";
        }
        if (studentCountry != null && !studentCountry.equals("")) {
            queryParam = queryParam + ", student_country";
            queryValues = queryValues + " , \"" + studentCountry + "\"";
        }
        if (studentPhone1 != null && !studentPhone1.equals("")) {
            queryParam = queryParam + ", stud_phone_1";
            queryValues = queryValues + " , \"" + studentPhone1 + "\"";
        }
        if (studentPhone2 != null && !studentPhone2.equals("")) {
            queryParam = queryParam + ", stud_phone_2";
            queryValues = queryValues + " , \"" + studentPhone2 + "\"";
        }
        if (fatherName != null && !fatherName.equals("")) {
            queryParam = queryParam + ", father_name";
            queryValues = queryValues + " , \"" + fatherName + "\"";
        }
        if (fatherSurname != null && !fatherSurname.equals("")) {
            queryParam = queryParam + ", father_surname";
            queryValues = queryValues + " , \"" + fatherSurname + "\"";
        }
        if (fatherLastname != null && !fatherLastname.equals("")) {
            queryParam = queryParam + ", father_lastname";
            queryValues = queryValues + " , \"" + fatherLastname + "\"";
        }
        if (fatherPhone1 != null && !fatherPhone1.equals("")) {
            queryParam = queryParam + ", father_phone_1";
            queryValues = queryValues + " , \"" + fatherPhone1 + "\"";
        }
        if (fatherPhone2 != null && !fatherPhone2.equals("")) {
            queryParam = queryParam + ", father_phone_2";
            queryValues = queryValues + " , \"" + fatherPhone2 + "\"";
        }
        if (motherName != null && !motherName.equals("")) {
            queryParam = queryParam + ", mother_name";
            queryValues = queryValues + " , \"" + motherName + "\"";
        }
        if (motherSurname != null && !motherSurname.equals("")) {
            queryParam = queryParam + ", mother_surname";
            queryValues = queryValues + " , \"" + motherSurname + "\"";
        }
        if (motherLastname != null && !motherLastname.equals("")) {
            queryParam = queryParam + ", mother_lastname";
            queryValues = queryValues + " , \"" + motherLastname + "\"";
        }
        if (motherPhone1 != null && !motherPhone1.equals("")) {
            queryParam = queryParam + ", mother_phone_1";
            queryValues = queryValues + " , \"" + motherPhone1 + "\"";
        }
        if (motherPhone2 != null && !motherPhone2.equals("")) {
            queryParam = queryParam + ", mother_phone_2";
            queryValues = queryValues + " , \"" + motherPhone2 + "\"";
        }
        if (parentHouse != null && !parentHouse.equals("")) {
            queryParam = queryParam + ", parent_house";
            queryValues = queryValues + " , \"" + parentHouse + "\"";
        }
        if (parentStreet != null && !parentStreet.equals("")) {
            queryParam = queryParam + ", parent_street";
            queryValues = queryValues + " , \"" + parentStreet + "\"";
        }
        if (parentCity != null && !parentCity.equals("")) {
            queryParam = queryParam + ", parent_city";
            queryValues = queryValues + " , \"" + parentCity + "\"";
        }
        if (parentState != null && !parentState.equals("")) {
            queryParam = queryParam + ", parent_state";
            queryValues = queryValues + " , \"" + parentState + "\"";
        }
        if (parentZip != null && !parentZip.equals("")) {
            queryParam = queryParam + ", parent_zip";
            queryValues = queryValues + " , \"" + parentZip + "\"";
        }
        if (parentCountry != null && !parentCountry.equals("")) {
            queryParam = queryParam + ", parent_country";
            queryValues = queryValues + " , \"" + parentCountry + "\"";
        }
        String query = "INSERT INTO students (" + queryParam + ") VALUES (" + queryValues + ")";
//        System.out.println(query);
        return query;
    }

    public void editStudent(String studId, String studentName, String studentSurname, String studentLastname, String entryDate,
                            String studentStatus, String studentGroup, String studentSubgroup, String studentFinancing,
                            String studentBook, String dateBirth, String passpSerial, String passpOffice, String passpDateRelease,
                            String identityCode, String studentHouse, String studentStreet, String studentCity,
                            String studentState, String studentZip, String studentCountry, String studentPhone1,
                            String studentPhone2, String fatherName, String fatherSurname, String fatherLastname,
                            String fatherPhone1, String fatherPhone2, String motherName, String motherSurname, String motherLastname,
                            String motherPhone1, String motherPhone2, String parentHouse, String parentStreet, String parentCity,
                            String parentState, String parentZip, String parentCountry) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();

            String queryStudent = createEditStudentQuery(studId, studentName, studentSurname, studentLastname, entryDate,
                    studentStatus, studentGroup, studentSubgroup, studentFinancing, studentBook, dateBirth, passpSerial,
                    passpOffice, passpDateRelease, identityCode, studentHouse, studentStreet, studentCity, studentState,
                    studentZip, studentCountry, studentPhone1, studentPhone2, fatherName, fatherSurname, fatherLastname,
                    fatherPhone1, fatherPhone2, motherName, motherSurname, motherLastname, motherPhone1, motherPhone2,
                    parentHouse, parentStreet, parentCity, parentState, parentZip, parentCountry);
            if (studentName != null && studentSurname != null && studentLastname != null && studentStatus != null && studentGroup != null && studentFinancing != null && studentBook != null) {
                statement.executeUpdate(queryStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public String createEditStudentQuery(String studId, String studentName, String studentSurname, String studentLastname, String entryDate,
                                         String studentStatus, String studentGroup, String studentSubgroup, String studentFinancing,
                                         String studentBook, String dateBirth, String passpSerial, String passpOffice, String passpDateRelease,
                                         String identityCode, String studentHouse, String studentStreet, String studentCity,
                                         String studentState, String studentZip, String studentCountry, String studentPhone1,
                                         String studentPhone2, String fatherName, String fatherSurname, String fatherLastname,
                                         String fatherPhone1, String fatherPhone2, String motherName, String motherSurname, String motherLastname,
                                         String motherPhone1, String motherPhone2, String parentHouse, String parentStreet, String parentCity,
                                         String parentState, String parentZip, String parentCountry) {

        Connection conn = startConnection();
        String groupId = null;
        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();
            //Return groupID
            ResultSet resultSet1 = statement.executeQuery("SELECT group_id FROM groups WHERE group_num = \"" + studentGroup + "\"");
            ResultSetMetaData meta1 = resultSet1.getMetaData();
            while (resultSet1.next()) {
                groupId = resultSet1.getString(meta1.getColumnName(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn);

        String queryConfig = " stud_name = \"" + studentName + "\", stud_surname = \"" + studentSurname + "\", stud_lastname = \"" +
                studentLastname + "\", entry_date = \"" + entryDate + "\", status = \"" + studentStatus + "\", group_id = \"" +
                groupId + "\", financing = \"" + studentFinancing + "\", stud_book = \"" + studentBook + "\"";

        if (studentSubgroup != null && !studentSubgroup.equals("")) {
            queryConfig = queryConfig + ", subgroup = \"" + studentSubgroup + "\"";
        } else {
            queryConfig = queryConfig + ", subgroup = NULL";
        }

        if (dateBirth != null && !dateBirth.equals("")) {
            queryConfig = queryConfig + ", birth_date = \"" + dateBirth + "\"";
        } else {
            queryConfig = queryConfig + ", birth_date = NULL";
        }

        if (passpSerial != null && !passpSerial.equals("")) {
            queryConfig = queryConfig + ", passport = \"" + passpSerial + "\"";
        } else {
            queryConfig = queryConfig + ", passport = NULL";
        }

        if (passpOffice != null && !passpOffice.equals("")) {
            queryConfig = queryConfig + ", passp_office = \"" + passpOffice + "\"";
        } else {
            queryConfig = queryConfig + ", passp_office = NULL";
        }

        if (passpDateRelease != null && !passpDateRelease.equals("")) {
            queryConfig = queryConfig + ", passp_date = \"" + passpDateRelease + "\"";
        } else {
            queryConfig = queryConfig + ", passp_date = NULL";
        }

        if (identityCode != null && !identityCode.equals("")) {
            queryConfig = queryConfig + ", identity_code = \"" + identityCode + "\"";
        } else {
            queryConfig = queryConfig + ", identity_code = NULL";
        }

        if (studentHouse != null && !studentHouse.equals("")) {
            queryConfig = queryConfig + ", student_house = \"" + studentHouse + "\"";
        } else {
            queryConfig = queryConfig + ", student_house = NULL";
        }

        if (studentStreet != null && !studentStreet.equals("")) {
            queryConfig = queryConfig + ", student_street = \"" + studentStreet + "\"";
        } else {
            queryConfig = queryConfig + ", student_street = NULL";
        }

        if (studentCity != null && !studentCity.equals("")) {
            queryConfig = queryConfig + ", student_city = \"" + studentCity + "\"";
        } else {
            queryConfig = queryConfig + ", student_city = NULL";
        }

        if (studentState != null && !studentState.equals("")) {
            queryConfig = queryConfig + ", student_state = \"" + studentState + "\"";
        } else {
            queryConfig = queryConfig + ", student_state = NULL";
        }

        if (studentZip != null && !studentZip.equals("")) {
            queryConfig = queryConfig + ", student_zip = \"" + studentZip + "\"";
        } else {
            queryConfig = queryConfig + ", student_zip = NULL";
        }

        if (studentCountry != null && !studentCountry.equals("")) {
            queryConfig = queryConfig + ", student_country = \"" + studentCountry + "\"";
        } else {
            queryConfig = queryConfig + ", student_country = NULL";
        }

        if (studentPhone1 != null && !studentPhone1.equals("")) {
            queryConfig = queryConfig + ", stud_phone_1 = \"" + studentPhone1 + "\"";
        } else {
            queryConfig = queryConfig + ", stud_phone_1 = NULL";
        }

        if (studentPhone2 != null && !studentPhone2.equals("")) {
            queryConfig = queryConfig + ", stud_phone_2 = \"" + studentPhone2 + "\"";
        } else {
            queryConfig = queryConfig + ", stud_phone_2 = NULL";
        }

        if (fatherName != null && !fatherName.equals("")) {
            queryConfig = queryConfig + ", father_name = \"" + fatherName + "\"";
        } else {
            queryConfig = queryConfig + ", father_name = NULL";
        }

        if (fatherSurname != null && !fatherSurname.equals("")) {
            queryConfig = queryConfig + ", father_surname = \"" + fatherSurname + "\"";
        } else {
            queryConfig = queryConfig + ", father_surname = NULL";
        }

        if (fatherLastname != null && !fatherLastname.equals("")) {
            queryConfig = queryConfig + ", father_lastname = \"" + fatherLastname + "\"";
        } else {
            queryConfig = queryConfig + ", father_lastname = NULL";
        }

        if (fatherPhone1 != null && !fatherPhone1.equals("")) {
            queryConfig = queryConfig + ", father_phone_1 = \"" + fatherPhone1 + "\"";
        } else {
            queryConfig = queryConfig + ", father_phone_1 = NULL";
        }

        if (fatherPhone2 != null && !fatherPhone2.equals("")) {
            queryConfig = queryConfig + ", father_phone_2 = \"" + fatherPhone2 + "\"";
        } else {
            queryConfig = queryConfig + ", father_phone_2 = NULL";
        }

        if (motherName != null && !motherName.equals("")) {
            queryConfig = queryConfig + ", mother_name = \"" + motherName + "\"";
        } else {
            queryConfig = queryConfig + ", mother_name = NULL";
        }

        if (motherSurname != null && !motherSurname.equals("")) {
            queryConfig = queryConfig + ", mother_surname = \"" + motherSurname + "\"";
        } else {
            queryConfig = queryConfig + ", mother_surname = NULL";
        }

        if (motherLastname != null && !motherLastname.equals("")) {
            queryConfig = queryConfig + ", mother_lastname = \"" + motherLastname + "\"";
        }else{
            queryConfig = queryConfig + ", mother_lastname = NULL";
        }

        if (motherPhone1 != null && !motherPhone1.equals("")) {
            queryConfig = queryConfig + ", mother_phone_1 = \"" + motherPhone1 + "\"";
        }else{
            queryConfig = queryConfig + ", mother_phone_1 = NULL";
        }

        if (motherPhone2 != null && !motherPhone2.equals("")) {
            queryConfig = queryConfig + ", mother_phone_2 = \"" + motherPhone2 + "\"";
        }else{
            queryConfig = queryConfig + ", mother_phone_2 = NULL";
        }

        if (parentHouse != null && !parentHouse.equals("")) {
            queryConfig = queryConfig + ", parent_house = \"" + parentHouse + "\"";
        }else{
            queryConfig = queryConfig + ", parent_house = NULL";
        }

        if (parentStreet != null && !parentStreet.equals("")) {
            queryConfig = queryConfig + ", parent_street = \"" + parentStreet + "\"";
        }else{
            queryConfig = queryConfig + ", parent_street = NULL";
        }

        if (parentCity != null && !parentCity.equals("")) {
            queryConfig = queryConfig + ", parent_city = \"" + parentCity + "\"";
        }else{
            queryConfig = queryConfig + ", parent_city = NULL";
        }

        if (parentState != null && !parentState.equals("")) {
            queryConfig = queryConfig + ", parent_state = \"" + parentState + "\"";
        }else{
            queryConfig = queryConfig + ", parent_state = NULL";
        }

        if (parentZip != null && !parentZip.equals("")) {
            queryConfig = queryConfig + ", parent_zip = \"" + parentZip + "\"";
        }else{
            queryConfig = queryConfig + ", parent_zip = NULL";
        }

        if (parentCountry != null && !parentCountry.equals("")) {
            queryConfig = queryConfig + ", parent_country = \"" + parentCountry + "\"";
        }else{
            queryConfig = queryConfig + ", parent_country = NULL";
        }
        String query = "UPDATE students SET" + queryConfig + " WHERE stud_id = \"" + studId + "\"";
        return query;
    }

    public ArrayList<Student> getStudList(String query) {

        ArrayList<Student> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT stud_id, stud_name, stud_surname, stud_lastname, status FROM students");
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String id = resultSet.getString(meta.getColumnName(1));
                String name = resultSet.getString(meta.getColumnName(2));
                String surname = resultSet.getString(meta.getColumnName(3));
                String lastname = resultSet.getString(meta.getColumnName(4));
                String status = resultSet.getString(meta.getColumnName(5));
                storage.add(new Student(id, name, surname, lastname, status));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public void deleteStudent(String studId) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(catalog);
            Statement statement = conn.createStatement();
            String query = "DELETE FROM students WHERE stud_id = \"" + studId + "\"";
            System.out.println("Query delete student: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }


    /**
     * Getting parameters for sidebar on student list
     */

    public HashMap<String, Boolean> getSpecListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT spec_name FROM specialities");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String status = resultSet.getString(meta.getColumnName(1));
                storage.put(status, false);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public HashMap<String, Boolean> getStatusListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT status FROM students");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String status = resultSet.getString(meta.getColumnName(1));
                storage.put(status, false);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public HashMap<String, Boolean> getQualificationListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT group_qual FROM groups");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String qualification = resultSet.getString(meta.getColumnName(1));
                storage.put(qualification, false);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);

        return storage;
    }

    public HashMap<String, Boolean> getCourseListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT group_course FROM groups");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String qualification = resultSet.getString(meta.getColumnName(1));
                storage.put(qualification, false);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);

        return storage;
    }

    public HashMap<String, Boolean> getEducFormListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT group_educ_form FROM groups");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String group = resultSet.getString(meta.getColumnName(1));
                storage.put(group, false);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);

        return storage;
    }

    public HashMap<String, Boolean> getFinanceListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT financing FROM students");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String group = resultSet.getString(meta.getColumnName(1));
                storage.put(group, false);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);

        return storage;
    }

    /**
     * Modify standart MySQL SELECT query to SELECT query with WHERE parameters
     */

    public String retSortedQuery(String query, HashMap<String, Boolean> specList, HashMap<String, Boolean> statusList,
                                 HashMap<String, Boolean> qualList, HashMap<String, Boolean> courseList,
                                 HashMap<String, Boolean> educFormList, HashMap<String, Boolean> finList,
                                 HashMap<String, String> groupParam, HashMap<String, String> subgroupParam,
                                 HashMap<String, String> cityParam, HashMap<String, String> stateParam) {

        if (retIfMapBoolTrue(specList)) {
            query = query + " INNER JOIN groups gr ON st.group_id = gr.group_id INNER JOIN specialities sp ON sp.id_spec = gr.spec_id";
        } else if (retIfMapBoolTrue(qualList) || retIfMapBoolTrue(courseList) || retIfMapBoolTrue(educFormList) ||
                retIfMapStrTrue(groupParam)) {
            query = query + " INNER JOIN groups gr ON st.group_id = gr.group_id";
        }

        for (Map.Entry<String, Boolean> entry : specList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "sp.spec_name", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : statusList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.status", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : qualList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "gr.group_qual", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : courseList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "gr.group_course", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : educFormList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "gr.group_educ_form", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : finList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.financing", entry.getKey());
            }
        }

        for (Map.Entry<String, String> entry : groupParam.entrySet()) {
            if (entry.getValue() != null) {
                if (!entry.getValue().equals("")) {
                    query = addQueryPart(query, "gr.group_num", entry.getValue());
                }
            }
        }

        for (Map.Entry<String, String> entry : subgroupParam.entrySet()) {
            if (entry.getValue() != null) {
                if (!entry.getValue().equals("")) {
                    query = addQueryPart(query, "st.subgroup", entry.getValue());
                }
            }
        }

        for (Map.Entry<String, String> entry : cityParam.entrySet()) {
            if (entry.getValue() != null) {
                if (!entry.getValue().equals("")) {
                    query = addQueryPart(query, "st.parent_city", entry.getValue());
                }
            }
        }

        for (Map.Entry<String, String> entry : stateParam.entrySet()) {
            if (entry.getValue() != null) {
                if (!entry.getValue().equals("")) {
                    query = addQueryPart(query, "st.parent_state", entry.getValue());
                }
            }
        }

        query = qParamGroup(query);

        return query;
    }

    public Boolean retIfMapBoolTrue(HashMap<String, Boolean> hashMap) {
        Boolean bool = false;
        for (Boolean value : hashMap.values()) {
            if (value == true) {
                bool = true;
            }
        }
        return bool;
    }

    public Boolean retIfMapStrTrue(HashMap<String, String> hashMap) {
        Boolean bool = false;
        for (String value : hashMap.values()) {
            if (value != null) {
                if (!value.equals("")) {
                    bool = true;
                }
            }
        }
        return bool;
    }

    public String addQueryPart(String queryFull, String queryParam, String parameter) {
        if (parameter != null) {
            if (qParam(queryFull, queryParam)) {
                if (qParam(queryFull, "WHERE")) {
                    queryFull = queryFull + " OR " + queryParam + " = '" + parameter + "'";
                } else {
                    queryFull = queryFull + " WHERE " + queryParam + " = '" + parameter + "'";
                }
            } else {
                if (qParam(queryFull, "WHERE")) {
                    queryFull = queryFull + " AND " + queryParam + " = '" + parameter + "'";
                } else {
                    queryFull = queryFull + " WHERE " + queryParam + " = '" + parameter + "'";
                }
            }
        }
        return queryFull;
    }

    private boolean qParam(String str, String word) {
        Boolean bull = false;
        String[] list = str.split(" ");
        for (int i = 0; i < list.length; i++) {
            String ti = list[i];
            if (ti.equals(word)) {
                bull = true;
            }
        }
        return bull;
    }

    public String qParamGroup(String query) {
        String[] list = query.split(" ");
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            array.add(list[i]);
        }
        boolean b = false;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals("WHERE")) {
                b = true;
            }
        }

        if (b == true) {
            for (int i = array.size(); i > 0; i--) {
                if (i == array.size()) {
                    array.add(i, ")");
                }
                if (array.get(i).equals("AND")) {
                    array.add(i + 1, "(");
                    array.add(i, ")");
                }
                if (array.get(i).equals("WHERE")) {
                    array.add(i + 1, "(");
                }
            }
        }

        String result = "";
        for (int i = 0; i < array.size(); i++) {
            result = result + " " + array.get(i);
        }

        return result;
    }

    /**
     * Return profile info from DB
     */

    public ArrayList<Student> getOneStudent(String studId, Boolean bool) {
        ArrayList<Student> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students st WHERE st.stud_id = \"" + studId + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String id = resultSet.getString(meta.getColumnName(1));
                String name = resultSet.getString(meta.getColumnName(2));
                String surname = resultSet.getString(meta.getColumnName(3));
                String lastname = resultSet.getString(meta.getColumnName(4));
                String entryDate = null;
                if (bool) {
                    entryDate = resultSet.getString(meta.getColumnName(5));
                } else {
                    entryDate = dateFormatLongText(resultSet.getString(meta.getColumnName(5)));
                }
//                String entryDate = dateFormatLongText(resultSet.getString(meta.getColumnName(5)));
//                String entryDate = resultSet.getString(meta.getColumnName(5));
                String status = resultSet.getString(meta.getColumnName(6));
                String groupId = resultSet.getString(meta.getColumnName(7));
                String subGroup = resultSet.getString(meta.getColumnName(8));
                String financing = resultSet.getString(meta.getColumnName(9));
                String studBook = resultSet.getString(meta.getColumnName(10));
                String birthDate = null;
                if (bool) {
                    birthDate = resultSet.getString(meta.getColumnName(11));
                } else {
                    birthDate = dateFormatLongText(resultSet.getString(meta.getColumnName(11)));
                }
//                String birthDate = resultSet.getString(meta.getColumnName(11));
//                String birthDate = dateFormatLongText(resultSet.getString(meta.getColumnName(11)));
                String passport = resultSet.getString(meta.getColumnName(12));
                String passpOffice = resultSet.getString(meta.getColumnName(13));
                String passpDate = null;
                if (bool) {
                    passpDate = resultSet.getString(meta.getColumnName(14));
                } else {
                    passpDate = dateFormatLongText(resultSet.getString(meta.getColumnName(14)));
                }
//                String passpDate = resultSet.getString(meta.getColumnName(14));
//                String passpDate = dateFormatLongText(resultSet.getString(meta.getColumnName(14)));
                String identityCode = resultSet.getString(meta.getColumnName(15));
                String studHouse = resultSet.getString(meta.getColumnName(16));
                String studStreet = resultSet.getString(meta.getColumnName(17));
                String studCity = resultSet.getString(meta.getColumnName(18));
                String studState = resultSet.getString(meta.getColumnName(19));
                String studZip = resultSet.getString(meta.getColumnName(20));
                String studCountry = resultSet.getString(meta.getColumnName(21));
                String studPhone1 = resultSet.getString(meta.getColumnName(22));
                String studPhone2 = resultSet.getString(meta.getColumnName(23));
                String fatherName = resultSet.getString(meta.getColumnName(24));
                String fatherSurname = resultSet.getString(meta.getColumnName(25));
                String fatherLastname = resultSet.getString(meta.getColumnName(26));
                String fatherPhone1 = resultSet.getString(meta.getColumnName(27));
                String fatherPhone2 = resultSet.getString(meta.getColumnName(28));
                String motherName = resultSet.getString(meta.getColumnName(29));
                String motherSurname = resultSet.getString(meta.getColumnName(30));
                String motherLastname = resultSet.getString(meta.getColumnName(31));
                String motherPhone1 = resultSet.getString(meta.getColumnName(32));
                String motherPhone2 = resultSet.getString(meta.getColumnName(33));
                String parentsHouse = resultSet.getString(meta.getColumnName(34));
                String parentsStreet = resultSet.getString(meta.getColumnName(35));
                String parentsCity = resultSet.getString(meta.getColumnName(36));
                String parentsState = resultSet.getString(meta.getColumnName(37));
                String parentsZip = resultSet.getString(meta.getColumnName(38));
                String parentsCountry = resultSet.getString(meta.getColumnName(39));
                storage.add(new Student(id, name, surname, lastname, entryDate, status, groupId, subGroup,
                        financing, studBook, birthDate, passport, passpOffice, passpDate, identityCode,
                        studHouse, studStreet, studCity, studState, studZip, studCountry, studPhone1,
                        studPhone2, fatherName, fatherSurname, fatherLastname, fatherPhone1,
                        fatherPhone2, motherName, motherSurname, motherLastname, motherPhone1,
                        motherPhone2, parentsHouse, parentsStreet, parentsCity, parentsState,
                        parentsZip, parentsCountry));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public ArrayList<Group> getOneStudentGroup(String studId) {
        ArrayList<Group> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groups gr INNER JOIN students st ON gr.group_id = st.group_id WHERE st.stud_id = \"" + studId + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String group_id = resultSet.getString(meta.getColumnName(1));
                String spec_id = resultSet.getString(meta.getColumnName(2));
                String group_num = resultSet.getString(meta.getColumnName(3));
                String gr_educ_form = resultSet.getString(meta.getColumnName(4));
                String group_qual = resultSet.getString(meta.getColumnName(5));
                String group_course = resultSet.getString(meta.getColumnName(6));

                storage.add(new Group(group_id, spec_id, group_num, gr_educ_form, group_qual, group_course));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public ArrayList<Speciality> getOneStudentSpec(String studId) {
        ArrayList<Speciality> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialities sp INNER JOIN groups gr ON gr.spec_id = sp.id_spec INNER JOIN students st ON gr.group_id = st.group_id WHERE st.stud_id = \"" + studId + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String spec_id = resultSet.getString(meta.getColumnName(1));
                String spec_name = resultSet.getString(meta.getColumnName(2));

                storage.add(new Speciality(spec_id, spec_name));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public String dateFormatNum(String date) {
        String result = null;

        if (date != null) {

            String[] list = date.split("/");
            ArrayList<Integer> array = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                array.add(Integer.parseInt(list[i]));
            }
            String day = null;
            String month = null;
            String year = null;

            if (array.get(0) <= 31 && array.get(0) > 0) {
                if (array.get(0) < 10) {
                    day = "0" + array.get(0);
                } else {
                    day = "" + array.get(0);
                }
            }

            if (array.get(1) <= 12) {
                if (array.get(1) < 10) {
                    month = "0" + array.get(1);
                } else {
                    month = "" + array.get(1);
                }
            }

            if (array.get(2) < 2050 && array.get(2) > 1950) {
                year = "" + array.get(2);
            }

            if (array.get(0) <= 31 && array.get(0) > 0 && array.get(1) <= 12 && array.get(1) > 0 && array.get(2) < 2050 && array.get(2) > 1950) {
                result = day + " " + month + " " + year;
            } else {
                result = "Недопустима дата";
            }
        }
        return result;
    }

    public String dateFormatLongText(String date) {
        String result = null;

        if (date != null) {

            String[] list = date.split("/");
            ArrayList<Integer> array = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                array.add(Integer.parseInt(list[i]));
            }
            String day = null;
            String month = null;
            String year = null;

            if (array.get(0) <= 31 && array.get(0) > 0) {
                if (array.get(0) < 10) {
                    day = "0" + array.get(0);
                } else {
                    day = "" + array.get(0);
                }
            }

            if (array.get(1) <= 12) {
                if (array.get(1) == 1) {
                    month = "Cічня";
                } else if (array.get(1) == 2) {
                    month = "Лютого";
                } else if (array.get(1) == 3) {
                    month = "Березня";
                } else if (array.get(1) == 4) {
                    month = "Квітня";
                } else if (array.get(1) == 5) {
                    month = "Травня";
                } else if (array.get(1) == 6) {
                    month = "Червня";
                } else if (array.get(1) == 7) {
                    month = "Липня";
                } else if (array.get(1) == 8) {
                    month = "Серпня";
                } else if (array.get(1) == 9) {
                    month = "Вересня";
                } else if (array.get(1) == 10) {
                    month = "Жовтня";
                } else if (array.get(1) == 11) {
                    month = "Листопада";
                } else if (array.get(1) == 12) {
                    month = "Грудня";
                }
            }

            if (array.get(2) < 2050 && array.get(2) > 1950) {
                year = "" + array.get(2);
            }

            if (array.get(0) <= 31 && array.get(0) > 0 && array.get(1) <= 12 && array.get(1) > 0 && array.get(2) < 2050 && array.get(2) > 1950) {
                result = day + " " + month + " " + year + " р.";
            } else {
                result = "Недопустима дата";
            }
        }
        return result;
    }


}