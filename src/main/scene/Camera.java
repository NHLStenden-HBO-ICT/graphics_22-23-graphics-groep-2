package main.scene;

import main.maths.Ray;
import main.maths.Vector3;

//import java.geometry.Vector3;

public class Camera {

    private Vector3 position;

    private Vector3 direction;

//todo following 3 must begiving using the constructor
    double viewport_height = 2.0;
    double viewport_width = 2.0;
    double fieldOfView = 10;

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

    //returns a ray that uses the coords x and y to find the correct spot on the virtual image
    public Ray rayThroughPixel(int x, int y, int imageX, int imageY) {

        //
        double u =0;
        double v =0;

        double heightcalc = viewport_height/imageY;
        double widthcalc =viewport_width/imageX;

        if (x<200&&y<200){ //upper left
            u=-(x-200)*widthcalc;
            v=(y-200)*heightcalc;
        }else if (x<200&&y>=200){//bottom left
            u=-(x-200)*widthcalc;
            v=-(y-200)*heightcalc;
        } else if (x>=200&&y<200){//upper right
            u=(x-200)*widthcalc;
            v=(y-200)*heightcalc;
        }else if (x>=200&&y>=200) {//bottem right.//and midden
            u = (x - 200) * widthcalc;
            v = -(y - 200) * heightcalc;
        }


        //System.out.println("widthcalc " + widthcalc + " heightcalc " +heightcalc + " u " + u + " v " + v);

        return new Ray( new Vector3(u,v,fieldOfView),new Vector3(0,0,-1));
    }




}
