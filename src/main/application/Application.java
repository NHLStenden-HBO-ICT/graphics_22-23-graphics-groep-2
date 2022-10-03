package main.application;

public class Application{

	private static Thread thread ;
	private static Window window;

	public static void main(String[] args) {

		window =new Window(400,16.0/9.0);
		//double start = System.nanoTime();//start of run time of one frame
		window.start();

		//double end = System.nanoTime();//end of run time of one frame

		//System.out.println("tijd per frame: " + (end-start)/1000000000f);//time per frame
	}



}
