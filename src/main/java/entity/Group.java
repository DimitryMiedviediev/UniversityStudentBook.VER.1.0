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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speciality_id", nullable = false)
    private Speciality speciality;

    @Column(name = "group_number", nullable = false)
    private int number;

    @Column(name = "group_education_form", nullable = false)
    private String educationForm;

    @Column(name = "group_qualification_level", nullable = false)
    private String qualificationLevel;

    @Column(name = "group_course", nullable = false)
    private int course;

    @Column(name = "group_status", nullable = false)
    private Boolean status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<Student> students = new HashSet<>();

    public Group() {
    }

    public Group(Speciality speciality, int number, String educationForm, String qualificationLevel, int course, Boolean status) {
        this.speciality = speciality;
        this.number = number;
        this.educationForm = educationForm;
        this.qualificationLevel = qualificationLevel;
        this.course = course;
        this.status = status;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(String educationForm) {
        this.educationForm = educationForm;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (number != group.number) return false;
        if (course != group.course) return false;
        if (speciality != null ? !speciality.equals(group.speciality) : group.speciality != null) return false;
        if (educationForm != null ? !educationForm.equals(group.educationForm) : group.educationForm != null)
            return false;
        if (qualificationLevel != null ? !qualificationLevel.equals(group.qualificationLevel) : group.qualificationLevel != null)
            return false;
        return status != null ? status.equals(group.status) : group.status == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + number;
        result = 31 * result + (educationForm != null ? educationForm.hashCode() : 0);
        result = 31 * result + (qualificationLevel != null ? qualificationLevel.hashCode() : 0);
        result = 31 * result + course;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", speciality=" + speciality +
                ", number=" + number +
                ", educationForm='" + educationForm + '\'' +
                ", qualificationLevel='" + qualificationLevel + '\'' +
                ", course=" + course +
                ", status=" + status +
                '}';
    }
}
