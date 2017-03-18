package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dimitry on 17.03.17.
 */
@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "student_name", nullable = false)),
            @AttributeOverride(name = "surname", column = @Column(name = "student_surname", nullable = false)),
            @AttributeOverride(name = "patronimic", column = @Column(name = "student_patronimic", nullable = false)),
            @AttributeOverride(name = "phone1", column = @Column(name = "student_phone1")),
            @AttributeOverride(name = "phone2", column = @Column(name = "student_phone2")),
            @AttributeOverride(name = "email", column = @Column(name = "student_email"))
    })
    private Person student;

    @ManyToMany
    private List<Order> orders = new ArrayList<>();

    @Column(name = "student_status", nullable = false)
    private String studentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @Column(name = "student_subgroup", nullable = false)
    private int studentSubgroup;

    @Column(name = "student_financing", nullable = false)
    private String studentFinancing;

    @Column(name = "student_book", nullable = false)
    private String studentBook;

    @Column(name = "student_birth_date")
    private String studentBirthDate;

    @Column(name = "student_passport_number")
    private String studentPassportNumber;

    @Column(name = "student_passport_office")
    private String studentPassportOffice;

    @Column(name = "student_passport_release_date")
    private String studentPassportReleaseDate;

    @Column(name = "student_identity_code")
    private String studentIdentityCode;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "house", column = @Column(name = "student_house")),
            @AttributeOverride(name = "street", column = @Column(name = "student_street")),
            @AttributeOverride(name = "city", column = @Column(name = "student_city")),
            @AttributeOverride(name = "state", column = @Column(name = "student_state")),
            @AttributeOverride(name = "zip", column = @Column(name = "student_zip")),
            @AttributeOverride(name = "country", column = @Column(name = "student_country"))
    })
    private Address studentAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "father_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "father_surname")),
            @AttributeOverride(name = "patronimic", column = @Column(name = "father_patronimic")),
            @AttributeOverride(name = "phone1", column = @Column(name = "father_phone1")),
            @AttributeOverride(name = "phone2", column = @Column(name = "father_phone2")),
            @AttributeOverride(name = "email", column = @Column(name = "father_email"))
    })
    private Person father;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "mother_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "mother_surname")),
            @AttributeOverride(name = "patronimic", column = @Column(name = "mother_patronimic")),
            @AttributeOverride(name = "phone1", column = @Column(name = "mother_phone1")),
            @AttributeOverride(name = "phone2", column = @Column(name = "mother_phone2")),
            @AttributeOverride(name = "email", column = @Column(name = "mother_email"))
    })
    private Person mother;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "house", column = @Column(name = "parent_house")),
            @AttributeOverride(name = "street", column = @Column(name = "parent_street")),
            @AttributeOverride(name = "city", column = @Column(name = "parent_city")),
            @AttributeOverride(name = "state", column = @Column(name = "parent_state")),
            @AttributeOverride(name = "zip", column = @Column(name = "parent_zip")),
            @AttributeOverride(name = "country", column = @Column(name = "parent_country"))
    })
    private Address parentAddress;

    public Student() {
    }

    public Student(Person student, List<Order> orders, String studentStatus, Group group, int studentSubgroup, String studentFinancing, String studentBook, String studentBirthDate, String studentPassportNumber, String studentPassportOffice, String studentPassportReleaseDate, String studentIdentityCode, Address studentAddress, Person father, Person mother, Address parentAddress) {
        this.student = student;
        this.orders = orders;
        this.studentStatus = studentStatus;
        this.group = group;
        this.studentSubgroup = studentSubgroup;
        this.studentFinancing = studentFinancing;
        this.studentBook = studentBook;
        this.studentBirthDate = studentBirthDate;
        this.studentPassportNumber = studentPassportNumber;
        this.studentPassportOffice = studentPassportOffice;
        this.studentPassportReleaseDate = studentPassportReleaseDate;
        this.studentIdentityCode = studentIdentityCode;
        this.studentAddress = studentAddress;
        this.father = father;
        this.mother = mother;
        this.parentAddress = parentAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getStudentSubgroup() {
        return studentSubgroup;
    }

    public void setStudentSubgroup(int studentSubgroup) {
        this.studentSubgroup = studentSubgroup;
    }

    public String getStudentFinancing() {
        return studentFinancing;
    }

    public void setStudentFinancing(String studentFinancing) {
        this.studentFinancing = studentFinancing;
    }

    public String getStudentBook() {
        return studentBook;
    }

    public void setStudentBook(String studentBook) {
        this.studentBook = studentBook;
    }

    public String getStudentBirthDate() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(String studentBirthDate) {
        this.studentBirthDate = studentBirthDate;
    }

    public String getStudentPassportNumber() {
        return studentPassportNumber;
    }

    public void setStudentPassportNumber(String studentPassportNumber) {
        this.studentPassportNumber = studentPassportNumber;
    }

    public String getStudentPassportOffice() {
        return studentPassportOffice;
    }

    public void setStudentPassportOffice(String studentPassportOffice) {
        this.studentPassportOffice = studentPassportOffice;
    }

    public String getStudentPassportReleaseDate() {
        return studentPassportReleaseDate;
    }

    public void setStudentPassportReleaseDate(String studentPassportReleaseDate) {
        this.studentPassportReleaseDate = studentPassportReleaseDate;
    }

    public String getStudentIdentityCode() {
        return studentIdentityCode;
    }

    public void setStudentIdentityCode(String studentIdentityCode) {
        this.studentIdentityCode = studentIdentityCode;
    }

    public Address getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(Address studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Address getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(Address parentAddress) {
        this.parentAddress = parentAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student1 = (Student) o;

        if (id != student1.id) return false;
        if (studentSubgroup != student1.studentSubgroup) return false;
        if (student != null ? !student.equals(student1.student) : student1.student != null) return false;
        if (orders != null ? !orders.equals(student1.orders) : student1.orders != null) return false;
        if (studentStatus != null ? !studentStatus.equals(student1.studentStatus) : student1.studentStatus != null)
            return false;
        if (group != null ? !group.equals(student1.group) : student1.group != null) return false;
        if (studentFinancing != null ? !studentFinancing.equals(student1.studentFinancing) : student1.studentFinancing != null)
            return false;
        if (studentBook != null ? !studentBook.equals(student1.studentBook) : student1.studentBook != null)
            return false;
        if (studentBirthDate != null ? !studentBirthDate.equals(student1.studentBirthDate) : student1.studentBirthDate != null)
            return false;
        if (studentPassportNumber != null ? !studentPassportNumber.equals(student1.studentPassportNumber) : student1.studentPassportNumber != null)
            return false;
        if (studentPassportOffice != null ? !studentPassportOffice.equals(student1.studentPassportOffice) : student1.studentPassportOffice != null)
            return false;
        if (studentPassportReleaseDate != null ? !studentPassportReleaseDate.equals(student1.studentPassportReleaseDate) : student1.studentPassportReleaseDate != null)
            return false;
        if (studentIdentityCode != null ? !studentIdentityCode.equals(student1.studentIdentityCode) : student1.studentIdentityCode != null)
            return false;
        if (studentAddress != null ? !studentAddress.equals(student1.studentAddress) : student1.studentAddress != null)
            return false;
        if (father != null ? !father.equals(student1.father) : student1.father != null) return false;
        if (mother != null ? !mother.equals(student1.mother) : student1.mother != null) return false;
        return parentAddress != null ? parentAddress.equals(student1.parentAddress) : student1.parentAddress == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (studentStatus != null ? studentStatus.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + studentSubgroup;
        result = 31 * result + (studentFinancing != null ? studentFinancing.hashCode() : 0);
        result = 31 * result + (studentBook != null ? studentBook.hashCode() : 0);
        result = 31 * result + (studentBirthDate != null ? studentBirthDate.hashCode() : 0);
        result = 31 * result + (studentPassportNumber != null ? studentPassportNumber.hashCode() : 0);
        result = 31 * result + (studentPassportOffice != null ? studentPassportOffice.hashCode() : 0);
        result = 31 * result + (studentPassportReleaseDate != null ? studentPassportReleaseDate.hashCode() : 0);
        result = 31 * result + (studentIdentityCode != null ? studentIdentityCode.hashCode() : 0);
        result = 31 * result + (studentAddress != null ? studentAddress.hashCode() : 0);
        result = 31 * result + (father != null ? father.hashCode() : 0);
        result = 31 * result + (mother != null ? mother.hashCode() : 0);
        result = 31 * result + (parentAddress != null ? parentAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", student=" + student +
                ", orders=" + orders +
                ", studentStatus='" + studentStatus + '\'' +
                ", group=" + group +
                ", studentSubgroup=" + studentSubgroup +
                ", studentFinancing='" + studentFinancing + '\'' +
                ", studentBook='" + studentBook + '\'' +
                ", studentBirthDate='" + studentBirthDate + '\'' +
                ", studentPassportNumber='" + studentPassportNumber + '\'' +
                ", studentPassportOffice='" + studentPassportOffice + '\'' +
                ", studentPassportReleaseDate='" + studentPassportReleaseDate + '\'' +
                ", studentIdentityCode='" + studentIdentityCode + '\'' +
                ", studentAddress=" + studentAddress +
                ", father=" + father +
                ", mother=" + mother +
                ", parentAddress=" + parentAddress +
                '}';
    }
}
