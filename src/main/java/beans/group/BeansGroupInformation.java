package beans.group;

import entity.Speciality;
import entity.group.Group;
import entity.group.parameters.GroupEducationForm;
import entity.group.parameters.GroupQualificationLevel;
import entity.group.parameters.GroupStatus;
import entity.order.Order;
import entity.order.parameters.OrderType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Dimitry on 03.04.17.
 */
public class BeansGroupInformation {

    public void createNewGroup(
            int groupNumber,
            int speciality,
            int groupEducationForm,
            int groupQualificationLevel,
            int groupCourse,
            int groupStatus
            ) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Group group = new Group(
                    session.get(Speciality.class, speciality),
                    groupNumber,
                    session.get(GroupEducationForm.class, groupEducationForm),
                    session.get(GroupQualificationLevel.class, groupQualificationLevel),
                    groupCourse,
                    session.get(GroupStatus.class, groupStatus)
                    );

            session.save(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void updateGroup(
            int id,
            int groupNumber,
            int speciality,
            int groupEducationForm,
            int groupQualificationLevel,
            int groupCourse,
            int groupStatus) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Group group = session.get(Group.class, id);
            group.setGroupNumber(groupNumber);
            group.setSpeciality(session.get(Speciality.class, speciality));
            group.setGroupEducationForm(session.get(GroupEducationForm.class, groupEducationForm));
            group.setGroupQualificationLevel(session.get(GroupQualificationLevel.class, groupQualificationLevel));
            group.setGroupCourse(groupCourse);
            group.setGroupStatus(session.get(GroupStatus.class, groupStatus));

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public Group getGroup(int groupID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        Group group = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            group = session.get(Group.class, groupID);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return group;
    }

    public void deleteGroup(int groupID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Group group = session.get(Group.class, groupID);
            session.delete(group);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public List<GroupEducationForm> getGroupEducationFormList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<GroupEducationForm> groupEducationFormList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            groupEducationFormList = session.createQuery("FROM GroupEducationForm ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return groupEducationFormList;
    }

    public List<GroupQualificationLevel> getGroupQualificationLevelList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<GroupQualificationLevel> groupQualificationLevelList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            groupQualificationLevelList = session.createQuery("FROM GroupQualificationLevel ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return groupQualificationLevelList;
    }

    public List<GroupStatus> getGroupStatusList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<GroupStatus> groupStatusList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            groupStatusList = session.createQuery("FROM GroupStatus ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return groupStatusList;
    }

    public List<Speciality> getSpecialityList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<Speciality> specialityList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            specialityList = session.createQuery("FROM Speciality ").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return specialityList;
    }

}
