package main.scene;

import main.geometry.Intersectable;
import main.geometry.Solid;
import main.geometry.Sphere;
import main.geometry.Triangle;
import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.Vector3;
import main.utils.Material;

public class Scene {

    private Intersectable[] geometry;

    private PointLight[] lights;

    private Camera camera;

    private float skyEmission;

    public Scene(double ratio)
    {
        camera =new Camera(2.0,10,ratio);
        geometry = new Intersectable[3];
        geometry[0]=new Sphere(new Material(), 1, new Vector3(10,5,100)); //test sphere
        geometry[1]=new Sphere(new Material(), 1, new Vector3(-10,5,300)); //test sphere
        geometry[2]=new Triangle(new Material(),new Vector3(-0.5,0,100),new Vector3(0.5,0,100),new Vector3(0,1,100));
    }


    //get methods
    public Intersectable[] getGeometry() {
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
