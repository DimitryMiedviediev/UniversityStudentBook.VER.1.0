package controller.order;

import beans.order.BeansOrderConfiguration;

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
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                dispatcher.forward(req, resp);

            } else if (req.getParameter("create") != null) {
                req.setAttribute("createType", "createType");
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("create_type_title") != null) {
                if (req.getParameter("order_type") != null && !req.getParameter("order_type").equals("")) {
                    beans.createNewOrderType(req.getParameter("order_type"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("createType", "createType");
                    req.setAttribute("wrongType", req.getParameter("order_type"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("update") != null) {
                req.setAttribute("updateType", "updateType");
                req.setAttribute("orderTypesList", beans.getOrderTypesList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("update_option") != null) {
                if(req.getParameter("update_order_type") != null){
                    req.setAttribute("updateType", "updateType");
                    req.setAttribute("updateTypeArea", "updateTypeArea");
                    req.setAttribute("orderToUpdate", beans.readOneOrderType(Integer.parseInt(req.getParameter("update_order_type"))));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("updateType", "updateType");
                    req.setAttribute("orderTypesList", beans.getOrderTypesList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                    dispatcher.forward(req, resp);
                }

            } else if (req.getParameter("update_save") != null) {
                if (req.getParameter("order_type_title") != null && !req.getParameter("order_type_title").equals("")) {
                    beans.updateOrderType(Integer.parseInt(req.getParameter("order_type_id")), req.getParameter("order_type_title"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("updateType", "updateType");
                    req.setAttribute("updateTypeArea", "updateTypeArea");
                    req.setAttribute("updateTypeAreaError", "updateTypeAreaError");
                    req.setAttribute("typeTitleError", req.getParameter("order_type_title"));
                    req.setAttribute("typeIdError", req.getParameter("order_type_id"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("delete") != null) {
                req.setAttribute("deleteType", "deleteType");
                req.setAttribute("orderTypesList", beans.getOrderTypesList());
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("delete_option") != null) {
                if(req.getParameter("delete_order_type") != null){
                    beans.deleteOrderType(Integer.parseInt(req.getParameter("delete_order_type")));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                    dispatcher.forward(req, resp);
                } else {
                    req.setAttribute("deleteType", "deleteType");
                    req.setAttribute("orderTypesList", beans.getOrderTypesList());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                    dispatcher.forward(req, resp);
                }
            } else if (req.getParameter("cancel") != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/order/order_config.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("order_list");
            }
        }
    }
}
