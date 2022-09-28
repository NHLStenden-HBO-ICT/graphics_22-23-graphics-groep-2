package test.maths;

import main.maths.Ray;
import main.maths.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RayTest {
    //superfluous test methods for getters and setters
    //mostly just here to verify the testing platform and familiarize myself with it
    @Test
    void getOrigin() {
        Ray testRay = new Ray(new Vector3(0, 1, 2), new Vector3(0, 0, 0));
        assertEquals(testRay.getOrigin().getX(), 0);
        assertEquals(testRay.getOrigin().getX(), 1);
        assertEquals(testRay.getOrigin().getY(), 2);

    }

    @Test
    void getDirection() {
    }
}