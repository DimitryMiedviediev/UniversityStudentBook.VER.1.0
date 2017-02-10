package model;

import java.io.Serializable;

/**
 * Created by Dimitry on 07.02.17.
 */
public class SortParams implements Serializable {
    String mechanics;
    String engineers;
    String educate;
    String archive;
    String bachelor;
    String specialist;
    String master;
    String course1;
    String course2;
    String course3;
    String course4;
    String course5;
    String course6;
    String group;
    String subgroup;
    String government;
    String commercial;
    String full_time;
    String distance;
    String state;
    String department;
    String orphan;
    String disabled;

    public SortParams() {
    }

    public SortParams(String mechanics, String engineers, String educate, String archive, String bachelor,
                      String specialist, String master, String course1, String course2, String course3,
                      String course4, String course5, String course6, String group, String subgroup,
                      String government, String commercial, String full_time, String distance,
                      String state, String department, String orphan, String disabled) {
        this.mechanics = mechanics;
        this.engineers = engineers;
        this.educate = educate;
        this.archive = archive;
        this.bachelor = bachelor;
        this.specialist = specialist;
        this.master = master;
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.course5 = course5;
        this.course6 = course6;
        this.group = group;
        this.subgroup = subgroup;
        this.government = government;
        this.commercial = commercial;
        this.full_time = full_time;
        this.distance = distance;
        this.state = state;
        this.department = department;
        this.orphan = orphan;
        this.disabled = disabled;
    }

    public String getMechanics() {
        return mechanics;
    }

    public void setMechanics(String mechanics) {
        this.mechanics = mechanics;
    }

    public String getEngineers() {
        return engineers;
    }

    public void setEngineers(String engineers) {
        this.engineers = engineers;
    }

    public String getEducate() {
        return educate;
    }

    public void setEducate(String educate) {
        this.educate = educate;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public String getBachelor() {
        return bachelor;
    }

    public void setBachelor(String bachelor) {
        this.bachelor = bachelor;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getCourse2() {
        return course2;
    }

    public void setCourse2(String course2) {
        this.course2 = course2;
    }

    public String getCourse3() {
        return course3;
    }

    public void setCourse3(String course3) {
        this.course3 = course3;
    }

    public String getCourse4() {
        return course4;
    }

    public void setCourse4(String course4) {
        this.course4 = course4;
    }

    public String getCourse5() {
        return course5;
    }

    public void setCourse5(String course5) {
        this.course5 = course5;
    }

    public String getCourse6() {
        return course6;
    }

    public void setCourse6(String course6) {
        this.course6 = course6;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public String getGovernment() {
        return government;
    }

    public void setGovernment(String government) {
        this.government = government;
    }

    public String getCommercial() {
        return commercial;
    }

    public void setCommercial(String commercial) {
        this.commercial = commercial;
    }

    public String getFull_time() {
        return full_time;
    }

    public void setFull_time(String full_time) {
        this.full_time = full_time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrphan() {
        return orphan;
    }

    public void setOrphan(String orphan) {
        this.orphan = orphan;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SortParams that = (SortParams) o;

        if (mechanics != null ? !mechanics.equals(that.mechanics) : that.mechanics != null) return false;
        if (engineers != null ? !engineers.equals(that.engineers) : that.engineers != null) return false;
        if (educate != null ? !educate.equals(that.educate) : that.educate != null) return false;
        if (archive != null ? !archive.equals(that.archive) : that.archive != null) return false;
        if (bachelor != null ? !bachelor.equals(that.bachelor) : that.bachelor != null) return false;
        if (specialist != null ? !specialist.equals(that.specialist) : that.specialist != null) return false;
        if (master != null ? !master.equals(that.master) : that.master != null) return false;
        if (course1 != null ? !course1.equals(that.course1) : that.course1 != null) return false;
        if (course2 != null ? !course2.equals(that.course2) : that.course2 != null) return false;
        if (course3 != null ? !course3.equals(that.course3) : that.course3 != null) return false;
        if (course4 != null ? !course4.equals(that.course4) : that.course4 != null) return false;
        if (course5 != null ? !course5.equals(that.course5) : that.course5 != null) return false;
        if (course6 != null ? !course6.equals(that.course6) : that.course6 != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;
        if (subgroup != null ? !subgroup.equals(that.subgroup) : that.subgroup != null) return false;
        if (government != null ? !government.equals(that.government) : that.government != null) return false;
        if (commercial != null ? !commercial.equals(that.commercial) : that.commercial != null) return false;
        if (full_time != null ? !full_time.equals(that.full_time) : that.full_time != null) return false;
        if (distance != null ? !distance.equals(that.distance) : that.distance != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (orphan != null ? !orphan.equals(that.orphan) : that.orphan != null) return false;
        return disabled != null ? disabled.equals(that.disabled) : that.disabled == null;
    }

    @Override
    public int hashCode() {
        int result = mechanics != null ? mechanics.hashCode() : 0;
        result = 31 * result + (engineers != null ? engineers.hashCode() : 0);
        result = 31 * result + (educate != null ? educate.hashCode() : 0);
        result = 31 * result + (archive != null ? archive.hashCode() : 0);
        result = 31 * result + (bachelor != null ? bachelor.hashCode() : 0);
        result = 31 * result + (specialist != null ? specialist.hashCode() : 0);
        result = 31 * result + (master != null ? master.hashCode() : 0);
        result = 31 * result + (course1 != null ? course1.hashCode() : 0);
        result = 31 * result + (course2 != null ? course2.hashCode() : 0);
        result = 31 * result + (course3 != null ? course3.hashCode() : 0);
        result = 31 * result + (course4 != null ? course4.hashCode() : 0);
        result = 31 * result + (course5 != null ? course5.hashCode() : 0);
        result = 31 * result + (course6 != null ? course6.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (subgroup != null ? subgroup.hashCode() : 0);
        result = 31 * result + (government != null ? government.hashCode() : 0);
        result = 31 * result + (commercial != null ? commercial.hashCode() : 0);
        result = 31 * result + (full_time != null ? full_time.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (orphan != null ? orphan.hashCode() : 0);
        result = 31 * result + (disabled != null ? disabled.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SortParams{" +
                "mechanics='" + mechanics + '\'' +
                ", engineers='" + engineers + '\'' +
                ", educate='" + educate + '\'' +
                ", archive='" + archive + '\'' +
                ", bachelor='" + bachelor + '\'' +
                ", specialist='" + specialist + '\'' +
                ", master='" + master + '\'' +
                ", course1='" + course1 + '\'' +
                ", course2='" + course2 + '\'' +
                ", course3='" + course3 + '\'' +
                ", course4='" + course4 + '\'' +
                ", course5='" + course5 + '\'' +
                ", course6='" + course6 + '\'' +
                ", group='" + group + '\'' +
                ", subgroup='" + subgroup + '\'' +
                ", government='" + government + '\'' +
                ", commercial='" + commercial + '\'' +
                ", full_time='" + full_time + '\'' +
                ", distance='" + distance + '\'' +
                ", state='" + state + '\'' +
                ", department='" + department + '\'' +
                ", orphan='" + orphan + '\'' +
                ", disabled='" + disabled + '\'' +
                '}';
    }
}
