package test.maths;

import org.junit.jupiter.api.Test;

import java.maths.Ray;
import java.maths.Vector3;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    //superfluous test methods for getters and setters
    //mostly just here to verify the testing platform and familiarize myself with it
    @Test
    void getOrigin() {
        Ray testRay = new Ray(new Vector3(0, 1, 2), new Vector3(0 , 0 , 0));
        assertEquals(testRay.getOrigin().GetX() , 0);
        assertEquals(testRay.getOrigin().GetX() , 1);
        assertEquals(testRay.getOrigin().GetY(), 2);

    }

    @Test
    void getDirection() {
    }
}