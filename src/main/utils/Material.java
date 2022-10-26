package main.utils;

import java.awt.image.BufferedImage;

public class Material {

    // Ior stands for "index of refraction"
    // This determines how much the object "refracts" incoming rays
    // More detailed explanation can of course be found online
    // Source used for this particular project:
    // https://www.scratchapixel.com/lessons/3d-basic-rendering/introduction-to-shading/reflection-refraction-fresnel

    // Common iors are:
    // Air = 1.0 (close to 1.0 in real life)
    // Water = 1.3
    // Glass = 1.5
    private double ior;

    // Note: This value can't be higher than 1 or lower than 0
    // This is accounted for in the constructor so entering a wrong value won't
    // break the program
    private double reflectivity;

    private BufferedImage texturmap;

    private VectorColor vectorColor;

    // If only a color is given default to a diffuse object
    // 0 reflectivity and 1:1 refractions
    public Material(VectorColor vectorColor) {

        this.vectorColor = vectorColor;
        this.reflectivity = 0;
        this.ior = 1;
    }

    // If reflectivity is also provided only set refraction to default value
    public Material(VectorColor vectorColor, double reflectivity) {

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

        this.ior = 1;
    }

    // Final overload constructor if ior is also provided
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

    public void setTexturmap(BufferedImage texturmap){
        this.texturmap=texturmap;
    }

    public BufferedImage getTexturmap() {
        return texturmap;
    }
    public void setIor(double ior) {
        this.ior = ior;
    }

}
