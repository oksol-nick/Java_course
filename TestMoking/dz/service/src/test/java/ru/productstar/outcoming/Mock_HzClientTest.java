package ru.productstar.outcoming;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.productstar.outcoming.service.DpendSubst;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class Mock_HzClientTest {

    @MockBean
    private DpendSubst dpendSubst;

    @BeforeEach
    public void init() {
        when(dpendSubst.getWordById(1)).thenReturn("Hello");
        when(dpendSubst.getWordById(2)).thenReturn("Hi");
        when(dpendSubst.getWordById(3)).thenReturn("Word with id 3 was not found");
    }

    @Test
    void h2Test() {

        dpendSubst.writeWordToDB(1, "Hello");
        dpendSubst.writeWordToDB(2, "Hi");

        assertThat(dpendSubst.getWordById(1)).isEqualTo("Hello");
        assertThat(dpendSubst.getWordById(2)).isEqualTo("Hi");
        assertThat(dpendSubst.getWordById(3)).isEqualTo("Word with id 3 was not found");
    }
}
