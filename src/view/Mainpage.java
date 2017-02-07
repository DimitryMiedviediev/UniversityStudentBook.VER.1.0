package view;

import controller.Controller;
import model.Beans;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Dimitry on 06.02.17.
 */
public class Mainpage extends HttpServlet {

    Controller controller = new Controller();
    ArrayList<Student> studList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->");
        out.println("<title>Main page</title>");

        out.println("<!-- Bootstrap -->");
        out.println("<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("<link href=\"css/users.css\" rel=\"stylesheet\">");
        out.println("<link href=\"css/sidebar.css\" rel=\"stylesheet\">");

        out.println("<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->");
        out.println("<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->");
        out.println("<!--[if lt IE 9]>");
        out.println("<script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>");
        out.println("<script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>");
        out.println("<![endif]-->");
        out.println("</head>");
        out.println("<body>");

        out.println("<!-- Fixed top navbar -->");
        out.println("<nav class=\"navbar navbar-default navbar-fixed-top\">");
        out.println("<div class=\"container\">");
        out.println("<div class=\"navbar-header\">");
        out.println("<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\" aria-expanded=\"false\">");
        out.println("<span class=\"sr-only\">Toggle navigation</span>");
        out.println("<span class=\"icon-bar\"></span>");
        out.println("<span class=\"icon-bar\"></span>");
        out.println("<span class=\"icon-bar\"></span>");
        out.println("</button>");
        out.println("</div>");

