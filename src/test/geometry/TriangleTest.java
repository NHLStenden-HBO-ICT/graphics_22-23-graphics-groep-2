package test.geometry;

import main.geometry.Triangle;
import main.maths.Ray;
import main.maths.RayHit;
import main.maths.Vector3;
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
                new Material(),
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
        Ray ray = new Ray(0, 0, -1, 0, 0, 4);

        RayHit hit = triangle.intersects(ray);

        assertEquals(0, hit.getContactPoint().getX());
        assertEquals(0, hit.getContactPoint().getY());
        assertEquals(0, hit.getContactPoint().getZ());
    }

    @Test
        //a ray narrowly not pointing at a triangle should not hit it
    void intersectMisses() {
        Ray ray = new Ray(0, 0, -1, 0, -0.1, 4);

        RayHit hit = triangle.intersects(ray);

        assertEquals(null, hit);
    }

    @Test
        //a ray perfectly parallel to a triangle should not intersect it
    void intersectsParallel() {
        Ray ray = new Ray(-1, 0, 0, 4, 0, 0);

        RayHit hit = triangle.intersects(ray);

        assertEquals(null, hit);
    }

    @Test
        //a ray pointing in the exact opposite direction of a triangle should not hit it
    void missesWhenFacingAway() {
        Ray ray = new Ray(0, 0, 1, 0, 0, 4);

        RayHit hit = triangle.intersects(ray);

        assertEquals(null, hit);
    }

}