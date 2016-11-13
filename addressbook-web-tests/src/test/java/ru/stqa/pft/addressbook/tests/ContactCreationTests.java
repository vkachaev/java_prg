package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().contactPage();

        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withName("Kachaev2 Vladimir2").withLastname("Kachaev2").withFirstname("Vladimir2").withGroup("test1");
        app.contact().createContact(contact, true);

        Contacts after = app.contact().all();

        //assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }


}
