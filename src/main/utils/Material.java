package main.utils;

public class Material {

    private double reflectivity;

    private VectorColor vectorColor;

    private double metallic;

    private double matte;

    public Material(VectorColor vectorColor, double metallic, double matte) {
        this.vectorColor = vectorColor;
        this.metallic = metallic;
        this.matte = matte;
    }

    public VectorColor getColor() {
        return vectorColor;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public double getMettalic() {
        return metallic;
    }

    public double getMatte() {
        return matte;
    }

    public void setColor(VectorColor vectorColor) {
        this.vectorColor = vectorColor;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

    public void setMetallic(double metallic) {
        this.metallic = metallic;
    }

    public void setMatte(double matte) {
        this.matte = matte;
    }

}
