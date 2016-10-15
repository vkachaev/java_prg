package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Vladimir", "Kachaev", "Moscow", "79001234567","test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Vladimir_update", "Kachaev_update", "Moscow_update", "79001234567_update",null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();

  }
}
