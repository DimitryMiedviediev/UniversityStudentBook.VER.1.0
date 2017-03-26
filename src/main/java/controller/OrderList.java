package controller;

import beans.BeansOrderList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dimitry on 09.03.17.
 */
@WebServlet(
        name = "Order list",
        description = "This is order list servlet",
        urlPatterns = "/order_list"
)
public class OrderList extends HttpServlet {
    BeansOrderList beans = new BeansOrderList();

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
            System.out.println();
            if (req.getParameter("order_sort") != null) {
                HashMap<String, Boolean> orderTypeList = beans.getOrderTypesListForTitle();
                for (String key : orderTypeList.keySet()) {
                    if (req.getParameter("orderType=" + key) != null) {
                        orderTypeList.put(key, true);
                    }
                }
                req.setAttribute("orderTypeList", orderTypeList);

                HashMap<String, Boolean> orderNumberList = beans.getOrderNumbersListForTitle();
                req.setAttribute("orderNumberList", orderNumberList);

                HashMap<String, Boolean> orderDateList = beans.getOrderDateListForTitle();
                req.setAttribute("orderDateList", orderDateList);

                String orderNumberParameter = req.getParameter("orderNumber");
                req.setAttribute("orderNumberParameter", orderNumberParameter);

                String orderDateParameter = req.getParameter("orderDate");
                req.setAttribute("orderDateParameter", orderDateParameter);

                req.setAttribute("orderList", beans.getOrderList(orderTypeList, orderNumberParameter, orderDateParameter));

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/orders_list.jsp");
                dispatcher.forward(req, resp);
            } else if (req.getParameter("order_create") != null){
                resp.sendRedirect("order_create");
            } else{
                HashMap<String, Boolean> orderTypeList = beans.getOrderTypesListForTitle();
                for (String key : orderTypeList.keySet()) {
                    if (req.getParameter("orderType=" + key) != null) {
                        orderTypeList.put(key, true);
                    }
                }
                req.setAttribute("orderTypeList", orderTypeList);

                HashMap<String, Boolean> orderNumberList = beans.getOrderNumbersListForTitle();
                req.setAttribute("orderNumberList", orderNumberList);

                HashMap<String, Boolean> orderDateList = beans.getOrderDateListForTitle();
                req.setAttribute("orderDateList", orderDateList);

                String orderNumberParameter = req.getParameter("orderNumber");
                req.setAttribute("orderNumberParameter", orderNumberParameter);

                String orderDateParameter = req.getParameter("orderDate");
                req.setAttribute("orderDateParameter", orderDateParameter);

                req.setAttribute("orderList", beans.getOrderList(orderTypeList, orderNumberParameter, orderDateParameter));

                RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/view/orders_list.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
