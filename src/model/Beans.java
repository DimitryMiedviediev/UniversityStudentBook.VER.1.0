package model;

import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Dimitry on 07.02.17.
 */
public class Beans {
    GetTime time = new GetTime();

    private Connection startConnection() {
        Connection connection = null;
        try {
            String driver = "com.mysql.jdbc.Driver";
            String username = "";
            String password = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false", "root", "");
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

    public ArrayList<Student> getStudList(String query) {

        ArrayList<Student> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog("test");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData meta = resultSet.getMetaData();

            while (resultSet.next()) {
                String name = resultSet.getString(meta.getColumnName(1));
                String surname = resultSet.getString(meta.getColumnName(2));
                String lastname = resultSet.getString(meta.getColumnName(3));
                String status = resultSet.getString(meta.getColumnName(4));
                storage.add(new Student(name, surname, lastname, status));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        stopConnection(con);


        return storage;
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
            conn.setCatalog("studDB");
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
            con.setCatalog("studDB");
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

    public ArrayList<Speciality> getOneSpec(String nameSpec){
        ArrayList<Speciality> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog("studDB");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM specialities WHERE spec_name = \""+ nameSpec + "\"");
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
            conn.setCatalog("studDB");
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

    public void deleteSpec(String nameSpec){
        Connection conn = startConnection();

        try {
            conn.setCatalog("studDB");
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
            conn.setCatalog("studDB");
            Statement statement = conn.createStatement();

            String specId = null;
            ResultSet resultSet = statement.executeQuery("SELECT id_spec FROM specialities WHERE spec_name = \""+ speciality + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();
            while (resultSet.next()) {
                specId = resultSet.getString(meta.getColumnName(1));
            }

            String query = "INSERT INTO groups (spec_id, group_num, group_educ_form, group_qual, group_course) " +
                    "VALUES (\"" + specId + "\" , \"" + groupNum + "\" , \"" + groupEducForm + "\" , \"" + groupEducQual + "\" , \"" + groupCourse + "\")";
            if ((!specId.equals(""))&&(!groupNum.equals(""))&&(!groupCourse.equals(""))) {
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
            con.setCatalog("studDB");
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

    public ArrayList<Group> getOneGroup(String groupNum){
        ArrayList<Group> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog("studDB");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM groups WHERE group_num = \""+ groupNum + "\"");
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
            conn.setCatalog("studDB");
            Statement statement = conn.createStatement();
            String specId = null;
            ResultSet resultSet = statement.executeQuery("SELECT id_spec FROM specialities WHERE spec_name = \""+ specName + "\"");
            ResultSetMetaData meta = resultSet.getMetaData();
            while (resultSet.next()) {
                specId = resultSet.getString(meta.getColumnName(1));
            }
            String query = "UPDATE groups SET spec_id = \"" + specId + "\", group_num = \"" + groupNum + "\", group_educ_form = \""
                    + groupEducForm + "\", group_qual = \"" + groupEducQual + "\", group_course = \"" + groupCourse + "\" WHERE group_id = \"" + groupId + "\"";
            if ((!groupNum.equals(""))&&(!groupCourse.equals(""))) {
                statement.executeUpdate(query);
                System.out.println("Query edit group: " + query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

    public void deleteGroup(String groupNum){
        Connection conn = startConnection();

        try {
            conn.setCatalog("studDB");
            Statement statement = conn.createStatement();
            String query = "DELETE FROM groups WHERE group_num = \"" + groupNum + "\"";
            System.out.println("Query delete group: " + query);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stopConnection(conn);
    }

}
