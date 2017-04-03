package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitry on 17.03.17.
 */
@Entity
@Table(name = "Universities")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "university_title", unique = true, nullable = false)
    private String title;

    @Column(name = "university_motto")
    private String motto;

    @Column(name = "university_date_found")
    private String dateFound;

    @Column(name = "university_type", nullable = false)
    private String type;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "president_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "president_surname")),
            @AttributeOverride(name = "lastname", column = @Column(name = "president_lastname")),
            @AttributeOverride(name = "phone1", column = @Column(name = "president_phone1")),
            @AttributeOverride(name = "phone2", column = @Column(name = "president_phone2")),
            @AttributeOverride(name = "email", column = @Column(name = "president_email"))
    })
    private Person president;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "house", column = @Column(name = "university_house")),
            @AttributeOverride(name = "street", column = @Column(name = "university_street")),
            @AttributeOverride(name = "city", column = @Column(name = "university_city")),
            @AttributeOverride(name = "state", column = @Column(name = "university_state")),
            @AttributeOverride(name = "zip", column = @Column(name = "university_zip")),
            @AttributeOverride(name = "country", column = @Column(name = "university_country"))
    })
    private Address address;

    @Column(name = "university_website")
    private String webSite;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "university")
    private Set<Faculty> faculties = new HashSet<>();

    public University() {
    }

    public University(String title, String motto, String dateFound, String type, Person president, Address address, String webSite) {
        this.title = title;
        this.motto = motto;
        this.dateFound = dateFound;
        this.type = type;
        this.president = president;
        this.address = address;
        this.webSite = webSite;
    }

    public University(String title, String motto, String dateFound, String type, Person president, Address address, String webSite, Set<Faculty> faculties) {
        this.title = title;
        this.motto = motto;
        this.dateFound = dateFound;
        this.type = type;
        this.president = president;
        this.address = address;
        this.webSite = webSite;
        this.faculties = faculties;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getDateFound() {
        return dateFound;
    }

    public void setDateFound(String dateFound) {
        this.dateFound = dateFound;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person getPresident() {
        return president;
    }

    public void setPresident(Person president) {
        this.president = president;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Set<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
        this.faculties = faculties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        University that = (University) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (motto != null ? !motto.equals(that.motto) : that.motto != null) return false;
        if (dateFound != null ? !dateFound.equals(that.dateFound) : that.dateFound != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (president != null ? !president.equals(that.president) : that.president != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (webSite != null ? !webSite.equals(that.webSite) : that.webSite != null) return false;
        return faculties != null ? faculties.equals(that.faculties) : that.faculties == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (motto != null ? motto.hashCode() : 0);
        result = 31 * result + (dateFound != null ? dateFound.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (president != null ? president.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + (faculties != null ? faculties.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", motto='" + motto + '\'' +
                ", dateFound='" + dateFound + '\'' +
                ", type='" + type + '\'' +
                ", president=" + president +
                ", address=" + address +
                ", webSite='" + webSite + '\'' +
                ", faculties=" + faculties +
                '}';
    }
}
