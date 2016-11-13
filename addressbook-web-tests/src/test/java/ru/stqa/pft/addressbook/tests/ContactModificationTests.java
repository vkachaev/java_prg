package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeTest
   public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().createContact(new ContactData(null, "Vladimir_update", "Kachaev_update", "Moscow_update", "79001234567_update", "test1"), true);
    }
  }
  @Test
  public void testContactModification(){


    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Kachaev_update Vladimir_update", "Vladimir_update", "Kachaev_update",null, null, null);
    app.contact().modifyContact(index, contact);

    List<ContactData> after = app.contact().list();
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
