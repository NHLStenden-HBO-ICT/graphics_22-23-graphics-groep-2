package main.scene;

import main.geometry.Model;
import main.geometry.ModelLoader;
import main.geometry.Sphere;
import main.geometry.Triangle;
import main.maths.Vector3;
import main.utils.Material;
import main.utils.VectorColor;

public class SceneFactory {
    //private constructor
    //only contains static methods so it doesn't need to be instanced
    private SceneFactory() {
    }

    // generates a demo scene.
    public static Scene modelScene(int height, int width) {
        //make a new scene
        Scene scene = new Scene();

        //make some stuff and add it to the scene
        scene.setCamera(new Camera(new Vector3(-10, 10, -10), height, width, 90));

        // Back plane
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 0, 0)), 0.9), new Vector3(100, -20, -100), new Vector3(-100, -20, -100), new Vector3(0, 100, -100)));
        
        // Bottom planes
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255,255 ,255 ))), new Vector3(-100, -20, -100), new Vector3(100, -20, -100), new Vector3(0, -20, 0)));
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 255, 255))), new Vector3(300, -20, 0), new Vector3(0, -20, 0), new Vector3(100, -20, -100)));
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(0, 255, 0))), new Vector3(0, -20, 0), new Vector3(-300, -20, 0), new Vector3(-100, -20, -100)));

        // Side planes
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 255, 255))), new Vector3(-100, -20, -100), new Vector3(-100, -20, 0), new Vector3(0, 100, -100)));
        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 255, 255))), new Vector3(100, -20, 0), new Vector3(100, -20, -100), new Vector3(0, 100, -100)));
        
        // Spheres
        scene.addIntersectable(new Sphere(new Material(new VectorColor(new Vector3(255, 0, 0)), 1.0, 1.5), 5, new Vector3(0, 0, -50))); //test sphere
        scene.addIntersectable(new Sphere(new Material(new VectorColor(new Vector3(0, 0, 255))), 5, new Vector3(-15, 0, -70))); //test sphere

        scene.addIntersectable(new Sphere(new Material(new VectorColor(new Vector3(0, 0, 255))), 5, new Vector3(-15, 0, -120))); //test sphere
        scene.addIntersectable(new Sphere(new Material(new VectorColor(new Vector3(0, 0, 255))), 5, new Vector3(0, 0, -140))); //test sphere
        

        scene.addLight(new PointLight(new VectorColor(new Vector3(255, 255, 255)), 15, new Vector3(0, 100, -70)));

        //creates a model loader
        /*ModelLoader modelloader = new ModelLoader();
        Model model;

        try {
            //adds model to scene using the model loader, it gets the file path and sets the start position
            model = modelloader.readFile("objfiles/cup.obj", (new Vector3(100, 0, -500)));
        } catch (Exception e) {
            //because a file is being read, for safety it needs a try and catch
            throw new RuntimeException(e);
        }
        model.setSize(100);
        scene.addIntersectable(model);*/


        return scene;
    }

    //generates a simple scene containing only a single light, a triangle & the camera
    public static Scene simpleScene(int height, int width) {
        Scene scene = new Scene();

        scene.setCamera(new Camera(new Vector3(0, 0, 0), height, width, 90));

        scene.addLight(new PointLight(new VectorColor(new Vector3(255, 255, 255)), 500, new Vector3(0, 100, 50)));

        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255, 255, 255)), 0.5), new Vector3(3, 0, -5), new Vector3(-3, 0, -5), new Vector3(0, 3, -5), new Vector3(0, 0, 1)));

        return scene;
    }
}
