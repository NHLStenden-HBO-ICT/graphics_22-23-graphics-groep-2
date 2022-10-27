package main.scene;

import main.geometry.Model;
import main.geometry.ModelLoader;
import main.geometry.Triangle;
import main.maths.Vector3;
import main.utils.Material;
import main.utils.VectorColor;

import java.util.ArrayList;

public class SceneFactory {
    //private constructor
    //only contains static methods so it doesn't need to be instanced
    private SceneFactory() {
    }

    // generates a demo scene.
    public static Scene modelScene(int height, int width) {
        //make a new scene
        Scene scene = new Scene();

        //adds camera to scene
        scene.setCamera(new Camera(new Vector3(0, 0, 0), height, width, 90));

        //adds light to scene
        scene.addLight(new PointLight(new VectorColor(new Vector3(255, 255, 255)), 500, new Vector3(0, 100, 50)));

        //creates a model loader
        ModelLoader modelloader = new ModelLoader();
        ArrayList<Model> models=new ArrayList<>();

        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(255,255,255)),0),new Vector3(-700,-400,-700),new Vector3(0,700,-700),new Vector3(700,-400,-700)));


        try {
            //adds model to scene using the model loader, it gets the file path and sets the start position
            models.add(modelloader.readFile("objfiles/","box1.obj", (new Vector3(-20, -10, -400)),60,null));
        } catch (Exception e) {
            //because a file is being read, for safety it needs a try and catch
            throw new RuntimeException(e);
        }
        //model.lookAt(new Vector3(0,0,20)); //todo rotation are fine unless it the x or y does not contain equal numbers
        for (Model model: models) {
            scene.addIntersectable(model);
        }

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

    public static Scene crystalScene(int height, int width) {
        //make a new scene
        Scene scene = new Scene();

        //adds camera to scene
        scene.setCamera(new Camera(new Vector3(0, 0, 0), height, width, 90));

        //adds light to scene
        scene.addLight(new PointLight(new VectorColor(new Vector3(255, 255, 255)), 500, new Vector3(0, 0, 50)));

        //creates a model loader
        ModelLoader modelloader = new ModelLoader();
        ArrayList<Model> models=new ArrayList<>();

        scene.addIntersectable(new Triangle(new Material(new VectorColor(new Vector3(42, 54, 74)), 0), new Vector3(-5000, -500, -500), new Vector3(0, 1000, -500), new Vector3(5000, -500, -500)));

        try {
            //adds model to scene using the model loader, it gets the file path and sets the start position

            models.add(modelloader.readFile("scene project/","crystal1.obj", (new Vector3(0, 0, -250)),10,null));
            models.add(modelloader.readFile("scene project/","crystal-1.obj", (new Vector3(-50, 0, -200)),10,null));
        } catch (Exception e) {
            //because a file is being read, for safety it needs a try and catch
            throw new RuntimeException(e);
        }
        //model.lookAt(new Vector3(0,0,20)); //todo rotation are fine unless it the x or y does not contain equal numbers
        for (Model model: models) {
            scene.addIntersectable(model);
        }

        return scene;
    }
}
