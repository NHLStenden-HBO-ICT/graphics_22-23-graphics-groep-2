package test.maths;

import org.junit.jupiter.api.Test;

import main.maths.Ray;
import main.maths.Vector3;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    //these tests check if Ray.getPointAtDistance return the expected point.
    @Test
    void getPointAtDistance1() {
        //make a new ray
        Ray testRay = new Ray(-1, 0, 0, 4, 0, 0);

        //get a point a certain distance from the origin along the ray
        Vector3 point = testRay.getPointAlongRay(10);

        //check if the point is where we expect it to be
        assertEquals(-6.0, point.getX());
        assertEquals(0.0, point.getY());
        assertEquals(0.0, point.getZ());
    }

    @Test
    void getPointAtDistance2() {
        //make a new ray
        Ray testRay = new Ray(0, -1, 0, 4, 1, 2);

        //get a point a certain distance from the origin along the ray
        Vector3 point = testRay.getPointAlongRay(3);

        //check if the point is where we expect it to be
        assertEquals(4.0, point.getX());
        assertEquals(-2.0, point.getY());
        assertEquals(2.0, point.getZ());
    }

}