package main.utils;

public class Material {

    private double reflectivity;

    private VectorColor vectorColor;

    private double diffuse;

    public Material(VectorColor vectorColor, double reflectivity, double diffuse) {
        this.vectorColor = vectorColor;
        this.reflectivity = reflectivity;
        this.diffuse = diffuse;
    }

    public VectorColor getColor() {
        return vectorColor;
    }

    public double getReflectivity() {
        return reflectivity;
    }
    public double getDiffuse() {
        return diffuse;
    }

    public void setColor(VectorColor vectorColor) {
        this.vectorColor = vectorColor;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = diffuse;
    }

}
