package ru.productstar.mockito.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.productstar.mockito.ProductNotFoundException;
import ru.productstar.mockito.model.Customer;
import ru.productstar.mockito.model.Delivery;
import ru.productstar.mockito.model.Order;
import ru.productstar.mockito.model.Warehouse;
import ru.productstar.mockito.repository.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    /**
     * Покрыть тестами методы create и addProduct.
     * Можно использовать вызовы реальных методов.
     *
     * Должны быть проверены следующие сценарии:
     * - создание ордера для существующего и нового клиента
     * - добавление существующего и несуществующего товара
     * - добавление товара в достаточном и не достаточном количестве
     * - заказ товара с быстрой доставкой
     *
     * Проверки:
     * - общая сумма заказа соответствует ожидаемой
     * - корректная работа для несуществующего товара
     * - порядок и количество вызовов зависимых сервисов
     * - факт выбрасывания ProductNotFoundException
     */

    @Mock
    CustomerRepository cr = new CustomerRepository();

    @Mock
    ProductRepository pr = new ProductRepository();
    @Mock
    WarehouseRepository whr = new WarehouseRepository(pr);

    @Mock
    CustomerService cs = new CustomerService(cr);

    @Mock
    WarehouseService whs = new WarehouseService(whr);

    @Mock
    OrderRepository orr = new OrderRepository();

    @Mock
    OrderService os = new OrderService (cs, whs, orr, pr);

    @Test
    void mockCreateAddProductTest() throws ProductNotFoundException {


        /**существующий клиент*/

        Order orderIvan = os.create("Ivan");
        Warehouse wh = whs.findWarehouse("phone", 1);
        Delivery delivery = new Delivery(pr.getByName("phone"), wh, 380, 1);
        Order orderIvanWithProduct = orr.addDelivery(1, delivery );

        when(os.create("Ivan")).thenReturn(orderIvan);
        when(os.addProduct(orderIvan, "phone", 1, false)).thenReturn(orderIvanWithProduct);

        assertEquals(orderIvan, os.create("Ivan"));
        assertEquals(orderIvanWithProduct, os.addProduct(orderIvan, "phone", 1, false));
        assertEquals(380, delivery.getPrice());


        /**новый клиент, быстрая доставка*/

        Order orderOleg = orr.create(cs.getOrCreate("Oleg"));
        Warehouse whO = whs.findClosestWarehouse("phone", 1);
        Delivery delivery1 = new Delivery(pr.getByName("phone"), whO, 380, 1);
        Order orderOlegWithProduct = orr.addDelivery(1, delivery1 );

        when(os.create("Oleg")).thenReturn(orderOleg);
        when(os.addProduct(orderOleg, "phone", 1, true)).thenReturn(orderOlegWithProduct);

        assertEquals(orderOleg, os.create("Oleg"));
        assertEquals(orderOlegWithProduct, os.addProduct(orderIvan, "phone", 1, true));
        assertEquals(380, delivery1.getPrice());


        /**недостаточно товара, исключние*/

        Order orderIvanTooManyPhones = os.create("Ivan");
        Warehouse whIvan = null;

        when(os.addProduct(orderIvanTooManyPhones, "phone", 25, false)).thenThrow(new ProductNotFoundException("phone"));

        assertThrows(ProductNotFoundException.class, () -> os.addProduct(orderIvanTooManyPhones, "phone", 25, false) );


        /**несуществующий товар, исключние*/

        Order orderIvanTV = os.create("Ivan");
        Warehouse whIvanTV = null;

        when(os.addProduct(orderIvanTooManyPhones, "tv", 1, false)).thenThrow(new ProductNotFoundException("tv"));

        assertThrows(ProductNotFoundException.class, () -> os.addProduct(orderIvanTooManyPhones, "tv", 1, false));


        verify(os, times(4)).create("Ivan");
        verify(whs, times(1)).findWarehouse("phone", 1);
        verify(orr, times(1)).addDelivery(1, delivery);
        verify(orr, times(1)).addDelivery(1, delivery1);

    }
}
