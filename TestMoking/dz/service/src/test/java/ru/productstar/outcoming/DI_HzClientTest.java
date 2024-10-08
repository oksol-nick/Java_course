package ru.productstar.outcoming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.productstar.outcoming.service.DpendSubst;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class DI_HzClientTest {

    @Autowired
    private DpendSubst dpendSubst;

    @Test
    void h2Test() {

        dpendSubst.writeWordToDB(1, "Hello");
        dpendSubst.writeWordToDB(2, "Hi");

        assertThat(dpendSubst.getWordById(1)).isEqualTo("Hello");
        assertThat(dpendSubst.getWordById(2)).isEqualTo("Hi");
        assertThat(dpendSubst.getWordById(3)).isEqualTo("Word with id 3 was not found");



    }
}
