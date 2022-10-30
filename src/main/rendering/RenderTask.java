package main.rendering;

import main.application.MainWindow;
import main.application.States;
import main.application.StatusBar;
import main.scene.Camera;
import main.scene.Scene;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static javax.swing.SwingUtilities.invokeLater;

public class RenderTask extends SwingWorker<BufferedImage, Integer> {
    MainWindow mainWindow;
    StatusBar statusBar;
    JLabel image;
    int width, height, pixelsToRender;
    Scene scene;

    public RenderTask(MainWindow mainWindow, int height, int width) {
        this.height = height;
        this.width = width;
        this.pixelsToRender = height * width;
        this.mainWindow = mainWindow;
        this.scene = mainWindow.getScene();
        this.statusBar = mainWindow.getStatusBar();
        this.image = mainWindow.getImageLabel();
    }

    @Override
    protected BufferedImage doInBackground() throws Exception {

        //use invokeLater to schedule UI updates on the EDT
        invokeLater(() -> {
            //update the program state
            statusBar.setState(States.RENDERING);

            //set the status bar to the correct values
            statusBar.setProgressMinMax(0, width * height);
        });

        //create relevant variables
        Camera camera = scene.getCamera();
        BufferedImage buffer = new BufferedImage((int) camera.getImageWidth(), (int) camera.getImageHeight(), BufferedImage.TYPE_INT_RGB);
        int renderProgress = 0;

        //tracks our progress among all the threads
        CountDownLatch countDownLatch = new CountDownLatch(pixelsToRender);

        //create a worker for each thread we want to run
        int numberOfThreads = 10;
        ArrayList<RenderWorker> workers = new ArrayList<>(numberOfThreads);

        //calculate the size of the chunks of screen we're rendering;
        int yChunkSize = height / numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            workers.add(new RenderWorker(countDownLatch, renderProgress, buffer, scene, 0, i * yChunkSize, width - 1, (i + 1) * yChunkSize - 1));
        }

        //start all the treads
        for (int i = 0; i < workers.size(); i++) {
            Runnable runnable = workers.get(i);
            Thread thread = new Thread(runnable);
            thread.start();
        }

        while (countDownLatch.getCount() >= 1) {
            //while threads are still running, publish the progress every n milis
            int n = 50;
            publish((int) countDownLatch.getCount());
            //Thread.sleep(n)
            Thread.sleep(n);
        }

        return buffer;
    }

    //gets called when swing decides the time is right and takes stuff published by publish()
    //updates the ui with the new info
    @Override
    protected void process(List<Integer> chunks) {
        int progress = chunks.get(chunks.size() - 1);
        //schedule UI updates on the ui thread
        invokeLater(() -> {
            statusBar.setProgress(pixelsToRender - progress);
        });
    }

    //gets called when the task is done
    @Override
    protected void done() {
        BufferedImage renderResult;

        //our thread might not resolve so we need to catch and handle errors
        //TODO actually handle the errors
        try {
            renderResult = get(); //get the result of our render
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        //schedule a UI update on the EDT
        invokeLater(() -> {
            //update the program status
            statusBar.setState(States.IDLE);

            //set the image
            mainWindow.setImage(renderResult);
        });
    }
}
