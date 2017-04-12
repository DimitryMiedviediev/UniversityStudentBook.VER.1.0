package beans.student;

import entity.student.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
            HashMap<String, Boolean> studentsSubgroupList,
            HashMap<String, Boolean> studentsFinancing,
            HashMap<String, Boolean> studentEducationFormList,
            String studentsCityParameter,
            String studentsStateParameter) {

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

        for (Map.Entry<String, Boolean> entry : studentsSubgroupList.entrySet()) {
            if (entry.getValue()) {
                if (studentSubgroupQuery == null) {
                    studentSubgroupQuery = "'" + entry.getKey() + "'";
                } else if (studentSubgroupQuery != null) {
                    studentSubgroupQuery = studentSubgroupQuery + ", " + "'" + entry.getKey() + "'";
                }
            }
        }

        if (studentsCityParameter != null && !studentsCityParameter.equals("")) {
            if (studentCityQuery == null) {
                studentCityQuery = "'" + studentsCityParameter + "'";
            } else if (studentCityQuery != null) {
                studentCityQuery = studentCityQuery + ", " + "'" + studentsCityParameter + "'";
            }
        }

        if (studentsStateParameter != null && !studentsStateParameter.equals("")) {
            if (studentStateQuery == null) {
                studentStateQuery = "'" + studentsStateParameter + "'";
            } else if (studentStateQuery != null) {
                studentStateQuery = studentStateQuery + ", " + "'" + studentsStateParameter + "'";
            }
        }

        query = addQueryPart(query, "group.speciality.title", studentSpecialityQuery);
        query = addQueryPart(query, "studentStatus.studentStatusTitle", studentStatusQuery);
        query = addQueryPart(query, "group.groupQualificationLevel.groupQualificationLevelTitle", studentQualificationQuery);
        query = addQueryPart(query, "group.groupCourse", studentCourseQuery);
        query = addQueryPart(query, "group.groupEducationForm.groupEducationFormTitle", studentEducationFormQuery);
        query = addQueryPart(query, "studentFinancing.studentFinancingTitle", studentFinancingQuery);
        query = addQueryPart(query, "group.groupNumber", studentGroupQuery);
        query = addQueryPart(query, "studentSubgroup.studentSubgroupTitle", studentSubgroupQuery);
        query = addQueryPart(query, "parentAddress.city", studentCityQuery);
        query = addQueryPart(query, "parentAddress.state", studentStateQuery);

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
                storage.put(studentList.get(i).getStudentStatus().getStudentStatusTitle(), false);
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
                storage.put(studentList.get(i).getGroup().getGroupQualificationLevel().getGroupQualificationLevelTitle(), false);
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
                storage.put(studentList.get(i).getGroup().getGroupCourse(), false);
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
                storage.put(studentList.get(i).getGroup().getGroupNumber(), false);
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

    public HashMap<String, Boolean> getSubgroupListForTitle() {
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
                storage.put(studentList.get(i).getStudentSubgroup().getStudentSubgroupTitle(), false);
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
                storage.put(studentList.get(i).getStudentFinancing().getStudentFinancingTitle(), false);
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
                storage.put(studentList.get(i).getGroup().getGroupEducationForm().getGroupEducationFormTitle(), false);
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

            studentList = session.createQuery("FROM Student WHERE parentAddress.city != null").getResultList();

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

            studentList = session.createQuery("FROM Student WHERE parentAddress.state != null").getResultList();

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
