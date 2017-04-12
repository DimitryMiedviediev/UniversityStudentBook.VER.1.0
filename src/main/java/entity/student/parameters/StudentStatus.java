package entity.student.parameters;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.order.Order;
import entity.student.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 08.04.17.
 */
@Entity
@Table(name = "StudentStatus")
public class StudentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "student_status_title", unique = true, nullable = false)
    private String studentStatusTitle;

    @Column(name = "student_status_role")
    private Boolean studentStatusRole;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "studentStatus")
    private Set<Student> students = new HashSet<>();

    public StudentStatus() {
    }

    public StudentStatus(String studentStatusTitle, Boolean studentStatusRole) {
        this.studentStatusTitle = studentStatusTitle;
        this.studentStatusRole = studentStatusRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentStatusTitle() {
        return studentStatusTitle;
    }

    public void setStudentStatusTitle(String studentStatusTitle) {
        this.studentStatusTitle = studentStatusTitle;
    }

    public Boolean getStudentStatusRole() {
        return studentStatusRole;
    }

    public void setStudentStatusRole(Boolean studentStatusRole) {
        this.studentStatusRole = studentStatusRole;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentStatus that = (StudentStatus) o;

        if (id != that.id) return false;
        if (studentStatusTitle != null ? !studentStatusTitle.equals(that.studentStatusTitle) : that.studentStatusTitle != null)
            return false;
        return studentStatusRole != null ? studentStatusRole.equals(that.studentStatusRole) : that.studentStatusRole == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (studentStatusTitle != null ? studentStatusTitle.hashCode() : 0);
        result = 31 * result + (studentStatusRole != null ? studentStatusRole.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentStatus{" +
                "id=" + id +
                ", studentStatusTitle='" + studentStatusTitle + '\'' +
                ", studentStatusRole=" + studentStatusRole +
                '}';
    }
}
