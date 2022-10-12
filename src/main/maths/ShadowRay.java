package main.maths;

import main.geometry.Intersectable;

import java.util.ArrayList;

public class ShadowRay extends Ray {

    private Vector3 origin;

    private Vector3 direction;

    public ShadowRay(Vector3 direction, Vector3 origin) {
        super(direction, origin);
    }

    //some overloaded constructors to reduce boilerplate code
    public ShadowRay(double dirX, double dirY, double dirZ, double originX, double originY, double originZ) {
        super(dirX, dirY, dirZ, originX, originY, originZ);
    }


    //given a set of intersectables, returns if a  collision between this ray and any of the objects happen
    public boolean castRay(ArrayList<Intersectable> intersectables) {
        //todo rewrite this to make use of streams to filter the list and improve performance
        //create a variable to store the collision


        //loop over all the intersectables
        for (int i = 0; i < intersectables.size(); i++) {
            boolean hit = intersectables.get(i).intersectsFast(this);

            //if no collision has been found, save the found collision
            if (hit == true) {
                return true;
            }
        }
        return false;
    }

}
