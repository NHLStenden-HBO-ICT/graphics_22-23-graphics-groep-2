package main.rendering;

import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.ShadowRay;
import main.maths.Vector3;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import main.utils.VectorColor;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.util.List;

public class Renderer {

    private Scene scene;

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

    // Cast rays from the camera into the scene to detect intersections and see if they need to be lit up
    // First get screen resolution and ratio
    // Then setip a buffer and a camera
    // Then cast a ray through each pixel and calculate the color for it if it intersects with a solid
    public BufferedImage RenderToImage(int height) {

        PixelData pixelData = new PixelData(height, scene.getCamera().getRatio());
        BufferedImage buffer = new BufferedImage(pixelData.getWidth(), pixelData.getHeight(), BufferedImage.TYPE_INT_RGB);

        Camera camera = scene.getCamera();

        // Loop for each pixel on the image
        for (int y = 0; y < pixelData.getHeight(); ++y) {
            for (int x = 0; x < pixelData.getWidth(); ++x) {
                // Cast ray and check if it intersects with something
                FullRay ray = camera.getRayFromPixel(x, y);//gets the ray with the coörds of the virtual screen that's equal to the x and y pixel of the image
                RayHit hit = ray.castRay(scene.getGeometry());

                // If the ray intersects with something, write coordinates in console and call the calculateLight(RayHit hit) method with the current rayhit
                // Finally set that pixel with the final color in the image buffer
                if (hit != null) {
                    System.out.println("intersect on  x: " + x + " y : " + y);
                    Color finalColor = calculateLight(hit, scene);

                    buffer.setRGB(x, y, finalColor.getRGB());

                } else {
                    //if there is no intersection then it will color x and y black
                    buffer.setRGB(x, y, 000000);
                }
            }
        }

        // At the end call toImage(bufferedImage image) in pixelData to convert the buffer to an image
        pixelData.toImage(buffer);
        return buffer;
    }

    // This method checks wheter or not a pixel should be lit up by casting a shadow ray to each light source
    // First it puts the lights and contactpoint in variables and makes a variable for the final output
    public Color calculateLight(RayHit hit, Scene scene) {
        List<PointLight> lights = scene.getLights();
        Vector3 hitPos = hit.getContactPoint();
        Color finalColor = new Color(0, 0, 0);

        // Then calculate the light for the given contactpoint for each light and check if it can see the light
        for (int i = 0; i < lights.size(); i++) {

            // Get the light position and the direction towards the light
            PointLight light = lights.get(i);
            Vector3 lightPos = light.getPosition();
            Vector3 lightDir = lightPos.sub(hitPos).normalise();

            // Cast a shadowray to the light source
            ShadowRay shaRay = new ShadowRay(lightDir, hitPos);
            boolean shadowHit = shaRay.castRay(scene.getGeometry());

            // If it doesn't intersect with anything calculate light and color
            // Else return black color
            if (!shadowHit) {
                // First calculate the intensity of the color of the pixel according to the intensity of the lightsource and the distance of the lightsource to the contactpoint
                double lightIntensity = light.getIntensity() / Math.pow(hitPos.distance(lightPos), 2);

                // Get contactpoint solid, get material of that then get the color of it and then get the vector of that, divide it by 255 to get 0-1 rgb values
                VectorColor hitColor = new VectorColor(hit.getHitSolid().getMaterial().getColor().getVector());

                // Then multiply it by the light intensity
                hitColor.setColor(hitColor.getVector().multi(lightIntensity));

                // Then do the same for the color of the light and add the result to the reflection vector
                VectorColor lightColor = new VectorColor(light.getColor().getVector().multi(lightIntensity));
                VectorColor reflection = hitColor.addVectorColor(lightColor);

                // Calculate the angle at which the light hits the contact point
                Vector3 lightAngleDir = hitPos.sub(lightPos);
                double lightAngle = Math.max(0.01, hit.getHitSolid().getSurfaceNormal(hitPos).dot(lightAngleDir));

                // Divide the reflection by the angle of the light and multiply it by 255 to get 255 rgb values again
                VectorColor reflectionFinal = new VectorColor(reflection.getVector().divide(lightAngle));

                // Clamp the color to a range of 0-255 to prevent rgb overflows

                //Color processedColor = new Color((int) reflectionFinal.getX(), (int) reflectionFinal.getY(), (int) reflectionFinal.getZ());

                // Add the result of the list loop with to the result of this loop
                // This only happens when there are multiple lights
                finalColor = addColors(finalColor, reflectionFinal.getJavaColor());
            } else {
                // If there is a hit return gray
                // we do this because it allows us to disambiguate shadows from the background
                return new Color(40, 40, 40);
            }
        }

        // Finally return the final result after all the lights have been calculated
        return finalColor;
    }

    // Quick method to add the rbg values of two java.awt.Color objects to each other
    public Color addColors(Color color1, Color color2) {
        int r = (color1.getRed() + color2.getRed());
        int g = (color1.getGreen() + color2.getGreen());
        int b = (color1.getBlue() + color2.getBlue());
        return new Color(r, g, b);
    }
}
