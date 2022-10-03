package main.utils;

public class Material {

    private double reflectivity;

    private Color color;

    public Color getColor() {
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
