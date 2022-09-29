package test.maths;

import org.junit.jupiter.api.Test;

import main.maths.Ray;
import main.maths.Vector3;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    @Test
    void getDistanceAtPoint1() {
        Ray testRay = new Ray(-1, 0, 0, 4, 0, 0);

        Vector3 point = testRay.getPointAlongRay(10);

        assertEquals(-6.0, point.getX());
        assertEquals(0.0, point.getY());
        assertEquals(0.0, point.getZ());
    }

    @Test
    void getDistanceAtPoint2() {
        Ray testRay = new Ray(0, -1, 0, 4, 1, 2);

        Vector3 point = testRay.getPointAlongRay(3);

        System.out.println(point.toString());
        assertEquals(4.0, point.getX());
        assertEquals(-2.0, point.getY());
        assertEquals(2.0, point.getZ());
    }

}