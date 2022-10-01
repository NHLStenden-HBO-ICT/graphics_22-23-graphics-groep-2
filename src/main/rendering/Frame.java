package main.rendering;

import main.geometry.Sphere;
import main.maths.Ray;
import main.maths.Vector3;
import main.scene.Camera;
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
		Sphere sphere =new Sphere(new Material(), 0.1, new Vector3(1,1,0));
		Camera camera = scene.getCamera();

		//for each pixel on the screen it will create a ray that goes through each pixel and sees if it hits item
		for (int y = 0; y <height; ++y){
			for (int x =0 ; x<width; ++x){

				/* https://raytracing.github.io/books/RayTracingInOneWeekend.html#rays,asimplecamera,andbackground/therayclass
				double u =x / (width-1);
				double v =y / (height-1);

				Ray ray =new Ray(camera.getLower_left_corner().add(camera.getHorizontal().multi(u)).add(camera.getVertical().multi(v)).sub(camera.getOrigin()),camera.getOrigin());

				Ray ray = new Ray(new Vector3(0,0,-1.0),new Vector3(x-width/2+.5,y-height/2+.5, 100)); //https://www.youtube.com/watch?v=-cQwb_RfZ8o
				*/


				//ray that goes for each x and y to z 100 en has an origin at 0,0,-1
				Ray ray =new Ray(new Vector3(x,y, 100),new Vector3(0,0,-1.0)); //TODO why is this one the only one that works


					if (sphere.intersects(ray)!=null){
						//hit it be blue
						buffer.setRGB(x,y, 1000);
						buffer.setRGB(1,1, 90000);
						System.out.println("intersect on  x: " + x + " y : " +y);

					}
					else {
						//no hit then black
					buffer.setRGB(x, y, 000000);
				}
			}
		}


		try{
			ImageIO.write(buffer,"png" , image);
		}
		catch(Exception e){
			System.out.println("Could not write to image");
		}
	}


}
