package model;

/**
 * Created by Dimitry on 12.02.17.
 */
public class Group {
    private String groupId;
    private String speciality;
    private String qualificationLevel;
    private String educationForm;
    private String number;
    private String course;

    public Group() {
    }

    public Group(String groupId, String speciality, String qualificationLevel, String educationForm, String number, String course) {
        this.groupId = groupId;
        this.speciality = speciality;
        this.qualificationLevel = qualificationLevel;
        this.educationForm = educationForm;
        this.number = number;
        this.course = course;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getQualificationLevel() {
        return qualificationLevel;
    }

    public void setQualificationLevel(String qualificationLevel) {
        this.qualificationLevel = qualificationLevel;
    }

    public String getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(String educationForm) {
        this.educationForm = educationForm;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupId != null ? !groupId.equals(group.groupId) : group.groupId != null) return false;
        if (speciality != null ? !speciality.equals(group.speciality) : group.speciality != null) return false;
        if (qualificationLevel != null ? !qualificationLevel.equals(group.qualificationLevel) : group.qualificationLevel != null)
            return false;
        if (educationForm != null ? !educationForm.equals(group.educationForm) : group.educationForm != null)
            return false;
        if (number != null ? !number.equals(group.number) : group.number != null) return false;
        return course != null ? course.equals(group.course) : group.course == null;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (qualificationLevel != null ? qualificationLevel.hashCode() : 0);
        result = 31 * result + (educationForm != null ? educationForm.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", speciality='" + speciality + '\'' +
                ", qualificationLevel='" + qualificationLevel + '\'' +
                ", educationForm='" + educationForm + '\'' +
                ", number='" + number + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
