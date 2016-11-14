package ru.stqa.pft.addressbook.model;

public class ContactData {
  //private String name;
  //private final String name;
  private int id;
  private String firstname;
  private String lastname;
  private String address;
  private String email1;
  private String email2;
  private String email3;
  private String group;
  private String home;
  private String work;
  private String mobile;

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

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
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


  public ContactData withHome(String home) {
    this.home = home;
    return this;
  }
  //public ContactData(String name, String firstname, String lastname, String address, String telephonehome, String group) {
   // this.id = Integer.MAX_VALUE;
   // this.name = name;
   // this.firstname = firstname;
   /// this.lastname = lastname;
   // this.address = address;
  //  this.telephonehome = telephonehome;
  //  this.group = group;
 // }
 // public ContactData(int id, String name, String firstname, String lastname, String address, String telephonehome, String group) {
 //   this.id = id;
 //   this.name = name;
 //   this.firstname = firstname;
 //   this.lastname = lastname;
//    this.address = address;
 //   this.telephonehome = telephonehome;
 //   this.group = group;

  //  }


  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    if (group != null ? !group.equals(that.group) : that.group != null) return false;
    if (home != null ? !home.equals(that.home) : that.home != null) return false;
    if (work != null ? !work.equals(that.work) : that.work != null) return false;
    return mobile != null ? mobile.equals(that.mobile) : that.mobile == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (email1 != null ? email1.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (group != null ? group.hashCode() : 0);
    result = 31 * result + (home != null ? home.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", group='" + group + '\'' +
            ", home='" + home + '\'' +
            ", work='" + work + '\'' +
            ", mobile='" + mobile + '\'' +
            '}';
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

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
  public String getGroup() {
    return group;
  }

}
