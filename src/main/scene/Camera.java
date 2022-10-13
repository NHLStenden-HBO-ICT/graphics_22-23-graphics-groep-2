package main.scene;

import main.maths.FullRay;
import main.maths.Quaternion;
import main.maths.Vector3;

//import java.geometry.Vector3;

public class Camera {
    private Vector3 position;

    private Quaternion orientation;

    private final Vector3 standardOrientation = new Vector3(0, 0, -1);

    private double imageHeight, imageWidth, fieldOfView, ratio;

    public Camera(Vector3 position, int imageHeight, int imageWidth, int fieldOfView) {
        this.position = position;
        this.ratio = (double) imageWidth / (double) imageHeight;
        this.imageHeight = imageHeight;
        this.fieldOfView = fieldOfView; //the field of view expressed in degrees
        this.imageWidth = imageWidth;
        //calculate the quaternion that represents no rotation
        this.orientation = new Quaternion().quaternionRepresentingRotationBetween(standardOrientation, new Vector3(0, 0, -1));
    }

    public Camera(Vector3 position, Vector3 lookingAt, int imageHeight, int imageWidth, int fieldOfView) {
        this.position = position;
        //calculate the quaternion that represents a certain location
        this.orientation = new Quaternion().quaternionRepresentingRotationBetween(standardOrientation, lookingAt.normalise());
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.fieldOfView = fieldOfView;
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

    public Quaternion getOrientation() {
        return orientation;
    }

    public void setOrientation(Quaternion orientation) {
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

        //assumes the camera has a direction of (0, 0, -1)
        Vector3 pointInCameraSpace = new Vector3(pixelCameraX, pixelCameraY, -1);

        //next we need to rotate this point to match the camera's actual direction
        Vector3 rotatedPointInCameraSpace = pointInCameraSpace.normalise().rotateByQuaternion(orientation);

        //finally we need to transform this point by the camera's position
        Vector3 PositionInSpace = rotatedPointInCameraSpace.add(position);

        return new FullRay(rotatedPointInCameraSpace, PositionInSpace);
    }


}
