package main.application;

import main.geometry.Sphere;
import main.geometry.Triangle;
import main.maths.Vector3;
import main.rendering.Renderer;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import main.utils.VectorColor;
import main.utils.Material;

public class Application {

    private static Renderer renderer;
    private static Thread thread;

    private static int height = 400;
    private static double ratio = 16.0 / 9.0;
    private static Window window;

    public static void main(String[] args) {
        //make a new scene
        Scene scene = new Scene();

        //make some stuff and add it to the scene
        scene.setCamera(new Camera(new Vector3(0, 0, 0), height, (int) (height * ratio), 90));

        scene.addIntersectable(new Sphere(new Material(new VectorColor(new Vector3(255, 0, 0)), 0.0, 0.0), 5, new Vector3(0, -10, -100))); //test sphere
        //scene.addIntersectable(new Sphere(new Material(new VectorColor(new Vector3(0, 255, 0)), 0.0, 0.0), 10, new Vector3(-10, 10, -60))); //test sphere
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 255, 255)), 0.0, 0.0), new Vector3(10, 0, -80), new Vector3(-10, 0, -60), new Vector3(0, 10, -50)));

        scene.addLight(new PointLight(new VectorColor(new Vector3(0, 0, 255)), 20, new Vector3(0, 0, 0)));

        renderer = new Renderer(scene);
        window = new Window(height, ratio, renderer);
        window.start();
    }
}
