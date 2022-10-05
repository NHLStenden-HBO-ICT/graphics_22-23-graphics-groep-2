package main.rendering;

import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.ShadowRay;
import main.maths.Vector3;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import java.awt.Color;

import java.awt.image.BufferedImage;
import java.util.List;

public class Renderer {

    private Scene scene;

    private int maxRayDepth;

    public Renderer(Scene scene) {
        this.scene = scene;
    }

    // Return the current scene
    public Scene getScene() {
        return scene;
    }

    // Set the current scene
    public void setScene(Scene scene_) {
        scene = scene_;
    }

    public void RenderToImage() {

        PixelData pixelData = new PixelData(400, scene.getCamera().getRatio());
        BufferedImage buffer = new BufferedImage(pixelData.getWidth(), pixelData.getHeight(), BufferedImage.TYPE_INT_RGB);

        Camera camera = scene.getCamera();

        //loop for each pixel on the image
        for (int y = 0; y < pixelData.getHeight(); ++y) {
            for (int x = 0; x < pixelData.getWidth(); ++x) {

                FullRay ray = camera.rayThroughPixel(x, y, pixelData.getWidth(), pixelData.getHeight());//gets the ray with the coörds of the virtual screen that's equal to the x and y pixel of the image
                RayHit hit = ray.castRay(scene.getGeometry());

                if (hit != null) {

                    System.out.println("intersect on  x: " + x + " y : " + y);
                    Color finalColor = calculateLight(hit);

                    buffer.setRGB(x, y, finalColor.getRGB());

                } else {
                    //if there is no intersection then it will color x and y black
                    buffer.setRGB(x, y, 000000);
                }
            }
        }

        pixelData.toImage(buffer);

    }

    public Color calculateLight(RayHit hit) {
        List<PointLight> lights  = scene.getLights();
        Vector3 hitPos = hit.getContactPoint();
        Color finalColor = new Color(0,0,0);
        for (int i = 0; i < lights.size(); i++){

            PointLight light = lights.get(i);
            Vector3 lightPos = light.getPosition();
            Vector3 lightDir = lightPos.sub(hitPos).normalise();

            ShadowRay shaRay = new ShadowRay(lightDir, hitPos);
            boolean lightHit = shaRay.castRay(scene.getGeometry());

            if (lightHit != true){
                
                double lightIntensity = light.getIntensity() / Math.pow(hitPos.distance(lightPos), 2);
                Vector3 reflection = hit.getHitSolid().getMaterial().getColor().getColor().divide(255).multi(lightIntensity).add(light.getColor().getColor().divide(255).multi(lightIntensity));

                Vector3 lightAngleDir = hitPos.sub(lightPos).normalise();
                double lightAngle = Math.cos(hitPos.dot(lightAngleDir));
                Vector3 reflectionFinal = reflection.divide(lightAngle).multi(255);
                
                Color processedColor = clampColor(reflectionFinal);                
                finalColor = addColors(finalColor, processedColor);
            }else{
                return new Color(0,0,0);
            }
        }

        return finalColor;

    }

    public Color addColors(Color color1, Color color2){
        int r = Math.min(255, (color1.getRed() + color2.getRed()));
        int g = Math.min(255, (color1.getGreen() + color2.getGreen()));
        int b = Math.min(255, (color1.getBlue() + color2.getBlue()));
        return new Color(r,g,b);
    }

    public Color clampColor(Vector3 color) {
        double r = color.getX();
        double g = color.getY();
        double b = color.getZ();

        if (r > 255){
            r = 255;
        }

        if (r < 0){
            r = 0;
        }

        if (g > 255){
            g = 255;
        }

        if (g < 0){
            g = 0;
        }

        if (b > 255){
            b = 255;
        }

        if (b < 0){
            b = 0;
        }

        return new Color((int)r,(int)g,(int)b);
    }
}
