package model.classes;

/**
 * Created by Dimitry on 27.02.17.
 */
public class User {
    private String user_id;
    private String user_email;
    private String user_password;
    private String user_database;

    public User() {
    }

    public User(String user_id, String user_email, String user_password, String user_database) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_database = user_database;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_database() {
        return user_database;
    }

    public void setUser_database(String user_database) {
        this.user_database = user_database;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (user_id != null ? !user_id.equals(user.user_id) : user.user_id != null) return false;
        if (user_email != null ? !user_email.equals(user.user_email) : user.user_email != null) return false;
        if (user_password != null ? !user_password.equals(user.user_password) : user.user_password != null)
            return false;
        return user_database != null ? user_database.equals(user.user_database) : user.user_database == null;
    }

    @Override
    public int hashCode() {
        int result = user_id != null ? user_id.hashCode() : 0;
        result = 31 * result + (user_email != null ? user_email.hashCode() : 0);
        result = 31 * result + (user_password != null ? user_password.hashCode() : 0);
        result = 31 * result + (user_database != null ? user_database.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_database='" + user_database + '\'' +
                '}';
    }
}
