package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Table;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@javax.persistence.Table(name ="addressbook")
@XStreamAlias("contact")
public class ContactData {
  //private String name;
  //private final String name;
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;
  @Expose
  @Transient
  private String allemails;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email1;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allphones;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String home;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String work;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;
  @Transient
  private String contactdetails;
  @Transient
  private String allcontacts;

  @Transient
//  @Column(name = "photo")
//  @Type(type = "text")
  private String photo;

  //@Expose
  //@Transient
  //private String group;

  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public ContactData withAllcontacts(String allcontacts) {
    this.allcontacts = allcontacts;
    return this;
  }

  public ContactData withContactdetails(String contactdetails) {
    this.contactdetails = contactdetails;
    return this;
  }
  //public ContactData withName(String name) {
  //  this.name = name;
  //  return this;

  //}

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withAllEmails(String allemails) {
    this.allemails = allemails;
    return this;
  }
  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  //public ContactData withGroup(String group) {
  //  this.group = group;
  //  return this;
  //}

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public ContactData withWork(String work) {
    this.work = work;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", allemails='" + allemails + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allphones='" + allphones + '\'' +
            ", home='" + home + '\'' +
            ", work='" + work + '\'' +
            ", mobile='" + mobile + '\'' +
            ", contactdetails='" + contactdetails + '\'' +
            ", allcontacts='" + allcontacts + '\'' +
            '}';
  }

  public ContactData withHome(String home) {
    this.home = home;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getAllphones() { return allphones;  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getAllemails() { return allemails;  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomePhone() {
    return home;
  }

  public String getWorkPhone() { return work;  }

  public String getMobilePhone() { return mobile;  }
  //to receive field

  public String getContactdetails() { return contactdetails; }

  public String getAllcontacts() { return allcontacts; }

  //public String getGroup() {
  //  return group;
  //}

  //getter возвращает объект типа groups
  public Groups getGroups() {
    return new Groups(groups);

  }

  public File getPhoto() { return new File(photo);  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (allemails != null ? !allemails.equals(that.allemails) : that.allemails != null) return false;
    if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    if (allphones != null ? !allphones.equals(that.allphones) : that.allphones != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    if (work != null ? !work.equals(that.work) : that.work != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (contactdetails != null ? !contactdetails.equals(that.contactdetails) : that.contactdetails != null)
      return false;
    return allcontacts != null ? allcontacts.equals(that.allcontacts) : that.allcontacts == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (allemails != null ? allemails.hashCode() : 0);
    result = 31 * result + (email1 != null ? email1.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (allphones != null ? allphones.hashCode() : 0);
    result = 31 * result + (home != null ? home.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (contactdetails != null ? contactdetails.hashCode() : 0);
    result = 31 * result + (allcontacts != null ? allcontacts.hashCode() : 0);
    return result;
  }

  public ContactData inGroups(GroupData group) {
    groups.add(group);
    return this;
  }
}

