package controller;

import beans.BeansOrderConfiguration;
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
 * Created by Dimitry on 31.03.17.
 */
@WebServlet(
        name = "Order configuration",
        description = "This is order configuration servlet",
        urlPatterns = {"/order_configuration"}
)
public class OrderConfiguration extends HttpServlet {
    BeansOrderConfiguration beans = new BeansOrderConfiguration();

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
            if (req.getParameter("order_config") != null) {
                req.setAttribute("orderTypesList", beans.getOrderTypesList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_config.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("create_order_type_btn") != null) {
                if (req.getParameter("create_order_type") != null && !req.getParameter("create_order_type").equals("")) {
                    beans.createNewOrderType(req.getParameter("create_order_type"));
                    req.setAttribute("orderTypesList", beans.getOrderTypesList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("orderTypeWithError", req.getParameter("create_order_type"));
                    req.setAttribute("orderTypesList", beans.getOrderTypesList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_config_error.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("update_order_type_btn") != null) {
                req.setAttribute("update_order_type", beans.readOneOrderType(Integer.parseInt(req.getParameter("update_order_type"))));
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_edit_title.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("delete_order_type_btn") != null) {
                beans.deleteOrderType(Integer.parseInt(req.getParameter("delete_order_type")));
                req.setAttribute("orderTypesList", beans.getOrderTypesList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_config.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("update_smth_order_type_title") != null) {
                if (req.getParameter("order_type_title") != null && !req.getParameter("order_type_title").equals("")){
                    beans.updateOrderType(Integer.parseInt(req.getParameter("order_type_id")), req.getParameter("order_type_title"));
                    req.setAttribute("orderTypesList", beans.getOrderTypesList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("order_type_id_error", Integer.parseInt(req.getParameter("order_type_id")));
                    req.setAttribute("order_type_title_error", req.getParameter("order_type_title"));

                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order_edit_title_error.jsp");
                    dispatcher.forward(req, resp);
                }

            } else if (req.getParameter("delete_order_type_btn") != null) {
                beans.deleteOrderType(Integer.parseInt(req.getParameter("delete_order_type")));
                resp.sendRedirect("order_list");

            } else {
                resp.sendRedirect("order_list");
            }
        }
    }
}
