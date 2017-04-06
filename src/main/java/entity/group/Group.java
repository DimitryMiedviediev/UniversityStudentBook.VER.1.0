package entity.group;

import entity.Speciality;
import entity.Student;
import entity.group.parameters.GroupEducationForm;
import entity.group.parameters.GroupQualificationLevel;
import entity.group.parameters.GroupStatus;
import entity.order.parameters.OrderType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 17.03.17.
 */
@Entity
@Table(name = "Groups")
public class Group {

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_status_id", nullable = false)
    private GroupStatus groupStatus;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    private Set<Student> students = new HashSet<>();

    public Group() {
    }

    public Group(Speciality speciality, int groupNumber, GroupEducationForm groupEducationForm, GroupQualificationLevel groupQualificationLevel, int groupCourse, GroupStatus groupStatus) {
        this.speciality = speciality;
        this.groupNumber = groupNumber;
        this.groupEducationForm = groupEducationForm;
        this.groupQualificationLevel = groupQualificationLevel;
        this.groupCourse = groupCourse;
        this.groupStatus = groupStatus;
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

    public GroupStatus getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(GroupStatus groupStatus) {
        this.groupStatus = groupStatus;
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

        Group group = (Group) o;

        if (id != group.id) return false;
        if (groupNumber != group.groupNumber) return false;
        if (groupCourse != group.groupCourse) return false;
        if (speciality != null ? !speciality.equals(group.speciality) : group.speciality != null) return false;
        if (groupEducationForm != null ? !groupEducationForm.equals(group.groupEducationForm) : group.groupEducationForm != null)
            return false;
        if (groupQualificationLevel != null ? !groupQualificationLevel.equals(group.groupQualificationLevel) : group.groupQualificationLevel != null)
            return false;
        return groupStatus != null ? groupStatus.equals(group.groupStatus) : group.groupStatus == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + groupNumber;
        result = 31 * result + (groupEducationForm != null ? groupEducationForm.hashCode() : 0);
        result = 31 * result + (groupQualificationLevel != null ? groupQualificationLevel.hashCode() : 0);
        result = 31 * result + groupCourse;
        result = 31 * result + (groupStatus != null ? groupStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", speciality=" + speciality +
                ", groupNumber=" + groupNumber +
                ", groupEducationForm=" + groupEducationForm +
                ", groupQualificationLevel=" + groupQualificationLevel +
                ", groupCourse=" + groupCourse +
                ", groupStatus=" + groupStatus +
                '}';
    }
}
