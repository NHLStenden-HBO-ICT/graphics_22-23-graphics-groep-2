package test.geometry;

import main.geometry.Triangle;
import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.ShadowRay;
import main.maths.Vector3;
import main.utils.VectorColor;
import main.utils.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    //these tests ensure that Triangle.setVertices does not allow us to change the length of the vertex array

    Triangle triangle;

    @BeforeEach
    void setUp() {
        //make a new triangle before each test
        //this triangle is aligned with the x-axis and has a height of 1
        triangle = new Triangle(
                new Material(new VectorColor(new Vector3(0, 0, 0))),

                new Vector3(1, 0, 0),
                new Vector3(-1, 0, 0),
                new Vector3(0, 1, 0));
    }

    @Test
    void setVertices() {
        //make a vertex array of the correct length
        Vector3[] vec = {new Vector3(1, 1, 1),
                new Vector3(1, 1, 1),
                new Vector3(1, 1, 1)};

        //assign that vertex array to the triangle
        triangle.setVertices(vec);

        //check if the triangle is changed as we expect it to
        assertEquals(1, triangle.getVertex(0).getX());
    }

    @Test
    void setVerticesWrong() {
        //make a vertex array of incorrect length
        Vector3[] newVertices = {new Vector3(1, 1, 1),
                new Vector3(1, 1, 1)};

        //() -> is a lambda expression, it's required to use assertThrows
        //This statement asserts that SetVertices correctly errors when
        //we try to assign an incorrect number of vertices to the triangle
        assertThrows(RuntimeException.class, () -> triangle.setVertices(newVertices));
    }

    @Test
        //a ray pointing at a triangle should intersect it at the correct coordinates
    void intersects() {
        //make a ray
        FullRay fullRay = new FullRay(0, 0, -1, 0, 0, 4);
        //get the intersection of the ray and the triangle
        RayHit hit = triangle.intersects(fullRay);

        //this ray should hit the triangle exactly at the origin
        assertEquals(0, hit.getContactPoint().getX());
        assertEquals(0, hit.getContactPoint().getY());
        assertEquals(0, hit.getContactPoint().getZ());
    }

    @Test
        //a ray narrowly not pointing at a triangle should not hit it
    void intersectMisses() {
        //make a ray that misses the triangle by 0.1
        FullRay fullRay = new FullRay(0, 0, -1, 0, -0.1, 4);

        //check if the triangle hits the ray
        RayHit hit = triangle.intersects(fullRay);

        //the answer should be null if no intersection takes place
        assertNull(hit);
    }

    @Test
        //a ray perfectly parallel to a triangle should not intersect it
    void intersectsParallel() {

        //create a ray that is perfectly parallel to the triangle
        FullRay fullRay = new FullRay(-1, 0, 0, 4, 0, 0);
        //check if we hit it
        RayHit hit = triangle.intersects(fullRay);
        //in case of no intersection the return should be null
        assertNull(hit);
    }

    @Test
        //a ray pointing in the exact opposite direction of a triangle should not hit it
    void missesWhenFacingAway() {
        //create a new ray that points in the exact opposite direction of the triangle
        FullRay fullRay = new FullRay(0, 0, 1, 0, 0, 4);
        //check if we intersect it
        RayHit hit = triangle.intersects(fullRay);
        //in case of no intersection the return should be null
        assertNull(hit);
    }

    //same tests but for intersectsFast
    @Test
    void intersectsFast() {
        //make a ray
        ShadowRay shadowRay = new ShadowRay(0, 0, -1, 0, 0, 4);
        //get the intersection of the ray and the triangle
        boolean hit = triangle.intersectsFast(shadowRay);

        //this ray should hit the triangle exactly at the origin
        assertTrue(hit);
    }

    @Test
        //a ray narrowly not pointing at a triangle should not hit it
    void intersectMissesFast() {
        //make a ray that misses the triangle by 0.1
        ShadowRay shadowRay = new ShadowRay(0, 0, -1, 0, -0.1, 4);

        //check if the triangle hits the ray
        boolean hit = triangle.intersectsFast(shadowRay);

        //the answer should be null if no intersection takes place
        assertFalse(hit);
    }

    @Test
        //a ray perfectly parallel to a triangle should not intersect it
    void intersectsParallelFast() {

        //create a ray that is perfectly parallel to the triangle
        ShadowRay shadowRay = new ShadowRay(-1, 0, 0, 4, 0, 0);
        //check if we hit it
        boolean hit = triangle.intersectsFast(shadowRay);
        //in case of no intersection the return should be null
        assertFalse(hit);
    }

    @Test
        //a ray pointing in the exact opposite direction of a triangle should not hit it
    void missesWhenFacingAwayFast() {
        //create a new ray that points in the exact opposite direction of the triangle
        ShadowRay shadowRay = new ShadowRay(0, 0, 1, 0, 0, 4);
        //check if we intersect it
        boolean hit = triangle.intersectsFast(shadowRay);
        //in case of no intersection the return should be null
        assertFalse(hit);
    }

}