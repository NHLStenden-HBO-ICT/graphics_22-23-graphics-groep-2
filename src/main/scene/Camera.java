package main.scene;

import main.maths.FullRay;
import main.maths.Vector3;

//import java.geometry.Vector3;

public class Camera {
    private Vector3 position, direction;

    double imageHeight, imageWidth, fieldOfView, ratio;

    public Camera(Vector3 position, int imageHeight, int imageWidth, int fieldOfView) {
        this.position = position;
        this.ratio = (double) imageWidth / (double) imageHeight;
        this.imageHeight = imageHeight;
        this.fieldOfView = fieldOfView; //the field of view expressed in degrees
        this.imageWidth = imageWidth;
    }

    public double getRatio() {
        return ratio;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getDirection() {
        return this.direction;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    //returns a ray that uses the x and y of an image and returns a ray that can be cast
    public FullRay getRayFromPixel(double x, double y) {
        //first we normalize the pixel coordinates
        x = ((x + 0.5) / imageWidth);
        y = ((y + 0.5) / imageHeight);

        double pixelScreenX = 1 - x;
        double pixelScreenY = 1 - y;

        double pixelCameraX = ((2 * pixelScreenX) - 1) * ratio * Math.tan(Math.toRadians(fieldOfView) / 2);
        double pixelCameraY = (1 - (2 * pixelScreenY)) * Math.tan(Math.toRadians(fieldOfView) / 2);

        //assumes the camera has a direction of (0, 0, -1)
        Vector3 pointInCameraSpace = new Vector3(pixelCameraX, pixelCameraY, -1);

        //next we need to rotate this point to match the camera's actual direction
        //todo make that happen using quaternions or something

        //finally we need to transform this point by the camera's position
        Vector3 PositionInSpace = pointInCameraSpace.add(position);


        return new FullRay(pointInCameraSpace, PositionInSpace);
    }


}
