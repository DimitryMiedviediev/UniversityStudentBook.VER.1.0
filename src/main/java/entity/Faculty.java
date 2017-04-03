package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 17.03.17.
 */
@Entity
@Table(name = "Faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @Column(name = "faculty_title", unique = true, nullable = false)
    private String title;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "dean_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "dean_surname")),
            @AttributeOverride(name = "patronimic", column = @Column(name = "dean_patronimic")),
            @AttributeOverride(name = "phone1", column = @Column(name = "dean_phone1")),
            @AttributeOverride(name = "phone2", column = @Column(name = "dean_phone2")),
            @AttributeOverride(name = "email", column = @Column(name = "dean_email"))
    })
    private Person dean;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<Speciality> specialities = new HashSet<>();

    public Faculty() {
    }

    public Faculty(University university, String title, Person dean) {
        this.university = university;
        this.title = title;
        this.dean = dean;
    }

    public Faculty(University university, String title, Person dean, Set<Speciality> specialities) {
        this.university = university;
        this.title = title;
        this.dean = dean;
        this.specialities = specialities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getDean() {
        return dean;
    }

    public void setDean(Person dean) {
        this.dean = dean;
    }

    public Set<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        if (id != faculty.id) return false;
        if (university != null ? !university.equals(faculty.university) : faculty.university != null) return false;
        if (title != null ? !title.equals(faculty.title) : faculty.title != null) return false;
        if (dean != null ? !dean.equals(faculty.dean) : faculty.dean != null) return false;
        return specialities != null ? specialities.equals(faculty.specialities) : faculty.specialities == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (university != null ? university.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (dean != null ? dean.hashCode() : 0);
        result = 31 * result + (specialities != null ? specialities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", university=" + university +
                ", title='" + title + '\'' +
                ", dean=" + dean +
                ", specialities=" + specialities +
                '}';
    }
}
