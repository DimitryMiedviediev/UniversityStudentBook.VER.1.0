package entity;

import entity.group.Group;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 17.03.17.
 */
@Entity
@Table(name = "Specialities")
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Column(name = "speciality_title", unique = true, nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "speciality")
    private Set<Group> groups = new HashSet<>();

    public Speciality() {
    }

    public Speciality(Faculty faculty, String title) {
        this.faculty = faculty;
        this.title = title;
    }

    public Speciality(Faculty faculty, String title, Set<Group> groups) {
        this.faculty = faculty;
        this.title = title;
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speciality that = (Speciality) o;

        if (id != that.id) return false;
        if (faculty != null ? !faculty.equals(that.faculty) : that.faculty != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return groups != null ? groups.equals(that.groups) : that.groups == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", faculty=" + faculty +
                ", title='" + title + '\'' +
                ", groups=" + groups +
                '}';
    }
}
