package main.scene;

import main.maths.Ray;
import main.maths.Vector3;

//import java.geometry.Vector3;

public class Camera {

    private Vector3 position;

    private Vector3 direction;

//todo following 3 must be given using the constructor
    double viewport_Height,viewport_Width,fieldOfView;
    private Vector3 origin = new Vector3();

    public Camera(double viewport_Height,  double viewport_Width, double fieldOfView ){
        this.viewport_Width=viewport_Width;
        this.viewport_Height=viewport_Height;
        this.fieldOfView=fieldOfView;
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
        Vector3 horizontal = new Vector3(viewport_Width, 0, 0);
        return horizontal;}

    public Vector3 getVertical(){
        Vector3 vertical = new Vector3(0, viewport_Height, 0);
        return vertical;}

/* the calculations for the four corners of the screen
    public Vector3 getLower_left_corner() {
        Vector3 lower_left_corner = origin.sub(getHorizontal().divide(2)).sub(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return lower_left_corner;
    }
    public Vector3 getLower_right_corner() {
        Vector3 lower_right_corner = origin.add(getHorizontal().divide(2)).sub(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return lower_right_corner;
    }
    public Vector3 getUpper_left_corner() {
        Vector3 upper_left_corner = origin.sub(getHorizontal().divide(2)).add(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return upper_left_corner;
    }
    public Vector3 getUpper_right_corner() {
        Vector3 upper_right_corner = origin.add(getHorizontal().divide(2)).add(getVertical().divide(2)).sub(new Vector3(0, 0, fieldOfView));
        return upper_right_corner;
    }
*/

    //returns a ray that uses the coörds x and y to find the correct spot on the virtual image
    public Ray rayThroughPixel(int x, int y, int imageX, int imageY) {

        //virtual camera coörds
        //u=x
        //v=y
        double u =0;
        double v =0;

        //calculates the ratio between x,y and u,v.
        //it uses the height and width of virtual camera divided by the height and width of the image that's going to be displayed.
        double heightRatio = viewport_Height/imageY;
        double widthRatio =viewport_Width/imageX;

        //if x <200 than it will be negative
        //y starts with - because y=1 starts in positive
        u=(x-200.0)*widthRatio;
        v=-(y-200.0)*heightRatio;

        //returns ray with the virtual camera coörds
        return new Ray(new Vector3(u,v,fieldOfView),new Vector3(0,0,-1));
    }




}
