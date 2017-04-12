package entity.student.parameters;

import entity.student.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 08.04.17.
 */
@Entity
@Table(name = "StudentFinancing")
public class StudentFinancing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "student_financing_title", unique = true, nullable = false)
    private String studentFinancingTitle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "studentFinancing")
    private Set<Student> students = new HashSet<>();

    public StudentFinancing() {
    }

    public StudentFinancing(String studentFinancingTitle) {
        this.studentFinancingTitle = studentFinancingTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentFinancingTitle() {
        return studentFinancingTitle;
    }

    public void setStudentFinancingTitle(String studentFinancingTitle) {
        this.studentFinancingTitle = studentFinancingTitle;
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

        StudentFinancing that = (StudentFinancing) o;

        if (id != that.id) return false;
        return studentFinancingTitle != null ? studentFinancingTitle.equals(that.studentFinancingTitle) : that.studentFinancingTitle == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (studentFinancingTitle != null ? studentFinancingTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentFinancing{" +
                "id=" + id +
                ", studentFinancingTitle='" + studentFinancingTitle + '\'' +
                '}';
    }
}
