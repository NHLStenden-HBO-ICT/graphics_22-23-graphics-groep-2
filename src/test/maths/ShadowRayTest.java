package test.maths;

import main.geometry.Intersectable;
import main.geometry.Sphere;
import main.maths.ShadowRay;
import main.maths.Vector3;
import main.utils.VectorColor;
import main.utils.Material;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ShadowRayTest {

    //these tests check if Ray.getPointAtDistance return the expected point.
    @Test
    void getPointAtDistance1() {
        //make a new ray
        ShadowRay testShadowRay = new ShadowRay(-1, 0, 0, 4, 0, 0);

        //get a point a certain distance from the origin along the ray
        Vector3 point = testShadowRay.getPointAlongRay(10);

        //check if the point is where we expect it to be
        assertEquals(-6.0, point.getX());
        assertEquals(0.0, point.getY());
        assertEquals(0.0, point.getZ());
    }

    @Test
    void getPointAtDistance2() {
        //make a new ray
        ShadowRay testShadowRay = new ShadowRay(0, -1, 0, 4, 1, 2);

        //get a point a certain distance from the origin along the ray
        Vector3 point = testShadowRay.getPointAlongRay(3);

        //check if the point is where we expect it to be
        assertEquals(4.0, point.getX());
        assertEquals(-2.0, point.getY());
        assertEquals(2.0, point.getZ());
    }


    //test to see if a ray intersecting with multiple objects returns true
    @Test
    void castTest() {
        //create an array with two spheres

        Material mat = new Material(new VectorColor(new Vector3(0, 0, 0)), 0.0, 0.0);

        Sphere sphereA = new Sphere(mat, 1.0, new Vector3(0, 0, 0));
        Sphere sphereB = new Sphere(mat, 1.0, new Vector3(2, 0, 0));
        Vector<Intersectable> intersectables = new Vector<>(2);
        intersectables.add(sphereB);
        intersectables.add(sphereA);

        //create a ray that will intersect with both spheres
        ShadowRay testRay = new ShadowRay(-1, 0, 0, 10, 0, 0);
        //this ray should hit the sphere with x: 2 because it's placed on the x-axis, facing towards the origin

        boolean hit = testRay.castRay(intersectables);

        assertTrue(hit);
    }

    @Test
    void castTestMiss() {
        //create an array with two spheres
        Material mat = new Material(new VectorColor(new Vector3(0, 0, 0)), 0.0, 0.0);

        Sphere sphereA = new Sphere(mat, 1.0, new Vector3(0, 0, 0));
        Sphere sphereB = new Sphere(mat, 1.0, new Vector3(2, 0, 0));
        Vector<Intersectable> intersectables = new Vector<>(2);
        intersectables.add(sphereB);
        intersectables.add(sphereA);

        //create a ray that will intersect with both spheres
        ShadowRay testRay = new ShadowRay(-1, 1.1, 0, 10, 0, 0);
        //this ray should miss the spheres

        boolean hit = testRay.castRay(intersectables);

        assertFalse(hit);
    }


}