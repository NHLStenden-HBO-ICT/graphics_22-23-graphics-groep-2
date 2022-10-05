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

    private static final long serialVersionUID = 1L;

    private boolean state;

    private String fpsC= "raytracer";



    public Window(int height, double ratio){
        this.state=false;
        canvas = new Canvas();
        this.height = height;
        this.ratio=ratio;
        canvas.setPreferredSize(new Dimension(widthCalc(),this.height));
        canvas.setMaximumSize(new Dimension(widthCalc(),this.height));
        canvas.setMinimumSize(new Dimension(widthCalc(),this.height));

        createWindow(height,ratio);
    }

    public void start(Renderer renderer1){
        //isRunning = true;
        System.out.println("start");
        renderer = renderer1;

        thread =new Thread(this::run, "thread 1");
        thread.start();
    }

    public void createWindow(int height, double ratio) {

        canvas = new Canvas();
        frame = new JFrame(fpsC);

        //fpsCounter =new JLabel(fpsC);
        //fpsCounter.setForeground(Color.white);

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

    public int widthCalc(){
        Double u = height *ratio;
        return u.intValue();
    } //calculates the width

    @Override
    public void run() {

        while (true){
            //fpsCounter.setText(fpsC);
            frame.setTitle(fpsC); //fps in window tittle
            System.out.println("running");
            double start = System.nanoTime();//start of run time of one frame
            render();
            double end = System.nanoTime();//end of run time of one frame
            System.out.println("tijd per frame: " + (end-start)/1000000000f);//time per frame
            fpsC ="tijd per frame: " + (end-start)/1000000000f;
            update();
        }

    }

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

    public void update(){

    }
}
