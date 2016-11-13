package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getNavigationHelper().gotoContactPage();

    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData(null, "Vladimir_update", "Kachaev_update", "Moscow_update", "79001234567_update","test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(), "Kachaev_update Vladimir_update", "Vladimir_update", "Kachaev_update",null, null, null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    //удаляем в списке последнюю запись, т.к. она была изменена
    before.remove(before.size() - 1);
    //добавляем созданный объект
    before.add(contact);
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    //сравниваем отсортированные списки
    Assert.assertEquals(before, after);

  }
}
