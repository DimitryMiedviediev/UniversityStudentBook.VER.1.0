package beans.group;

import entity.group.Group;
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
public class BeansGroupList {
    public List<Student> getGroupList(
            HashMap<String, Boolean> groupSpecialityList,
            HashMap<String, Boolean> groupEducationFormList,
            HashMap<String, Boolean> groupQualificationLevelList,
            HashMap<Integer, Boolean> groupNumberList,
            HashMap<Integer, Boolean> groupCourseList,
            HashMap<String, Boolean> groupStatusList
    ) {

        String query = "FROM Group";
        String groupSpecialityQuery = null;
        String groupEducationFormQuery = null;
        String groupQualificationLevelQuery = null;
        String groupNumberQuery = null;
        String groupCourseQuery = null;
        String groupStatusQuery = null;

        for (Map.Entry<String, Boolean> entry : groupSpecialityList.entrySet()) {
            if (entry.getValue()) {
                if (groupSpecialityQuery == null) {
                    groupSpecialityQuery = "'" + entry.getKey() + "'";
                } else if (groupSpecialityQuery != null) {
                    groupSpecialityQuery = groupSpecialityQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : groupEducationFormList.entrySet()) {
            if (entry.getValue()) {
                if (groupEducationFormQuery == null) {
                    groupEducationFormQuery = "'" + entry.getKey() + "'";
                } else if (groupEducationFormQuery != null) {
                    groupEducationFormQuery = groupEducationFormQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : groupQualificationLevelList.entrySet()) {
            if (entry.getValue()) {
                if (groupQualificationLevelQuery == null) {
                    groupQualificationLevelQuery = "'" + entry.getKey() + "'";
                } else if (groupQualificationLevelQuery != null) {
                    groupQualificationLevelQuery = groupQualificationLevelQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<Integer, Boolean> entry : groupNumberList.entrySet()) {
            if (entry.getValue()) {
                if (groupNumberQuery == null) {
                    groupNumberQuery = entry.getKey().toString();
                } else if (groupNumberQuery != null) {
                    groupNumberQuery = groupNumberQuery + ", " + entry.getKey();
                }
            }
        }

        for (Map.Entry<Integer, Boolean> entry : groupCourseList.entrySet()) {
            if (entry.getValue()) {
                if (groupCourseQuery == null) {
                    groupCourseQuery = entry.getKey().toString();
                } else if (groupCourseQuery != null) {
                    groupCourseQuery = groupCourseQuery + ", " + entry.getKey();
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : groupStatusList.entrySet()) {
            if (entry.getValue()) {
                if (groupStatusQuery == null) {
                    groupStatusQuery = "'" + entry.getKey() + "'";
                } else if (groupStatusQuery != null) {
                    groupStatusQuery = groupStatusQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }



        query = addQueryPart(query, "speciality.title", groupSpecialityQuery);
        query = addQueryPart(query, "groupEducationForm.groupEducationFormTitle", groupEducationFormQuery);
        query = addQueryPart(query, "groupQualificationLevel.groupQualificationLevelTitle", groupQualificationLevelQuery);
        query = addQueryPart(query, "groupNumber", groupNumberQuery);
        query = addQueryPart(query, "groupCourse", groupCourseQuery);
        query = addQueryPart(query, "groupStatus.groupStatusTitle", groupStatusQuery);

//        System.out.println("?????????????");
//        System.out.println(query);
//        System.out.println("?????????????");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<Student> studentList = null;
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

    public HashMap<String, Boolean> getGroupSpecialityListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Group> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Group").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getSpeciality().getTitle(), false);
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

    public HashMap<Integer, Boolean> getGroupNumbersListForTitle() {
        HashMap<Integer, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Group> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Group").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getGroupNumber(), false);
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

    public HashMap<String, Boolean> getGroupEducationFormListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Group> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Group").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getGroupEducationForm().getGroupEducationFormTitle(), false);
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

    public HashMap<String, Boolean> getGroupQualificationLevelListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Group> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Group").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getGroupQualificationLevel().getGroupQualificationLevelTitle(), false);
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

    public HashMap<Integer, Boolean> getGroupCourseListForTitle() {
        HashMap<Integer, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Group> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Group").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getGroupCourse(), false);
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

    public HashMap<String, Boolean> getGroupStatusListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Group> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            orderList = session.createQuery("FROM Group").getResultList();

            for (int i = 0; i < orderList.size(); i++) {
                storage.put(orderList.get(i).getGroupStatus().getGroupStatusTitle(), false);
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
