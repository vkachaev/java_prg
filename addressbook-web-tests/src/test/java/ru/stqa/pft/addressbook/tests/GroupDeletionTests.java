package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
    }
    @Test
    public void testGroupDeletion() {

        Groups before = app.db().groups();
        //вернет первый попавшейся элемент
        GroupData deletedGroup = before.iterator().next();
        app.goTo().groupPage();
        app.group().delete(deletedGroup);

        assertThat(app.group().count(), equalTo(before.size() - 1));
        Groups after = app.db().groups();

        //удаляем ненужный элемент
        before.remove(deletedGroup);
        assertThat(after, equalTo(before.without(deletedGroup)));

    }




}
