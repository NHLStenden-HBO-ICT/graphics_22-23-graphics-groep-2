package main.scene;

import main.maths.FullRay;
import main.maths.Orientation;
import main.maths.Quaternion;
import main.maths.Vector3;

//import java.geometry.Vector3;

public class Camera {
    private Vector3 position;

    private Orientation orientation;

    private final Vector3 standardOrientation = new Vector3(0, 0, -1);

    private double imageHeight, imageWidth, fieldOfView, ratio;

    public Camera(Vector3 position, int imageHeight, int imageWidth, int fieldOfView) {
        this.position = position;
        this.ratio = (double) imageWidth / (double) imageHeight;
        this.imageHeight = imageHeight;
        this.fieldOfView = fieldOfView; //the field of view expressed in degrees
        this.imageWidth = imageWidth;
        this.orientation = new Orientation();
    }

    public Camera(Vector3 position, Vector3 dir, int imageHeight, int imageWidth, int fieldOfView) {
        this.position = position;
        //calculate the quaternion that represents a certain location
        this.orientation = new Orientation(dir);
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.fieldOfView = fieldOfView;
        this.ratio = (double) imageWidth / (double) imageHeight;
    }

    public double getRatio() {
        return ratio;
    }

    public Vector3 getPosition() {
        return position;
    }


    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    //returns a ray that uses the x and y of an image and returns a ray that can be cast
    //full explanation can be found at: https://www.scratchapixel.com/lessons/3d-basic-rendering/ray-tracing-generating-camera-rays/generating-camera-rays
    public FullRay getRayFromPixel(double x, double y) {
        //first we normalize the pixel coordinates
        //this makes it so that x & y become values between 0...1
        //so if your image width is 100, and x is 50 x becomes 0.5
        x = ((x + 0.5) / imageWidth);
        y = ((y + 0.5) / imageHeight);

        //here we translate the pixel from image space into camera space
        double pixelCameraX = ((2 * x) - 1) * ratio * Math.tan(Math.toRadians(fieldOfView) / 2);
        double pixelCameraY = (1 - (2 * y)) * Math.tan(Math.toRadians(fieldOfView) / 2);

        //constructs a new point from the calculated coordinates
        //assumes the camera has a direction of (0, 0, -1)
        Vector3 pointInCameraSpace = new Vector3(pixelCameraX, pixelCameraY, -1)

                //next we need to rotate this point to match the camera's actual direction
                .rotateByQuaternion(orientation.getQuaternion());

        //finally we need to transform this point by the camera's position to get the position of the ray
        return new FullRay(pointInCameraSpace, pointInCameraSpace.add(position));
    }


}
