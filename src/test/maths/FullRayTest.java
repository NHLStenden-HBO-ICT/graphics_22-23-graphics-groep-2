package test.maths;

import main.geometry.Intersectable;
import main.geometry.Sphere;
import main.maths.RayHit;
import main.utils.Material;
import org.junit.jupiter.api.Test;

import main.maths.FullRay;
import main.maths.Vector3;

import static org.junit.jupiter.api.Assertions.*;

class FullRayTest {

    //these tests check if Ray.getPointAtDistance return the expected point.
    @Test
    void getPointAtDistance1() {
        //make a new ray
        FullRay testFullRay = new FullRay(-1, 0, 0, 4, 0, 0);

        //get a point a certain distance from the origin along the ray
        Vector3 point = testFullRay.getPointAlongRay(10);

        //check if the point is where we expect it to be
        assertEquals(-6.0, point.getX());
        assertEquals(0.0, point.getY());
        assertEquals(0.0, point.getZ());
    }

    @Test
    void getPointAtDistance2() {
        //make a new ray
        FullRay testFullRay = new FullRay(0, -1, 0, 4, 1, 2);

        //get a point a certain distance from the origin along the ray
        Vector3 point = testFullRay.getPointAlongRay(3);

        //check if the point is where we expect it to be
        assertEquals(4.0, point.getX());
        assertEquals(-2.0, point.getY());
        assertEquals(2.0, point.getZ());
    }


    //test to see if a ray intersecting with multiple objects actually returns the closest one
    @Test
    void castTest() {
        //create an array with two spheres
        Material mat = new Material();

        Sphere sphereA = new Sphere(mat, 1.0, new Vector3(0, 0, 0));
        Sphere sphereB = new Sphere(mat, 1.0, new Vector3(2, 0, 0));
        Intersectable[] intersectables = {sphereA, sphereB};

        //create a ray that will intersect with both spheres
        FullRay testRay = new FullRay(-1, 0, 0, 10, 0, 0);
        //this ray should hit the sphere with x: 2 because it's placed on the x axis, facing towards the origin

        RayHit closestHit = testRay.castRay(intersectables);

        assertEquals(sphereB, closestHit.getHitSolid());
    }

}