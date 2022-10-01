package main.rendering;

import main.scene.Scene;

public class Renderer {

    private Scene scene;

    private int maxRayDepth;

	public Renderer(){
		scene =new Scene();
	}


	// Return the current scene
	public Scene getscene() {
		return scene;
	}

	// Set the current scene
	public void setscene(Scene scene_) {
		scene = scene_;
	}

	//renders frame and returns it
	public Frame renderFrame() {

		return new Frame(400, 400, scene);
	}



}
