package main.scene;

import main.geometry.Intersectable;
import main.geometry.Model;
import main.geometry.Solid;
import main.maths.FullRay;
import main.maths.RayHit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Scene {

    private Vector<Intersectable> geometry;

    private Vector<Model> models;
    private List<PointLight> lights = new ArrayList<PointLight>();;

    private Camera camera;

    private float skyEmission;

    public Scene() {
        this.geometry = new Vector<Intersectable>();
        this.models = new Vector<Model>();
    }


    //get methods
    public Vector<Intersectable> getGeometry() {
        return geometry;
    }

    public List<PointLight> getLights() {
        return lights;
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

    public void addModel(Model model) {
        this.models.add(model);
    }

    public Vector<Model> getModels() {
        return models;
    }
    public Model getModelofIndex(int index) {
        return models.get(index);
    }

    public void clearSolid() {

    }

    public void removeSolid(Solid solid) {

    }

    public void removeSolid(int index) {

    }

    public void addLight(PointLight light) {
        this.lights.add(light); 
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

    //returns a list of all the models intersectables so the models can move
    public Vector<Intersectable> getModelintersectables(){
        Vector<Intersectable> modelInter = new Vector<Intersectable>();
        for (Model model:models) {
            modelInter.addAll(model.getTriangles());
        }
        return modelInter;
    }
}
