package model.classes;

/**
 * Created by Dimitry on 12.02.17.
 */
public class Group {
    private String groupId;
    private String specId;
    private String number;
    private String educationForm;
    private String qualificationLevel;
    private String course;
    private String status;

    public Group() {
    }

    public Group(String groupId, String specId, String number, String educationForm, String qualificationLevel, String course) {
        this.groupId = groupId;
        this.specId = specId;
        this.number = number;
        this.educationForm = educationForm;
        this.qualificationLevel = qualificationLevel;
        this.course = course;
    }

    public Group(String groupId, String specId, String number, String educationForm, String qualificationLevel, String course, String status) {
        this.groupId = groupId;
        this.specId = specId;
        this.number = number;
        this.educationForm = educationForm;
        this.qualificationLevel = qualificationLevel;
        this.course = course;
        this.status = status;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupId != null ? !groupId.equals(group.groupId) : group.groupId != null) return false;
        if (specId != null ? !specId.equals(group.specId) : group.specId != null) return false;
        if (number != null ? !number.equals(group.number) : group.number != null) return false;
        if (educationForm != null ? !educationForm.equals(group.educationForm) : group.educationForm != null)
            return false;
        if (qualificationLevel != null ? !qualificationLevel.equals(group.qualificationLevel) : group.qualificationLevel != null)
            return false;
        if (course != null ? !course.equals(group.course) : group.course != null) return false;
        return status != null ? status.equals(group.status) : group.status == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (specId != null ? specId.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (educationForm != null ? educationForm.hashCode() : 0);
        result = 31 * result + (qualificationLevel != null ? qualificationLevel.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", specId='" + specId + '\'' +
                ", number='" + number + '\'' +
                ", educationForm='" + educationForm + '\'' +
                ", qualificationLevel='" + qualificationLevel + '\'' +
                ", course='" + course + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
