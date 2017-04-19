package entity.group;

import entity.Speciality;
import entity.group.parameters.GroupEducationForm;
import entity.group.parameters.GroupQualificationLevel;
import entity.group.parameters.GroupStatus;
import entity.student.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 19.04.17.
 */
@Entity
@Table(name = "Psevdogroups")
public class Psevdogroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    @Column(name = "group_number", nullable = false)
    private int groupNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_education_form_id", nullable = false)
    private GroupEducationForm groupEducationForm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_qualification_level_id", nullable = false)
    private GroupQualificationLevel groupQualificationLevel;

    @Column(name = "group_course", nullable = false)
    private int groupCourse;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "psevdogroup")
    private Student student;

    public Psevdogroup() {
    }

    public Psevdogroup(Speciality speciality, int groupNumber, GroupEducationForm groupEducationForm, GroupQualificationLevel groupQualificationLevel, int groupCourse, Student student) {
        this.speciality = speciality;
        this.groupNumber = groupNumber;
        this.groupEducationForm = groupEducationForm;
        this.groupQualificationLevel = groupQualificationLevel;
        this.groupCourse = groupCourse;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public GroupEducationForm getGroupEducationForm() {
        return groupEducationForm;
    }

    public void setGroupEducationForm(GroupEducationForm groupEducationForm) {
        this.groupEducationForm = groupEducationForm;
    }

    public GroupQualificationLevel getGroupQualificationLevel() {
        return groupQualificationLevel;
    }

    public void setGroupQualificationLevel(GroupQualificationLevel groupQualificationLevel) {
        this.groupQualificationLevel = groupQualificationLevel;
    }

    public int getGroupCourse() {
        return groupCourse;
    }

    public void setGroupCourse(int groupCourse) {
        this.groupCourse = groupCourse;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Psevdogroup that = (Psevdogroup) o;

        if (id != that.id) return false;
        if (groupNumber != that.groupNumber) return false;
        if (groupCourse != that.groupCourse) return false;
        if (speciality != null ? !speciality.equals(that.speciality) : that.speciality != null) return false;
        if (groupEducationForm != null ? !groupEducationForm.equals(that.groupEducationForm) : that.groupEducationForm != null)
            return false;
        if (groupQualificationLevel != null ? !groupQualificationLevel.equals(that.groupQualificationLevel) : that.groupQualificationLevel != null)
            return false;
        return student != null ? student.equals(that.student) : that.student == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + groupNumber;
        result = 31 * result + (groupEducationForm != null ? groupEducationForm.hashCode() : 0);
        result = 31 * result + (groupQualificationLevel != null ? groupQualificationLevel.hashCode() : 0);
        result = 31 * result + groupCourse;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        return result;
    }
}
