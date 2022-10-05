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
	private static Thread thread ;
	private static Window window;

    public static void main(String[] args) {
        double start = System.nanoTime();//start of run time of one frame

        //make a new scene
        Scene scene = new Scene();

        //make some stuff and add it to the scene
        scene.setCamera(new Camera(2.0, 10, 16.0 / 9.0));

        scene.addIntersectable(new Sphere(new Material(), 1, new Vector3(10, 5, 100))); //test sphere
        scene.addIntersectable(new Sphere(new Material(), 1, new Vector3(-10, 5, 300))); //test sphere
        scene.addIntersectable(new Triangle(new Material(), new Vector3(-0.5, 0, 100), new Vector3(0.5, 0, 100), new Vector3(0, 1, 100)));

        //add said stuff to that scene

        renderer = new Renderer(scene);
        renderer.RenderToImage();
        double end = System.nanoTime();//end of run time of one frame
		window =new Window(400,16.0/9.0);
		window.start();

        System.out.println("tijd per frame: " + (end - start) / 1000000000f);//time per frame
    }
}
