package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().gotoHomePage();
  }
}
