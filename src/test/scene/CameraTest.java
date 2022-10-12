package test.scene;

import main.maths.FullRay;
import main.maths.Vector3;
import main.scene.Camera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CameraTest {

    Camera camera;

    @BeforeEach
    void setup() {
        camera = new Camera(new Vector3(10, 10, 10), 100, 100, 90);
    }

    @Test
    void RayThroughMiddle() {
        //when we want to shoot a ray from the camera through the middle of the image
        //then the ray should be cast from exactly 1 away, as long as the FOV is 90 degrees
        FullRay ray = camera.getRayFromPixel(50, 50);
        double offset = ray.getOrigin().sub(camera.getPosition()).length();
        assertEquals(1.0, offset, 0.001); //delta is our fuzzyness, like epsilon
        //the ray should also be pointing almost exactly towards z:-1
        assertEquals(-1, ray.getDirection().getZ(), 0.001);
    }
}