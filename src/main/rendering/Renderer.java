package main.rendering;

import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.ShadowRay;
import main.maths.Vector3;
import main.scene.Camera;
import main.scene.Scene;
import main.utils.Color;

import java.awt.image.BufferedImage;

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
                    //if there is intersection then it will color x and y blue
                    //buffer.setRGB(x, y, 1000);//todo get the color of hitobject

                    System.out.println("intersect on  x: " + x + " y : " + y);

                    Vector3 mainLight = scene.getLights().get(0).getPosition();
                    Vector3 lightDir = scene.getLights().get(0).getPosition().sub(hit.getContactPoint()).normalise();
                    ShadowRay shaRay = new ShadowRay(lightDir, hit.getContactPoint());
                    boolean lightHit = shaRay.castRay(scene.getGeometry());

                    if (lightHit != true){

                        Vector3 hitPos = hit.getContactPoint();
                        double distance = Math.sqrt(Math.pow((hitPos.getX() - mainLight.getX()), 2) + Math.pow((hitPos.getY() - mainLight.getY()), 2) + Math.pow((hitPos.getZ() - mainLight.getZ()), 2));
                        double light = scene.getLights().get(0).getIntensity() / Math.pow(distance, 2);
                        Vector3 reflection = hit.getHitSolid().getMaterial().getColor().getColor().divide(255).multi(light);
                        Vector3 lightImpact = hitPos.sub(mainLight).normalise();
                        double lightImpactFinal = Math.cos(hitPos.dot(lightImpact));
                        //Vector3 reflectionImpact = new Vector3(reflection.getX() - lightImpactFinal, reflection.getY() - lightImpactFinal, reflection.getZ() - lightImpactFinal);
                        Vector3 reflectionImpact = reflection.divide(lightImpactFinal);
                        Vector3 reflectionFinal = reflectionImpact.multi(255);
                        if (reflectionFinal.getX() > 255){
                            reflectionFinal.setX(255);
                        }

                        if (reflectionFinal.getX() < 0){
                            reflectionFinal.setX(0);
                        }

                        if (reflectionFinal.getY() > 255){
                            reflectionFinal.setY(255);
                        }

                        if (reflectionFinal.getY() < 0){
                            reflectionFinal.setY(0);
                        }

                        if (reflectionFinal.getZ() > 255){
                            reflectionFinal.setZ(255);
                        }

                        if (reflectionFinal.getZ() < 0){
                            reflectionFinal.setZ(0);
                        }
                        System.out.println("x" + reflectionFinal.getX() + "Y" + reflectionFinal.getY() + "Z" + reflectionFinal.getZ());
                        
                        java.awt.Color objColorJava = new java.awt.Color((int)reflectionFinal.getX(),(int)reflectionFinal.getY(),(int)reflectionFinal.getZ()); 
                        
                        buffer.setRGB(x, y, objColorJava.getRGB());
                        System.out.println("Hit can see light!");
                    }else{
                        buffer.setRGB(x, y, 000000);
                    }


                } else {
                    //if there is no intersection then it will color x and y black
                    buffer.setRGB(x, y, 000000);
                }
            }
        }

        pixelData.toImage(buffer);

    }


}
