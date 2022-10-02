package test.scene;

import main.maths.Vector3;

public class CameraTest {

    private int screenWidth = 500;
    private int screenHeight = 500;

    private Vector3 position = new Vector3(0,0,0);

    private Vector3 direction = new Vector3(1,0,0);

    private int fieldOfView = 70;
    
    private Vector3 center;
    private Vector3 topLeft,topRight,botLeft;

    // Return camera position
    public Vector3 getPosition() {
        return position;
    }

    // Return camera look direction
    public Vector3 getDirection() {
        return direction;
    }

    // Return the field of view of the camera
    public int getFOV() {
        return fieldOfView;
    }

    // Set camera position
    public void setPosition(Vector3 vector3_) {
        position = vector3_;
    }

    // Set camera look direction
    public void setDirection(Vector3 vector3_) {
        direction = vector3_;
    }

    // Set the field of view of the camera
    public void setFOV(int fov) {
        fieldOfView = fov;
    }

    // Calculate the center of the screen
    public void getCenter() {
        center = position.add(direction.multi(fieldOfView));
    }

    // Calculate the corners of the screen
    public void calculateCorners() {
        topLeft = center.add(new Vector3(-1,1,0));
        topRight = center.add(new Vector3(1,1,0));
        botLeft = center.add(new Vector3(-1,-1,0));
    }

    // Calculate a coordinate on the screen using (NOT DONE YET)
    public void calculatePoint(double x, double y) {

        double u = x / screenWidth;
        double v = y / screenHeight;

    }
}
