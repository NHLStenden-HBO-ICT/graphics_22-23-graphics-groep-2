package test.geometry;

import main.geometry.Sphere;
import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.ShadowRay;
import main.maths.Vector3;
import main.utils.Color;
import main.utils.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    Sphere sphere;

    @BeforeEach
    void setup() {
        sphere = new Sphere(new Material(new Color(),0,0), 1, new Vector3(50, 50, 0));
    }

    @Test
    void intersectsSphere() {
        //this ray should hit the sphere dead center
        RayHit hit = sphere.intersects(new FullRay(new Vector3(-1, 0, 0), new Vector3(3, 0, 0)));

        assertNotNull(hit);
    }

    @Test
    void DoesNotIntersectsSphere() {
        //this ray should miss by 0.1
        RayHit hit = sphere.intersects(new FullRay(new Vector3(-1, 0, 0), new Vector3(3, 0, 1.1)));
        assertNull(hit);
    }

    @Test
    void intersectsAtCorrectDistance() {
        //this ray should hit the sphere from exactly 1 away
        RayHit hit = sphere.intersects(new FullRay(new Vector3(-1, 0, 0), new Vector3(3, 0, 0)));

        assertEquals(2.0, hit.getContactPoint().getX());
        assertEquals(0.0, hit.getContactPoint().getY());
        assertEquals(0.0, hit.getContactPoint().getZ());
    }


    //same tests but for intersectsFast
    @Test
    void intersectsFastSphere() {
        //this ray should hit the sphere dead center
        boolean hit = sphere.intersectsFast(new ShadowRay(new Vector3(-1, 0, 0), new Vector3(3, 0, 0)));
        assertTrue(hit);
    }

    @Test
    void DoesNotIntersectsFastSphere() {
        //this ray should miss by 0.1
        boolean hit = sphere.intersectsFast(new ShadowRay(new Vector3(-1, 0, 0), new Vector3(3, 0, 1.1)));
        assertFalse(hit);
    }

    @Test
    void Spheretesttset() {
        //this ray should miss by 0.1
        RayHit hit = sphere.intersects(new FullRay(new Vector3(50, 50, 100), new Vector3(0, 0, -1)));
        assertEquals(true, hit == null);
    }

}