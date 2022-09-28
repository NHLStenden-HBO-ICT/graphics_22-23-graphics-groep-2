package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;

public interface Intersectable {

    public abstract RayHit intersects(Ray ray);

}
