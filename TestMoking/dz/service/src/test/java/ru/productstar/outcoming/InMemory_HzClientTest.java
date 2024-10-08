package ru.productstar.outcoming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.productstar.outcoming.service.DBInterface;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class InMemory_HzClientTest {

    @Autowired
    private DBInterface dbInterface;

    @Test
    void h2Test() {

       dbInterface.writeWordToDB(1, "Hello");
       dbInterface.writeWordToDB(2, "Hi");

        assertThat(dbInterface.getWordById(1)).isEqualTo("Hello");
        assertThat(dbInterface.getWordById(2)).isEqualTo("Hi");
        assertThatRuntimeException().isThrownBy(() -> {dbInterface.getWordById(3);}).
                withMessageContaining("Word with id 3 was not found");
    }
}
