package test.scene;

public class CameraTest {

    private Vector3 position;

    private Vector3 direction;

    private int fieldOfView;

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

}
