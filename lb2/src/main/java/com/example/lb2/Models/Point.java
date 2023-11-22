package com.example.lb2.Models;

public class Point {
    private final double x;
    private final double y;
    private final double r;
    private final String  isInArea;

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInArea = isInArea(x,y,r);
    }

    private String isInArea(double x, double y, double r) {
        if(((x >= 0 )&&(x <= r)&&(0 >= y)&& (y>= -r/2))||
                ((x >= 0)&& (y >= 0)&& (x*x+y*y <= r*r))||
                        ((-r/2 <= x)&&(x<=0)&&(y >= 0)&&(y <= r+2*x))){
            return ("Попал");

        }else {
            return ("Мимо");
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public String isInArea() {
        return isInArea;
    }
    @Override
    public String toString(){
        return("x = " + this.x + " y = " + this.y +  " r = " + this.r + " " + isInArea);
    }
    @Override
    public boolean equals(Object obj) {

        if ( obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Point point = (Point) obj;
        return point.x == this.x && point.y == this.y
                && point.r == this.r && point.isInArea.equals(this.isInArea);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}