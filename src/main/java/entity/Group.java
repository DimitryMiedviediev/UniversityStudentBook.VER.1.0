package entity;

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
    @Column(name = "group_id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    @Column(name = "group_number", nullable = false)
    private int groupNumber;

    @Column(name = "group_education_form", nullable = false)
    private String groupEducationForm;

    @Column(name = "group_qualification_level", nullable = false)
    private String groupQualificationLevel;

    @Column(name = "group_course", nullable = false)
    private int groupCourse;

    @Column(name = "group_status", nullable = false)
    private String groupStatus;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    private Set<Student> students = new HashSet<>();

    public Group() {
    }

    public Group(Speciality speciality, int groupNumber, String groupEducationForm, String groupQualificationLevel, int groupCourse, String groupStatus) {
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

    public String getGroupEducationForm() {
        return groupEducationForm;
    }

    public void setGroupEducationForm(String groupEducationForm) {
        this.groupEducationForm = groupEducationForm;
    }

    public String getGroupQualificationLevel() {
        return groupQualificationLevel;
    }

    public void setGroupQualificationLevel(String groupQualificationLevel) {
        this.groupQualificationLevel = groupQualificationLevel;
    }

    public int getGroupCourse() {
        return groupCourse;
    }

    public void setGroupCourse(int groupCourse) {
        this.groupCourse = groupCourse;
    }

    public String getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(String groupStatus) {
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
        if (groupStatus != null ? !groupStatus.equals(group.groupStatus) : group.groupStatus != null) return false;
        return students != null ? students.equals(group.students) : group.students == null;
    }

    @Override
    public int hashCode() {
        int result = id;
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
                ", groupEducationForm='" + groupEducationForm + '\'' +
                ", groupQualificationLevel='" + groupQualificationLevel + '\'' +
                ", groupCourse=" + groupCourse +
                ", groupStatus='" + groupStatus + '\'' +
                ", students=" + students +
                '}';
    }
}
