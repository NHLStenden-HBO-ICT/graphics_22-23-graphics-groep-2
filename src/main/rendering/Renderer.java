package main.rendering;

import main.geometry.Solid;
import main.geometry.Triangle;
import main.maths.*;
import main.scene.Camera;
import main.scene.PointLight;
import main.scene.Scene;
import main.utils.Material;
import main.utils.VectorColor;

import java.awt.image.BufferedImage;
import java.util.List;

public class Renderer {

    private static double MAX_RAY_DEPTH = 5;


    private Renderer() {
    }

    public static int tracePixel(Camera camera, Scene scene, int x, int y) {
        // Cast ray and check if it intersects with something
        FullRay ray = camera.getRayFromPixel(x, y);// gets the ray with the coörds of the virtual screen that's
        // equal to the x and y pixel of the image
        RayHit hit = ray.castRay(scene.getGeometry());

        // If the ray intersects with something, write coordinates in console and call
        // the calculateLight(RayHit hit) method with the current rayhit
        // Finally set that pixel with the final color in the image buffer
        if (hit != null) {
            if (hit.getHitSolid().getMaterial().getTexturemap()!=null)
            {
                return textures(hit);
            }
            else
            {
                VectorColor finalColor = calculateLight(hit, scene, 0);
                return finalColor.getJavaColor().getRGB();
            }


        } else {
            // if there is no intersection then it will color x and y black
            return 000000;
        }
    }

    public double getMaxRayDepth() {
        return MAX_RAY_DEPTH;
    }

    // Cast rays from the camera into the scene to detect intersections and see if
    // they need to be lit up
    // First get screen resolution and ratio
    // Then setip a buffer and a camera
    // Then cast a ray through each pixel and calculate the color for it if it
    // intersects with a solid
    public BufferedImage RenderToImage(Scene scene, int height) {

        PixelData pixelData = new PixelData(height, scene.getCamera().getRatio());
        BufferedImage buffer = new BufferedImage(pixelData.getWidth(), pixelData.getHeight(),
                BufferedImage.TYPE_INT_RGB);

        Camera camera = scene.getCamera();

        // Loop for each pixel on the image
        for (int y = 0; y < pixelData.getHeight(); ++y) {
            for (int x = 0; x < pixelData.getWidth(); ++x) {
                buffer.setRGB(x, y, tracePixel(camera, scene, x, y));
            }
        }

        // At the end call toImage(bufferedImage image) in pixelData to convert the
        // buffer to an image
        pixelData.toImage(buffer);
        return buffer;
    }

