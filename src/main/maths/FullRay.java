package main.maths;

import main.geometry.Intersectable;

public class FullRay extends Ray {

    private Vector3 origin;

    private Vector3 direction;

    public FullRay(Vector3 direction, Vector3 origin) {
        super(direction, origin);
    }

    //some overloaded constructors to reduce boilerplate code
    public FullRay(double dirX, double dirY, double dirZ, double originX, double originY, double originZ) {
        super(dirX, dirY, dirZ, originX, originY, originZ);
    }


    //given a set of intersectables, return collision between this ray and the closest intersectable
    //if no intersection takes place, return null
    public RayHit castRay(Intersectable[] intersectables) {
        //todo rewrite this to make use of streams to filter the list and improve performance
        //create a variable to store the collision
        RayHit closestHit = null;

        //loop over all the intersectables
        for (int i = 0; i < intersectables.length; i++) {
            RayHit hit = intersectables[i].intersects(this);

            //if no collision took place, go to the next cycle in the loop
            if (hit == null) {
                continue;
            }

            //if no collision has been found, save the found collision
            if (closestHit == null) {
                closestHit = hit;
                continue;
            }

            //if the existing collision is closer than the previously found one then save this collision
            if (closestHit.getDistance() > hit.getDistance()) {
                closestHit = hit;
            }
        }

        return closestHit;
    }

}
