import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GCDTest {
    @Test
    void basicCaseWith0() {
        int a = 5;
        int b = 0;
        assertEquals(5, Helpers.GCD(a, b));
    }
    @Test
    void basicCaseWith1() {
        int a = 1;
        int b = 5;
        assertEquals(1, Helpers.GCD(a, b));
    }

    @Test
    void complexCase1() {
        int a = 6;
        int b = 9;
        assertEquals(3, Helpers.GCD(a, b));
    }

    @Test
    void complexCase2() {
        int a = 1071;
        int b = 462;
        assertEquals(21, Helpers.GCD(a, b));
    }
}
