package main.scene;

import main.maths.Vector3;

//import java.geometry.Vector3;

public class Camera {

    private Vector3 position;

    private Vector3 direction;

//todo following 3 must begiving using the constructor
    double viewport_height = 2.0;
    double viewport_width = 2.0;
    double fieldOfView = 1.0;

    private Vector3 origin = new Vector3();
    //private Vector3 horizontal = new Vector3(viewport_width, 0, 0);
    //private Vector3 vertical = new Vector3(0, viewport_height, 0);
    //private Vector3 lower_left_corner = origin.sub(horizontal.divide(2).add(vertical.divide(2)).add(new Vector3(0, 0, fieldOfView)));



    public Camera(){

    }


    public Vector3 getPosition() {
        return null;
    }

    public Vector3 getDirection() {
        return null;
    }

    public void setPosition(Vector3 vector3) {

    }

    public void setDirection(Vector3 vector3) {

    }



    public Vector3 getOrigin(){return origin;}

    public Vector3 getHorizontal(){
        Vector3 horizontal = new Vector3(viewport_width, 0, 0);
        return horizontal;}

    public Vector3 getVertical(){
        Vector3 vertical = new Vector3(0, viewport_height, 0);
        return vertical;}

    public Vector3 getLower_left_corner() {
        Vector3 lower_left_corner = origin.sub(getHorizontal().divide(2)).sub(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return lower_left_corner;
    }
}
