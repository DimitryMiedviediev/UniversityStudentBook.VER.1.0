package model;

import model.classes.Group;
import model.classes.Speciality;
import model.classes.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    public String qJoins(String query, ArrayList<SortParams> params) {
        SortParams sp = params.get(0);

        if (sp.getBachelor() != null || sp.getSpecialist() != null || sp.getMaster() != null || sp.getFull_time() != null ||
                sp.getDistance() != null || sp.getGovernment() != null || sp.getCommercial() != null ||
                sp.getCourse1() != null || sp.getCourse2() != null || sp.getCourse3() != null ||
                sp.getCourse4() != null || sp.getCourse5() != null || sp.getCourse6() != null) {
            query = query + " INNER JOIN unfiles un ON stud.stud_id = un.stud_id";
        }
        if (sp.getOrphan() != null || sp.getDisabled() != null) {
            query = query + " INNER JOIN exemptions ex ON stud.stud_id = ex.stud_id";
        }
        if (sp.getGroup() != null && sp.getSubgroup() != null) {
            if (!sp.getGroup().equals("") || !sp.getSubgroup().equals("") || sp.getMechanics() != null || sp.getEngineers() != null) {
                if (sp.getMechanics() != null || sp.getEngineers() != null) {
                    query = query + " INNER JOIN groups gr ON stud.stud_id = gr.stud_id";
                    query = query + " INNER JOIN specialities spec ON gr.spec_id = spec.spec_id";
                } else if (!sp.getGroup().equals("") || !sp.getSubgroup().equals("")) {
                    query = query + " INNER JOIN groups gr ON stud.stud_id = gr.stud_id";
                }
            }
        }

        return query;
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

    public String addQueryPartText(String queryFull, String queryParam, String parameter) {
        if (parameter != null) {
            if (!parameter.equals("")) {
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
        }

        return queryFull;
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
            //Return groupID
            String groupId = null;
            ResultSet resultSet1 = statement.executeQuery("SELECT group_id FROM groups WHERE group_num = \"" + studentGroup + "\"");
            ResultSetMetaData meta1 = resultSet1.getMetaData();
            while (resultSet1.next()) {
                groupId = resultSet1.getString(meta1.getColumnName(1));
            }

            //Create student
            String queryStudent = "INSERT INTO students (stud_name, stud_surname, stud_lastname, entry_date, " +
                    "status, group_id, subgroup, financing, stud_book, birth_date, passport, passp_office, passp_date, " +
                    "identity_code, student_house, student_street, student_city, student_state, student_zip, " +
                    "student_country, stud_phone_1, stud_phone_2, father_name, father_surname, father_lastname, father_phone_1, " +
                    "father_phone_2, mother_name, mother_surname, mother_lastname, mother_phone_1, mother_phone_2, " +
                    "parent_house, parent_street, parent_city, parent_state, parent_zip, parent_country) " +
                    "VALUES (\"" + studentName + "\" , \"" + studentSurname + "\" , \"" + studentLastname +
                    "\" , \"" + entryDate + "\" , \"" + studentStatus + "\" , \"" + groupId +
                    "\" , \"" + studentSubgroup + "\" , \"" + studentFinancing + "\" , \"" + studentBook +
                    "\" , \"" + dateBirth + "\" , \"" + passpSerial + "\" , \"" + passpOffice +
                    "\" , \"" + passpDateRelease + "\" , \"" + identityCode + "\" , \"" + studentHouse +
                    "\" , \"" + studentStreet + "\" , \"" + studentCity + "\" , \"" + studentState +
                    "\" , \"" + studentZip + "\" , \"" + studentCountry + "\" , \"" + studentPhone1 +
                    "\" , \"" + studentPhone2 + "\" , \"" + fatherName +
                    "\" , \"" + fatherSurname + "\" , \"" + fatherLastname +
                    "\" , \"" + fatherPhone1 + "\" , \"" + fatherPhone2 + "\" , \"" + motherName +
                    "\" , \"" + motherSurname + "\" , \"" + motherLastname + "\" , \"" + motherPhone1 +
                    "\" , \"" + motherPhone2 + "\" , \"" + parentHouse + "\" , \"" + parentStreet +
                    "\" , \"" + parentCity + "\" , \"" + parentState + "\" , \"" + parentZip +
                    "\" , \"" + parentCountry + "\")";
            System.out.println(queryStudent);
            if (studentName != null && studentSurname != null && studentLastname != null && studentStatus != null && studentGroup != null && studentFinancing != null && studentBook != null) {
                statement.executeUpdate(queryStudent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);

    }

    public ArrayList<Student> getStudList() {

        ArrayList<Student> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog(catalog);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT stud_id, stud_name, stud_surname, stud_lastname, status FROM students");
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

}
