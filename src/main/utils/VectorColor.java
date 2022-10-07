package main.utils;

import main.maths.Vector3;

import java.awt.*;

public class VectorColor {

    private Vector3 color;

    public VectorColor(Vector3 color) {
        this.color = color;
    }

    public Vector3 getColor() {
        return color;
    }

    public void setColor(Vector3 color) {
        this.color = color;
    }

    public Color getJavaColor() {
        return new Color((int) color.getX(), (int) color.getY(), (int) color.getZ());
    }

}