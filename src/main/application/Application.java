package main.application;

import main.rendering.Renderer;
import main.rendering.Frame;

public class Application {

	private static Renderer renderer;

	public static void main(String[] args) {
		double start = System.nanoTime();//start of run time of one frame

		renderer = new Renderer();
		Frame frame = renderer.renderFrame();
		frame.RenderToImage(renderer.getscene());

		double end = System.nanoTime();//end of run time of one frame

		System.out.println("tijd per frame: " + (end-start)/1000000000f);//time per frame
	}
}
