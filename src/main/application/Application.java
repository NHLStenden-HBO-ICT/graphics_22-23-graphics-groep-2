package main.application;

import main.geometry.ModelLoader;
import main.geometry.Triangle;
import main.maths.Vector3;
import main.rendering.Renderer;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import main.utils.Color;
import main.utils.Material;

import java.awt.*;
import java.io.File;

public class Application {

   private static Renderer renderer;
	private static Thread thread ;

    private static int height=400;
    private static double ratio =16.0 / 9.0;
	private static Window window;

    public static void main(String[] args) {

        double start = System.nanoTime();//start of run time of one frame

        renderer = new Renderer(sceneSetup());
        renderer.RenderToImage();
        double end = System.nanoTime();//end of run time of one frame

        System.out.println("tijd per frame: " + (end - start) / 1000000000f);//time per frame
    }

    public static Scene sceneSetup(){
        //make a new scene
        Scene scene = new Scene();

        ModelLoader modelloader =new ModelLoader();//creates a model loader

        //make some stuff and add it to the scene
        scene.setCamera(new Camera(2.0, 10, ratio));
        scene.setCamera(new Camera(2.0, 10, 1.0/1.0));

        //scene.addIntersectable(new Sphere(new Material(new Color(new Vector3(255,0,0)),0.0,0.0), 1, new Vector3(10, 5, 100))); //test sphere
        //scene.addIntersectable(new Sphere(new Material(new Color(new Vector3(0,255,0)),0.0,0.0), 1, new Vector3(-10, 5, 300))); //test sphere
        scene.addIntersectable(new Triangle(new Material(new Color(new Vector3(255,255,255)),0.0,0.0), new Vector3(-10, 0, 100), new Vector3(10, 0, 100), new Vector3(0, 10, 100)));
        File modelfile =new File("objfiles/cup.obj");//could end up as a list

        scene.addLight(new PointLight(new Color(new Vector3(0,0,255)), 300, new Vector3(-10,-5,20)));
        try {
            scene.addModel(modelloader.readFile(modelfile,(new Vector3(0,0,50))));//adds model to scene using the modelloader, it gets the file path and sets the startposition
        } catch (Exception e) {//because a file being read for safety it needs a try and catch
            throw new RuntimeException(e);
        }

        scene.addLight(new PointLight(new Color(new Vector3(255,255,255)), 1000, new Vector3(0,-10,-10)));
        //add said stuff to that scene

        renderer = new Renderer(scene);
		    window =new Window(height,ratio,renderer);
		    window.start();
        return scene;
    }
}
