package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Vladimir", "Kachaev", "Moscow", "79001234567"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToContactPage();
    }


}
