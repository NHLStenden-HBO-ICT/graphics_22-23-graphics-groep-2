package main.application;

import main.rendering.RenderTask;
import main.scene.Scene;
import main.scene.SceneFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;

public class MainWindow extends JFrame implements EventListener {

    //ray tracing stuff
    private Scene scene;
    private BufferedImage image;

    //UI elements
    private StatusBar statusBar;
    private JPanel actionBar;
    private JPanel ui;
    private Button renderButton;
    private Button imageSaveButton;
    private Button forestButton;
    private Button simpleButton;
    private Button modelButton;
    private JLabel imageLabel;

    public void setImage(BufferedImage image) {
        //safe the image in a local variable
        this.image = image;
        //render the image to the screen
        this.imageLabel.setIcon(new ImageIcon(image));
    }

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

        //set the layout for our window
        getLayeredPane().setLayout(new BorderLayout());
        //make the layered pane not render background
        getLayeredPane().setOpaque(false);
    }

    private void configureUI() {
        //make a new JLabel to display our image in and configure it
        imageLabel = new JLabel();
        imageLabel.setBackground(Color.BLACK);
        imageLabel.setBounds(0, 0, this.getLayeredPane().getWidth(), this.getLayeredPane().getHeight());
        this.getLayeredPane().add(imageLabel, BorderLayout.CENTER);
        this.getLayeredPane().setLayer(imageLabel, JLayeredPane.DEFAULT_LAYER);

        //make two different panels for the separate UI elements (actionBar & statusBar)
        // & a panel to contain both (ui)
        //configure the ui JPanel. This panel contains the action bar and the status bar
        ui = new JPanel();
        ui.setBounds(0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
        ui.setLayout(new BorderLayout());
        ui.setOpaque(false);

        //statusBar is its own class that extends JPanel
        //it handles its own configuration and a number of other tasks
        statusBar = new StatusBar();

        //configure the action bar
        actionBar = new JPanel();
        actionBar.setOpaque(false);
        actionBar.setLayout(new FlowLayout(FlowLayout.LEFT));


        //instance a new button. This button will render our scene when clicked
        renderButton = new Button("Render");
        renderButton.setPreferredSize(new Dimension(70, 20));
        renderButton.addActionListener(e -> render());

        //instance a new button. This button will save our render to a file when clicked
        imageSaveButton = new Button("save image to file");
        imageSaveButton.setPreferredSize(new Dimension(100, 20));
        imageSaveButton.addActionListener(e -> saveImageToFile());


        forestButton = new Button("forest");
        forestButton.setPreferredSize(new Dimension(70, 20));
        forestButton.addActionListener(e -> forestScene());

        simpleButton = new Button("simple");
        simpleButton.setPreferredSize(new Dimension(70, 20));
        simpleButton.addActionListener(e -> simpleScene());

        modelButton = new Button("model");
        modelButton.setPreferredSize(new Dimension(70, 20));
        modelButton.addActionListener(e -> modelScene());

        //add the buttons to the appropriate bar
        actionBar.add(renderButton);
        actionBar.add(imageSaveButton);
        actionBar.add(forestButton);
        actionBar.add(simpleButton);
        actionBar.add(modelButton);

        //add the bars to the ui panel
        ui.add(statusBar, BorderLayout.NORTH);
        ui.add(actionBar, BorderLayout.SOUTH);

        //add the ui on the palette layer
        this.getLayeredPane().add(ui, BorderLayout.CENTER);
        this.getLayeredPane().setLayer(ui, JLayeredPane.PALETTE_LAYER);

        //set everything to visible
        //if this isn't called unexpected behavior and weird graphical glitches might occur
        this.setVisible(true);
    }

    //sets up everything, so we're ready to render a scene.
    private void setUpRayTracer() {
        statusBar.setState(States.LOADING_MODEL);
        scene = SceneFactory.forestScene(getHeight(), getWidth());
        statusBar.setState(States.IDLE);
    }

    //when this is called a scene is rendered
    private void render() {
        int width, height;
        width = (int) scene.getCamera().getImageWidth();
        height = (int) scene.getCamera().getImageHeight();

        //makes a new render task and executes it
        new RenderTask(this, height, width).execute();
    }

    //when called saves the currently stored image to a file
    private void saveImageToFile() {
        String outputDestination = "out/image.png";
        File outputFile = new File(outputDestination);
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private void forestScene() {
        scene=SceneFactory.forestScene(getHeight(), getWidth());
    }
    private void simpleScene() {
        scene=SceneFactory.modelScene(getHeight(), getWidth());
    }
    private void modelScene() {
        scene=SceneFactory.floatingIslandScene(getHeight(), getWidth());
    }
}