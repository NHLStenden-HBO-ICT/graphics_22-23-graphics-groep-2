package main.rendering;

import main.utils.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PixelData {

    private int height;
    private int width;
    private Color[][] pixelData;

	public PixelData(int height, int width){
		this.height =height;
		this.width =width;
	}

	// Return the height of the frame
	public int getHeight() {
		return height;
	}

	// Return the width of the frame
	public int getWidth() {
		return width;
	}

	// Set the height of the frame
	public void setHeight(int height_) {
		height = height_;
	}

	// Set the width of the frame
	public void setWidth(int width_) {
		width = width_;
	}

	public void toImage(BufferedImage buffer){
		File image =new File("image.png");

		try{
			ImageIO.write(buffer,"png" , image);
		}
		catch(Exception e){
			System.out.println("Could not write to image");
			System.exit(1);
		}
	}


}
