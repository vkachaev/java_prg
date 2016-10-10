package ru.stqa.pft.sandbox;

/**
 * Created by Katya on 28.09.2016.
 */
public class Point {
  double x;
  double y;
  private double у;


  public Point(double x, double y){
    this.x = x;
    this.y = y;


  }
  double distance(double х, double у) {

    double dx = this.x - х;
    double dy = this.у - у;

    return Math.sqrt(dx * dx + dy * dy);
  }
  public double distance(Point p2) {

    return distance(p2.x, p2.y);


  }

}
