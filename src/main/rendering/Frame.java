package main.rendering;

import main.geometry.Sphere;
import main.maths.Ray;
import main.maths.Vector3;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import main.utils.Color;
import main.utils.Material;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Frame {

    private int height;
    private int width;
    private Color[][] pixelData;

	private Scene scene;


	public Frame (int height, int width, Scene scene){
		this.height =height;
		this.width =width;
		this.scene = scene;
	}

	// Return the height of the frame
	public int getheight() {
		return height;
	}

	// Return the width of the frame
	public int getwidth() {
		return width;
	}

	// Set the height of the frame
	public void setheight(int height_) {
		height = height_;
	}

	// Set the width of the frame
	public void setwidth(int width_) {
		width = width_;
	}

	public void RenderToImage(Scene scene) {
		File image =new File("image.png");
		BufferedImage buffer =new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);

		//Intersectable[] inters = scene.getGeometry();
		Sphere sphere =new Sphere(new Material(), 1, new Vector3(0,0,100));
		PointLight light =new PointLight(new Color(), 1, new Vector3(0,100,100));
		Camera camera = scene.getCamera();

		//for each pixel on the screen it will create a ray that goes through each pixel and sees if it hits item
		for (int y = 0; y <height; ++y){
			for (int x =0 ; x<width; ++x){

				Ray ray =camera.rayThroughPixel(x,y,width,height);//gets the ray with the coords of the virtual screen
				if (sphere.intersects(ray)!=null){
					buffer.setRGB(x,y, 1000);
					System.out.println("intersect on  x: " + x + " y : " +y);
				}
				else {
					//no hit then black
					buffer.setRGB(x, y, 000000);
				}
			}
		}
		//System.out.println("left botom : " + camera.getLower_left_corner() + " right botom : " +camera.getLower_right_corner() + " right top : " + camera.getUpper_right_corner() + " left top : "+ camera.getUpper_left_corner());

		try{
			ImageIO.write(buffer,"png" , image);
		}
		catch(Exception e){
			System.out.println("Could not write to image");
			System.exit(1);
		}
	}


}
