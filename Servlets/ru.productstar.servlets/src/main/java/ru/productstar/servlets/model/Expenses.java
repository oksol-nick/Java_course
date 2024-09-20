package ru.productstar.servlets.model;

public class Expenses {

    private String name;
    private int sum;

    public Expenses(String name, int sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }
}
