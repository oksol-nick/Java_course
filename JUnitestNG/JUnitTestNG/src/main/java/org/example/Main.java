package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    }

    public static double costCalculator(double distance, CargoSizeScale cargoSize,
                                 CargoFragileScale cargoFragile, DeliveryLoadScale deliveryLoad ) {

        if (distance > 30 && cargoFragile == CargoFragileScale.FRAGILE) {
            throw new IllegalArgumentException("Хрупкий груз не доставляется на расстояние более 30 км, измените параметры доставки.");
        }
        if (distance <=0) {
            throw new IllegalArgumentException("Некорректное значение расстояния, уточните данные.");
        }

        int distanceCost = distanceCostSelector(distance);
        int sizeCost = cargoSizeCostSelector(cargoSize);
        int fragileCost = cargoFragileCostSelector(cargoFragile);
        double deliveryLoadFActor = deliveryLoadFactorSelector(deliveryLoad);
        double cost = (distanceCost + sizeCost + fragileCost) * deliveryLoadFActor;
        double totalCost = Math.round(cost * 100) / 100;
        if (totalCost > 400) {
            return totalCost;
        } else {return 400.00;}
    }

    public static int distanceCostSelector(double distance) {
        int result = 0;
        int[] edges = {2, 10, 30};
        int[] rate = {50, 100, 200, 300};

        for(int i = 0; i < edges.length; i++) {
            if(distance <= edges[i]) {
                result = rate[i];
                break;
            } else {result = rate[rate.length-1];};
        }
        return result;
    }

    public static double deliveryLoadFactorSelector(DeliveryLoadScale deliveryLoad) {
        double[] factors = {1, 1.2, 1.4, 1.6};
        DeliveryLoadScale[] loadScales = {DeliveryLoadScale.NORMAL_LOAD, DeliveryLoadScale.MIDDLE_LOAD,
                DeliveryLoadScale.HIGH_LOAD, DeliveryLoadScale.OVER_LOAD};
        return factors[Arrays.asList(loadScales).indexOf(deliveryLoad)];
    }

    public static int cargoSizeCostSelector(CargoSizeScale cargoSize) {
        int cost = 0;
        if(cargoSize == CargoSizeScale.SMALL) {
            cost = 100;
        }
        if(cargoSize == CargoSizeScale.BIG) {
            cost = 200;
        }
        return cost;
    }

    public static int cargoFragileCostSelector(CargoFragileScale cargoFragileScale) {
        int cost = 0;
        if(cargoFragileScale == CargoFragileScale.NORMAL) {
            cost = 0;
        }
        if(cargoFragileScale == CargoFragileScale.FRAGILE) {
            cost = 300;
        }
        return cost;
    }
}
