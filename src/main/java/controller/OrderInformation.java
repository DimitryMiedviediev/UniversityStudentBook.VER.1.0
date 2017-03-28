package controller;

import beans.BeansOrderInformation;
import entity.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Dimitry on 19.03.17.
 */
@WebServlet(
        name = "Order information",
        description = "This is order information servlet",
        urlPatterns = {"/order_create", "/order_info"}
)
public class OrderInformation extends HttpServlet {
    BeansOrderInformation beans = new BeansOrderInformation();

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
            resp.sendRedirect("authorization");
        } else {
            if (req.getParameter("order_create") != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_create.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("back_to_list") != null) {
                resp.sendRedirect("order_list");
            } else if (req.getParameter("clear_new_order") != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_create.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("save_new_order") != null) {

                if (req.getParameter("orderNumber") != null &&
                        !req.getParameter("orderNumber").equals("") &&
                        req.getParameter("orderType") != null &&
                        !req.getParameter("orderType").equals("") &&
                        req.getParameter("orderDate") != null &&
                        !req.getParameter("orderDate").equals("")) {

                    beans.createNewOrder(
                            req.getParameter("orderNumber"),
                            req.getParameter("orderType"),
                            req.getParameter("orderDate"),
                            req.getParameter("orderComment"));
                    resp.sendRedirect("order_list");
                } else {
                    Order orderWithError = new Order(req.getParameter("orderNumber"),
                            req.getParameter("orderType"),
                            req.getParameter("orderDate"),
                            req.getParameter("orderComment"));
                    req.setAttribute("orderWithError", orderWithError);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_create_error.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("delete_btn") != null) {
                beans.deleteOrder(Integer.parseInt(req.getParameter("order_id")));
                resp.sendRedirect("order_list");
            } else if (req.getParameter("back_to_info_btn") != null) {
                Order order = beans.getOrder(Integer.parseInt(req.getParameter("order_id")));
                req.setAttribute("orderObject", order);
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_info.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("update_btn") != null) {
                Order order = beans.getOrder(Integer.parseInt(req.getParameter("order_id")));
                req.setAttribute("orderObject", order);
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_edit.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("save_update_btn") != null) {
                if (req.getParameter("orderNumber") != null &&
                        !req.getParameter("orderNumber").equals("") &&
                        req.getParameter("orderType") != null &&
                        !req.getParameter("orderType").equals("") &&
                        req.getParameter("orderDate") != null &&
                        !req.getParameter("orderDate").equals("")) {
                    beans.updateOrder(Integer.parseInt(req.getParameter("order_id")),
                            req.getParameter("orderNumber"),
                            req.getParameter("orderType"),
                            req.getParameter("orderDate"),
                            req.getParameter("orderComment"));
                    Order order = beans.getOrder(Integer.parseInt(req.getParameter("order_id")));
                    req.setAttribute("orderObject", order);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_info.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    Order order = new Order(req.getParameter("orderNumber"),
                            req.getParameter("orderDate"),
                            req.getParameter("orderType"),
                            req.getParameter("orderComment"));
                    req.setAttribute("orderObject", order);
                    req.setAttribute("order_id", req.getParameter("order_id"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_edit_error.jsp");
                    dispatcher.forward(req, resp);
                }
            } else {
                if (req.getParameter("order_id") != null) {
                    Order order = beans.getOrder(Integer.parseInt(req.getParameter("order_id")));
                    req.setAttribute("orderObject", order);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_info.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    resp.sendRedirect("order_list");
                }

            }
        }
    }
}
