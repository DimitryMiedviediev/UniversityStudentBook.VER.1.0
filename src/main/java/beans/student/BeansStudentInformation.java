package beans.student;

import entity.Address;
import entity.Person;
import entity.group.parameters.GroupStatus;
import entity.order.Order;
import entity.student.Student;
import entity.group.Group;
import entity.student.parameters.StudentFinancing;
import entity.student.parameters.StudentStatus;
import entity.student.parameters.StudentSubgroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Dimitry on 08.04.17.
 */
public class BeansStudentInformation {

    public void createNewStudent(
            String studentName,
            String studentSurname,
            String studentPatronimic,
            String studentPhone1,
            String studentPhone2,
            String studentEmail,
//            int statusId,
            int groupId,
            int studentSubgroup,
            int financingId,
            String studentBook,
            String studentBirthDate,
            String studentPassportNumber,
            String studentPassportOffice,
            String studentPassportReleaseDate,
            String studentIdentityCode,
            String studentAddressHouse,
            String studentAddressStreet,
            String studentAddressCity,
            String studentAddressState,
            String studentAddressZip,
            String studentAddressCountry,
            String fatherName,
            String fatherSurname,
            String fatherPatronimic,
            String fatherPhone1,
            String fatherPhone2,
            String fatherEmail,
            String motherName,
            String motherSurname,
            String motherPatronimic,
            String motherPhone1,
            String motherPhone2,
            String motherEmail,
            String parentAddressHouse,
            String parentAddressStreet,
            String parentAddressCity,
            String parentAddressState,
            String parentAddressZip,
            String parentAddressCountry) {

        if(studentPhone1.equals("")){
            studentPhone1 = null;
        }
        if(studentPhone2.equals("")){
            studentPhone2 = null;
        }
        if(studentEmail.equals("")){
            studentEmail = null;
        }
        if(studentBirthDate.equals("")){
            studentBirthDate = null;
        }
        if(studentPassportNumber.equals("")){
            studentPassportNumber = null;
        }
        if(studentPassportOffice.equals("")){
            studentPassportOffice = null;
        }
        if(studentPassportReleaseDate.equals("")){
            studentPassportReleaseDate = null;
        }
        if(studentIdentityCode.equals("")){
            studentIdentityCode = null;
        }
        if(fatherName.equals("")){
            fatherName = null;
        }
        if(fatherSurname.equals("")){
            fatherSurname = null;
        }
        if(fatherPatronimic.equals("")){
            fatherPatronimic = null;
        }
        if(fatherPhone1.equals("")){
            fatherPhone1 = null;
        }
        if(fatherPhone2.equals("")){
            fatherPhone2 = null;
        }
        if(fatherEmail.equals("")){
            fatherEmail = null;
        }
        if(motherName.equals("")){
            motherName = null;
        }
        if(motherSurname.equals("")){
            motherSurname = null;
        }
        if(motherPatronimic.equals("")){
            motherPatronimic = null;
        }
        if(motherPhone1.equals("")){
            motherPhone1 = null;
        }
        if(motherPhone2.equals("")){
            motherPhone2 = null;
        }
        if(motherEmail.equals("")){
            motherEmail = null;
        }


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Student student = new Student(
                    new Person(
                            studentName,
                            studentSurname,
                            studentPatronimic,
                            studentPhone1,
                            studentPhone2,
                            studentEmail
                    ),
                    getDefaultCreateStatus(),
                    session.get(Group.class, groupId),
                    session.get(StudentSubgroup.class, studentSubgroup),
                    session.get(StudentFinancing.class, financingId),
                    studentBook,
                    studentBirthDate,
                    studentPassportNumber,
                    studentPassportOffice,
                    studentPassportReleaseDate,
                    studentIdentityCode,
                    new Address(
                            studentAddressHouse,
                            studentAddressStreet,
                            studentAddressCity,
                            studentAddressState,
                            studentAddressZip,
                            studentAddressCountry
                    ),
                    new Person(
                            fatherName,
                            fatherSurname,
                            fatherPatronimic,
                            fatherPhone1,
                            fatherPhone2,
                            fatherEmail
                    ),
                    new Person(
                            motherName,
                            motherSurname,
                            motherPatronimic,
                            motherPhone1,
                            motherPhone2,
                            motherEmail
                    ),
                    new Address(
                            parentAddressHouse,
                            parentAddressStreet,
                            parentAddressCity,
                            parentAddressState,
                            parentAddressZip,
                            parentAddressCountry
                    )
            );

            session.save(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void updateStudent(
            int studentId,
            String studentName,
            String studentSurname,
            String studentPatronimic,
            String studentPhone1,
            String studentPhone2,
            String studentEmail,
//            int statusID,
            int groupId,
            int studentSubgroup,
            int financingId,
            String studentBook,
            String studentBirthDate,
            String studentPassportNumber,
            String studentPassportOffice,
            String studentPassportReleaseDate,
            String studentIdentityCode,
            String studentAddressHouse,
            String studentAddressStreet,
            String studentAddressCity,
            String studentAddressState,
            String studentAddressZip,
            String studentAddressCountry,
            String fatherName,
            String fatherSurname,
            String fatherPatronimic,
            String fatherPhone1,
            String fatherPhone2,
            String fatherEmail,
            String motherName,
            String motherSurname,
            String motherPatronimic,
            String motherPhone1,
            String motherPhone2,
            String motherEmail,
            String parentAddressHouse,
            String parentAddressStreet,
            String parentAddressCity,
            String parentAddressState,
            String parentAddressZip,
            String parentAddressCountry
    ) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Student student = session.get(Student.class, studentId);
            student.setStudent(
                    new Person(
                            studentName,
                            studentSurname,
                            studentPatronimic,
                            studentPhone1,
                            studentPhone2,
                            studentEmail
                    )
            );
//            student.setStudentStatus(session.get(StudentStatus.class, statusID));
            student.setGroup(session.get(Group.class, groupId));
            student.setStudentSubgroup(session.get(StudentSubgroup.class, studentSubgroup));
            student.setStudentFinancing(session.get(StudentFinancing.class, financingId));
            student.setStudentBook(studentBook);
            student.setStudentBirthDate(studentBirthDate);
            student.setStudentPassportNumber(studentPassportNumber);
            student.setStudentPassportOffice(studentPassportOffice);
            student.setStudentPassportReleaseDate(studentPassportReleaseDate);
            student.setStudentIdentityCode(studentIdentityCode);
            student.setStudentAddress(
                    new Address(
                            studentAddressHouse,
                            studentAddressStreet,
                            studentAddressCity,
                            studentAddressState,
                            studentAddressZip,
                            studentAddressCountry
                    )
            );
            student.setFather(
                    new Person(
                            fatherName,
                            fatherSurname,
                            fatherPatronimic,
                            fatherPhone1,
                            fatherPhone2,
                            fatherEmail
                    )
            );
            student.setMother(
                    new Person(
                            motherName,
                            motherSurname,
                            motherPatronimic,
                            motherPhone1,
                            motherPhone2,
                            motherEmail
                    )
            );
            student.setParentAddress(
                    new Address(
                            parentAddressHouse,
                            parentAddressStreet,
                            parentAddressCity,
                            parentAddressState,
                            parentAddressZip,
                            parentAddressCountry
                    )
            );

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public Student getStudent(int studentID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        Student student = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            student = session.get(Student.class, studentID);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return student;
    }

    public void deleteStudent(int studentID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Student student = session.get(Student.class, studentID);
            session.delete(student);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public List<Group> getGroupList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<Group> groupList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            groupList = session.createQuery("FROM Group ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return groupList;
    }

    public List<Order> getOrderList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<Order> orderList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            orderList = session.createQuery("FROM Order ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return orderList;
    }

    public List<StudentSubgroup> getSubgroupList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<StudentSubgroup> subgroupList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            subgroupList = session.createQuery("FROM StudentSubgroup ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return subgroupList;
    }

    public List<StudentStatus> getStudentStatusList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<StudentStatus> studentStatusList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            studentStatusList = session.createQuery("FROM StudentStatus ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return studentStatusList;
    }

    public List<StudentFinancing> getStudentFinancingList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<StudentFinancing> studentFinancingList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            studentFinancingList = session.createQuery("FROM StudentFinancing ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return studentFinancingList;
    }

    public StudentStatus getDefaultCreateStatus() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        StudentStatus studentStatus = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List<StudentStatus> studentStatusList = session.createQuery("FROM StudentStatus WHERE studentStatusRole = true").getResultList();
            for (int i = 0; i < studentStatusList.size(); i++) {
                studentStatus = studentStatusList.get(i);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return studentStatus;
    }

}
