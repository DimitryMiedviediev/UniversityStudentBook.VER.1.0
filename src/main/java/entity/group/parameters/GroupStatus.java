package entity.group.parameters;

import entity.group.Group;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 04.04.17.
 */
@Entity
@Table(name = "GroupStatus")
public class GroupStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_status_title", unique = true, nullable = false)
    private String groupStatusTitle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "groupStatus")
    private Set<Group> groups = new HashSet<>();

    public GroupStatus() {
    }

    public GroupStatus(String groupStatusTitle) {
        this.groupStatusTitle = groupStatusTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupStatusTitle() {
        return groupStatusTitle;
    }

    public void setGroupStatusTitle(String groupStatusTitle) {
        this.groupStatusTitle = groupStatusTitle;
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

        GroupStatus that = (GroupStatus) o;

        if (id != that.id) return false;
        return groupStatusTitle != null ? groupStatusTitle.equals(that.groupStatusTitle) : that.groupStatusTitle == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupStatusTitle != null ? groupStatusTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupStatus{" +
                "id=" + id +
                ", groupStatusTitle='" + groupStatusTitle + '\'' +
                '}';
    }
}
