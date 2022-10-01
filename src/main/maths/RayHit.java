package main.maths;

import main.geometry.Solid;

public class RayHit {

    private Ray ray;

    private Solid hitSolid;
    private Vector3 contactPoint;


    public RayHit(Ray ray, Solid hitSolid, Vector3 contactPoint) {
        this.ray = ray;
        this.hitSolid = hitSolid;
        this.contactPoint = contactPoint;
    }

    public Ray getRay() {
        return ray;
    }

    public Solid getHitSolid() {
        return hitSolid;
    }

    public Vector3 getContactPoint() {
        return contactPoint;
    }

    public Vector3 getNormal() {
        return null;
    }

}
