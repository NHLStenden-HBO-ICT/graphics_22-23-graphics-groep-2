package main.maths;

import main.geometry.Solid;

public class RayHit {

    //the ray that collided
    private FullRay fullRay;
    //the solid it collided with
    private Solid hitSolid;
    //the point it made contact at
    private Vector3 contactPoint;
    //the distance to the contact point
    private double distance;

    //the surface normal of the object hit
    private Vector3 surfaceNormal;


    public RayHit(FullRay fullRay, Solid hitSolid, Vector3 contactPoint, double distance, Vector3 surfaceNormal) {
        this.fullRay = fullRay;
        this.hitSolid = hitSolid;
        this.contactPoint = contactPoint;
        this.distance = distance;
        this.surfaceNormal = surfaceNormal;
    }

    public FullRay getRay() {
        return fullRay;
    }

    public Solid getHitSolid() {
        return hitSolid;
    }

    public Vector3 getContactPoint() {
        return contactPoint;
    }

    public Vector3 getNormal() {
        return surfaceNormal;
    }

    public double getDistance() {
        return distance;
    }

}
