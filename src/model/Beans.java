package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Dimitry on 07.02.17.
 */
public class Beans {
    GetTime time = new GetTime();

    public Connection startConnection() {
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

    public void stopConnection(Connection connection) {
        try {
            connection.close();
            if (connection.isClosed()) {
                System.out.println(time.getTime() + " --Connection to DB is disabled...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudLIst() {

        ArrayList<Student> storage = new ArrayList<>();

        Connection con = startConnection();
        try {
            con.setCatalog("test");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT stud_name, stud_surname, stud_lastname, stud_status FROM students");
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

}
