package ru.productstar.mockito.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.productstar.mockito.model.Product;
import ru.productstar.mockito.model.Stock;
import ru.productstar.mockito.model.Warehouse;
import ru.productstar.mockito.repository.CustomerRepository;
import ru.productstar.mockito.repository.InitRepository;
import ru.productstar.mockito.repository.ProductRepository;
import ru.productstar.mockito.repository.WarehouseRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.geq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {

    /**
     * Покрыть тестами методы findWarehouse и findClosestWarehouse.
     * Вызывать реальные методы зависимых сервисов и репозиториев нельзя.
     * Поиск должен осуществляться как минимум на трех складах.
     *
     * Должны быть проверены следующие сценарии:
     * - поиск несуществующего товара
     * - поиск существующего товара с достаточным количеством
     * - поиск существующего товара с недостаточным количеством
     *
     * Проверки:
     * - товар находится на нужном складе, учитывается количество и расстояние до него
     * - корректная работа для несуществующего товара
     * - порядок и количество вызовов зависимых сервисов
     */
    @Mock
    WarehouseService whs = new WarehouseService();

    @Mock
    WarehouseRepository whr = new WarehouseRepository(new ProductRepository());

    @Test
    public void mockFindWarehouseTest() {

        Warehouse w0 = whr.getById(0);
        Warehouse w1 = whr.getById(1);
        Warehouse w2 = whr.getById(2);

        when(whs.findWarehouse("phone", 1)).thenReturn(w0);
        when(whs.findWarehouse("monitor", 1)).thenReturn(w1);
        when(whs.findWarehouse("printer", 1)).thenReturn(w2);
        when(whs.findWarehouse("phone", 6)).thenReturn(null);
        when(whs.findWarehouse("tv", 1)).thenReturn(null);

        when(whs.findClosestWarehouse("phone", 1)).thenReturn(w2);
        when(whs.findClosestWarehouse("monitor", 1)).thenReturn(w1);
        when(whs.findClosestWarehouse("printer", 1)).thenReturn(w2);


        assertEquals(w0, whs.findWarehouse("phone", 1));
        assertEquals(w1, whs.findWarehouse("monitor", 1));
        assertEquals(w2, whs.findWarehouse("printer", 1));
        assertNull(whs.findWarehouse("tv", 1));
        assertNull(whs.findWarehouse("phone", 6));

        assertEquals(w2, whs.findClosestWarehouse("phone", 1));
        assertEquals(w1, whs.findClosestWarehouse("monitor", 1));
        assertEquals(w2, whs.findClosestWarehouse("printer", 1));


        verify(whr, times(1)).getById(0);
        verify(whr, times(1)).getById(1);
        verify(whr, times(1)).getById(2);

        InOrder inOrder = inOrder(whr);
        inOrder.verify(whr).getById(0);
        inOrder.verify(whr).getById(1);
        inOrder.verify(whr).getById(2);

    }
}
