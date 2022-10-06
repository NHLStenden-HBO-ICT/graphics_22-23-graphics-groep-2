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
                this.x /division,
                this.y /division,
                this.z /division
        );
    }

    //Dot method. multiplies this vector times the parameter vector where the dot product is return
    public double dot(Vector3 vectorA) {
        return vectorA.x * x + vectorA.y * y + vectorA.z * z;
    }

    //Normalise vector, creates a normale vector of this vector
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
    public double distance(Vector3 destination){
        return Math.sqrt(Math.pow((x - destination.getX()), 2) + Math.pow((y - destination.getY()), 2) + Math.pow((z - destination.getZ()), 2));
    }

    // Check if the coordinates of the vector are above the max or below the minimum
    // If they are set them to either the max or the min
    public void clamp(double min, double max){

        //Check if they are above or below the minimum
        if (x > max){
            x = max;
        }

        if (x < min){
            x = min;
        }

        if (y > max){
            y = max;
        }

        if (y < min){
            y = min;
        }

        if (z > max){
            z = max;
        }

        if (z < min){
            z = min;
        }
    }

    @Override
    public String toString() {
        return "(x: " + x + ", y: " + y + ", z: " + z + ")";
    }

}
