package model.classes;

import java.io.Serializable;

/**
 * Created by Dimitry on 06.02.17.
 */
public class Student implements Serializable {
    private String id;
    private String name;
    private String surname;
    private String lastname;
    private String entryDate;
    private String status;
    private String groupId;
    private String subGroup;
    private String financing;
    private String studBook;
    private String birthDate;
    private String passport;
    private String passpOffice;
    private String passpDate;
    private String identityCode;
    private String studHouse;
    private String studStreet;
    private String studCity;
    private String studState;
    private String studZip;
    private String studCountry;
    private String studPhone1;
    private String fatherName;
    private String fatherSurname;
    private String fatherLastname;
    private String fatherPhone1;
    private String fatherPhone2;
    private String motherName;
    private String motherSurname;
    private String motherLastname;
    private String motherPhone1;
    private String motherPhone2;
    private String parentsHouse;
    private String parentsStreet;
    private String parentsCity;
    private String parentsState;
    private String parentsZip;
    private String parentsCountry;


    public Student() {
    }

    public Student(String id, String name, String surname, String lastname, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.status = status;
    }

    public Student(String id, String name, String surname, String lastname, String entryDate,
                   String status, String groupId, String subGroup, String financing,
                   String studBook, String birthDate, String passport, String passpOffice,
                   String passpDate, String identityCode, String studHouse, String studStreet,
                   String studCity, String studState, String studZip, String studCountry,
                   String studPhone1, String fatherName, String fatherSurname, String fatherLastname,
                   String fatherPhone1, String fatherPhone2, String motherName, String motherSurname,
                   String motherLastname, String motherPhone1, String motherPhone2, String parentsHouse,
                   String parentsStreet, String parentsCity, String parentsState,
                   String parentsZip, String parentsCountry) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.entryDate = entryDate;
        this.status = status;
        this.groupId = groupId;
        this.subGroup = subGroup;
        this.financing = financing;
        this.studBook = studBook;
        this.birthDate = birthDate;
        this.passport = passport;
        this.passpOffice = passpOffice;
        this.passpDate = passpDate;
        this.identityCode = identityCode;
        this.studHouse = studHouse;
        this.studStreet = studStreet;
        this.studCity = studCity;
        this.studState = studState;
        this.studZip = studZip;
        this.studCountry = studCountry;
        this.studPhone1 = studPhone1;
        this.fatherName = fatherName;
        this.fatherSurname = fatherSurname;
        this.fatherLastname = fatherLastname;
        this.fatherPhone1 = fatherPhone1;
        this.fatherPhone2 = fatherPhone2;
        this.motherName = motherName;
        this.motherSurname = motherSurname;
        this.motherLastname = motherLastname;
        this.motherPhone1 = motherPhone1;
        this.motherPhone2 = motherPhone2;
        this.parentsHouse = parentsHouse;
        this.parentsStreet = parentsStreet;
        this.parentsCity = parentsCity;
        this.parentsState = parentsState;
        this.parentsZip = parentsZip;
        this.parentsCountry = parentsCountry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public String getFinancing() {
        return financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing;
    }

    public String getStudBook() {
        return studBook;
    }

