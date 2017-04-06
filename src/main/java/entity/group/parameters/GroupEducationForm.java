package entity.group.parameters;

import entity.group.Group;
import entity.order.Order;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 04.04.17.
 */
@Entity
@Table(name = "GroupEducationForm")
public class GroupEducationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_education_form_title", unique = true, nullable = false)
    private String groupEducationFormTitle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "groupEducationForm")
    private Set<Group> groups = new HashSet<>();

    public GroupEducationForm() {
    }

    public GroupEducationForm(String groupEducationFormTitle) {
        this.groupEducationFormTitle = groupEducationFormTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupEducationFormTitle() {
        return groupEducationFormTitle;
    }

    public void setGroupEducationFormTitle(String groupEducationFormTitle) {
        this.groupEducationFormTitle = groupEducationFormTitle;
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

        GroupEducationForm that = (GroupEducationForm) o;

        if (id != that.id) return false;
        return groupEducationFormTitle != null ? groupEducationFormTitle.equals(that.groupEducationFormTitle) : that.groupEducationFormTitle == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupEducationFormTitle != null ? groupEducationFormTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupEducationForm{" +
                "id=" + id +
                ", groupEducationFormTitle='" + groupEducationFormTitle + '\'' +
                '}';
    }
}
