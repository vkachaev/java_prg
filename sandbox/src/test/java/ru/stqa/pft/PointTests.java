package ru.stqa.pft;
import ru.stqa.pft.sandbox.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Katya on 08.10.2016.
 */
public class PointTests {
  @Test
  public void testDistance(){
    Point p1 = new Point(0,0);
    Point p2 = new Point(4,3);

    Assert.assertEquals(p1.distance(p2), 5.0);
  }
}
