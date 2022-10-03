package main.application;

import main.rendering.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class Window implements Runnable{

    private static JFrame frame;
    private boolean isRunning;

    private static Renderer renderer;

    private Canvas canvas;

    private Thread thread;

    private static int height;
    private static double ratio;

    private static final long serialVersionUID = 1L;
    private static JLabel label;
    private boolean state;
    private Graphics g;

    private Image image;

    public Window(int height, double ratio){
        this.state=false;
        canvas = new Canvas();
        this.height = height;
        this.ratio=ratio;
        canvas.setPreferredSize(new Dimension(widthCalc(),this.height));
        canvas.setMaximumSize(new Dimension(widthCalc(),this.height));
        canvas.setMinimumSize(new Dimension(widthCalc(),this.height));

        createWindow(height,ratio);
        //start();
        //run();
    }

    public void start(){
        //isRunning = true;
        System.out.println("start");
        renderer = new Renderer(ratio);

        thread =new Thread(this::run, "thread 1");
        thread.start();
        //canvas.createBufferStrategy(3);
        //run();
    }

    public void createWindow(int height, double ratio) {

        //canvas = new Canvas();
        frame = new JFrame("Raytest");
        JButton button = new JButton("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

    @Override
    public void run() {

        while (true){
            System.out.println("running");
            double start = System.nanoTime();//start of run time of one frame
            render();
            double end = System.nanoTime();//end of run time of one frame
            System.out.println("tijd per frame: " + (end-start)/1000000000f);//time per frame
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