    // This method checks wheter or not a pixel should be lit up by casting a shadow
    // ray to each light source
    // First it puts the lights and contactpoint in variables and makes a variable
    // for the final output
    public static VectorColor calculateLight(RayHit hit, Scene scene, double rayDepth) {

        // Stopping conditions for when this function is called recursively
        // Stop when max raydepth has been reached
        if (rayDepth == MAX_RAY_DEPTH) {
            return new VectorColor(new Vector3(0, 0, 0));
        }

        // Stop if nothing has been hit
        if (hit == null) {
            return new VectorColor(new Vector3(0, 0, 0));
        }

        // Retrieve scene lights
        List<PointLight> lights = scene.getLights();

        // Get hit position and object
        Vector3 hitPos = hit.getContactPoint();
        Solid hitSolid = hit.getHitSolid();
        Vector3 fullRayDir = hit.getRay().getDirection();

        // Create colors later to be used for reflective calculations and diffuse
        // calculations
        VectorColor reflColor = new VectorColor(new Vector3(0, 0, 0));
        VectorColor diffColor = new VectorColor(new Vector3(0, 0, 0));

        // Calculate the light for the given contactpoint for each light and check if it
        // can see the light
        for (int i = 0; i < lights.size(); i++) {

            // Get the light position and the direction towards the light
            PointLight light = lights.get(i);
            Vector3 lightPos = light.getPosition();
            Vector3 lightDir = lightPos.sub(hitPos).normalise();

            // First calculate the intensity of the color of the pixel according to the
            // intensity of the lightsource and the distance of the lightsource to the
            // contactpoint
            double lightIntensity = light.getIntensity() / Math.pow(hitPos.distance(lightPos), 2);

            // Get contactpoint solid, get material of that then get the color of it and
            // then get the vector of that, divide it by 255 to get 0-1 rgb values
            VectorColor hitColor = new VectorColor(hit.getHitSolid().getMaterial().getColor().getVector());

            // Then multiply it by the light intensity
            hitColor.setColor(hitColor.getVector().multi(lightIntensity));

            // Then do the same for the color of the light and add the result to the
            // reflection vector
            VectorColor lightColor = new VectorColor(light.getColor().getVector().multi(lightIntensity));
            VectorColor reflection = hitColor.addVectorColor(lightColor);

            // Check if object is reflective and send a reflective ray if it is
            if (hitSolid.getMaterial().getReflectivity() > 0.0) {

                // Calculate new ray direction
                Vector3 reflectedRayDir = fullRayDir.sub(hit.getNormal().multi(2 * fullRayDir.dot(hit.getNormal())));

                // Cast a new ray for the reflection
                FullRay reflectedRay = new FullRay(reflectedRayDir, hitPos);
                RayHit reflectedHit = reflectedRay.castRay(scene.getGeometry());

                // Add one step to the raydepth
                rayDepth = rayDepth + 1;

                // Start recursive calculation for the reflection
                // Multiply the result by the reflectivity of the material
                reflColor = new VectorColor(reflection.addVectorColor(calculateLight(reflectedHit, scene, rayDepth))
                        .getVector().multi(hitSolid.getMaterial().getReflectivity()));

                // Check if object refracts light
                if (hitSolid.getMaterial().getIor() > 1.0) {

                    // Get ior of material
                    double ior = hitSolid.getMaterial().getIor();

                    // Calculate fresnel equations
                    double kr = RefractionMath.fresnel(fullRayDir, hit.getNormal(), ior);

                    // Check if ray is outside surface
                    boolean outside = fullRayDir.dot(hit.getNormal()) < 0;

                    // Bias to offset the ray and avoid shadow acne-like effects
                    Vector3 bias = hit.getNormal().multi(Constants.EPSILON);

                    // If the result of the fresnel equations is below 1 calculate refraction
                    if (kr < 1) {

                        // Calculate refraction direction and set origin
                        Vector3 refractDir = RefractionMath.refract(fullRayDir, hit.getNormal(), ior).normalise();
                        Vector3 refractOrigin = hitPos;

                        // Offset origin with bias
                        if (outside) {
                            refractOrigin = refractOrigin.sub(bias);
                        } else {
                            refractOrigin = refractOrigin.add(bias);
                        }

                        // Create refraction ray and cast it
                        FullRay refractRay = new FullRay(refractDir, refractOrigin);
                        RayHit refractHit = refractRay.castRay(scene.getGeometry());

                        // Mix reflection color with refraction color
                        reflColor = new VectorColor(reflColor.getVector().multi(kr)).addVectorColor(
                                new VectorColor(calculateLight(refractHit, scene, rayDepth).getVector().multi(1 - kr)));
                    }
                }
            }

            // Check if reflectivity of object is lower then 1
            // If it is 1, diffuse doesn't need to be calculated anyways
            if (hitSolid.getMaterial().getReflectivity() < 1.0) {

                // Cast a shadowray to the light source
                ShadowRay shaRay = new ShadowRay(lightDir, hitPos);
                boolean shadowHit = shaRay.castRay(scene.getGeometry());

                // If it doesn't intersect with anything calculate light and color
                // Else return black color
                if (!shadowHit) {

                    // Calculate the angle at which the light hits the contact point
                    Vector3 lightAngleDir = hitPos.sub(lightPos);
                    double lightAngle = Math.max(0.01, hit.getNormal().dot(lightAngleDir));

                    // Divide the reflection by the angle of the light and multiply it by 255 to get
                    // 255 rgb values again
                    VectorColor reflectionFinal = new VectorColor(reflection.getVector().divide(lightAngle));

                    // Multiply the resulting color with 1 - the reflectivity of the object
                    diffColor = new VectorColor(reflection.addVectorColor(reflectionFinal).getVector()
                            .multi(1 - hitSolid.getMaterial().getReflectivity()));
                } else {
                    // If there is a hit return gray
                    // we do this because it allows us to disambiguate shadows from the background
                    return new VectorColor(new Vector3(40, 40, 40));
                }
            }
        }
        // Finally return the final result after all the lights have been calculated
        return diffColor.addVectorColor(reflColor);
    }

