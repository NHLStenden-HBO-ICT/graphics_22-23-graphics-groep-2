package main.scene;

import main.maths.Ray;
import main.maths.Vector3;

//import java.geometry.Vector3;

public class Camera {

    private Vector3 position;

    private Vector3 direction;

//todo following 3 must be given using the constructor
    double viewportHeight,viewportWidth,fieldOfView,ratio;
    private Vector3 origin = new Vector3();

    public Camera(double viewportHeight, double fieldOfView, double ratio ){
        this.ratio=ratio;
        this.viewportHeight=viewportHeight;
        this.fieldOfView=fieldOfView;
        Double u = viewportHeight *ratio;
        this.viewportWidth=u.intValue();
    }

    public double getRatio(){return ratio;}

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
        Vector3 horizontal = new Vector3(viewportWidth, 0, 0);
        return horizontal;}

    public Vector3 getVertical(){
        Vector3 vertical = new Vector3(0, viewportHeight, 0);
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
        double heightRatio = viewportHeight/imageY;
        double widthRatio =viewportWidth/imageX;

        //if x <200 than it will be negative
        //y starts with - because y=1 starts in positive
        u=(x-(imageX/2))*widthRatio;
        v=-(y-(imageY/2))*heightRatio;

        //returns ray with the virtual camera coörds
        return new Ray(new Vector3(u,v,fieldOfView),new Vector3(0,0,-1));
    }




}
