package test.geometry;

import main.geometry.Sphere;
import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.Vector3;
import main.utils.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    Sphere sphere;

    @BeforeEach
    void setup() {
        sphere = new Sphere(new Material(), 1, new Vector3(1, 0, 0));
    }

    @Test
    void intersectsSphere() {
        //this ray should hit the sphere dead center
        RayHit hit = sphere.intersects(new FullRay(new Vector3(-1, 0, 0), new Vector3(3, 0, 0)));
        assertEquals(false, hit == null);
    }

    @Test
    void DoesNotIntersectsSphere() {
        //this ray should miss by 0.1
        RayHit hit = sphere.intersects(new FullRay(new Vector3(-1, 0, 0), new Vector3(3, 0, 1.1)));
        assertEquals(true, hit == null);
    }

    @Test
    void intersectsAtCorrectDistance() {
        //this ray should hit the sphere from exactly 1 away
        RayHit hit = sphere.intersects(new FullRay(new Vector3(-1, 0, 0), new Vector3(3, 0, 0)));

        assertEquals(2.0, hit.getContactPoint().getX());
        assertEquals(0.0, hit.getContactPoint().getY());
        assertEquals(0.0, hit.getContactPoint().getZ());
    }

}