    public static int textures(RayHit hit){

        Solid solid = hit.getHitSolid();
        Triangle triangle = new Triangle(solid.getMaterial(),new Vector3(),new Vector3(), new Vector3());
        Material material =solid.getMaterial();


        if(solid.getClass()==Triangle.class){
            triangle = (Triangle) solid.getClass().cast(solid);
        }
        else
        {
            return 0;
        }


        //Triangle copy =triangle;
        //copy.setPosition(new Vector3());
        //copy.getOrientation().LookAt(copy.getPosition(),new Vector3());
        //Quaternion rotation = copy.getOrientation().getQuaternion();

        Vector3 hitpoint =hit.getContactPoint();
        hitpoint = hitpoint.sub(new Vector3(Math.abs()));
        //Quaternion reverserotation =new Quaternion(new Vector3(-rotation.getX(),-rotation.getY(),-rotation.getZ()),rotation.getRotation());
        //hitpoint.rotateByQuaternion(reverserotation);

        //Vector3[] vertexes = triangle.getVertices();
        System.out.println(hitpoint.getX()+" "+hitpoint.getY());
        Vector3[] texturevertices = triangle.getTexturemap();

        Vector3[] vertexesOrg = triangle.getVerticesorigin();


        double vertXmin = Math.min(vertexesOrg[0].getX(),Math.min(vertexesOrg[1].getX(),vertexesOrg[2].getX()));
        double vertYmin = Math.min(vertexesOrg[0].getY(),Math.min(vertexesOrg[1].getY(),vertexesOrg[2].getY()));
        double textXmin = Math.min(texturevertices[0].getX(),Math.min(texturevertices[1].getX(),texturevertices[2].getX()));
        double textYmin = Math.min(texturevertices[0].getY(),Math.min(texturevertices[1].getY(),texturevertices[2].getY()));

        double widthRatio3D=Math.max(vertexesOrg[0].getX(),Math.max(vertexesOrg[1].getX(),vertexesOrg[2].getX())) - vertXmin;
        double heightRatio3D=Math.max(vertexesOrg[0].getY(),Math.max(vertexesOrg[1].getY(),vertexesOrg[2].getY())) - vertYmin;
        double widthRatioTM=Math.max(texturevertices[0].getX(),Math.max(texturevertices[1].getX(),texturevertices[2].getX())) - textXmin;
        double heightRatioTM=Math.max(texturevertices[0].getY(),Math.max(texturevertices[1].getY(),texturevertices[2].getY())) - textYmin;


        int width;
        int height;

        if(vertXmin<=0){
            width= Math.abs((int) ((textXmin * material.getTexturemap().getWidth()) +
                    (((Math.abs(vertXmin) + hitpoint.getX()) * material.getTexturemap().getWidth() * widthRatioTM) / widthRatio3D)));
        }
        else{
            width= Math.abs((int) ((textXmin *material.getTexturemap().getWidth()) +
                    (((hitpoint.getX()-vertXmin) * material.getTexturemap().getWidth() * widthRatioTM) / widthRatio3D)));
        }

        if(vertYmin<=0){
            height= Math.abs((int) ((textYmin * material.getTexturemap().getHeight()) +
                    (((Math.abs(vertYmin) + hitpoint.getY()) * material.getTexturemap().getHeight() * heightRatioTM )/heightRatio3D)));

        }
        else{
            height= Math.abs((int) ((textYmin * material.getTexturemap().getHeight()) +
                    (((hitpoint.getY() - vertYmin) * material.getTexturemap().getHeight() * heightRatioTM) / heightRatio3D)));
        }


        //System.out.println(vertexes[0].getX()+" "+vertexes[1].getX()+" "+vertexes[2].getX() );
        System.out.println("texture loading " +width +" "+height);

        if (height<=0){
            height =0;
        }
        if (height>=solid.getMaterial().getTexturemap().getHeight()){
            height=solid.getMaterial().getTexturemap().getHeight()-1;
        }

        if (width<=0){
            width =0;
        }
        if (width>=solid.getMaterial().getTexturemap().getWidth()){
            width=solid.getMaterial().getTexturemap().getWidth()-1;
        }

        return solid.getMaterial().getTexturemap().getRGB(width, height);
    }



}