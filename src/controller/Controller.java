package controller;

import model.*;

import javax.activation.DataSource;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by Dimitry on 06.02.17.
 */
public class Controller extends HttpServlet {

    Beans beans = new Beans();
    GetTime time = new GetTime();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        StringWriter sWriter = new StringWriter();
        PrintWriter out = new PrintWriter(sWriter);

        response.getWriter().print(sWriter.toString());

        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \n" + "  \"http://www.w3.org/TR/html4/strict.dtd\">");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        out.println("<title>controller</title>");

        out.println("</head>");
        out.println("<body>");

//        for (int i = 0; i<storage.size(); i++){
//            System.out.println(storage.get(i).getName() + " " + storage.get(i).getSurname() + " " + storage.get(i).getLastname() + " " + storage.get(i).getStatus());
//        }

        response.sendRedirect("main_page");


        out.println("</body>");
        out.println("</html>");
    }

    public ArrayList<Student> getStudList(){
        ArrayList<Student> storage = beans.getStudLIst();
        System.out.println("---------------------");
        System.out.println(time.getTime() + " Returning on the \"Main Page\" list of students:");

        for (int i = 0; i < storage.size(); i++) {
            System.out.println(storage.get(i).getName() + " " + storage.get(i).getSurname() + " " + storage.get(i).getLastname() + " " + storage.get(i).getStatus());
        }
        System.out.println(time.getTime() + " End of DB activity");
        System.out.println("---------------------");
        return beans.getStudLIst();
    }
}

