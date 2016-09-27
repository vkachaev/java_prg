package ru.stqa.pft.sandbox;

public class MyFirstProgram
{
public static void main(String[] args) {
  hello("world");
  hello("user");
  hello("Vladimir");

  Square s = new Square(5);

  System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

  Rectangle r = new Rectangle(4, 6);


  System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());


  Point p = new Point(1, 1, 5, 4);
  System.out.println("Расстояние между точкой с координатой x1=" + p.x1 + "; y1=" + p.y1 + " и координатой x2=" + p.x2 + "; y2=" + p.y2 + " равно " + distance(p));
}
  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double distance(Point p) {
    return Math.sqrt(Math.pow((p.x2 - p.x1), 2) + Math.pow((p.y2 - p.y1), 2));
  }


}