package main.rendering;

import main.scene.Scene;

public class Renderer {

	private Scene scene;

	private int maxRayDepth;

	public Frame RenderFrame(Scene scene, int maxRayDepth) {
		return null;
	}

	// Return the current scene
	public Scene getscene() {
		return scene;
	}

	// Set the current scene
	public void setscene(Scene scene_) {
		scene = scene_;
	}

}
