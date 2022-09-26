package java.geometry;

import java.maths.RayHit;
import java.maths.Ray;

public interface Intersectable {

	public abstract RayHit Intersects(Ray ray);

}
