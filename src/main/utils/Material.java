package main.utils;

public class Material {

    private double ior;

    // Note: This value can't be higher than 1 or lower than 0
    // This is accounted for in the constructor so entering a wrong value won't
    // break the program
    private double reflectivity;

    private VectorColor vectorColor;

    public Material(VectorColor vectorColor, double reflectivity, double ior) {

        this.vectorColor = vectorColor;

        // Check if reflectivity value is above or below the max or min value
        if (reflectivity > 1.0) {
            this.reflectivity = 1.0;
        } else {
            this.reflectivity = reflectivity;
        }

        if (reflectivity < 0.0) {
            this.reflectivity = 0.0;
        } else {
            this.reflectivity = reflectivity;
        }

        this.ior = ior;
    }

    public VectorColor getColor() {
        return vectorColor;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public double getIor() {
        return ior;
    }

    public void setColor(VectorColor vectorColor) {
        this.vectorColor = vectorColor;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

    public void setIor(double ior) {
        this.ior = ior;
    }

}
