package main.application;

import main.geometry.Model;
import main.geometry.ModelLoader;
import main.geometry.Sphere;
import main.geometry.Triangle;
import main.maths.Vector3;
import main.rendering.Renderer;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import main.utils.Material;
import main.utils.VectorColor;

import java.io.File;

public class Application {

    private static Renderer renderer;
    private static Thread thread;

    private static int height = 1000;
    private static double ratio = 16.0 / 9.0;
    private static Window window;

    public static void main(String[] args) {
        renderer = new Renderer(sceneSetup());
        window = new Window(height, ratio, renderer);
        window.start();
    }


    //scenesetup method will contain al the setup for the scene and all the items add to said scene so the main method stays small and clean.
    public static Scene sceneSetup() {
        //make a new scene
        Scene scene = new Scene();

        //make some stuff and add it to the scene
        scene.setCamera(new Camera(new Vector3(-10, 10, -20), height, (int) (height * ratio), 90));

        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 0, 0)), 0.9), new Vector3(100, -20, -100), new Vector3(-100, -20, -100), new Vector3(0, 100, -100)));
        
        // Bottom plane
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255,255 ,255 ))), new Vector3(-100, -20, -100), new Vector3(100, -20, -100), new Vector3(0, -20, 0)));

        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 255, 255))), new Vector3(300, -20, 0), new Vector3(0, -20, 0), new Vector3(100, -20, -100)));
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(0, 255, 0))), new Vector3(0, -20, 0), new Vector3(-300, -20, 0), new Vector3(-100, -20, -100)));

        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 255, 255))), new Vector3(-100, -20, -100), new Vector3(-100, -20, 0), new Vector3(0, 100, -100)));
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 255, 255))), new Vector3(100, -20, 0), new Vector3(100, -20, -100), new Vector3(0, 100, -100)));
        
        scene.addIntersectable(new Sphere(new Material(new VectorColor(new Vector3(255, 0, 0)), 1.0, 1.5), 5, new Vector3(0, 0, -50))); //test sphere
        scene.addIntersectable(new Sphere(new Material(new VectorColor(new Vector3(0, 0, 255)), 1.0, 1.3), 5, new Vector3(-15, 0, -70))); //test sphere
        

        scene.addLight(new PointLight(new VectorColor(new Vector3(255, 255, 255)), 20, new Vector3(0, 100, -50)));


        //creates a model loader
        ModelLoader modelloader = new ModelLoader();

        //creates the modelfile with path that is going to be read, which could end up becoming a list of items.
        //in our case all object will be put in the folder called objfiles to keep everything clean and tidy
        File modelfile = new File("objfiles/cup.obj");
        try {
            //adds model to scene using the modelloader, it gets the file path and sets the start position
            Model model = modelloader.readFile(modelfile, (new Vector3(100, 0, -500)));
            model.setSize(100);
            //scene.addIntersectable(model);
        } catch (Exception e) {
            //because a file is being read, for safety it needs a try and catch
            throw new RuntimeException(e);
        }


        return scene;
    }
}
