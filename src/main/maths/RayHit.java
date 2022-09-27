package main.maths;

import main.geometry.Solid;

public class RayHit {

    private Ray ray;

    private Solid hitSolid;

    private Vector3 hitPosition;

    public RayHit(Ray ray, Solid hitSolid, Vector3 hitPosition) {
        this.ray = ray;
        this.hitPosition = hitPosition;
        this.hitSolid = hitSolid;
    }

    public Ray getRay() {
        return ray;
    }

    public Solid gethitSolid() {
        return hitSolid;
    }

    public Vector3 gethitPosition() {
        return hitPosition;
    }

    public Vector3 getNormal() {
        return null;
    }

}
