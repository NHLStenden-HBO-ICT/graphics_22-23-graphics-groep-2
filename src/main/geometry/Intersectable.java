package main.geometry;

import main.maths.FullRay;
import main.maths.RayHit;

public interface Intersectable {

    public abstract RayHit intersects(FullRay fullRay);

}
