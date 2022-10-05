package main.application;

import main.geometry.Intersectable;
import main.geometry.Sphere;
import main.geometry.Triangle;
import main.maths.Vector3;
import main.rendering.Renderer;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import main.utils.Color;
import main.utils.Material;

public class Application {

    private static Renderer renderer;

    public static void main(String[] args) {
        double start = System.nanoTime();//start of run time of one frame

        //make a new scene
        Scene scene = new Scene();

        //make some stuff and add it to the scene
        scene.setCamera(new Camera(2.0, 10, 16.0 / 9.0));

        scene.addIntersectable(new Sphere(new Material(new Color(new Vector3(255,0,0)),0.0,0.0), 1, new Vector3(10, 5, 100))); //test sphere
        scene.addIntersectable(new Sphere(new Material(new Color(new Vector3(0,255,0)),0.0,0.0), 1, new Vector3(-10, 5, 300))); //test sphere
        scene.addIntersectable(new Triangle(new Material(new Color(new Vector3(255,255,255)),0.0,0.0), new Vector3(-10, 0, 100), new Vector3(10, 0, 100), new Vector3(0, 10, 100)));

        scene.addLight(new PointLight(new Color(new Vector3(200,200,200)), 1000, new Vector3(5,5,20)));
        //add said stuff to that scene

        renderer = new Renderer(scene);
        renderer.RenderToImage();
        double end = System.nanoTime();//end of run time of one frame

        System.out.println("tijd per frame: " + (end - start) / 1000000000f);//time per frame
    }
}
