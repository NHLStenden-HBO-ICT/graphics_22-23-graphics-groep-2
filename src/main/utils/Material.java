package main.utils;

public class Material {

    private double reflectivity;

    private VectorColor vectorColor;

    public Material(VectorColor vectorColor, double reflectivity) {
        this.vectorColor = vectorColor;
        this.reflectivity = reflectivity;
    }

    public VectorColor getColor() {
        return vectorColor;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public void setColor(VectorColor vectorColor) {
        this.vectorColor = vectorColor;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

}
