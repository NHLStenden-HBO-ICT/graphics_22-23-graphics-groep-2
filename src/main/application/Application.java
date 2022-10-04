package main.application;

public class Application{

	private static Thread thread ;
	private static Window window;

	public static void main(String[] args) {

		window =new Window(400,16.0/9.0);
		window.start();

	}



}
