package beans;

import entity.Order;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dimitry on 18.03.17.
 */
public class BeansOrderList {

    public List<Order> getOrderList(
            HashMap<String, Boolean> orderTypeList,
            String orderNumberParameter,
            String orderDateParameter) {

        String query = "FROM Order";
        String orderTypeQuery = null;
        String orderNumberQuery = null;
        String orderDateQuery = null;

        for (Map.Entry<String, Boolean> entry : orderTypeList.entrySet()) {
            if (entry.getValue()) {
                if (orderTypeQuery == null) {
                    orderTypeQuery = "'" + entry.getKey() + "'";
                } else if (orderTypeQuery != null) {
                    orderTypeQuery = orderTypeQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        if (orderNumberParameter != null && !orderNumberParameter.equals("")) {
            if (orderNumberQuery == null) {
                orderNumberQuery = "'" + orderNumberParameter + "'";
            } else if (orderNumberQuery != null) {
                orderNumberQuery = orderNumberQuery + ", " + "'" + orderNumberParameter + "'";
            }
        }

        if (orderDateParameter !=null && !orderDateParameter.equals("")) {
            if (orderDateQuery == null) {
                orderDateQuery = "'" + orderDateParameter + "'";
            } else if (orderDateQuery != null) {
                orderDateQuery = orderDateQuery + ", " + "'" + orderDateParameter + "'";
            }
        }

        query = addQueryPart(query, "orderType", orderTypeQuery);
        query = addQueryPart(query, "orderNumber", orderNumberQuery);
        query = addQueryPart(query, "orderDate", orderDateQuery);

        System.out.println("?????????????");
        System.out.println(query);
        System.out.println("?????????????");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<Order> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            studentList = session.createQuery(query).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return studentList;
    }

    public HashMap<String, Boolean> getOrderTypesListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Order> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Order").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getOrderType(), false);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return storage;
    }

    public HashMap<String, Boolean> getOrderNumbersListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Order> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Order").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getOrderNumber(), false);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return storage;
    }

    public HashMap<String, Boolean> getOrderDateListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Order> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Order").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getOrderDate(), false);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return storage;
    }

    public String addQueryPart(String queryFull, String queryParam, String parameter) {
        if (parameter != null) {
            if (qParam(queryFull, "WHERE")) {
                queryFull = queryFull + " AND " + queryParam + " IN (" + parameter + ")";
            } else {
                queryFull = queryFull + " WHERE " + queryParam + " IN (" + parameter + ")";
            }
        }
        return queryFull;
    }

    private boolean qParam(String str, String word) {
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
}
