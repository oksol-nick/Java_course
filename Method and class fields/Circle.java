package com.example.circle;

/**
 * Заполните этот класс в соответсвии с заданием из лекции.
 */
public class Circle {

	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		if (radius <=0) {
			System.out.println("Radius is allways > 0");
		}
		else this.radius = radius;
		
	}

	public double getArea() {
	return Math.PI * radius * radius;
	}
}
