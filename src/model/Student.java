package model;

import java.io.Serializable;

/**
 * Created by Dimitry on 06.02.17.
 */
public class Student implements Serializable {
    private String name;
    private String surname;
    private String lastname;
    private String status;

    public Student() {
    }

    public Student(String name, String surname, String lastname, String status) {
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        if (lastname != null ? !lastname.equals(student.lastname) : student.lastname != null) return false;
        return status != null ? status.equals(student.status) : student.status == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
