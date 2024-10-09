package ru.productstar.mockito.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.productstar.mockito.model.Customer;
import ru.productstar.mockito.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    /**
     * Тест 1 - Получение покупателя "Ivan"
     * Проверки:
     * - очередность и точное количество вызовов каждого метода из CustomerRepository
     *
     * Тест 2 - Получение покупателя "Oleg"
     * Проверки:
     * - очередность и точное количество вызовов каждого метода из CustomerRepository
     * - в метод getOrCreate была передана строка "Oleg"
     */
    @Mock
    CustomerRepository cr = new CustomerRepository();

    @Mock
    CustomerService cs = new CustomerService(cr);


    @Test
    void mockGetOrCreateTestIvan() {

        Customer customerIvan = cr.getByName("Ivan");

        when(cs.getOrCreate("Ivan")).thenReturn(customerIvan);

        assertEquals(customerIvan, cs.getOrCreate("Ivan"));

        verify(cr, times(1)).getByName("Ivan");
        verify(cs, times(1)).getOrCreate("Ivan");

        InOrder inOrder = inOrder(cr);
        inOrder.verify(cr).getByName("Ivan");
    }

    @Test
    void mockGetOrCreateTestOleg() {

        Customer customerOleg = new Customer("Oleg");

        when(cs.getOrCreate("Oleg")).thenReturn(customerOleg);

        assertEquals(customerOleg, cs.getOrCreate("Oleg"));

        verify(cs, times(1)).getOrCreate("Oleg");

        InOrder inOrder = inOrder(cs);
        inOrder.verify(cs).getOrCreate("Oleg");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(cs).getOrCreate(captor.capture());
        assertEquals("Oleg", captor.getValue());


    }
}
