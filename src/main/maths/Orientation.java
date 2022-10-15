package main.maths;

public class Orientation {
    public static final Vector3 standardOrientation = new Vector3(0, 0, -1);

    private Quaternion quaternion;

    //creates a new orientation facing towards the standard orientation (0, 0, -1)
    public Orientation() {
        //a quaternion representing no orientation
        this.quaternion = new Quaternion(1, 0, 0, 0);
    }

    // constructs a new orientation from a looking at vector
    // this vector should be relative to the object
    public Orientation(Vector3 vector) {
        this.quaternion = new Quaternion().quaternionRepresentingRotationBetween(standardOrientation, vector.normalise());
    }

    //constructs an orientation from a quaternion.
    //you have to be sure the quaternion is calculated correctly
    //if your quaternion does not have a length of 1 shit will break!
    public Orientation(Quaternion quaternion) {
        this.quaternion = quaternion;
    }

    public Quaternion getQuaternion() {
        return quaternion;
    }

    public void setQuaternion(Quaternion quaternion) {
        this.quaternion = quaternion;
    }

    //takes a point, and your position and then sets this orientation so that you're looking at the given point
    public void LookAt(Vector3 position, Vector3 point) {
        Vector3 relativePoint = point.sub(position).normalise();

        quaternion = new Quaternion().quaternionRepresentingRotationBetween(standardOrientation, relativePoint);
    }

    public Vector3 getLookingAt() {
        return standardOrientation.rotateByQuaternion(quaternion);
    }
}
