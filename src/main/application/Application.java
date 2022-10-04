package main.application;

import main.geometry.Intersectable;
import main.geometry.Sphere;
import main.geometry.Triangle;
import main.maths.Vector3;
import main.rendering.Renderer;
import main.scene.Camera;
import main.scene.Scene;
import main.utils.Material;

public class Application {

    private static Renderer renderer;

    public static void main(String[] args) {
        double start = System.nanoTime();//start of run time of one frame

        //make a new scene
        Scene scene = new Scene();

        //make some stuff
        Camera camera = new Camera(2.0, 10, 16.0 / 9.0);
        Intersectable[] geometry = new Intersectable[3];
        geometry[0] = new Sphere(new Material(), 1, new Vector3(10, 5, 100)); //test sphere
        geometry[1] = new Sphere(new Material(), 1, new Vector3(-10, 5, 300)); //test sphere
        geometry[2] = new Triangle(new Material(), new Vector3(-0.5, 0, 100), new Vector3(0.5, 0, 100), new Vector3(0, 1, 100));

        //add said stuff to that scene
        scene.setCamera(camera);
        scene.setGeometry(geometry);

        renderer = new Renderer(scene);
        renderer.RenderToImage();
        double end = System.nanoTime();//end of run time of one frame

        System.out.println("tijd per frame: " + (end - start) / 1000000000f);//time per frame
    }
}
