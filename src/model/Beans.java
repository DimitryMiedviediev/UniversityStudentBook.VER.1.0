package model;

import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Dimitry on 07.02.17.
 */
public class Beans {
    static GetTime time = new GetTime();

    private static Connection startConnection() {
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

    private static void stopConnection(Connection connection) {
        try {
            connection.close();
            if (connection.isClosed()) {
                System.out.println(time.getTime() + " --Connection to DB is disabled...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getStudList(String query) {

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

    public static String qJoins(String query, ArrayList<SortParams> params) {
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

    private static boolean qParam(String str, String word) {
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

    public static String qParamGroup(String query) {
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

    public static String addQueryPart(String queryFull, String queryParam, String parameter) {
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

    public static String addQueryPartText(String queryFull, String queryParam, String parameter) {
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

}
