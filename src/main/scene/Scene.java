package main.scene;

import main.geometry.Intersectable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scene {

    private ArrayList<Intersectable> geometry;

    private List<PointLight> lights = new ArrayList<PointLight>();

    private Camera camera;


    public Scene() {
        this.geometry = new ArrayList<Intersectable>();
    }


    //get methods
    public ArrayList<Intersectable> getGeometry() {
        return geometry;
    }

    public List<PointLight> getLights() {
        return lights;
    }

    public Camera getCamera() {
        return this.camera;
    }

    public void addIntersectable(Intersectable intersectable) {
        this.geometry.add(intersectable);
    }

    public void addLight(PointLight light) {
        this.lights.add(light);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setGeometry(Intersectable[] geometry) {
        Collections.addAll(this.geometry, geometry);
    }
}
