package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod (enabled = false)
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0 ){
            app.group().create(new GroupData("test1", null, null));
        }
    }
    @Test (enabled = false)
    public void testGroupDeletion() {

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);
        //удаляем ненужный элемент
        before.remove(index);
        Assert.assertEquals(before, after);

    }




}
