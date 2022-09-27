package test.geometry;

import main.geometry.Triangle;
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
        triangle = new Triangle(
                new Material(),
                new Vector3(0, 0, 0),
                new Vector3(0, 0, 0),
                new Vector3(0, 0, 0));
    }

    @Test
    void setVertices() {
        //make a vertex array of the correct length
        Vector3[] vecs = {new Vector3(1, 1, 1),
                new Vector3(1, 1, 1),
                new Vector3(1, 1, 1)};

        //assign that vertex array to the triangle
        triangle.setVertices(vecs);

        //check if the triangle is changed as we expect it to
        assertEquals(1, triangle.getvertex(0).GetX());
    }

    @Test
    void setVerticesWrong() {
        //make a vertex array of incorrect length
        Vector3[] vecs = {new Vector3(1, 1, 1),
                new Vector3(1, 1, 1)};

        //() -> is a lambda expression, it's required to use assertThrows
        //This statement asserts that SetVertices correctly errors when
        //we try to assign an incorrect number of vertices to the triangle
        assertThrows(RuntimeException.class, () -> triangle.setVertices(vecs));
    }
}