
import org.example.CargoFragileScale;
import org.example.CargoSizeScale;
import org.example.DeliveryLoadScale;
import org.example.Main;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeliveryCalculationTest {

    @ParameterizedTest
    @CsvSource({"1.2, 50", "3.6, 100", "15.7, 200", "35.4, 300"})
    @Tag("single_method")
    void distanceCostSelectorTest(double distance, int cost) {

        assertEquals(cost, Main.distanceCostSelector(distance));
    }

    @ParameterizedTest
    @CsvSource({"CargoSizeScale.SMALL, 100", "CargoSizeScale.BIG, 200"})
    @Tag("single_method")
    void cargoSizeCostSelectorTest(String size, int cost) {
        CargoSizeScale cargoSize = null;
        if(size.equals("CargoSizeScale.SMALL")) {
            cargoSize = CargoSizeScale.SMALL;
        }
        if(size.equals("CargoSizeScale.BIG")) {
            cargoSize = CargoSizeScale.BIG;
        }

        assertEquals(cost, Main.cargoSizeCostSelector(cargoSize));
    }

    @ParameterizedTest
    @CsvSource({"CargoFragileScale.NORMAL, 0", "CargoFragileScale.FRAGILE, 300"})
    @Tag("single_method")
    void cargoFragileCostSelectorTest(String fragile, int cost) {
        CargoFragileScale cargoFragile = null;
        if(fragile.equals("CargoFragileScale.NORMAL")) {
            cargoFragile = CargoFragileScale.NORMAL;
        }
        if(fragile.equals("CargoFragileScale.FRAGILE")) {
            cargoFragile = CargoFragileScale.FRAGILE;
        }

        assertEquals(cost, Main.cargoFragileCostSelector(cargoFragile));
    }

    @ParameterizedTest
    @CsvSource({"DeliveryLoadScale.NORMAL_LOAD, 1", "DeliveryLoadScale.MIDDLE_LOAD, 1.2", "DeliveryLoadScale.HIGH_LOAD, 1.4", "DeliveryLoadScale.OVER_LOAD, 1.6"})
    @Tag("single_method")
    void deliveryLoadFactorSelectorTest(String load, double factor) {
        DeliveryLoadScale deliveryLoad = null;
        if(load.equals("DeliveryLoadScale.NORMAL_LOAD")){
            deliveryLoad = DeliveryLoadScale.NORMAL_LOAD;
        }
        if(load.equals("DeliveryLoadScale.MIDDLE_LOAD")){
            deliveryLoad = DeliveryLoadScale.MIDDLE_LOAD;
        }
        if(load.equals("DeliveryLoadScale.HIGH_LOAD")){
            deliveryLoad = DeliveryLoadScale.HIGH_LOAD;
        }
        if(load.equals("DeliveryLoadScale.OVER_LOAD")){
            deliveryLoad = DeliveryLoadScale.OVER_LOAD;
        }

        assertEquals(factor, Main.deliveryLoadFactorSelector(deliveryLoad));
    }

    @Test
    @Tag("full_method")
    void costCalculatorThrowsExceptionWrongDistanceTest(){

        assertThrows(IllegalArgumentException.class, () -> Main.costCalculator(-1, CargoSizeScale.SMALL,
                CargoFragileScale.FRAGILE,DeliveryLoadScale.NORMAL_LOAD),
                "Некорректное значение расстояния, уточните данные.");
    }

    @Test
    @Tag("full_method")
    void costCalculatorThrowsExceptionFragileCargoDistanceMoreover30Test(){

        assertThrows(IllegalArgumentException.class, () -> Main.costCalculator(35, CargoSizeScale.SMALL,
                CargoFragileScale.FRAGILE,DeliveryLoadScale.NORMAL_LOAD),
                "Хрупкий груз не доставляется на расстояние более 30 км, измените параметры доставки.");
    }

    @ParameterizedTest
    @CsvSource({
            "1.2, CargoSizeScale.SMALL, CargoFragileScale.FRAGILE, DeliveryLoadScale.NORMAL_LOAD, 450",
            "2.5, CargoSizeScale.BIG, CargoFragileScale.NORMAL, DeliveryLoadScale.NORMAL_LOAD, 400", //факт 360
            "15, CargoSizeScale.BIG, CargoFragileScale.FRAGILE, DeliveryLoadScale.MIDDLE_LOAD, 840",
            "30, CargoSizeScale.BIG, CargoFragileScale.FRAGILE, DeliveryLoadScale.HIGH_LOAD, 980",
            "30, CargoSizeScale.BIG, CargoFragileScale.FRAGILE, DeliveryLoadScale.OVER_LOAD, 1120"
    })
    @Tag("full_method")
    void costCalculatorTest(double distance, String size, String fragile, String load, int totalCost){

        CargoSizeScale cargoSize = null;
        CargoFragileScale cargoFragile = null;
        DeliveryLoadScale deliveryLoad = null;

        if(size.equals("CargoSizeScale.SMALL")) {
            cargoSize = CargoSizeScale.SMALL;
        }
        if(size.equals("CargoSizeScale.BIG")) {
            cargoSize = CargoSizeScale.BIG;
        }

        if(fragile.equals("CargoFragileScale.NORMAL")) {
            cargoFragile = CargoFragileScale.NORMAL;
        }
        if(fragile.equals("CargoFragileScale.FRAGILE")) {
            cargoFragile = CargoFragileScale.FRAGILE;
        }

        if(load.equals("DeliveryLoadScale.NORMAL_LOAD")){
            deliveryLoad = DeliveryLoadScale.NORMAL_LOAD;
        }
        if(load.equals("DeliveryLoadScale.MIDDLE_LOAD")){
            deliveryLoad = DeliveryLoadScale.MIDDLE_LOAD;
        }
        if(load.equals("DeliveryLoadScale.HIGH_LOAD")){
            deliveryLoad = DeliveryLoadScale.HIGH_LOAD;
        }
        if(load.equals("DeliveryLoadScale.OVER_LOAD")){
            deliveryLoad = DeliveryLoadScale.OVER_LOAD;
        }

        assertEquals(totalCost, Main.costCalculator(distance, cargoSize, cargoFragile, deliveryLoad));
    }
}
