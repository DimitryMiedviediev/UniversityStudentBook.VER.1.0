package model;

import model.classes.*;

import java.sql.*;
import java.util.*;

/**
 * Created by Dimitry on 07.02.17.
 */
public class Beans {
    GetTime time = new GetTime();
//    String userSchema = "studDBfin";

    private Connection startConnection() {
        Connection connection = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            String username = "";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false&useUnicode=yes&characterEncoding=UTF-8", "root", "");
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
    public void createNewSpec(String userSchema, String newSpecName) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
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

    public ArrayList<Speciality> getSpecList(String userSchema) {
        ArrayList<Speciality> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
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

    public ArrayList<Speciality> getOneSpec(String userSchema, String nameSpec) {
        ArrayList<Speciality> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
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

    public void editSpec(String userSchema, String specId, String newName) {
        Connection conn = startConnection();
        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();
            String querySpec = "UPDATE specialities SET spec_name = \"" + newName + "\" WHERE id_spec = \"" + specId + "\"";
            if (!newName.equals("")) {
                statement.executeUpdate(querySpec);
                System.out.println("Query create speciality: " + querySpec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn);

        ArrayList<String> groupsId = new ArrayList<>();
        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT group_id FROM groups WHERE spec_id = \"" + specId + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String id = resultSet.getString(meta.getColumnName(1));
                groupsId.add(id);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);

        Connection conn3 = startConnection();
        try {
            conn3.setCatalog(userSchema);
            Statement statement = conn3.createStatement();
            String querySpec = "UPDATE students SET stud_spec = \"" + newName + "\" WHERE";

            for (int i = 0; i < groupsId.size(); i++) {
                querySpec = querySpec + " group_id = \"" + groupsId.get(i) + "\"";
                if (i != (groupsId.size() - 1)) {
                    querySpec = querySpec + " OR";
                }
            }

            if (!newName.equals("")) {
                statement.executeUpdate(querySpec);
                System.out.println("Query update students when update speciality: " + querySpec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn3);
    }

    public void deleteSpec(String userSchema, String nameSpec) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
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
    public void createNewGroup(String userSchema, String speciality, String groupNum, String groupEducForm, String groupEducQual, String groupCourse) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();

            String specId = null;
            ResultSet resultSet = statement.executeQuery("SELECT id_spec FROM specialities WHERE spec_name = '" + speciality + "'");
            ResultSetMetaData meta = resultSet.getMetaData();
            while (resultSet.next()) {
                specId = resultSet.getString(meta.getColumnName(1));
            }

            String query = "INSERT INTO groups (spec_id, group_num, group_educ_form, group_qual, group_course, group_status) " +
                    "VALUES ('" + specId + "' , '" + groupNum + "' , '" + groupEducForm + "' , '" + groupEducQual + "' , '" + groupCourse + "' , 'Активна')";
            if ((!specId.equals("")) && (!groupNum.equals("")) && (!groupCourse.equals(""))) {
                statement.executeUpdate(query);
                System.out.println("Query create speciality: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public ArrayList<Group> getGroupList(String userSchema) {
        ArrayList<Group> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT gr.group_id, sp.spec_name, gr.group_num, gr.group_educ_form, gr.group_qual, gr.group_course, group_status FROM groups gr INNER JOIN specialities sp ON gr.spec_id = sp.id_spec WHERE group_status = 'Активна' ORDER BY sp.spec_name, gr.group_num, gr.group_qual, gr.group_course, gr.group_educ_form");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String groupId = resultSet.getString(meta.getColumnName(1));
                String specId = resultSet.getString(meta.getColumnName(2));
                String groupNum = resultSet.getString(meta.getColumnName(3));
                String groupEducForm = resultSet.getString(meta.getColumnName(4));
                String groupQual = resultSet.getString(meta.getColumnName(5));
                String groupCourse = resultSet.getString(meta.getColumnName(6));
                String groupStatus = resultSet.getString(meta.getColumnName(7));
                storage.add(new Group(groupId, specId, groupNum, groupEducForm, groupQual, groupCourse, groupStatus));
            }

            System.out.println("List of groups:" + storage);

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public ArrayList<Group> getOneGroup(String userSchema, String group_Id) {
        ArrayList<Group> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groups WHERE group_id = '" + group_Id + "'");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String groupId = resultSet.getString(meta.getColumnName(1));
                String specId = resultSet.getString(meta.getColumnName(2));
                String number = resultSet.getString(meta.getColumnName(3));
                String educationForm = resultSet.getString(meta.getColumnName(4));
                String qualificationLevel = resultSet.getString(meta.getColumnName(5));
                String course = resultSet.getString(meta.getColumnName(6));
                String status = resultSet.getString(meta.getColumnName(7));
                storage.add(new Group(groupId, specId, number, educationForm, qualificationLevel, course, status));
            }

            System.out.println("List of editGroup:" + storage);

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public void editGroup(String userSchema, String groupId, String specName, String groupNum, String groupEducForm, String groupEducQual, String groupCourse) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();
            String specId = null;
            ResultSet resultSet = statement.executeQuery("SELECT id_spec FROM specialities WHERE spec_name = '" + specName + "'");
            ResultSetMetaData meta = resultSet.getMetaData();
            while (resultSet.next()) {
                specId = resultSet.getString(meta.getColumnName(1));
            }
            String queryGroup = "UPDATE groups SET spec_id = '" + specId + "', group_num = '" + groupNum + "', group_educ_form = '"
                    + groupEducForm + "', group_qual = '" + groupEducQual + "', group_course = '" + groupCourse + "' WHERE group_id = '" + groupId + "'";

            String queryStudent = "UPDATE students SET stud_spec = '" + specName + "', stud_educ_form = '" + groupEducForm + "', stud_qual = '"
                    + groupEducQual + "', stud_course = '" + groupCourse + "' WHERE group_id = '" + groupId + "'";

            if ((!groupNum.equals("")) && (!groupCourse.equals(""))) {
                statement.executeUpdate(queryGroup);
                statement.executeUpdate(queryStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public void deleteGroup(String userSchema, String groupId) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();
            String query = "DELETE FROM groups WHERE group_id = '" + groupId + "'";
            System.out.println("Query delete group: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public void graduateGroup(String userSchema, String groupId, String orderGraduate, String dateGraduate) {

        Connection conn1 = startConnection();
        try {
            conn1.setCatalog(userSchema);
            Statement statement = conn1.createStatement();

            statement.executeUpdate("UPDATE students SET status='Закінчив навчання', graduate_date='" + dateGraduate + "', graduate_order='" + orderGraduate + "' WHERE group_id='" + groupId + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn1);

        Connection conn2 = startConnection();

        try {
            conn2.setCatalog(userSchema);
            Statement statement = conn2.createStatement();
//            String query = "DELETE FROM groups WHERE group_id = '" + groupId + "'";
            statement.executeUpdate("UPDATE groups SET group_status='Неактивна' WHERE group_id = '" + groupId + "'");

//            System.out.println("Query delete group: " + query);
//            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn2);
    }

    public void courseTransfer(String userSchema) {

        ArrayList<String> courses = new ArrayList<>();
        Connection conn1 = startConnection();
        try {
            conn1.setCatalog(userSchema);
            Statement statement = conn1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT group_course FROM groups ORDER BY group_course DESC");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String id = resultSet.getString(meta.getColumnName(1));
                courses.add(id);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(conn1);
        System.out.println(courses);


        Connection conn2 = startConnection();
        try {
            conn2.setCatalog(userSchema);
            Statement statement = conn2.createStatement();
            for (int i = 0; i < courses.size(); i++) {
                statement.executeUpdate("UPDATE groups SET group_course = '" + (Integer.parseInt(courses.get(i)) + 1) + "' WHERE group_course = '" + courses.get(i) + "' AND group_status = 'Активна'");
            }
            for (int i = 0; i < courses.size(); i++) {
                statement.executeUpdate("UPDATE students SET stud_course = '" + (Integer.parseInt(courses.get(i)) + 1) + "' WHERE stud_course = '" + courses.get(i) + "' AND status = 'Навчається'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn2);
    }

    /**
     * Working with students
     */
    public void createNewStudent(String userSchema, String studentName, String studentSurname, String studentLastname, String entryDate,
                                 String entryOrder,
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
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();

            String queryStudent = createNewStudentQuery(userSchema, studentName, studentSurname, studentLastname, entryDate,
                    entryOrder,
                    studentStatus, studentGroup, studentSubgroup, studentFinancing, studentBook, dateBirth, passpSerial,
                    passpOffice, passpDateRelease, identityCode, studentHouse, studentStreet, studentCity, studentState,
                    studentZip, studentCountry, studentPhone1, studentPhone2, fatherName, fatherSurname, fatherLastname,
                    fatherPhone1, fatherPhone2, motherName, motherSurname, motherLastname, motherPhone1, motherPhone2,
                    parentHouse, parentStreet, parentCity, parentState, parentZip, parentCountry);
            System.out.println(queryStudent);
            if (studentName != null && studentSurname != null && studentLastname != null && studentStatus != null && studentGroup != null && studentFinancing != null && studentBook != null && !studentName.equals("") && !studentSurname.equals("") && !studentLastname.equals("") && !studentStatus.equals("") && !studentGroup.equals("") && !studentFinancing.equals("") && !studentBook.equals("")) {
                statement.executeUpdate(queryStudent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);

    }

    public String createNewStudentQuery(String userSchema, String studentName, String studentSurname, String studentLastname, String entryDate,
                                        String entryOrder,
                                        String studentStatus, String studentGroup, String studentSubgroup, String studentFinancing,
                                        String studentBook, String dateBirth, String passpSerial, String passpOffice, String passpDateRelease,
                                        String identityCode, String studentHouse, String studentStreet, String studentCity,
                                        String studentState, String studentZip, String studentCountry, String studentPhone1,
                                        String studentPhone2, String fatherName, String fatherSurname, String fatherLastname,
                                        String fatherPhone1, String fatherPhone2, String motherName, String motherSurname, String motherLastname,
                                        String motherPhone1, String motherPhone2, String parentHouse, String parentStreet, String parentCity,
                                        String parentState, String parentZip, String parentCountry) {

        String queryParam = "stud_name, stud_surname, stud_lastname, entry_date, entry_order, status, group_id, financing, stud_book";
        String queryValues = "\"" + studentName + "\" , \"" + studentSurname + "\" , \"" + studentLastname +
                "\" , \"" + entryDate + "\" , \"" + entryOrder + "\" , \"" + studentStatus + "\" , \"" + studentGroup +
                "\" , \"" + studentFinancing + "\" , \"" + studentBook +
                "\"";


        String studSpec = "";
        String studEducForm = "";
        String studQual = "";
        String studCourse = "";

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groups WHERE group_id = \"" + studentGroup + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                studSpec = resultSet.getString(meta.getColumnName(2));
                studEducForm = resultSet.getString(meta.getColumnName(4));
                studQual = resultSet.getString(meta.getColumnName(5));
                studCourse = resultSet.getString(meta.getColumnName(6));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);

        Connection conn = startConnection();
        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialities WHERE id_spec = \"" + studSpec + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                studSpec = resultSet.getString(meta.getColumnName(2));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(conn);

        if (studSpec != null && !studSpec.equals("")) {
            queryParam = queryParam + ", stud_spec";
            queryValues = queryValues + " , \"" + studSpec + "\"";
        }
        if (studEducForm != null && !studEducForm.equals("")) {
            queryParam = queryParam + ", stud_educ_form";
            queryValues = queryValues + " , \"" + studEducForm + "\"";
        }
        if (studQual != null && !studQual.equals("")) {
            queryParam = queryParam + ", stud_qual";
            queryValues = queryValues + " , \"" + studQual + "\"";
        }
        if (studCourse != null && !studCourse.equals("")) {
            queryParam = queryParam + ", stud_course";
            queryValues = queryValues + " , \"" + studCourse + "\"";
        }


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

    public void editStudent(String userSchema, String studId, String studentName, String studentSurname, String studentLastname,
                            String entryDate, String entryOrder, String graduateDate, String graduateOrder,
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
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();

            String queryStudent = createEditStudentQuery(userSchema, studId, studentName, studentSurname, studentLastname,
                    entryDate, entryOrder, graduateDate, graduateOrder,
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

    public String createEditStudentQuery(String userSchema, String studId, String studentName, String studentSurname, String studentLastname,
                                         String entryDate, String entryOrder, String graduateDate, String graduateOrder,
                                         String studentStatus, String studentGroup, String studentSubgroup, String studentFinancing,
                                         String studentBook, String dateBirth, String passpSerial, String passpOffice, String passpDateRelease,
                                         String identityCode, String studentHouse, String studentStreet, String studentCity,
                                         String studentState, String studentZip, String studentCountry, String studentPhone1,
                                         String studentPhone2, String fatherName, String fatherSurname, String fatherLastname,
                                         String fatherPhone1, String fatherPhone2, String motherName, String motherSurname, String motherLastname,
                                         String motherPhone1, String motherPhone2, String parentHouse, String parentStreet, String parentCity,
                                         String parentState, String parentZip, String parentCountry) {

        String queryConfig = " stud_name = \"" + studentName + "\", stud_surname = \"" + studentSurname + "\", stud_lastname = \"" +
                studentLastname + "\", entry_date = \"" + entryDate + "\", entry_order = \"" + entryOrder + "\", status = \"" + studentStatus + "\", group_id = \"" +
                studentGroup + "\", financing = \"" + studentFinancing + "\", stud_book = \"" + studentBook + "\"";

        String speciality = "";
        String educationForm = "";
        String qualificationLevel = "";
        String course = "";

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT group_id, spec_id, group_num, group_educ_form, group_qual, group_course FROM groups WHERE group_id = \"" + studentGroup + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                speciality = resultSet.getString(meta.getColumnName(2));
                educationForm = resultSet.getString(meta.getColumnName(4));
                qualificationLevel = resultSet.getString(meta.getColumnName(5));
                course = resultSet.getString(meta.getColumnName(6));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);

        Connection conn = startConnection();
        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialities WHERE id_spec = \"" + speciality + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                speciality = resultSet.getString(meta.getColumnName(2));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(conn);


        if (speciality != null && !speciality.equals("")) {
            queryConfig = queryConfig + ", stud_spec = \"" + speciality + "\"";
        } else {
            queryConfig = queryConfig + ", stud_spec = NULL";
        }

        if (educationForm != null && !educationForm.equals("")) {
            queryConfig = queryConfig + ", stud_educ_form = \"" + educationForm + "\"";
        } else {
            queryConfig = queryConfig + ", stud_educ_form = NULL";
        }

        if (qualificationLevel != null && !qualificationLevel.equals("")) {
            queryConfig = queryConfig + ", stud_qual = \"" + qualificationLevel + "\"";
        } else {
            queryConfig = queryConfig + ", stud_qual = NULL";
        }

        if (course != null && !course.equals("")) {
            queryConfig = queryConfig + ", stud_course = \"" + course + "\"";
        } else {
            queryConfig = queryConfig + ", stud_course = NULL";
        }


        if (studentSubgroup != null && !studentSubgroup.equals("") && !studentSubgroup.equals("Відсутня")) {
            queryConfig = queryConfig + ", subgroup = \"" + studentSubgroup + "\"";
        } else {
            queryConfig = queryConfig + ", subgroup = NULL";
        }

        if (graduateDate != null && !graduateDate.equals("")) {
            queryConfig = queryConfig + ", graduate_date = \"" + graduateDate + "\"";
        } else {
            queryConfig = queryConfig + ", graduate_date = NULL";
        }

        if (graduateOrder != null && !graduateOrder.equals("")) {
            queryConfig = queryConfig + ", graduate_order = \"" + graduateOrder + "\"";
        } else {
            queryConfig = queryConfig + ", graduate_order = NULL";
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
        } else {
            queryConfig = queryConfig + ", mother_lastname = NULL";
        }

        if (motherPhone1 != null && !motherPhone1.equals("")) {
            queryConfig = queryConfig + ", mother_phone_1 = \"" + motherPhone1 + "\"";
        } else {
            queryConfig = queryConfig + ", mother_phone_1 = NULL";
        }

        if (motherPhone2 != null && !motherPhone2.equals("")) {
            queryConfig = queryConfig + ", mother_phone_2 = \"" + motherPhone2 + "\"";
        } else {
            queryConfig = queryConfig + ", mother_phone_2 = NULL";
        }

        if (parentHouse != null && !parentHouse.equals("")) {
            queryConfig = queryConfig + ", parent_house = \"" + parentHouse + "\"";
        } else {
            queryConfig = queryConfig + ", parent_house = NULL";
        }

        if (parentStreet != null && !parentStreet.equals("")) {
            queryConfig = queryConfig + ", parent_street = \"" + parentStreet + "\"";
        } else {
            queryConfig = queryConfig + ", parent_street = NULL";
        }

        if (parentCity != null && !parentCity.equals("")) {
            queryConfig = queryConfig + ", parent_city = \"" + parentCity + "\"";
        } else {
            queryConfig = queryConfig + ", parent_city = NULL";
        }

        if (parentState != null && !parentState.equals("")) {
            queryConfig = queryConfig + ", parent_state = \"" + parentState + "\"";
        } else {
            queryConfig = queryConfig + ", parent_state = NULL";
        }

        if (parentZip != null && !parentZip.equals("")) {
            queryConfig = queryConfig + ", parent_zip = \"" + parentZip + "\"";
        } else {
            queryConfig = queryConfig + ", parent_zip = NULL";
        }

        if (parentCountry != null && !parentCountry.equals("")) {
            queryConfig = queryConfig + ", parent_country = \"" + parentCountry + "\"";
        } else {
            queryConfig = queryConfig + ", parent_country = NULL";
        }
        String query = "UPDATE students SET" + queryConfig + " WHERE stud_id = \"" + studId + "\"";
        return query;
    }

    public ArrayList<Student> getStudList(String userSchema, String query) {

        ArrayList<Student> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
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

    public void giveAcademicVacation(String userSchema, String studId) {
        Connection conn = startConnection();
        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();

            statement.executeUpdate("UPDATE students SET status = \"Академічна відпустка\", group_id = NULL, subgroup = NULL WHERE stud_id = \"" + studId + "\"");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn);
    }

    public void expellStudent(String userSchema, String studId) {
        Connection conn = startConnection();
        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();

            statement.executeUpdate("UPDATE students SET status = \"Відрахований\", group_id = NULL, subgroup = NULL WHERE stud_id = \"" + studId + "\"");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn);
    }

    public void deleteStudent(String userSchema, String studId) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
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
     * Working with ORDERS
     */

    public ArrayList<Order> getOrderList(String userSchema, String query) {
        ArrayList<Order> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT stud_id, stud_name, stud_surname, stud_lastname, status FROM students");
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String orderId = resultSet.getString(meta.getColumnName(1));
                String orderNum = resultSet.getString(meta.getColumnName(2));
                String orderType = resultSet.getString(meta.getColumnName(3));
                String orderDate = dateFormatLongText(resultSet.getString(meta.getColumnName(4)));
                String orderComment = resultSet.getString(meta.getColumnName(5));
                storage.add(new Order(orderId, orderNum, orderType, orderDate, orderComment));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public ArrayList<Order> getOrderList(String userSchema) {
        ArrayList<Order> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT stud_id, stud_name, stud_surname, stud_lastname, status FROM students");
            ResultSet resultSet = statement.executeQuery("SELECT order_id, order_num, order_type, order_date, order_comment FROM orders");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String orderId = resultSet.getString(meta.getColumnName(1));
                String orderNum = resultSet.getString(meta.getColumnName(2));
                String orderType = resultSet.getString(meta.getColumnName(3));
                String orderDate = dateFormatLongText(resultSet.getString(meta.getColumnName(4)));
                String orderComment = resultSet.getString(meta.getColumnName(5));
                storage.add(new Order(orderId, orderNum, orderType, orderDate, orderComment));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public ArrayList<Order> getOneOrder(String userSchema, String orderId) {
        ArrayList<Order> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT order_id, order_num, order_type, order_date, order_comment FROM orders WHERE order_id = '" + orderId + "'");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
//                String orderId = resultSet.getString(meta.getColumnName(1));
                String orderNum = resultSet.getString(meta.getColumnName(2));
                String orderType = resultSet.getString(meta.getColumnName(3));
                String orderDate = dateFormatLongText(resultSet.getString(meta.getColumnName(4)));
                String order_comment = resultSet.getString(meta.getColumnName(5));
                storage.add(new Order(orderId, orderNum, orderType, orderDate, order_comment));
            }

            System.out.println("List of orders:" + storage);

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
    }

    public HashMap<String, Boolean> getOrderListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT order_num FROM orders");
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

    public HashMap<String, Boolean> getOrderTypeListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT order_type FROM orders");
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

    public String retOrdersSortedQuery(HashMap<String, Boolean> orderType, HashMap<String, String> orderNumParam,
                                       HashMap<String, String> orderDateParam) {
        String query = "SELECT order_id, order_num, order_type, order_date, order_comment FROM orders";

        for (Map.Entry<String, String> entry : orderNumParam.entrySet()) {
            if (entry.getValue() != null) {
                if (!entry.getValue().equals("")) {
                    query = addQueryPart(query, "order_num", entry.getValue());
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : orderType.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "order_type", entry.getKey());
            }
        }

        for (Map.Entry<String, String> entry : orderDateParam.entrySet()) {
            if (entry.getValue() != null) {
                if (!entry.getValue().equals("")) {
                    query = addQueryPart(query, "order_date", entry.getValue());
                }
            }
        }

        query = qParamGroup(query);

        return query;
    }

    public void createNewOrder(String userSchema, String orderNum, String orderType, String orderDate, String orderComment) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();

            String query = "INSERT INTO orders (order_num, order_type, order_date, order_comment) " +
                    "VALUES ('" + orderNum + "' , '" + orderType + "' , '" + orderDate + "' , '" + orderComment + "')";
            if ((!orderNum.equals("")) && (orderNum != null) && (!orderType.equals("")) && (orderType != null) && (!orderDate.equals("")) && (orderDate != null)) {
                statement.executeUpdate(query);
                System.out.println("Query create speciality: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public void updateOrder(String userSchema, String orderId, String orderNum, String orderType, String orderDate, String orderComment) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();
            String query = "UPDATE orders SET order_num = '" + orderNum + "', order_type = '"
                    + orderType + "', order_date = '" + orderDate + "', order_comment = '" + orderComment + "' WHERE order_id = '" + orderId + "'";

            if ((!orderNum.equals("")) && (orderNum != null) && (!orderType.equals("")) && (orderType != null) && (!orderDate.equals("")) && (orderDate != null)) {
                statement.executeUpdate(query);
                System.out.println("Query update order: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public void removeOrder(String userSchema, String orderId) {
        Connection conn = startConnection();

        try {
            conn.setCatalog(userSchema);
            Statement statement = conn.createStatement();
            String query = "DELETE FROM orders WHERE order_id = '" + orderId + "'";
            System.out.println("Query delete group: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }


    /**
     * Getting parameters for sidebar on student list
     */

    public HashMap<String, Boolean> getSpecListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT stud_spec FROM students");
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

    public HashMap<String, Boolean> getStatusListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
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

    public HashMap<String, Boolean> getQualificationListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT stud_qual FROM students");
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

    public HashMap<String, Boolean> getCourseListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT stud_course FROM students");
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

    public HashMap<String, Boolean> getGroupListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT group_num FROM groups");
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

    public HashMap<String, Boolean> getSubgroupListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT subgroup FROM students");
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

    public HashMap<String, Boolean> getEducFormListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT stud_educ_form FROM students");
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

    public HashMap<String, Boolean> getFinanceListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
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

    public HashMap<String, Boolean> getCityListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT parent_city FROM students");
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

    public HashMap<String, Boolean> getStateListForTitle(String userSchema) {
        HashMap<String, Boolean> storage = new HashMap<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT DISTINCT parent_state FROM students");
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
                                 HashMap<String, Boolean> groupList, HashMap<String, Boolean> subgroupList,
                                 HashMap<String, String> cityParam, HashMap<String, String> stateParam) {

        if (retIfMapBoolTrue(groupList)) {
            query = query + " INNER JOIN groups gr ON st.group_id = gr.group_id";
        }

        for (Map.Entry<String, Boolean> entry : specList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.stud_spec", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : statusList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.status", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : qualList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.stud_qual", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : courseList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.stud_course", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : educFormList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.stud_educ_form", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : finList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.financing", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : groupList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "gr.group_num", entry.getKey());
            }
        }

        for (Map.Entry<String, Boolean> entry : subgroupList.entrySet()) {
            if (entry.getValue()) {
                query = addQueryPart(query, "st.subgroup", entry.getKey());
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

//    public Boolean retIfMapStrTrue(HashMap<String, String> hashMap) {
//        Boolean bool = false;
//        for (String value : hashMap.values()) {
//            if (value != null) {
//                if (!value.equals("")) {
//                    bool = true;
//                }
//            }
//        }
//        return bool;
//    }

    public String addQueryPart(String queryFull, String queryParam, String parameter) {
        if (parameter != null) {
            if (qParam(queryFull, queryParam)) {
                if (qParam(queryFull, "WHERE")) {
                    queryFull = queryFull + " OR " + queryParam + " = \"" + parameter + "\"";
                } else {
                    queryFull = queryFull + " WHERE " + queryParam + " = \"" + parameter + "\"";
                }
            } else {
                if (qParam(queryFull, "WHERE")) {
                    queryFull = queryFull + " AND " + queryParam + " = \"" + parameter + "\"";
                } else {
                    queryFull = queryFull + " WHERE " + queryParam + " = \"" + parameter + "\"";
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

    public ArrayList<Student> getOneStudent(String userSchema, String studId, Boolean bool) {
        ArrayList<Student> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT stud_id, stud_name, stud_surname, stud_lastname, " +
                    "entry_date, entry_order, graduate_date, graduate_order, status, group_id, stud_spec, " +
                    "stud_educ_form, stud_qual, stud_course, subgroup, financing, stud_book, birth_date, " +
                    "passport, passp_office, passp_date, identity_code, student_house, student_street, " +
                    "student_city, student_state, student_zip, student_country, stud_phone_1, stud_phone_2, " +
                    "father_name, father_surname, father_lastname, father_phone_1, father_phone_2, mother_name, " +
                    "mother_surname, mother_lastname, mother_phone_1, mother_phone_2, parent_house, parent_street, " +
                    "parent_city, parent_state, parent_zip, parent_country FROM students st WHERE st.stud_id = \"" + studId + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String id = resultSet.getString(meta.getColumnName(1));
                String name = resultSet.getString(meta.getColumnName(2));
                String surname = resultSet.getString(meta.getColumnName(3));
                String lastname = resultSet.getString(meta.getColumnName(4));
                String entryDate = resultSet.getString(meta.getColumnName(5));
                if (bool) {
                    entryDate = resultSet.getString(meta.getColumnName(5));
                } else {
                    entryDate = dateFormatLongText(resultSet.getString(meta.getColumnName(5)));
                }
                String entryOrder = resultSet.getString(meta.getColumnName(6));
                String graduateDate = resultSet.getString(meta.getColumnName(7));
                if (bool) {
                    graduateDate = resultSet.getString(meta.getColumnName(7));
                } else {
                    graduateDate = dateFormatLongText(resultSet.getString(meta.getColumnName(7)));
                }
                String graduateOrder = resultSet.getString(meta.getColumnName(8));
                String status = resultSet.getString(meta.getColumnName(9));
                String groupId = resultSet.getString(meta.getColumnName(10));
                String speciality = resultSet.getString(meta.getColumnName(11));
                String educForm = resultSet.getString(meta.getColumnName(12));
                String studQual = resultSet.getString(meta.getColumnName(13));
                String studCourse = resultSet.getString(meta.getColumnName(14));
                String subGroup = resultSet.getString(meta.getColumnName(15));
                String financing = resultSet.getString(meta.getColumnName(16));
                String studBook = resultSet.getString(meta.getColumnName(17));
                String birthDate = null;
                if (bool) {
                    birthDate = resultSet.getString(meta.getColumnName(18));
                } else {
                    birthDate = dateFormatLongText(resultSet.getString(meta.getColumnName(18)));
                }
                String passport = resultSet.getString(meta.getColumnName(19));
                String passpOffice = resultSet.getString(meta.getColumnName(20));
                String passpDate = null;
                if (bool) {
                    passpDate = resultSet.getString(meta.getColumnName(21));
                } else {
                    passpDate = dateFormatLongText(resultSet.getString(meta.getColumnName(21)));
                }
                String identityCode = resultSet.getString(meta.getColumnName(22));
                String studHouse = resultSet.getString(meta.getColumnName(23));
                String studStreet = resultSet.getString(meta.getColumnName(24));
                String studCity = resultSet.getString(meta.getColumnName(25));
                String studState = resultSet.getString(meta.getColumnName(26));
                String studZip = resultSet.getString(meta.getColumnName(27));
                String studCountry = resultSet.getString(meta.getColumnName(28));
                String studPhone1 = resultSet.getString(meta.getColumnName(29));
                String studPhone2 = resultSet.getString(meta.getColumnName(30));
                String fatherName = resultSet.getString(meta.getColumnName(31));
                String fatherSurname = resultSet.getString(meta.getColumnName(32));
                String fatherLastname = resultSet.getString(meta.getColumnName(33));
                String fatherPhone1 = resultSet.getString(meta.getColumnName(34));
                String fatherPhone2 = resultSet.getString(meta.getColumnName(35));
                String motherName = resultSet.getString(meta.getColumnName(36));
                String motherSurname = resultSet.getString(meta.getColumnName(37));
                String motherLastname = resultSet.getString(meta.getColumnName(38));
                String motherPhone1 = resultSet.getString(meta.getColumnName(39));
                String motherPhone2 = resultSet.getString(meta.getColumnName(40));
                String parentsHouse = resultSet.getString(meta.getColumnName(41));
                String parentsStreet = resultSet.getString(meta.getColumnName(42));
                String parentsCity = resultSet.getString(meta.getColumnName(43));
                String parentsState = resultSet.getString(meta.getColumnName(44));
                String parentsZip = resultSet.getString(meta.getColumnName(45));
                String parentsCountry = resultSet.getString(meta.getColumnName(46));
                storage.add(new Student(id, name, surname, lastname, entryDate, entryOrder, graduateDate, graduateOrder,
                        status, groupId, speciality, educForm, studQual, studCourse, subGroup,
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

    public ArrayList<Group> getOneStudentGroup(String userSchema, String studId) {
        ArrayList<Group> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groups gr INNER JOIN students st ON gr.group_id = st.group_id WHERE st.stud_id = '" + studId + "'");
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

    public ArrayList<Speciality> getOneStudentSpec(String userSchema, String studId) {
        ArrayList<Speciality> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(userSchema);
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
            String month = null;
            String day = null;
            String year = null;

            if (array.get(0) <= 12) {
                if (array.get(0) == 1) {
                    month = "січня";
                } else if (array.get(0) == 2) {
                    month = "лютого";
                } else if (array.get(0) == 3) {
                    month = "березня";
                } else if (array.get(0) == 4) {
                    month = "квітня";
                } else if (array.get(0) == 5) {
                    month = "травня";
                } else if (array.get(0) == 6) {
                    month = "червня";
                } else if (array.get(0) == 7) {
                    month = "липня";
                } else if (array.get(0) == 8) {
                    month = "серпня";
                } else if (array.get(0) == 9) {
                    month = "вересня";
                } else if (array.get(0) == 10) {
                    month = "жовтня";
                } else if (array.get(0) == 11) {
                    month = "листопада";
                } else if (array.get(0) == 12) {
                    month = "грудня";
                }
            }
            if (array.get(1) <= 31 && array.get(1) > 0) {
                if (array.get(1) < 10) {
                    day = "0" + array.get(1);
                } else {
                    day = "" + array.get(1);
                }
            }

            if (array.get(2) < 2050 && array.get(2) > 1950) {
                year = "" + array.get(2);
            }

            if (array.get(0) <= 12 && array.get(0) > 0 && array.get(1) <= 31 && array.get(1) > 0 && array.get(2) < 2050 && array.get(2) > 1950) {
                result = day + " " + month + " " + year + " р.";
            } else {
                result = "Недопустима дата";
            }
        }
        return result;
    }


    /**
     * Working with users accounts
     */

    public Boolean getUser(String email, String password) {
        boolean bool = false;

        Connection con = startConnection();
        try {
            try {
                con.setCatalog("Users");
            } catch (SQLException e) {
                String str = "" + e;
                if (str.equals("java.sql.SQLSyntaxErrorException: Unknown database 'users'")) {
                    createUserSchema();
                }
            }
            con.setCatalog("Users");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE user_email = \"" + email + "\" AND user_password = \"" + password + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String user_id = resultSet.getString(meta.getColumnName(1));
                String user_email = resultSet.getString(meta.getColumnName(2));
                String user_password = resultSet.getString(meta.getColumnName(3));
                String user_database = resultSet.getString(meta.getColumnName(4));

                if (user_email.equals(email) && user_password.equals(password)) {
                    bool = true;
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);

        return bool;
    }

    public ArrayList<User> getOneUser(String email, String password) {
        ArrayList<User> oneUser = new ArrayList<>();

        Connection con = startConnection();
        try {

            con.setCatalog("Users");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE user_email = \"" + email + "\" AND user_password = \"" + password + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String user_id = resultSet.getString(meta.getColumnName(1));
                String user_email = resultSet.getString(meta.getColumnName(2));
                String user_password = resultSet.getString(meta.getColumnName(3));
                String user_database = resultSet.getString(meta.getColumnName(4));

                oneUser.add(new User(user_id, user_email, user_password, user_database));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);
        return oneUser;
    }

    public void createNewUser(String email, String password) {
        Connection conn = startConnection();

        try {
            conn.setCatalog("Users");
        } catch (SQLException e) {
            String str = "" + e;
            if (str.equals("java.sql.SQLSyntaxErrorException: Unknown database 'users'")) {
                createUserSchema();
            }
        }

        try {
            conn.setCatalog("Users");
            Statement statement = conn.createStatement();

            if (email != null && !email.equals("") && password != null && !password.equals("")) {
                statement.executeUpdate("INSERT INTO users (user_email, user_password) VALUES (\"" + email + "\", \"" + password + "\")");
            }

            String user_id = "";
            ResultSet resultSet1 = statement.executeQuery("SELECT user_id FROM users WHERE user_email = \"" + email + "\" AND user_password = \"" + password + "\"");
            ResultSetMetaData meta1 = resultSet1.getMetaData();
            while (resultSet1.next()) {
                user_id = resultSet1.getString(meta1.getColumnName(1));
            }

            statement.executeUpdate("UPDATE users SET user_database = \"" + "StudDB" + user_id + "\" WHERE user_email = \"" + email + "\" AND user_password = \"" + password + "\"");

            String user_database = "";
            ResultSet resultSet2 = statement.executeQuery("SELECT user_database FROM users WHERE user_email = \"" + email + "\" AND user_password = \"" + password + "\"");
            ResultSetMetaData meta2 = resultSet2.getMetaData();
            while (resultSet2.next()) {
                user_database = resultSet2.getString(meta2.getColumnName(1));
            }
            createNewDB(user_database);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public void createNewDB(String dataBase) {
        Connection conn = startConnection();

        try {
            conn.setCatalog("Users");
            Statement statement = conn.createStatement();

            statement.executeUpdate("CREATE SCHEMA `" + dataBase + "` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci");
            statement.executeUpdate("CREATE TABLE `" + dataBase + "`.`specialities` (`id_spec` int(10) unsigned NOT NULL AUTO_INCREMENT, `spec_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL, PRIMARY KEY (`id_spec`), UNIQUE KEY `id_spec_UNIQUE` (`id_spec`)) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1");
            statement.executeUpdate("CREATE TABLE `" + dataBase + "`.`groups` (`group_id` int(10) unsigned NOT NULL AUTO_INCREMENT, `spec_id` int(10) unsigned DEFAULT NULL, `group_num` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `group_educ_form` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `group_qual` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `group_course` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `group_status` varchar(100) CHARACTER SET utf8 DEFAULT NULL, PRIMARY KEY (`group_id`), UNIQUE KEY `group_id_UNIQUE` (`group_id`), KEY `spec_id_idx` (`spec_id`), CONSTRAINT `spec_id_1` FOREIGN KEY (`spec_id`) REFERENCES `specialities` (`id_spec`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1");
            statement.executeUpdate("CREATE TABLE `" + dataBase + "`.`students` (`stud_id` int(10) unsigned NOT NULL AUTO_INCREMENT, `stud_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `stud_surname` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `stud_lastname` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `entry_date` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `entry_order` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `graduate_date` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `graduate_order` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `status` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `group_id` int(10) unsigned DEFAULT NULL, `stud_spec` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `stud_educ_form` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `stud_qual` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `stud_course` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `subgroup` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `financing` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `stud_book` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `birth_date` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `passport` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `passp_office` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `passp_date` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `identity_code` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `student_house` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `student_street` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `student_city` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `student_state` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `student_zip` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `student_country` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `stud_phone_1` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `stud_phone_2` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `father_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `father_surname` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `father_lastname` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `father_phone_1` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `father_phone_2` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `mother_name` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `mother_surname` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `mother_lastname` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `mother_phone_1` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `mother_phone_2` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `parent_house` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `parent_street` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `parent_city` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `parent_state` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `parent_zip` varchar(100) CHARACTER SET utf8 DEFAULT NULL, `parent_country` varchar(100) CHARACTER SET utf8 DEFAULT NULL, PRIMARY KEY (`stud_id`), UNIQUE KEY `stud_id_UNIQUE` (`stud_id`), KEY `group_id_1_idx` (`group_id`), KEY `parent_id_1_idx` (`father_name`), CONSTRAINT `group_id_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1");
            statement.executeUpdate("CREATE TABLE `" + dataBase + "`.`orders` (`order_id` int(10) unsigned NOT NULL AUTO_INCREMENT, `order_num` varchar(100) DEFAULT NULL, `order_type` varchar(100) DEFAULT NULL, `order_date` varchar(100) DEFAULT NULL, `order_comment` text, PRIMARY KEY (`order_id`), UNIQUE KEY `order_id_UNIQUE` (`order_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public Boolean testEmail(String email) {

        Boolean bool = false;

        char[] charList = email.toCharArray();
        for (int i = 0; i < charList.length; i++) {
            if (String.valueOf(charList[i]).equals("@")) {
                bool = true;
            }
        }

        return bool;
    }

    public Boolean testPassword(String password) {

        Boolean bool = false;

        char[] charList = password.toCharArray();
        if (charList.length >= 6) {
            bool = true;
        }

        return bool;
    }

    public void createUserSchema() {
        Connection conn = startConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE SCHEMA `Users` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci");
            statement.executeUpdate("CREATE TABLE `Users`.`users` (`user_id` int(10) unsigned NOT NULL AUTO_INCREMENT, `user_email` varchar(100) DEFAULT NULL, `user_password` varchar(100) DEFAULT NULL, `user_database` varchar(100) DEFAULT NULL, PRIMARY KEY (`user_id`), UNIQUE KEY `user_id_UNIQUE` (`user_id`)) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        stopConnection(conn);
    }
}