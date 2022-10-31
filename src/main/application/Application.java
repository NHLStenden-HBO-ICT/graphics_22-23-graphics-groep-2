package main.application;

import static javax.swing.SwingUtilities.invokeLater;

public class Application {

    private static int height = 400;
    private static double ratio = 16.0 / 9.0;
    private static int width = (int) (ratio * height);

    public static void main(String[] args) {
        //use invoke later to schedule a task on the event dispatch thread (EDT)
        //swing elements should only be created and modified on the EDT
        //otherwise unexpected behavior might occur
        //but because swing is weird unexpected behavior might still occur
        invokeLater(() -> {
            new MainWindow(height, width);
        });

    }
}
