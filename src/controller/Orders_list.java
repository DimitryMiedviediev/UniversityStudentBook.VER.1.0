package controller;

import model.Beans;
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
import java.util.HashMap;

/**
 * Created by Dimitry on 09.03.17.
 */
public class Orders_list extends HttpServlet {
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
        } else if (req.getParameter("create_btn") != null){
            resp.sendRedirect("order_add");
        } else {

            ArrayList<User> thisUser = (ArrayList<User>) session.getAttribute("UserInfo");
            String userSchema = thisUser.get(0).getUser_database();

            HashMap<String, Boolean> orderTypeList = beans.getOrderTypeListForTitle(userSchema);
            for (String key : orderTypeList.keySet()) {
                if (req.getParameter("orderType=" + key) != null) {
                    orderTypeList.put(key, true);
                }
            }
            req.setAttribute("orderTypeList", orderTypeList);

            HashMap<String, String> orderNumParam = new HashMap<>();
            orderNumParam.put("orderNumParam", req.getParameter("orderNum"));
            req.setAttribute("orderNumParam", orderNumParam);

            req.setAttribute("orderNumList", beans.getOrderListForTitle(userSchema));

            HashMap<String, String> orderDateParam = new HashMap<>();
            orderDateParam.put("orderDateParam", req.getParameter("orderDate"));
            req.setAttribute("orderDateParam", orderDateParam);

            String modQuery = beans.retOrdersSortedQuery(orderTypeList, orderNumParam, orderDateParam);
            System.out.println(modQuery);
            req.setAttribute("orderList", beans.getOrderList(userSchema, modQuery));

            RequestDispatcher dispatcher = req.getRequestDispatcher("orders_list.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