        out.println("<!-- Collect the nav links, forms, and other content for toggling -->");
        out.println("<div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
        out.println("<ul class=\"nav navbar-nav\">");
        out.println("<li class=\"dropdown\">");
        out.println("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Mechanics <span class=\"caret\"></span></a>");
        out.println("<ul class=\"dropdown-menu\">");
        out.println("<li><a href=\"#\">Mechanics</a></li>");
        out.println("<li><a href=\"#\">Archive</a></li>");
        out.println("</ul>");
        out.println("</li>");
        out.println("<li class=\"dropdown\">");
        out.println("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Agro-Ingeneria <span class=\"caret\"></span></a>");
        out.println("<ul class=\"dropdown-menu\">");
        out.println("<li><a href=\"#\">Agro-Ingeneria</a></li>");
        out.println("<li><a href=\"#\">Archive</a></li>");
        out.println("</ul>");
        out.println("</li>");
        out.println("</ul>");
        out.println("<ul class=\"nav navbar-nav navbar-right\">");
        out.println("<li><a href=\"controller\">Add new student</a></li>");
        out.println("<li><a href=\"#\">LogOut</a></li>");
        out.println("</ul>");
        out.println("</div><!-- /.navbar-collapse -->");
        out.println("</div><!-- /.container-collapse -->");
        out.println("</nav>");

        out.println("<!-- Header Jumbotron -->");
        out.println("<div class=\"jumbotron text-center\">");
        out.println("<h2>Student Database</h2>");
        out.println("<p>It's a page list of all students that are in your university!</p>");
        out.println("</div>");

        out.println("<!-- Body -->");
        out.println("<div class=\"container\">");
        out.println("<div class=\"row\">");
        out.println("<div class=\"col-sm-2\">");

        out.println("<!-- Sidebar -->");
        out.println("<div class=\"list-group\">");
        out.println("<h5>Qualification</h5>");
        out.println("<ul>");
        out.println("<li><h6><input type=\"checkbox\"> Bachelor </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> Specialist </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> Master </h6></li>");
        out.println("</ul>");
        out.println("<h5>Courses</h5>");
        out.println("<ul>");
        out.println("<li><h6><input type=\"checkbox\"> 1 course </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> 2 course </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> 3 course </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> 4 course </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> 5 course </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> 6 course </h6></li>");
        out.println("</ul>");
        out.println("<h5>Group</h5>");
        out.println("<ul>");
        out.println("<li><input type=\"text\" placeholder=\"Group\"></li>");
        out.println("</ul>");
        out.println("<h5>Financing</h5>");
        out.println("<ul>");
        out.println("<li><h6><input type=\"checkbox\"> State </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> Commercial </h6></li>");
        out.println("</ul>");
        out.println("<h5>Education form</h5>");
        out.println("<ul>");
        out.println("<li><h6><input type=\"checkbox\"> Full-time </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> Distance </h6></li>");
        out.println("</ul>");
        out.println("<h5>Birthplace</h5>");
        out.println("<ul>");
        out.println("<li><input type=\"text\" placeholder=\"State\"></li>");
        out.println("<li><input type=\"text\" placeholder=\"Department\"></li>");
        out.println("</ul>");
        out.println("<h5>Other categories</h5>");
        out.println("<ul>");
        out.println("<li><h6><input type=\"checkbox\"> Military </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> Orphan </h6></li>");
        out.println("<li><h6><input type=\"checkbox\"> Disabled </h6></li>");
        out.println("</ul>");
        out.println("<button type=\"button\" class=\"btn btn-default btn-block\">Sort</button>");

        out.println("</div>");

        out.println("</div>");
        out.println("<div class=\"col-sm-10\">");

        out.println("<!-- Tables -->");
        out.println("<div class=\"container\">");
        out.println("<table class=\"table table-hover\">");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Surname</th>");
        out.println("<th>Firstname</th>");
        out.println("<th>Lastname</th>");
        out.println("<th>Status</th>");
        out.println("</tr>");
        out.println("</thead>");


        out.println("<tbody>");

        out.println("<div>");
//        out.println("<tr>");
//        out.println("<td><a href=\"controller\">Miedviediev</a></td>");
//        out.println("<td><a href=\"controller\">Dimitry</a></td>");
//        out.println("<td><a href=\"controller\">Michailovich</a></td>");
//        out.println("<td><a href=\"controller\">Educate</a></td>");
//        out.println("</tr>");
//        out.println("</div>");
//        out.println("</a>");
//        out.println("<tr>");
//        out.println("<td><a href=\"controller\">Tsimbrovsky</a></td>");
//        out.println("<td><a href=\"controller\">Artem</a></td>");
//        out.println("<td><a href=\"controller\">Igorovich</a></td>");
//        out.println("<td><a href=\"controller\">Educate</a></td>");
//        out.println("</tr>");
//        out.println("<tr>");
//        out.println("<td><a href=\"controller\">Kaminska</a></td>");
//        out.println("<td><a href=\"controller\">Marina</a></td>");
//        out.println("<td><a href=\"controller\">Vasylivna</a></td>");
//        out.println("<td><a href=\"controller\">Educate</a></td>");
//        out.println("</tr>");

        studList = controller.getStudList();
        for (int i = 0; i < studList.size(); i++) {
            out.println("<tr>");
            out.println("<td><a href=\"controller\">" + studList.get(i).getName() + "</a></td>");
            out.println("<td><a href=\"controller\">" + studList.get(i).getSurname() + "</a></td>");
            out.println("<td><a href=\"controller\">" + studList.get(i).getLastname() + "</a></td>");
            out.println("<td><a href=\"controller\">" + studList.get(i).getStatus() + "</a></td>");
            out.println("</tr>");
        }

        out.println("</tbody>");


        out.println("</table>");
        out.println("</div>");

        out.println("</div>");
        out.println("</div>");
        out.println("</div>");

        out.println("<!-- Footer Jumbotron -->");
        out.println("<div class=\"jumbotron text-center\">");
        out.println("<p>To collaboration call: +38 (097) 709-18-32</p>");
        out.println("</div>");


        out.println("<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js\"></script>");
        out.println("<!-- Include all compiled plugins (below), or include individual files as needed -->");
        out.println("<script src=\"js/sidebar.js\"></script>");
        out.println("<script src=\"js/bootstrap.min.js\"></script>");
        out.println("</body>");
        out.println("</html>");
    }
}