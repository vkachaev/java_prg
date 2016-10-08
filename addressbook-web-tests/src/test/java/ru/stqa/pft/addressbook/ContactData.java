package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String telephonehome;

  public ContactData(String firstname, String lastname, String address, String telephonehome) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.telephonehome = telephonehome;
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
}
