package main.maths;

import main.geometry.Solid;

public class RayHit {

    private Ray ray;

    private Solid hitSolid;
    private double distance;


    public RayHit(Ray ray, Solid hitSolid, double distance) {
        this.ray = ray;
        this.hitSolid = hitSolid;
        this.distance = distance;
    }

    public Ray getRay() {
        return ray;
    }

    public Solid gethitSolid() {
        return hitSolid;
    }

    public double getDistance() {
        return distance;
    }

    public Vector3 getNormal() {
        return null;
    }

}
