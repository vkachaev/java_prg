package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().contactPage();

        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData("Kachaev2 Vladimir2", "Vladimir2", "Kachaev2", null, null,"test1");
        app.contact().createContact(contact, true);

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        app.goTo().gotoHomePage();
        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        //преобразруем упорядоченный список в неупорядоченный
        Assert.assertEquals(before, after);
    }


}
