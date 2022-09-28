package main.scene;

import main.geometry.Solid;
import main.maths.Ray;
import main.maths.RayHit;

public class Scene {

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

    public RayHit rayCast(Ray ray) {
        return null;
    }

}
