package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoContactPage();
    ContactData contact = new ContactData(null, "Vladimir", "Kachaev", "Moscow", "79001234567","test1");
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(contact, true);
    }

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();

    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    //удаляем ненужный элемент
    before.remove(before.size() - 1);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    Assert.assertEquals(before, after);
  }
}
