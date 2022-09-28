package test.maths;

import main.maths.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    @Test
    void add() {
        Vector3 vecA = new Vector3(1, 1, 1);
        vecA.add(new Vector3(2, 2, 2));

        assertEquals(3, vecA.getX());
        assertEquals(3, vecA.getY());
        assertEquals(3, vecA.getY());


    }

    @Test
    void sub() {
        Vector3 vecA = new Vector3(3, 3, 3);
        vecA.sub(new Vector3(2, 2, 2));

        assertEquals(1, vecA.getX());
        assertEquals(1, vecA.getY());
        assertEquals(1, vecA.getY());
    }

    @Test
    void multi() {
        Vector3 vecA = new Vector3(3, 3, 3);
        vecA.multi(2);

        assertEquals(6, vecA.getX());
        assertEquals(6, vecA.getY());
        assertEquals(6, vecA.getY());
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
        Vector3 vecB = vecA.normalise();

        assertEquals(0.5773502691896258, vecB.getX());
        assertEquals(0.5773502691896258, vecB.getY());
        assertEquals(0.5773502691896258, vecB.getY());

    }

    @Test
    void lenght() {
        Vector3 vecA = new Vector3(4, 2, 4);
        double length = vecA.length();
        assertEquals(6, length);
    }

}