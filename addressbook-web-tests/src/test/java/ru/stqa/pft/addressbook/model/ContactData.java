package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  //private final String name;
  private int id;
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String telephonehome;
  private String group;

  public ContactData(String name, String firstname, String lastname, String address, String telephonehome, String group) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.telephonehome = telephonehome;
    this.group = group;
  }
  public ContactData(int id, String name, String firstname, String lastname, String address, String telephonehome, String group) {
    this.id = id;
    this.name = name;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.telephonehome = telephonehome;
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    return name != null ? name.equals(that.name) : that.name == null;

  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", id=" + id +
            '}';
  }



  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getTelephonehome() {
    return telephonehome;
  }

  //to receive field
  public String getGroup() {
    return group;
  }

}
