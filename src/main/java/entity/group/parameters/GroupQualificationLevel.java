package entity.group.parameters;

import entity.group.Group;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 04.04.17.
 */
@Entity
@Table(name = "GroupQualificationLevel")
public class GroupQualificationLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "group_qualification_level_title", unique = true, nullable = false)
    private String groupQualificationLevelTitle;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "groupQualificationLevel")
    private Set<Group> groups = new HashSet<>();

    public GroupQualificationLevel() {
    }

    public GroupQualificationLevel(String groupQualificationLevelTitle) {
        this.groupQualificationLevelTitle = groupQualificationLevelTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupQualificationLevelTitle() {
        return groupQualificationLevelTitle;
    }

    public void setGroupQualificationLevelTitle(String groupQualificationLevelTitle) {
        this.groupQualificationLevelTitle = groupQualificationLevelTitle;
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

        GroupQualificationLevel that = (GroupQualificationLevel) o;

        if (id != that.id) return false;
        return groupQualificationLevelTitle != null ? groupQualificationLevelTitle.equals(that.groupQualificationLevelTitle) : that.groupQualificationLevelTitle == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupQualificationLevelTitle != null ? groupQualificationLevelTitle.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GroupQualificationLevel{" +
                "id=" + id +
                ", groupQualificationLevelTitle='" + groupQualificationLevelTitle + '\'' +
                '}';
    }
}
