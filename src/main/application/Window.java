package main.application;

import main.rendering.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class Window implements Runnable{

    private static JFrame frame;
    private static Renderer renderer;
    private Canvas canvas;
    private Thread thread;
    private Graphics g;
    private JLabel fpsCounter;


    private static int height;
    private static double ratio;

    private boolean state;

    private String fpsC= "raytracer";


    //constructor in which the prefered, min and max size is set. the renderer is also given to the constructor
    public Window(int height, double ratio, Renderer renderer){
        this.state=false;
        canvas = new Canvas();
        this.height = height;
        this.ratio=ratio;
        canvas.setPreferredSize(new Dimension(widthCalc(),this.height));
        canvas.setMaximumSize(new Dimension(widthCalc(),this.height));
        canvas.setMinimumSize(new Dimension(widthCalc(),this.height));
        this.renderer = renderer;

        createWindow(height,ratio);
    }


    //the method that creates the thread and starts the run method
    public void start(){
        thread =new Thread(this::run, "thread 1"); //this allows it to put the run loop on a thread
        thread.start(); //starts the thread
    }

    //creates the window using height of the input and the ratio
    //it also creates al the frame items and adds these to the frame
    public void createWindow(int height, double ratio) {
        canvas = new Canvas();
        frame = new JFrame(fpsC);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("test");
        button.setBounds(50,100,95,30);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (state==false){
                    renderer.setTestcolor(16000000);
                    state=true;
                }
                else {
                    renderer.setTestcolor(1000);
                    state=false;
                }

            }
        });
        frame.add(button);
        frame.setSize(widthCalc(),height);
        frame.add(canvas);
        frame.setVisible(true);

    }

    //calculates the width
    public int widthCalc(){
        Double u = height *ratio;
        return u.intValue();
    }

    @Override
    public void run() {

        while (true){//loop
            //fpsCounter.setText(fpsC);
            frame.setTitle(fpsC); //fps in window tittle
            System.out.println("running");
            double start = System.nanoTime();//start of run time of one frame
            render();//the graphics side of the loop
            double end = System.nanoTime();//end of run time of one frame
            System.out.println("tijd per frame: " + (end-start)/1000000000f);//time per frame
            fpsC ="tijd per frame: " + (end-start)/1000000000f;
            update();//the backside calculations, in most cases the game code would start here also known as the engine or non-graphic code.
        }

    }

    //the graphics side of the loop
    public void render(){

        BufferStrategy bs= canvas.getBufferStrategy();

        if(bs==null){
            canvas.createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        g.setColor(new Color(0));
        g.fillRect(0,0, widthCalc(),height);//background

        g.drawImage(renderer.RenderToImage(height),0,0,null);

        bs.show();
        g.dispose();
    }

    //engine
    public void update(){

    }
}
