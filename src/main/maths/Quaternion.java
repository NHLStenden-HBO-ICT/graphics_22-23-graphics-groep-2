package main.maths;

import static main.maths.Constants.EPSILON;

public class Quaternion {
    // using the (vector, scalar) representation of quaternions
    // for more info on this representation of quaternions see:
    // https://www.euclideanspace.com/maths/algebra/realNormedAlgebra/quaternions/notations/scalarAndVector/index.htm

    private Vector3 xyz;
    private double rotation;

    public Quaternion(double rotation, double x, double y, double z) {
        this.xyz = new Vector3(x, y, z);
        this.rotation = rotation;
    }

    public Quaternion(Vector3 vector, double rotation) {
        this.xyz = vector;
        this.rotation = rotation;
    }

    public Quaternion() {
        this.xyz = new Vector3();
        this.rotation = 0.0;
    }

    public double getX() {
        return xyz.getX();
    }

    public double getY() {
        return xyz.getY();
    }

    public double getZ() {
        return xyz.getZ();
    }

    public double getRotation() {
        return rotation;
    }

    public Vector3 getVectorComponent() {
        return xyz;
    }

    //returns a multiplication of this quaternion by the given quaternion
    //using this formula: https://www.mathworks.com/help/aerotbx/ug/quatmultiply.html?w.mathworks.com
    public Quaternion multi(Quaternion r) {
        return new Quaternion(
                r.getRotation() * rotation - r.getX() * getX() - r.getY() * getY() - r.getZ() * getZ(),
                r.getRotation() * getX() + r.getX() * rotation - r.getY() * getZ() + r.getZ() * getY(),
                r.getRotation() * getY() + r.getX() * getZ() + r.getY() * rotation - r.getZ() * getX(),
                r.getRotation() * getZ() - r.getX() * getY() + r.getY() * getX() + r.getZ() * rotation
        );
    }

    //returns the length of a quaternion
    public double length() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ() + rotation * rotation);
    }

    //normalizes a given quaternion
    public Quaternion normalize() {
        double magnitude = length();
        return new Quaternion(xyz.divide(magnitude), rotation / magnitude);
    }

    //returns the conjugate of a given quaternion
    public Quaternion conjugate() {
        return new Quaternion(xyz.multi(-1), rotation);
    }

    //given two vectors, finds the quaternion representing a rotation from vector a to vector b
    //there are infinite ways to rotate from one vector to another, so this gives a possible shortest rotation
    public Quaternion quaternionRepresentingRotationBetween(Vector3 a, Vector3 b) {
        //check if the vectors are parallel because math doesn't work in that case
        //if the vectors are parallel return an identity quaternion
        //this quaternion represents no rotation, just exactly the
        double d = a.dot(b);

        if (d > 1 - EPSILON) {
            return new Quaternion(1, 0, 0, 0);
        }
        //check if vectors are opposite because it also doesn't work in that case
        if (d < -1 + EPSILON) {
            return new Quaternion(0, 0, 1, 0); //represents a 180 degree rotation
        }

        Vector3 c = a.cross(b);
        double w = Math.sqrt(Math.pow(a.length(), 2) * Math.pow(b.length(), 2) + a.dot(b));
        return new Quaternion(c, w).normalize();
    }

    //fraction should be a number between 0 & 1
    //with a fraction of 0 it returns a quaternion that's equal to this quaternion
    //with a fraction of 1 it returns a quaternion that's equal to the given quaternion
    //with a fraction of 0.5 it returns a quaternion halfway between this quaternion and the given quaternion
    //finds a quaternion that's a fraction (amount) between the current
    public Quaternion interpolate(Quaternion target, double fraction) {
        return new Quaternion(
                rotation + fraction * (target.getRotation() - rotation),
                getX() + fraction * (target.getX() - getX()),
                getY() + fraction * (target.getY() - getY()),
                getZ() + fraction * (target.getZ() - getZ()));
    }
}
