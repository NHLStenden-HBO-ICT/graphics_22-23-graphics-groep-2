package test.maths;

import main.maths.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    @Test
    void add() {
        Vector3 vecA = new Vector3(1, 1, 1);
        Vector3 result = vecA.add(new Vector3(2, 2, 2));

        assertEquals(3, result.getX());
        assertEquals(3, result.getY());
        assertEquals(3, result.getY());


    }

    @Test
    void sub() {
        Vector3 vecA = new Vector3(3, 3, 3);
        Vector3 result = vecA.sub(new Vector3(2, 2, 2));

        assertEquals(1, result.getX());
        assertEquals(1, result.getY());
        assertEquals(1, result.getY());
    }

    @Test
    void multi() {
        Vector3 vecA = new Vector3(3, 3, 3);
        Vector3 result = vecA.multi(2);

        assertEquals(6, result.getX());
        assertEquals(6, result.getY());
        assertEquals(6, result.getY());
    }

    @Test
    void dot() {
        Vector3 vecA = new Vector3(3, 3, 3);
        double dot = vecA.dot(new Vector3(2, 2, 2));
        assertEquals(18, dot);
    }

    @Test
    void normalise() {
        Vector3 vecA = new Vector3(2, 2, 2);
        Vector3 result = vecA.normalise();

        assertEquals(0.5773502691896258, result.getX());
        assertEquals(0.5773502691896258, result.getY());
        assertEquals(0.5773502691896258, result.getY());

    }

    @Test
    void length() {
        Vector3 vecA = new Vector3(4, 2, 4);
        double length = vecA.length();
        assertEquals(6, length);
    }

}