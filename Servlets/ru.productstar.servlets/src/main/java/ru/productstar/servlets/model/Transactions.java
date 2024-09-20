package ru.productstar.servlets.model;

public class Transactions {

    private String name;
    private int sum;

    public Transactions(String name, int sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }
}
