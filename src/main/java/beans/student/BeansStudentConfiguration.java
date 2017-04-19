package beans.student;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.group.parameters.GroupStatus;
import entity.student.Student;
import entity.student.parameters.StudentFinancing;
import entity.student.parameters.StudentStatus;
import entity.student.parameters.StudentSubgroup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dimitry on 16.04.17.
 */
public class BeansStudentConfiguration {

    public void createNewSubgroup(String subgroupTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentSubgroup studentSubgroup = new StudentSubgroup(subgroupTitle);

            session.save(studentSubgroup);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void deleteSubgroup(int subgroupID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentSubgroup studentSubgroup = session.get(StudentSubgroup.class, subgroupID);

            session.delete(studentSubgroup);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void updateSubgroup(int subgroupID, String subgroupNewTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentSubgroup studentSubgroup = session.get(StudentSubgroup.class, subgroupID);
            studentSubgroup.setStudentSubgroupTitle(subgroupNewTitle);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public StudentSubgroup readOneSubgroup(int subgroupID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        StudentSubgroup studentSubgroup = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentSubgroup = session.get(StudentSubgroup.class, subgroupID);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return studentSubgroup;
    }

    public List<StudentSubgroup> getSubgroupList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<StudentSubgroup> studentSubgroupList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            studentSubgroupList = session.createQuery("FROM StudentSubgroup ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return studentSubgroupList;
    }

    public void createNewFinancing(String financingTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentFinancing studentFinancing = new StudentFinancing(financingTitle);

            session.save(studentFinancing);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void deleteFinancing(int financingID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentFinancing studentFinancing = session.get(StudentFinancing.class, financingID);

            session.delete(studentFinancing);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void updateFinancing(int financingID, String financingNewTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentFinancing studentFinancing = session.get(StudentFinancing.class, financingID);
            studentFinancing.setStudentFinancingTitle(financingNewTitle);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public StudentFinancing readOneFinancing(int financingID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        StudentFinancing studentFinancing = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentFinancing = session.get(StudentFinancing.class, financingID);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return studentFinancing;
    }

    public List<StudentFinancing> getFinancingList() {
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

    public void createNewStatus(String statusTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentStatus studentStatus = new StudentStatus(statusTitle, null);

            session.save(studentStatus);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void deleteStatus(int statusID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentStatus studentStatus = session.get(StudentStatus.class, statusID);

            session.delete(studentStatus);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void updateStatusTitle(int statusID, String statusNewTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            StudentStatus studentStatus = session.get(StudentStatus.class, statusID);
            studentStatus.setStudentStatusTitle(statusNewTitle);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public StudentStatus readOneStatus(int statusID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        StudentStatus studentStatus = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            studentStatus = session.get(StudentStatus.class, statusID);

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

    public List<StudentStatus> getStatusList() {
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

    public void changeTrueStatus(int statusID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        StudentStatus studentStatus = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            try{
                studentStatus = (StudentStatus) session.createQuery("FROM StudentStatus WHERE studentStatusRole = true").getSingleResult();
                studentStatus.setStudentStatusRole(null);
            }catch (NoResultException e){

            }

            studentStatus = session.get(StudentStatus.class, statusID);
            studentStatus.setStudentStatusRole(true);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void changeFalseStatus(List<Integer> idList) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            StudentStatus studentStatus = null;

            for (int i = 0; i < idList.size(); i++) {
                studentStatus = session.get(StudentStatus.class, idList.get(i));
                studentStatus.setStudentStatusRole(false);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void changeNullStatus(List<Integer> idList) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            StudentStatus studentStatus = null;

            for (int i = 0; i < idList.size(); i++) {
                studentStatus = session.get(StudentStatus.class, idList.get(i));
                studentStatus.setStudentStatusRole(null);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}
