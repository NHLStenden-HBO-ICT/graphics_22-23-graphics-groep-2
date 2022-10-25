package main.rendering;

import main.scene.Camera;
import main.scene.Scene;

import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;

public class RenderWorker implements Runnable {
    private CountDownLatch countDownLatch;
    BufferedImage bufferedImage;
    private final Scene scene;
    private final int startX, startY, endX, endY;

    public RenderWorker(CountDownLatch countDownLatch, int pixelsRendered, BufferedImage bufferedImage, Scene scene, int startX, int startY, int endX, int endY) {
        this.countDownLatch = countDownLatch;
        this.bufferedImage = bufferedImage;
        this.scene = scene;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    @Override
    public void run() {
        Camera camera = scene.getCamera();
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                int rgb = Renderer.tracePixel(camera, scene, x, y);
                //set the pixel
                bufferedImage.setRGB(x, y, rgb);

                //update our progress
                countDownLatch.countDown();
            }
        }


    }
}
