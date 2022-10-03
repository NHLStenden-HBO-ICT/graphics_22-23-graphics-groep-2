package test.scene;

import main.geometry.Solid;
import main.maths.FullRay;
import main.maths.RayHit;

public class SceneTest {

    private Solid[] geometry;

    private PointLight[] lights;

    private Camera camera;

    private float skyEmission;

    public Solid[] getGeometry() {
        return null;
    }

    public PointLight[] getLights() {
        return null;
    }

    public Camera getCamera() {
        return null;
    }

    public float getSkyEmission() {
        return 0;
    }

    public void addSolid(Solid solid) {

    }

    public void clearSolid() {

    }

    public void removeSolid(Solid solid) {

    }

    public void removeSolid(int index) {

    }

    public void addLight(PointLight light) {

    }

    public void clearLights() {

    }

    public void removeLight(PointLight light) {

    }

    public void removeLight(int index) {

    }

    public RayHit rayCast(FullRay fullRay) {
        return null;
    }
}
