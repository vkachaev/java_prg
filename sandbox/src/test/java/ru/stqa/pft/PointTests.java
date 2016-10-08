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
    Point p = new Point(1, 1, 5, 4);

    Assert.assertEquals(p.distance(), 5.0);
  }
}
