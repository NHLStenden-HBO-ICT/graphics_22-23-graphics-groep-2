package main.geometry;

import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.ShadowRay;
import main.maths.Vector3;

public interface Intersectable {

    public abstract RayHit intersects(FullRay fullRay);

    public abstract boolean intersectsFast(ShadowRay shadowRay);

    public abstract Vector3 getSurfaceNormal(Vector3 point);


}
