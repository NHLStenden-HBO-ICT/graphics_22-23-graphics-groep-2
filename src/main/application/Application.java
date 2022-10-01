package main.application;

import main.rendering.Renderer;
import main.rendering.Frame;

public class Application {

	private static Renderer renderer;

	public static void main(String[] args) {
		double start = System.nanoTime();

		renderer = new Renderer();
		Frame frame = renderer.renderFrame();
		frame.RenderToImage(renderer.getscene());

		double end = System.nanoTime();

		System.out.println("tijd per frame: " + (end-start)/1000000000f);
	}
}
