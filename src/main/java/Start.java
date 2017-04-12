import beans.authentication.BeansAuthorizationRegistration;
import beans.group.BeansGroupConfiguration;
import beans.group.BeansGroupInformation;
import beans.order.BeansOrderInformation;
import beans.order.BeansOrderList;
import beans.student.BeansStudentInformation;
import beans.student.BeansStudentList;
import entity.*;
import entity.group.Group;
import entity.student.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dimitry on 16.03.17.
 */
public class Start {
    public static void main(String[] args) {
        BeansAuthorizationRegistration beans = new BeansAuthorizationRegistration();
        BeansStudentList beansStudentList = new BeansStudentList();
        BeansOrderList beansOrderList = new BeansOrderList();
        BeansStudentInformation beansStudentInformation = new BeansStudentInformation();
        BeansGroupInformation beansGroupInformation = new BeansGroupInformation();
        BeansGroupConfiguration beansGroupConfiguration = new BeansGroupConfiguration();

//        createNewUniversity();
//        createNewFaculty();
//        createNewSpeciality();
//        createNewGroup();
//        createNewStudent();
//        createNewOrder();
//        System.out.println(beansStudentInformation.getOrderList());


//        beansGroupConfiguration.createNewStatus("Активна");
    }

    public static void createNewUniversity() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            University university = new University(
                    "КНТЕУ",
                    "Scientia difficilis sed fructuosa",
                    "1946",
                    "Державний",
                    new Person(
                            "Анатолій",
                            "Мазеракі",
                            "Антонович",
                            null,
                            null,
                            null
                    ),
                    new Address(
                            "19",
                            "Kioto str.",
                            "Kyiv",
                            "Kyiv st.",
                            "02156",
                            "Ukraine"
                    ),
                    "www.knteu.kiev.ua"
            );

            session.save(university);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public static void createNewFaculty() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            University university = session.get(University.class, 1);

            Faculty faculty = new Faculty(
                    university,
                    "ФЕМП",
                    new Person(
                            "Наталія",
                            "Гуляєва",
                            "Миколаївна",
                            null,
                            null,
                            null
                    )
            );

            session.save(faculty);


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public static void createNewSpeciality() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Faculty faculty = session.get(Faculty.class, 1);

            Speciality speciality = new Speciality(
                    faculty,
                    "Економіка підприємств"
            );
            Speciality speciality1 = new Speciality(
                    faculty,
                    "Фінанси підприємств"
            );

            session.save(speciality);
            session.save(speciality1);


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

//    public static void createNewGroup() {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        SessionFactory factory = null;
//        try {
//            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            Session session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            Speciality speciality = session.get(Speciality.class, 2);
//
//            Group group1 = new Group(
//                    speciality,
//                    13,
//                    "Distance",
//                    "Specialist",
//                    3,
//                    "Active"
//            );
//            Group group2 = new Group(
//                    speciality,
//                    14,
//                    "Full-time",
//                    "Bachelor",
//                    1,
//                    "Active"
//            );
//
//            session.save(group1);
//            session.save(group2);
//
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (factory != null) {
//                factory.close();
//            }
//        }
//    }
//
//    public static void createNewStudent() {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        SessionFactory factory = null;
//        try {
//            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            Session session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            Group group1 = session.get(Group.class, 1);
//            Group group2 = session.get(Group.class, 2);
//
//            Student student1 = new Student(
//                    new Person(
//                            "Artem",
//                            "Tkachenko",
//                            "Andriyovich",
//                            null,
//                            null,
//                            "tkachenko.artem@gmail.com"
//                    ),
//                    null,
//                    "Educate",
//                    group1,
//                    1,
//                    "Commercial",
//                    "s5f34j3",
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null
//            );
//
//            Student student2 = new Student(
//                    new Person(
//                            "Anastasia",
//                            "Zubok",
//                            "Vasilivna",
//                            null,
//                            null,
//                            "zubok.nastya@gmail.com"
//                    ),
//                    null,
//                    "Educate",
//                    group2,
//                    1,
//                    "Government",
//                    "874543",
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null
//            );
//            Student student3 = new Student(
//                    new Person(
//                            "Artem",
//                            "Tsimbrovsky",
//                            "Igorovich",
//                            null,
//                            null,
//                            "tsimbrovsky@gmail.com"
//                    ),
//                    null,
//                    "Educate",
//                    group1,
//                    1,
//                    "Commercial",
//                    "297634234",
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null
//            );
//
//            session.save(student1);
//            session.save(student2);
//            session.save(student3);
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (factory != null) {
//                factory.close();
//            }
//        }
//    }
//
//    public static void createNewOrder() {
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        SessionFactory factory = null;
//        try {
//            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            Session session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            Order order1 = new Order(
//                    "66666666",
//                    "02/02/2022",
//                    "To graduate",
//                    "Dfhsjgfkzfgzldfhgkfgziurlgzyrgvr"
//            );
//
//            session.save(order1);
//
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (factory != null) {
//                factory.close();
//            }
//        }
//    }
}
