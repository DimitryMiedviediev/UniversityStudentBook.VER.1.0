package beans.group;

import entity.group.Group;
import entity.group.parameters.GroupEducationForm;
import entity.group.parameters.GroupQualificationLevel;
import entity.group.parameters.GroupStatus;
import entity.order.parameters.OrderType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Dimitry on 07.04.17.
 */
public class BeansGroupConfiguration {

    public void createNewEducationForm(String educationFormTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            GroupEducationForm educationForm = new GroupEducationForm(educationFormTitle);

            session.save(educationForm);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void deleteEducationForm(int educationFormID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            GroupEducationForm educationForm = session.get(GroupEducationForm.class, educationFormID);

            session.delete(educationForm);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void updateEducationForm(int educationFormID, String educationFormNewTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            GroupEducationForm educationForm = session.get(GroupEducationForm.class, educationFormID);
            educationForm.setGroupEducationFormTitle(educationFormNewTitle);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public GroupEducationForm readOneEducationForm(int educationFormID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        GroupEducationForm educationForm = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            educationForm = session.get(GroupEducationForm.class, educationFormID);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return educationForm;
    }

    public List<GroupEducationForm> getGroupEducationFormsList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<GroupEducationForm> groupEducationFormList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            groupEducationFormList = session.createQuery("FROM GroupEducationForm").getResultList();
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



    public void createNewQualificationLevel(String qualificationLevelTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            GroupQualificationLevel qualificationLevel = new GroupQualificationLevel(qualificationLevelTitle);

            session.save(qualificationLevel);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void deleteQualificationLevel(int qualificationLevelID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            GroupQualificationLevel qualificationLevel = session.get(GroupQualificationLevel.class, qualificationLevelID);

            session.delete(qualificationLevel);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void updateQualificationLevel(int qualificationLevelID, String qualificationLevelNewTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            GroupQualificationLevel qualificationLevel = session.get(GroupQualificationLevel.class, qualificationLevelID);
            qualificationLevel.setGroupQualificationLevelTitle(qualificationLevelNewTitle);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public GroupQualificationLevel readOneQualificationLevel(int qualificationLevelID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        GroupQualificationLevel qualificationLevel = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            qualificationLevel = session.get(GroupQualificationLevel.class, qualificationLevelID);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return qualificationLevel;
    }

    public List<GroupQualificationLevel> getGroupQualificationLevelsList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<GroupQualificationLevel> groupQualificationLevelList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            groupQualificationLevelList = session.createQuery("FROM GroupQualificationLevel").getResultList();
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



    public void createNewStatus(String statusTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            GroupStatus status = new GroupStatus(statusTitle);

            session.save(status);
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

            GroupStatus status = session.get(GroupStatus.class, statusID);

            session.delete(status);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public void updateStatus(int statusID, String statusNewTitle) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            GroupStatus status = session.get(GroupStatus.class,statusID);
            status.setGroupStatusTitle(statusNewTitle);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public GroupStatus readOneStatus(int statusID) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        GroupStatus status = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            status = session.get(GroupStatus.class, statusID);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return status;
    }

    public List<GroupStatus> getGroupStatusList() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        List<GroupStatus> groupStatusList = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            groupStatusList = session.createQuery("FROM GroupStatus").getResultList();
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

}
