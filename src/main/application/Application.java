package main.application;

import main.geometry.ModelLoader;
import main.geometry.Triangle;
import main.maths.Vector3;
import main.rendering.Renderer;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import main.utils.Color;

import java.io.File;

public class Application {

    private static Renderer renderer;

    public static void main(String[] args) {

        ModelLoader modelloader =new ModelLoader();
        double start = System.nanoTime();//start of run time of one frame

        //make a new scene
        Scene scene = new Scene();

        //make some stuff and add it to the scene
        scene.setCamera(new Camera(2.0, 10, 1.0/1.0));

        //scene.addIntersectable(new Sphere(new Material(), 1, new Vector3(10, 5, 100))); //test sphere
        //scene.addIntersectable(new Sphere(new Material(), 1, new Vector3(-10, 5, 300))); //test sphere
        //scene.addIntersectable(new Triangle(new Material(), new Vector3(-0.5, 0, 100), new Vector3(0.5, 0, 100), new Vector3(0, 1, 100)));
        File modelfile =new File("objfiles/cup.obj");

        try {
            for (Triangle triangle: modelloader.parseFile(modelfile, new Vector3(0,0,50))) {
                scene.addIntersectable(triangle);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        scene.addLight(new PointLight(new Color(new Vector3(255,255,255)), 1000, new Vector3(0,-10,-10)));
        //add said stuff to that scene

        renderer = new Renderer(scene);
        renderer.RenderToImage();
        double end = System.nanoTime();//end of run time of one frame

        System.out.println("tijd per frame: " + (end - start) / 1000000000f);//time per frame
    }
}
