package test.maths;

import main.maths.Vector3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    @Test
    void add() {
        Vector3 veca =new Vector3(1,1,1) ;
        veca.Add(new Vector3(2,2,2));

        assertEquals(3, veca.GetX());
        assertEquals(3, veca.GetY());
        assertEquals(3, veca.GetY());


    }

    @Test
    void sub() {
        Vector3 veca =new Vector3(3,3,3) ;
        veca.Sub(new Vector3(2,2,2));

        assertEquals(1, veca.GetX());
        assertEquals(1, veca.GetY());
        assertEquals(1, veca.GetY());
    }

    @Test
    void multi() {
        Vector3 veca =new Vector3(3,3,3) ;
        veca.Multi(2);

        assertEquals(6, veca.GetX());
        assertEquals(6, veca.GetY());
        assertEquals(6, veca.GetY());
    }

    @Test
    void dot() {
        Vector3 veca =new Vector3(3,3,3) ;
        double dot = veca.Dot(new Vector3(2,2,2));
        assertEquals(18, dot);
    }

    @Test
    void normalise() {
        Vector3 veca =new Vector3(2,2,2) ;
        Vector3 vecb = veca.Normalise();

        assertEquals(0.5773502691896258, vecb.GetX());
        assertEquals(0.5773502691896258, vecb.GetY());
        assertEquals(0.5773502691896258, vecb.GetY());

    }

    @Test
    void lenght() {
        Vector3 veca =new Vector3(4,2,4) ;
        double lenght= veca.Lenght();
        assertEquals(6, lenght);
    }

}