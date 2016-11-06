package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().gotoGroupPage();

    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("test5", null, null);
    app.getGroupHelper().createGroup(group);
    //app.getGroupHelper().returnToGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1); //сравниваем размеры списков
    //поиск максимаольного значения value

    //неэффективный способ поиска макс id
    int max = 0;
    for (GroupData g: after){
      if (g.getId() > max){
        max = g.getId();
      }
    }
    //Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    //список превращаем в поток, по этому потоку пробегается функция comparator, при этом сравниваются объекты groupData
    //путем сравнения их идентификаторов, на выходе будет группа с макс идентификатором и берем ее идентификатор
    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    //преобразруем упорядоченный список в неупорядоченный
    Assert.assertEquals(before, after);
  }

}
