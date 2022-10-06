package main.scene;

import main.maths.FullRay;
import main.maths.Vector3;

//import java.geometry.Vector3;

public class Camera {

    private Vector3 position;

    private Vector3 direction;

    //todo following 3 must be given using the constructor
    double viewportHeight, viewportWidth, fieldOfView, ratio;

    public Camera(Vector3 position, double viewportHeight, double fieldOfView, double ratio) {
        this.position = position;
        this.ratio = ratio;
        this.viewportHeight = viewportHeight;
        this.fieldOfView = fieldOfView;
        Double u = viewportHeight * ratio;
        this.viewportWidth = u.intValue();
    }

    public double getRatio() {
        return ratio;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getDirection() {
        return null;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public void setDirection(Vector3 vector3) {

    }

    public Vector3 getHorizontal() {
        Vector3 horizontal = new Vector3(viewportWidth, 0, 0);
        return horizontal;
    }

    public Vector3 getVertical() {
        Vector3 vertical = new Vector3(0, viewportHeight, 0);
        return vertical;
    }

/* the calculations for the four corners of the screen
    public Vector3 getLower_left_corner() {
        Vector3 lower_left_corner = origin.sub(getHorizontal().divide(2)).sub(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return lower_left_corner;
    }
    public Vector3 getLower_right_corner() {
        Vector3 lower_right_corner = origin.add(getHorizontal().divide(2)).sub(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return lower_right_corner;
    }
    public Vector3 getUpper_left_corner() {
        Vector3 upper_left_corner = origin.sub(getHorizontal().divide(2)).add(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return upper_left_corner;
    }
    public Vector3 getUpper_right_corner() {
        Vector3 upper_right_corner = origin.add(getHorizontal().divide(2)).add(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return upper_right_corner;
    }
*/

    //returns a ray that uses the x and y of an image and returns a ray that can be cast
    public FullRay getRayFromPixel(double x, double y) {
        //first we normalize the pixel coordinates
        x = ((x + 0.5) / viewportWidth);
        y = ((y + 0.5) / viewportHeight);

        double pixelScreenX = 1 - (2 * x);
        double pixelScreenY = 1 - (2 * y);

        double pixelCameraX = ((2 * pixelScreenX) - 1) * ratio * Math.tan(fieldOfView / 2);
        double pixelCameraY = (1 - (2 * pixelScreenY)) * Math.tan(fieldOfView / 2);

        //assumes the camera has a direction of (0, 0, -1)
        Vector3 pointInCameraSpace = new Vector3(pixelCameraX, pixelCameraY, -1);

        //next we need to rotate this point to match the camera's actual direction
        //first we need to calculate how much we need to rotate by


        //finally we need to transform this point by the camera's position
        pointInCameraSpace = pointInCameraSpace.add(position);


        return new FullRay(position, pointInCameraSpace);
    }


}
