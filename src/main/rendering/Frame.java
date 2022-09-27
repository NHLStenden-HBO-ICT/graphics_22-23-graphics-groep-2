package main.rendering;

import main.utils.Color;

public class Frame {

	private int height;

	private int width;

	private Color[][] pixelData;

	public Void RenderToImage(String path) {
		return null;
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

}
