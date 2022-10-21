package main.application;

import main.rendering.RenderTask;
import main.rendering.Renderer;
import main.scene.Scene;
import main.scene.SceneFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.EventListener;

public class MainWindow extends JFrame implements EventListener {

    //ray tracing stuff
    private Scene scene;
    private Renderer renderer;

    //UI elements
    private JLayeredPane layeredPane;
    private StatusBar statusBar;
    private JPanel actionBar;
    private JPanel ui;
    private Button renderButton;
    private JLabel imageLabel;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        //safe the image in a local variable
        this.image = image;
        //render the image to the screen
        this.imageLabel.setIcon(new ImageIcon(image));
    }

    private BufferedImage image;

    MainWindow(int height, int width) {
        configureWindow(width, height);
        configureUI();
        statusBar.setState(States.STARTING);
        setUpRayTracer();
    }

    private void configureWindow(int width, int height) {
        setTitle("RayTracer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(width, height);
        setVisible(true);
        setLayout(null);
        setBackground(Color.BLACK);
    }

    private void configureUI() {
        this.getLayeredPane().setLayout(new BorderLayout());
        this.getLayeredPane().setOpaque(false);

        //image
        imageLabel = new JLabel();
        imageLabel.setBackground(Color.BLACK);
        imageLabel.setBounds(0, 0, this.getLayeredPane().getWidth(), this.getLayeredPane().getHeight());
        this.getLayeredPane().add(imageLabel, BorderLayout.CENTER);
        this.getLayeredPane().setLayer(imageLabel, JLayeredPane.DEFAULT_LAYER);

        //make two different panels for the separate UI elements & a panel to contain both
        ui = new JPanel();
        statusBar = new StatusBar();
        actionBar = new JPanel();

        //configure the bars
        ui.setBounds(0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
        ui.setLayout(new BorderLayout());
        ui.setOpaque(false);
        //ui.setBackground(Color.RED);

        actionBar.setOpaque(false);
        actionBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        //instantiate UI elements
        renderButton = new Button("Render");

        //configure the UI elements
        //render button
        renderButton.setPreferredSize(new Dimension(70, 20));
        renderButton.addActionListener(e -> render());


        //add the UI elements to the appropriate bars
        actionBar.add(renderButton);

        //add the bars to the ui panel
        ui.add(statusBar, BorderLayout.NORTH);
        ui.add(actionBar, BorderLayout.SOUTH);

        //add the ui on the palette layer
        this.getLayeredPane().add(ui, BorderLayout.CENTER);
        this.getLayeredPane().setLayer(ui, JLayeredPane.PALETTE_LAYER);

        //set everything to visible
        this.setVisible(true);
    }

    private void setUpRayTracer() {
        statusBar.setState(States.LOADING_MODEL);
        scene = SceneFactory.modelScene(getHeight(), getWidth());
        statusBar.setState(States.IDLE);
    }

    private void render() {
        int width, height;
        width = (int) scene.getCamera().getImageWidth();
        height = (int) scene.getCamera().getImageHeight();

        new RenderTask(this, height, width).execute();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Scene getScene() {
        return scene;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }


}