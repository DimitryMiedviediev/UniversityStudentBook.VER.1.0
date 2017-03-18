package beans;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.Student;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dimitry on 17.03.17.
 */
public class BeansStudentList {

    public List<Student> getStudentList(
            HashMap<String, Boolean> studentsSpecialityList,
            HashMap<String, Boolean> studentsStatusList,
            HashMap<String, Boolean> studentsQualificationList,
            HashMap<Integer, Boolean> studentsCourseList,
            HashMap<Integer, Boolean> studentsGroupsList,
            HashMap<Integer, Boolean> studentsSubgroupList,
            HashMap<String, Boolean> studentsFinancing,
            HashMap<String, Boolean> studentEducationFormList,
            HashMap<String, Boolean> studentsCityParameter,
            HashMap<String, Boolean> studentsStateParameter) {

        String query = "FROM Student";
        String studentSpecialityQuery = null;
        String studentStatusQuery = null;
        String studentQualificationQuery = null;
        String studentCourseQuery = null;
        String studentEducationFormQuery = null;
        String studentFinancingQuery = null;
        String studentGroupQuery = null;
        String studentSubgroupQuery = null;
        String studentCityQuery = null;
        String studentStateQuery = null;

        for (Map.Entry<String, Boolean> entry : studentsSpecialityList.entrySet()) {
            if (entry.getValue()) {
                if (studentSpecialityQuery == null) {
                    studentSpecialityQuery = "'" + entry.getKey() + "'";
                } else if (studentSpecialityQuery != null) {
                    studentSpecialityQuery = studentSpecialityQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : studentsStatusList.entrySet()) {
            if (entry.getValue()) {
                if (studentStatusQuery == null) {
                    studentStatusQuery = "'" + entry.getKey() + "'";
                } else if (studentStatusQuery != null) {
                    studentStatusQuery = studentStatusQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : studentsQualificationList.entrySet()) {
            if (entry.getValue()) {
                if (studentQualificationQuery == null) {
                    studentQualificationQuery = "'" + entry.getKey() + "'";
                } else if (studentQualificationQuery != null) {
                    studentQualificationQuery = studentQualificationQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<Integer, Boolean> entry : studentsCourseList.entrySet()) {
            if (entry.getValue()) {
                if (studentCourseQuery == null) {
                    studentCourseQuery = entry.getKey().toString();
                } else if (studentCourseQuery != null) {
                    studentCourseQuery = studentCourseQuery + ", " + entry.getKey();
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : studentEducationFormList.entrySet()) {
            if (entry.getValue()) {
                if (studentEducationFormQuery == null) {
                    studentEducationFormQuery = "'" + entry.getKey() + "'";
                } else if (studentEducationFormQuery != null) {
                    studentEducationFormQuery = studentEducationFormQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : studentsFinancing.entrySet()) {
            if (entry.getValue()) {
                if (studentFinancingQuery == null) {
                    studentFinancingQuery = "'" + entry.getKey() + "'";
                } else if (studentFinancingQuery != null) {
                    studentFinancingQuery = studentFinancingQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<Integer, Boolean> entry : studentsGroupsList.entrySet()) {
            if (entry.getValue()) {
                if (studentGroupQuery == null) {
                    studentGroupQuery = entry.getKey().toString();
                } else if (studentGroupQuery != null) {
                    studentGroupQuery = studentGroupQuery + ", " + entry.getKey();
                }
            }
        }

        for (Map.Entry<Integer, Boolean> entry : studentsSubgroupList.entrySet()) {
            if (entry.getValue()) {
                if (studentSubgroupQuery == null) {
                    studentSubgroupQuery = entry.getKey().toString();
                } else if (studentSubgroupQuery != null) {
                    studentSubgroupQuery = studentSubgroupQuery + ", " + entry.getKey();
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : studentsCityParameter.entrySet()) {
            if (entry.getValue()) {
                if (studentCityQuery == null) {
                    studentCityQuery = "'" + entry.getKey() + "'";
                } else if (studentCityQuery != null) {
                    studentCityQuery = studentCityQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        for (Map.Entry<String, Boolean> entry : studentsStateParameter.entrySet()) {
            if (entry.getValue()) {
                if (studentStateQuery == null) {
                    studentStateQuery = "'" + entry.getKey() + "'";
                } else if (studentStateQuery != null) {
                    studentStateQuery = studentStateQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        query = addQueryPart(query, "group.speciality.title", studentSpecialityQuery);
        query = addQueryPart(query, "studentStatus", studentStatusQuery);
        query = addQueryPart(query, "group.qualificationLevel", studentQualificationQuery);
        query = addQueryPart(query, "group.course", studentCourseQuery);
        query = addQueryPart(query, "group.educationForm", studentEducationFormQuery);
        query = addQueryPart(query, "studentFinancing", studentFinancingQuery);
        query = addQueryPart(query, "group.number", studentGroupQuery);
        query = addQueryPart(query, "studentSubgroup", studentSubgroupQuery);
        query = addQueryPart(query, "parentAddress.city", studentCityQuery);
        query = addQueryPart(query, "parentAddress.state", studentStateQuery);

        System.out.println("?????????????");
        System.out.println(query);
        System.out.println("?????????????");

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

    public HashMap<String, Boolean> getSpecListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getGroup().getSpeciality().getTitle(), false);
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

    public HashMap<String, Boolean> getStatusListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getStudentStatus(), false);
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

    public HashMap<String, Boolean> getQualificationListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getGroup().getQualificationLevel(), false);
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

    public HashMap<Integer, Boolean> getCourseListForTitle() {
        HashMap<Integer, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getGroup().getCourse(), false);
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

    public HashMap<Integer, Boolean> getGroupListForTitle() {
        HashMap<Integer, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getGroup().getNumber(), false);
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

    public HashMap<Integer, Boolean> getSubgroupListForTitle() {
        HashMap<Integer, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getStudentSubgroup(), false);
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

    public HashMap<String, Boolean> getFinanceListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getStudentFinancing(), false);
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

    public HashMap<String, Boolean> getEducFormListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getGroup().getEducationForm(), false);
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

    public HashMap<String, Boolean> getCityListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getParentAddress().getCity(), false);
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

    public HashMap<String, Boolean> getStateListForTitle() {
        HashMap<String, Boolean> storage = new HashMap<>();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<Student> studentList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentList = session.createQuery("FROM Student").getResultList();

            for (int i = 0; i < studentList.size(); i++) {
                storage.put(studentList.get(i).getParentAddress().getState(), false);
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
