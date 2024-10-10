import org.example.IsPrimeNum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IsPrimeNumTest {

    @Test
    void isPrimeTestTrue() {

        boolean result = IsPrimeNum.isPrime(2);

        assertTrue(result);
    }

    @Test
    void isPrimeTestFalse() {

        boolean result = IsPrimeNum.isPrime(4);

        assertFalse(result);
    }

    @Test
    void isPrimeTestWrongN() {

        assertThrows(IllegalArgumentException.class, () -> IsPrimeNum.isPrime(-1));
    }
}
