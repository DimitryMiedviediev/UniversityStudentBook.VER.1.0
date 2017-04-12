package entity.student.parameters;

import entity.student.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 09.04.17.
 */
@Entity
@Table(name = "StudentSubgroup")
public class StudentSubgroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "student_subgroup_title", unique = true, nullable = false)
    private String studentSubgroupTitle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "studentSubgroup")
    private Set<Student> students = new HashSet<>();

    public StudentSubgroup() {
    }

    public StudentSubgroup(String studentSubgroupTitle) {
        this.studentSubgroupTitle = studentSubgroupTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentSubgroupTitle() {
        return studentSubgroupTitle;
    }

    public void setStudentSubgroupTitle(String studentSubgroupTitle) {
        this.studentSubgroupTitle = studentSubgroupTitle;
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

        StudentSubgroup that = (StudentSubgroup) o;

        if (id != that.id) return false;
        return studentSubgroupTitle != null ? studentSubgroupTitle.equals(that.studentSubgroupTitle) : that.studentSubgroupTitle == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (studentSubgroupTitle != null ? studentSubgroupTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentSubgroup{" +
                "id=" + id +
                ", studentSubgroupTitle='" + studentSubgroupTitle + '\'' +
                '}';
    }
}
