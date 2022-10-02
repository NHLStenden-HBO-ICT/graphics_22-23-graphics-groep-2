package test.maths;

import main.maths.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    @Test
    void add() {
        //create two vectors
        Vector3 vecA = new Vector3(1, 1, 1);
        //add the together
        Vector3 result = vecA.add(new Vector3(2, 2, 2));

        //check if the resulting vector has the values we expect
        assertEquals(3, result.getX());
        assertEquals(3, result.getY());
        assertEquals(3, result.getY());


    }

    @Test
    void sub() {
        //create two new vectors
        Vector3 vecA = new Vector3(3, 3, 3);
        Vector3 vecB = new Vector3(2, 2, 2);

        //subtract one from the other
        Vector3 result = vecA.sub(vecB);

        //check if the result is what we should expect
        assertEquals(1, result.getX());
        assertEquals(1, result.getY());
        assertEquals(1, result.getY());
    }

    @Test
    void multi() {
        //create a new vector
        Vector3 vecA = new Vector3(3, 3, 3);
        //multiply the vector by 2
        Vector3 result = vecA.multi(2);

        //check if the resulting vector is twice as large
        assertEquals(6, result.getX());
        assertEquals(6, result.getY());
        assertEquals(6, result.getY());
    }

    @Test
    void dot() {
        //create a new vector
        Vector3 vecA = new Vector3(3, 3, 3);
        //get the dot product of the vector
        double dot = vecA.dot(new Vector3(2, 2, 2));

        //check if the result is what we expect it to be
        assertEquals(18, dot);
    }

    @Test
    void normalise() {
        //create a new vector
        Vector3 vecA = new Vector3(2, 2, 2);
        //normalize the vector
        Vector3 result = vecA.normalise();
        //check if the vector now has a length of 1
        assertEquals(1.0, result.length());

    }

    @Test
        //test if the length of a vector is correctly calculated
    void length() {
        //make a new vector
        Vector3 vecA = new Vector3(4, 2, 4);
        //calculate the length
        double length = vecA.length();
        //see if the length is what we expect it to be
        assertEquals(6, length);
    }

    @Test
    void cross() {
        //create two vectors
        Vector3 vecA = new Vector3(1, 2, 3);
        Vector3 vecB = new Vector3(4, 5, 6);

        //get the cross product of those vectors
        Vector3 result = vecA.cross(vecB);

        //check if the resulting vector is what we expect it to be
        assertEquals(-3, result.getX());
        assertEquals(6, result.getY());
        assertEquals(-3, result.getZ());
    }

}