package Labwork_1_v13;

class Triangle {

  private final double a, b, c;

  public Triangle(double a, double b, double c) {
    if (a + b <= c || a + c <= b || b + c <= a) {
      throw new IllegalArgumentException("Точки не образуют треугольник.");
    }
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public void identifyType() {
    if (Math.abs(a - b) < 1e-6 && Math.abs(b - c) < 1e-6) {
      System.out.println("Треугольник равносторонний.");
    } else if (
      Math.abs(a - b) < 1e-6 || Math.abs(b - c) < 1e-6 || Math.abs(c - a) < 1e-6
    ) {
      System.out.println("Треугольник равнобедренный.");
    } else if (
      Math.abs(a * a + b * b - c * c) < 1e-6 ||
      Math.abs(b * b + c * c - a * a) < 1e-6 ||
      Math.abs(c * c + a * a - b * b) < 1e-6
    ) {
      System.out.println("Треугольник прямоугольный.");
    } else {
      System.out.println("Треугольник разносторонний.");
    }
  }
}
