package test.scene;

import main.maths.Ray;
import main.scene.Camera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraTest {

 Camera camera;


    @BeforeEach
    void setup() {
        camera = new Camera(2.0 ,2.0,10);
    }
    @Test
    void rayThroughPixelAtCenterCamera()
    {
        Ray ray =camera.rayThroughPixel(200,200,400,400);
        assertEquals(0, ray.getDirection().getX());
        assertEquals(0, ray.getDirection().getY());
    }

    @Test
    void pixelAtTopleft()
    {
        double heightRatio = 2.0/400;
        double widthRatio =2.0/400;

        double u = (0.0 - 200) * widthRatio;
        double v = -(0.0 - 200) * heightRatio;

        assertEquals(-1, u);
        assertEquals(1, v);
    }

    @Test
    void pixelAtTopright()
    {
        double heightRatio = 2.0/400;
        double widthRatio =2.0/400;

        double u = (400.0 - 200) * widthRatio;
        double v = -(400.0 - 200) * heightRatio;

        assertEquals(1, u);
        assertEquals(-1, v);
    }
    @Test
    void pixelAtBottomleft()
    {
        double heightRatio = 2.0/400;
        double widthRatio =2.0/400;

        double u = (0.0 - 200) * widthRatio;
        double v = -(400.0 - 200) * heightRatio;

        assertEquals(-1, u);
        assertEquals(-1, v);
    }
    @Test
    void pixelAtBottomright()
    {
        double heightRatio = 2.0/400;
        double widthRatio =2.0/400;

        double u = (400.0 - 200) * widthRatio;
        double v = -(400.0 - 200) * heightRatio;

        assertEquals(1, u);
        assertEquals(-1, v);
    }

}
