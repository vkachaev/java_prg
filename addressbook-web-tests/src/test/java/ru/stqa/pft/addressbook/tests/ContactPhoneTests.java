package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by Katya on 14.11.2016.
 */
public class ContactPhoneTests extends TestBase {
  @Test
  public void testContactPhone(){
    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    ContactData contact = before.iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
  }

  public String cleaned(String phone){
    //for space, tab, enter
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
