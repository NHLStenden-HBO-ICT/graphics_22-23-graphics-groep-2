package main.application;

import main.geometry.ModelLoader;
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
        scene.setCamera(new Camera(2.0, 10, 1.0/1.0));

        File modelfile =new File("objfiles/cup.obj");//could end up as a list

        try {
            scene.addModel(modelloader.readFile(modelfile,(new Vector3(0,0,50))));//adds model to scene using the modelloader, it gets the file path and sets the startposition
        } catch (Exception e) {//because a file being read for safety it needs a try and catch
            throw new RuntimeException(e);
        }

        scene.addLight(new PointLight(new Color(new Vector3(255,255,255)), 1000, new Vector3(0,-10,-10)));
        //add said stuff to that scene

        return scene;
    }
}
