package main.scene;

import main.maths.Vector3;
import main.utils.VectorColor;

//import java.geometry.Vector3;

public class PointLight {

    private Vector3 position;

    private VectorColor vectorColor;

    private double intensity;

    //constructor for light source at origin
    public PointLight(VectorColor vectorColor, double intensity) {
        this.vectorColor = vectorColor;
        this.intensity = intensity;
        this.position = new Vector3(0, 0, 0);
    }

    //constructor
    public PointLight(VectorColor vectorColor, double intensity, Vector3 position) {
        this.vectorColor = vectorColor;
        this.intensity = intensity;
        this.position = position;
    }

    //get methods
    public Vector3 getPosition() {
        return position;
    }

    public VectorColor getColor() {
        return vectorColor;
    }

    public double getIntensity() {
        return intensity;
    }

    //set methods
    public void setPosition(Vector3 vector3) {
        this.position = vector3;
    }

    public void setColor(VectorColor vectorColor) {
        this.vectorColor = vectorColor;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

}
