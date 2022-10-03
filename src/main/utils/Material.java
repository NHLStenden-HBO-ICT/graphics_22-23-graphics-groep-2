package main.utils;

import main.maths.Vector3;

public class Material {

    private double reflectivity;

    private Vector3 color;

    public Vector3 getColor() {
        return color;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

}
