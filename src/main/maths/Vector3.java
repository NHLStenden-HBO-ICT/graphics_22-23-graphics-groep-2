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

    //add method. adds this vector with the paremeter vector
    public Vector3 add(Vector3 vector3) {
        this.x += vector3.x;
        this.z += vector3.z;
        this.y += vector3.y;
        return this;
    }

    //sub method. subtracts this vector with parameter vector
    public Vector3 sub(Vector3 vector3) {
        this.x -= vector3.x;
        this.z -= vector3.z;
        this.y -= vector3.y;
        return this;
    }

    //Multi method. multiplies this vector with parameter of double
    public Vector3 multi(double multiply) {
        this.x = this.x * multiply;
        this.z = this.z * multiply;
        this.y = this.y * multiply;
        return this;
    }

    //Dot method. multiplies this vector times the parameter vector where the dot product is return
    public double dot(Vector3 vectorA) {
        return vectorA.x * x + vectorA.y * y + vectorA.z * z;
    }

    //Normalise vector, creates a normale vector of this vector
    public Vector3 normalise() {
        double magnitude = length();
        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
        return this;
    }

    //calculates the length of this vector
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

}
