package main.utils;

public class Material {

    private double reflectivity;

    private Color color;

    private double metallic;

    private double matte;

    public Color getColor() {
        return color;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public double getMettalic(){
        return metallic;
    }

    public double getMatte(){
        return matte;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

    public void setMetallic(double metallic){
        this.metallic = metallic;
    }

    public void setMatte(double matte){
        this.matte = matte;
    }

}
