package main.utils;

import main.maths.Vector3;

import java.awt.*;

public class VectorColor {

    private Vector3 color;

    public VectorColor(Vector3 color) {
        this.color = color;
    }

    public Vector3 getVector() {
        return color;
    }

    public double getR() {
        return color.getX();
    }

    public double getG() {
        return color.getY();
    }

    public double getB() {
        return color.getZ();
    }

    public void setR(double value) {
        color.setX(value);
    }

    public void setG(double value) {
        color.setY(value);
    }

    public void setB(double value) {
        color.setZ(value);
    }

    public void setColor(Vector3 color) {
        this.color = color;
    }

    public VectorColor addVectorColor(VectorColor colorToAdd) {
        if (getR() + colorToAdd.getR() > Double.MAX_VALUE) {
            setR(Double.MAX_VALUE);
        }

        if (getG() + colorToAdd.getG() > Double.MAX_VALUE) {
            setG(Double.MAX_VALUE);
        }

        if (getB() + colorToAdd.getB() > Double.MAX_VALUE) {
            setB(Double.MAX_VALUE);
        }
        return new VectorColor(color.add(colorToAdd.getVector()));
    }

    public Color getJavaColor() {
        color.clamp(0, 255);
        return new Color((int) color.getX(), (int) color.getY(), (int) color.getZ());
    }

}