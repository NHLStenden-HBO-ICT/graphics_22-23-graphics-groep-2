package main.scene;

import main.geometry.Intersectable;
import main.geometry.Solid;
import main.geometry.Sphere;
import main.geometry.Triangle;
import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.Vector3;
import main.utils.Material;

import java.util.Collections;
import java.util.Vector;

public class Scene {

    private Vector<Intersectable> geometry;

    private PointLight[] lights;

    private Camera camera;

    private float skyEmission;

    public Scene() {
        this.geometry = new Vector<Intersectable>();
    }


    //get methods
    public Vector<Intersectable> getGeometry() {
        return geometry;
    }

    public PointLight[] getLights() {
        return null;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public float getSkyEmission() {
        return 0;
    }


    public void addSolid(Solid solid) {
        this.geometry.add((Intersectable) solid);
    }

    public void addIntersectable(Intersectable intersectable) {
        this.geometry.add(intersectable);
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

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setGeometry(Intersectable[] geometry) {
        Collections.addAll(this.geometry, geometry);
    }
}