    public void setStudBook(String studBook) {
        this.studBook = studBook;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPasspOffice() {
        return passpOffice;
    }

    public void setPasspOffice(String passpOffice) {
        this.passpOffice = passpOffice;
    }

    public String getPasspDate() {
        return passpDate;
    }

    public void setPasspDate(String passpDate) {
        this.passpDate = passpDate;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getStudHouse() {
        return studHouse;
    }

    public void setStudHouse(String studHouse) {
        this.studHouse = studHouse;
    }

    public String getStudStreet() {
        return studStreet;
    }

    public void setStudStreet(String studStreet) {
        this.studStreet = studStreet;
    }

    public String getStudCity() {
        return studCity;
    }

    public void setStudCity(String studCity) {
        this.studCity = studCity;
    }

    public String getStudState() {
        return studState;
    }

    public void setStudState(String studState) {
        this.studState = studState;
    }

    public String getStudZip() {
        return studZip;
    }

    public void setStudZip(String studZip) {
        this.studZip = studZip;
    }

    public String getStudCountry() {
        return studCountry;
    }

    public void setStudCountry(String studCountry) {
        this.studCountry = studCountry;
    }

    public String getStudPhone1() {
        return studPhone1;
    }

    public void setStudPhone1(String studPhone1) {
        this.studPhone1 = studPhone1;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherSurname() {
        return fatherSurname;
    }

    public void setFatherSurname(String fatherSurname) {
        this.fatherSurname = fatherSurname;
    }

    public String getFatherLastname() {
        return fatherLastname;
    }

    public void setFatherLastname(String fatherLastname) {
        this.fatherLastname = fatherLastname;
    }

    public String getFatherPhone1() {
        return fatherPhone1;
    }

    public void setFatherPhone1(String fatherPhone1) {
        this.fatherPhone1 = fatherPhone1;
    }

    public String getFatherPhone2() {
        return fatherPhone2;
    }

    public void setFatherPhone2(String fatherPhone2) {
        this.fatherPhone2 = fatherPhone2;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherSurname() {
        return motherSurname;
    }

    public void setMotherSurname(String motherSurname) {
        this.motherSurname = motherSurname;
    }

    public String getMotherLastname() {
        return motherLastname;
    }

    public void setMotherLastname(String motherLastname) {
        this.motherLastname = motherLastname;
    }

    public String getMotherPhone1() {
        return motherPhone1;
    }

    public void setMotherPhone1(String motherPhone1) {
        this.motherPhone1 = motherPhone1;
    }

    public String getMotherPhone2() {
        return motherPhone2;
    }

    public void setMotherPhone2(String motherPhone2) {
        this.motherPhone2 = motherPhone2;
    }

    public String getParentsHouse() {
        return parentsHouse;
    }

    public void setParentsHouse(String parentsHouse) {
        this.parentsHouse = parentsHouse;
    }

    public String getParentsStreet() {
        return parentsStreet;
    }

    public void setParentsStreet(String parentsStreet) {
        this.parentsStreet = parentsStreet;
    }

    public String getParentsCity() {
        return parentsCity;
    }

    public void setParentsCity(String parentsCity) {
        this.parentsCity = parentsCity;
    }

    public String getParentsState() {
        return parentsState;
    }

    public void setParentsState(String parentsState) {
        this.parentsState = parentsState;
    }

    public String getParentsZip() {
        return parentsZip;
    }

    public void setParentsZip(String parentsZip) {
        this.parentsZip = parentsZip;
    }

    public String getParentsCountry() {
        return parentsCountry;
    }

    public void setParentsCountry(String parentsCountry) {
        this.parentsCountry = parentsCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (surname != null ? !surname.equals(student.surname) : student.surname != null) return false;
        if (lastname != null ? !lastname.equals(student.lastname) : student.lastname != null) return false;
        if (entryDate != null ? !entryDate.equals(student.entryDate) : student.entryDate != null) return false;
        if (status != null ? !status.equals(student.status) : student.status != null) return false;
        if (groupId != null ? !groupId.equals(student.groupId) : student.groupId != null) return false;
        if (subGroup != null ? !subGroup.equals(student.subGroup) : student.subGroup != null) return false;
        if (financing != null ? !financing.equals(student.financing) : student.financing != null) return false;
        if (studBook != null ? !studBook.equals(student.studBook) : student.studBook != null) return false;
        if (birthDate != null ? !birthDate.equals(student.birthDate) : student.birthDate != null) return false;
        if (passport != null ? !passport.equals(student.passport) : student.passport != null) return false;
        if (passpOffice != null ? !passpOffice.equals(student.passpOffice) : student.passpOffice != null) return false;
        if (passpDate != null ? !passpDate.equals(student.passpDate) : student.passpDate != null) return false;
        if (identityCode != null ? !identityCode.equals(student.identityCode) : student.identityCode != null)
            return false;
        if (studHouse != null ? !studHouse.equals(student.studHouse) : student.studHouse != null) return false;
        if (studStreet != null ? !studStreet.equals(student.studStreet) : student.studStreet != null) return false;
        if (studCity != null ? !studCity.equals(student.studCity) : student.studCity != null) return false;
        if (studState != null ? !studState.equals(student.studState) : student.studState != null) return false;
        if (studZip != null ? !studZip.equals(student.studZip) : student.studZip != null) return false;
        if (studCountry != null ? !studCountry.equals(student.studCountry) : student.studCountry != null) return false;
        if (studPhone1 != null ? !studPhone1.equals(student.studPhone1) : student.studPhone1 != null) return false;
        if (fatherName != null ? !fatherName.equals(student.fatherName) : student.fatherName != null) return false;
        if (fatherSurname != null ? !fatherSurname.equals(student.fatherSurname) : student.fatherSurname != null)
            return false;
        if (fatherLastname != null ? !fatherLastname.equals(student.fatherLastname) : student.fatherLastname != null)
            return false;
        if (fatherPhone1 != null ? !fatherPhone1.equals(student.fatherPhone1) : student.fatherPhone1 != null)
            return false;
        if (fatherPhone2 != null ? !fatherPhone2.equals(student.fatherPhone2) : student.fatherPhone2 != null)
            return false;
        if (motherName != null ? !motherName.equals(student.motherName) : student.motherName != null) return false;
        if (motherSurname != null ? !motherSurname.equals(student.motherSurname) : student.motherSurname != null)
            return false;
        if (motherLastname != null ? !motherLastname.equals(student.motherLastname) : student.motherLastname != null)
            return false;
        if (motherPhone1 != null ? !motherPhone1.equals(student.motherPhone1) : student.motherPhone1 != null)
            return false;
        if (motherPhone2 != null ? !motherPhone2.equals(student.motherPhone2) : student.motherPhone2 != null)
            return false;
        if (parentsHouse != null ? !parentsHouse.equals(student.parentsHouse) : student.parentsHouse != null)
            return false;
        if (parentsStreet != null ? !parentsStreet.equals(student.parentsStreet) : student.parentsStreet != null)
            return false;
        if (parentsCity != null ? !parentsCity.equals(student.parentsCity) : student.parentsCity != null) return false;
        if (parentsState != null ? !parentsState.equals(student.parentsState) : student.parentsState != null)
            return false;
        if (parentsZip != null ? !parentsZip.equals(student.parentsZip) : student.parentsZip != null) return false;
        return parentsCountry != null ? parentsCountry.equals(student.parentsCountry) : student.parentsCountry == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (entryDate != null ? entryDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (subGroup != null ? subGroup.hashCode() : 0);
        result = 31 * result + (financing != null ? financing.hashCode() : 0);
        result = 31 * result + (studBook != null ? studBook.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (passpOffice != null ? passpOffice.hashCode() : 0);
        result = 31 * result + (passpDate != null ? passpDate.hashCode() : 0);
        result = 31 * result + (identityCode != null ? identityCode.hashCode() : 0);
        result = 31 * result + (studHouse != null ? studHouse.hashCode() : 0);
        result = 31 * result + (studStreet != null ? studStreet.hashCode() : 0);
        result = 31 * result + (studCity != null ? studCity.hashCode() : 0);
        result = 31 * result + (studState != null ? studState.hashCode() : 0);
        result = 31 * result + (studZip != null ? studZip.hashCode() : 0);
        result = 31 * result + (studCountry != null ? studCountry.hashCode() : 0);
        result = 31 * result + (studPhone1 != null ? studPhone1.hashCode() : 0);
        result = 31 * result + (fatherName != null ? fatherName.hashCode() : 0);
        result = 31 * result + (fatherSurname != null ? fatherSurname.hashCode() : 0);
        result = 31 * result + (fatherLastname != null ? fatherLastname.hashCode() : 0);
        result = 31 * result + (fatherPhone1 != null ? fatherPhone1.hashCode() : 0);
        result = 31 * result + (fatherPhone2 != null ? fatherPhone2.hashCode() : 0);
        result = 31 * result + (motherName != null ? motherName.hashCode() : 0);
        result = 31 * result + (motherSurname != null ? motherSurname.hashCode() : 0);
        result = 31 * result + (motherLastname != null ? motherLastname.hashCode() : 0);
        result = 31 * result + (motherPhone1 != null ? motherPhone1.hashCode() : 0);
        result = 31 * result + (motherPhone2 != null ? motherPhone2.hashCode() : 0);
        result = 31 * result + (parentsHouse != null ? parentsHouse.hashCode() : 0);
        result = 31 * result + (parentsStreet != null ? parentsStreet.hashCode() : 0);
        result = 31 * result + (parentsCity != null ? parentsCity.hashCode() : 0);
        result = 31 * result + (parentsState != null ? parentsState.hashCode() : 0);
        result = 31 * result + (parentsZip != null ? parentsZip.hashCode() : 0);
        result = 31 * result + (parentsCountry != null ? parentsCountry.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", status='" + status + '\'' +
                ", groupId='" + groupId + '\'' +
                ", subGroup='" + subGroup + '\'' +
                ", financing='" + financing + '\'' +
                ", studBook='" + studBook + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", passport='" + passport + '\'' +
                ", passpOffice='" + passpOffice + '\'' +
                ", passpDate='" + passpDate + '\'' +
                ", identityCode='" + identityCode + '\'' +
                ", studHouse='" + studHouse + '\'' +
                ", studStreet='" + studStreet + '\'' +
                ", studCity='" + studCity + '\'' +
                ", studState='" + studState + '\'' +
                ", studZip='" + studZip + '\'' +
                ", studCountry='" + studCountry + '\'' +
                ", studPhone1='" + studPhone1 + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", fatherSurname='" + fatherSurname + '\'' +
                ", fatherLastname='" + fatherLastname + '\'' +
                ", fatherPhone1='" + fatherPhone1 + '\'' +
                ", fatherPhone2='" + fatherPhone2 + '\'' +
                ", motherName='" + motherName + '\'' +
                ", motherSurname='" + motherSurname + '\'' +
                ", motherLastname='" + motherLastname + '\'' +
                ", motherPhone1='" + motherPhone1 + '\'' +
                ", motherPhone2='" + motherPhone2 + '\'' +
                ", parentsHouse='" + parentsHouse + '\'' +
                ", parentsStreet='" + parentsStreet + '\'' +
                ", parentsCity='" + parentsCity + '\'' +
                ", parentsState='" + parentsState + '\'' +
                ", parentsZip='" + parentsZip + '\'' +
                ", parentsCountry='" + parentsCountry + '\'' +
                '}';
    }
}
