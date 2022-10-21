package main.application;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {

    States currentState;

    private Label stateDisplay;
    private JProgressBar progressBar;

    //this method should only be called from the event dispatch thread because it affects the gui
    public StatusBar() {
        //configure this JPanel
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //make some elements to add to it
        stateDisplay = new Label();
        progressBar = new JProgressBar();

        //configure state display label
        stateDisplay.setPreferredSize(new Dimension(100, 20));

        //configure progress bar
        progressBar.setPreferredSize(new Dimension(100, 20));

        this.add(stateDisplay);
        this.add(progressBar);
        this.setVisible(true);
    }

    public Label getStateDisplay() {
        return stateDisplay;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    //this method modifies the GUI and should therefore only be called from the event dispatch thread
    public void setProgressMinMax(int min, int max) {
        progressBar.setMinimum(min);
        progressBar.setMaximum(max);
    }

    //takes a state and updates the ui to be correct
    //this method modifies the gui and should therefore only be called from the event dispatch thread
    public void setState(States state) {
        currentState = state;
        progressBar.setVisible(false);

        switch (currentState) {
            case STARTING -> stateDisplay.setText("Starting...");

            case LOADING_MODEL -> stateDisplay.setText("Loading a model...");

            case IDLE -> stateDisplay.setText("Awaiting input.");

            case RENDERING -> {
                stateDisplay.setText("Rendering...");
                progressBar.setVisible(true);
            }
        }

    }

    public void setProgress(int value) {
        progressBar.setValue(value);
    }
}
