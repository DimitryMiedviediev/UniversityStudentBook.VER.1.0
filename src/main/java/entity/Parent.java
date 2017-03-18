package entity;

import javax.persistence.Embeddable;

/**
 * Created by Dimitry on 17.03.17.
 */
@Embeddable
public class Parent {
    private Person parent;
    private String phone1;
    private String phone2;

    public Parent() {
    }

    public Parent(Person parent, String phone1, String phone2) {
        this.parent = parent;
        this.phone1 = phone1;
        this.phone2 = phone2;
    }

    public Person getParent() {
        return parent;
    }

    public void setParent(Person parent) {
        this.parent = parent;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parent parent1 = (Parent) o;

        if (parent != null ? !parent.equals(parent1.parent) : parent1.parent != null) return false;
        if (phone1 != null ? !phone1.equals(parent1.phone1) : parent1.phone1 != null) return false;
        return phone2 != null ? phone2.equals(parent1.phone2) : parent1.phone2 == null;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (phone1 != null ? phone1.hashCode() : 0);
        result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "parent=" + parent +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                '}';
    }
}
