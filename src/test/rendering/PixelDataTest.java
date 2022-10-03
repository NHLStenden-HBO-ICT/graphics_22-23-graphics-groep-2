package test.rendering;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PixelDataTest {

    @Test
    void widthcalc() {
        int height = 720;
        double ratio = 16.0/9.0;
        Double u = height * ratio;
        int width = u.intValue();

        assertEquals(1280, width);
    }
}