package main.rendering;

import main.geometry.Sphere;
import main.maths.Ray;
import main.maths.Vector3;
import main.scene.Camera;
import main.scene.Scene;
import main.utils.Material;

import java.awt.image.BufferedImage;

public class Renderer {

    private Scene scene;

    private int maxRayDepth;

	public Renderer(){
		scene =new Scene();
	}


	// Return the current scene
	public Scene getScene() {
		return scene;
	}

	// Set the current scene
	public void setScene(Scene scene_) {
		scene = scene_;
	}

	public void RenderToImage() {

		PixelData pixelData =new PixelData(400,scene.getCamera().getRatio());
		BufferedImage buffer =new BufferedImage(pixelData.getWidth(), pixelData.getHeight(), BufferedImage.TYPE_INT_RGB);

		Sphere sphere =new Sphere(new Material(), 1, new Vector3(0,0,100)); //test sphere
		Camera camera = scene.getCamera();

		//loop for each pixel on the image
		for (int y = 0; y < pixelData.getHeight(); ++y){
			for (int x = 0; x< pixelData.getWidth(); ++x){

				Ray ray =camera.rayThroughPixel(x,y, pixelData.getWidth(), pixelData.getHeight());//gets the ray with the coörds of the virtual screen that's equal to the x and y pixel of the image

				if (sphere.intersects(ray)!=null){
					//if there is intersection then it will color x and y blue
					buffer.setRGB(x,y, 1000);
					System.out.println("intersect on  x: " + x + " y : " +y);
				}
				else {
					//if there is no intersection then it will color x and y black
					buffer.setRGB(x, y, 000000);
				}
			}
		}

		pixelData.toImage(buffer);

	}


}
