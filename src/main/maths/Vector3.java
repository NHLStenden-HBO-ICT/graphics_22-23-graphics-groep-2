package main.maths;

import java.lang.Math;

public class Vector3 {

    //variables
    private double x;
    private double y;
    private double z;

    //empty constructor. all variables are set to 0
    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    //constructor. all variables are set to the parameters
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //get methods
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    //Set methodes
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }

    //add method. adds this vector with the parameter vector
    public Vector3 add(Vector3 vector3) {
        return new Vector3(
                this.x + vector3.x,
                this.y + vector3.y,
                this.z + vector3.z
        );
    }

    //sub method. subtracts this vector with parameter vector
    public Vector3 sub(Vector3 vector3) {
        return new Vector3(
                this.x - vector3.x,
                this.y - vector3.y,
                this.z - vector3.z
        );
    }

    //Multi method. multiplies this vector with parameter of double
    public Vector3 multi(double multiply) {
        return new Vector3(
                this.x * multiply,
                this.y * multiply,
                this.z * multiply
        );
    }

    //part method. divides the vector with the division parameter
    public Vector3 divide(double division) {
        return new Vector3(
                this.x / division,
                this.y / division,
                this.z / division
        );
    }

    //Dot method. multiplies this vector times the parameter vector where the dot product is return
    public double dot(Vector3 vectorA) {
        return vectorA.x * x + vectorA.y * y + vectorA.z * z;
    }

    //Normalise vector, creates a normal vector from this vector
    public Vector3 normalise() {
        double magnitude = length();
        return new Vector3(
                x / magnitude,
                y / magnitude,
                z / magnitude
        );
    }

    //returns the cross product of this vector and a given vector
    public Vector3 cross(Vector3 vector3) {
        return new Vector3(
                (this.y * vector3.z) - (this.z * vector3.y),
                (this.z * vector3.x) - (this.x * vector3.z),
                (this.x * vector3.y) - (this.y * vector3.x)
        );
    }

    //calculates the length of this vector
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Calculate distance between this vector and a given vector
    public double distance(Vector3 destination) {
        return Math.sqrt(Math.pow((x - destination.getX()), 2) + Math.pow((y - destination.getY()), 2) + Math.pow((z - destination.getZ()), 2));
    }

    // Check if the coordinates of the vector are above the max or below the minimum
    // If they are set them to either the max or the min
    public Vector3 clamp(double min, double max) {
        Vector3 vec = new Vector3(x, y, z);
        //Check if they are above or below the minimum
        if (x > max) {
            vec.setX(max);
        }

        if (x < min) {
            vec.setX(min);
        }

        if (y > max) {
            vec.setY(max);
        }

        if (y < min) {
            vec.setY(min);
        }

        if (z > max) {
            vec.setZ(max);
        }

        if (z < min) {
            vec.setZ(min);
        }

        return vec;
    }

    //converts a direction unit vector to a quaternion given a rotation angle in radians
    //this assumes the vector is normalized
    //it will not provide the expected result if it isn't
    //explanation of why this works this way can be found at: https://www.euclideanspace.com/maths/algebra/realNormedAlgebra/quaternions/notations/scalarAndVector/index.htm
    //and here: https://en.wikipedia.org/wiki/Quaternions_and_spatial_rotation#Example_conjugation_operation
    public Quaternion unitVectorToQuaternion(double angleInRadians) {
        return new Quaternion(this.multi(Math.sin(angleInRadians / 2)), Math.cos(angleInRadians / 2));
    }


    //rotates point by a quaternion
    public Vector3 rotateByQuaternion(Quaternion q) {
        Vector3 p = new Vector3();

        // this is the full expanded formula
        // this is a combination of a half left rotation and then a negative half right rotation
        // this ensures that the w of the quaternion is 0, otherwise our point would lie in a 4th dimension
        // if it's 0 however, we can just ignore it
        // full explanation can be found here: https://www.euclideanspace.com/maths/algebra/realNormedAlgebra/quaternions/transforms/derivations/vectors/index.htm
        // TODO replace fully expanded formula by one containing abstraction to improve readability
        // I'm probably not going to, though.
        p.setX(x * (q.getX() * q.getX() + q.getRotation() * q.getRotation() - q.getY() * q.getY() - q.getZ() * q.getZ()) + y * (2 * q.getX() * q.getY() - 2 * q.getRotation() * q.getZ()) + z * (2 * q.getX() * q.getZ() + 2 * q.getRotation() * q.getY()));
        p.setY(x * (2 * q.getRotation() * q.getZ() + 2 * q.getX() * q.getY()) + y * (q.getRotation() * q.getRotation() - q.getX() * q.getX() + q.getY() * q.getY() - q.getZ() * q.getZ()) + z * (-2 * q.getRotation() * q.getX() + 2 * q.getY() * q.getZ()));
        p.setZ(x * (-2 * q.getRotation() * q.getY() + 2 * q.getX() * q.getZ()) + y * (2 * q.getRotation() * q.getX() + 2 * q.getY() * q.getZ()) + z * (q.getRotation() * q.getRotation() - q.getX() * q.getX() - q.getY() * q.getY() + q.getZ() * q.getZ()));

        return p;
    }

    @Override
    public String toString() {
        return "(x: " + x + ", y: " + y + ", z: " + z + ")";
    }

}
