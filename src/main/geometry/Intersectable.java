package main.geometry;

import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.ShadowRay;

public interface Intersectable {

    public abstract RayHit intersects(FullRay fullRay);

    public abstract boolean intersectsFast(ShadowRay shadowRay);


}
