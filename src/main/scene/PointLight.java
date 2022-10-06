package main.scene;

import main.maths.Vector3;
import main.utils.Color;

//import java.geometry.Vector3;

public class PointLight {

    private Vector3 position;

    private Color color;

    private float intensity;

    //constructor for light source at origin
    public PointLight(Color color, float intensity){
        this.color = color;
        this.intensity = intensity;
        this.position = new Vector3(0,0,0);
    }

    //constructor
    public PointLight(Color color, float intensity, Vector3 position){
        this.color = color;
        this.intensity = intensity;
        this.position = position;
    }

    //get methods
    public Vector3 getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public float getIntensity() {
        return intensity;
    }

    //set methods
    public void setPosition(Vector3 vector3) {
    this.position = vector3;
    }

    public void setColor(Color color) {
        this.color = color;

    }

    public void setIntensity(float intensity)
    {
        this.intensity = intensity;
    }

}
