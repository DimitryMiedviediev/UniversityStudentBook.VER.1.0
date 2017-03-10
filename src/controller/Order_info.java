package controller;

import model.Beans;
import model.classes.Group;
import model.classes.Order;
import model.classes.Student;
import model.classes.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Dimitry on 10.03.17.
 */
public class Order_info extends HttpServlet {
    Beans beans = new Beans();

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
            resp.sendRedirect("sign_in.jsp");
        } else {

            if (req.getParameter("update_btn") != null) {
                
            } else if (req.getParameter("remove_btn") != null) {

            } else if (req.getParameter("back_btn") != null) {
                resp.sendRedirect("orders_list");
            } else {
                ArrayList<User> thisUser = (ArrayList<User>) session.getAttribute("UserInfo");
                String userSchema = thisUser.get(0).getUser_database();

                ArrayList<Order> oneOrder = beans.getOneOrder(userSchema, req.getParameter("order_id"));
                req.setAttribute("oneOrder", oneOrder);

                RequestDispatcher dispatcher = req.getRequestDispatcher("order_info.jsp");
                dispatcher.forward(req, resp);
            }

        }
    }
}
