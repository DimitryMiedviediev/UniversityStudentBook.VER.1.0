package beans;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by Dimitry on 16.03.17.
 */
public class BeansAuthorizationRegistration {

    public void createNewUser(String email, String password) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            User user = new User(email, password);

            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }

    public User getUserInfo(String email, String password){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<User> userList;
        User userInfo = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            userList = session.createQuery("FROM User WHERE userEmail = '" + email + "' AND userPassword = '" + password + "'").getResultList();

            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserEmail().equals(email) && userList.get(i).getUserPassword().equals(password)) {
                    userInfo = userList.get(i);
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
        return userInfo;
    }

    public Boolean testUser(String email, String password) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<User> userList;
        Boolean bool = false;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            userList = session.createQuery("FROM User WHERE userEmail = '" + email + "' AND userPassword = '" + password + "'").getResultList();

            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserEmail().equals(email) && userList.get(i).getUserPassword().equals(password)) {
                    bool = true;
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return bool;
    }

    public Boolean testEmailExist(String email) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory factory = null;

        List<User> userList;
        Boolean bool = false;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            userList = session.createQuery("FROM User WHERE userEmail = '" + email + "'").getResultList();

            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserEmail().equals(email)) {
                    bool = true;
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }

        return bool;
    }

    public Boolean testEmailFormat(String email) {

        Boolean bool = false;

        char[] charList = email.toCharArray();
        for (int i = 0; i < charList.length; i++) {
            if (String.valueOf(charList[i]).equals("@")) {
                bool = true;
            }
        }

        return bool;
    }

    public Boolean testPasswordLength(String password) {

        Boolean bool = false;

        char[] charList = password.toCharArray();
        if (charList.length >= 8) {
            bool = true;
        }

        return bool;
    }

}
