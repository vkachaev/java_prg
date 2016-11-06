package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Katya on 09.10.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();

    if (!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    //бурем id у выбранной строки для группы и передаем в groupddata
    GroupData group = new GroupData(before.get(before.size()-1).getId(), "test1", "test2", "test3");
    app.getGroupHelper().fillGroupForm(group);

    app.getGroupHelper().submitGroupModification();

    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size());
    //удаляем в списке последнюю запись, т.к. она была изменена
    before.remove(before.size() - 1);
    //добавляем созданный объект
    before.add(group);
    //преобразруем упорядоченный список в неупорядоченный
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

  }
}
