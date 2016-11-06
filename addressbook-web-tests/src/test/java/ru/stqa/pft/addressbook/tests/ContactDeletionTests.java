package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Vladimir", "Kachaev", "Moscow", "79001234567","test1"), true);
    }
    int  before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();

    app.getNavigationHelper().gotoHomePage();
    int  after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);

  }
}
