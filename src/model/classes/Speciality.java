package model.classes;

/**
 * Created by Dimitry on 13.02.17.
 */
public class Speciality {

    String id;
    String speciality;

    public Speciality() {
    }

    public Speciality(String id, String speciality) {
        this.id = id;
        this.speciality = speciality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speciality that = (Speciality) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return speciality != null ? speciality.equals(that.speciality) : that.speciality == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id='" + id + '\'' +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
