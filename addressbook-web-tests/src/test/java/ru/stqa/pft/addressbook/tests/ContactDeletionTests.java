package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeTest (enabled = false)
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().createContact(new ContactData(null, "Vladimir_update", "Kachaev_update", "Moscow_update", "79001234567_update", "test1"), true);
    }
  }

  @Test (enabled = false)
  public void testContactDeletion(){

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    //удаляем ненужный элемент
    before.remove(before.size() - 1);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    Assert.assertEquals(before, after);
  }


}
