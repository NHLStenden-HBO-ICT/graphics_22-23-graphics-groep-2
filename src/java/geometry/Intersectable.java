package java.geometry;

import java.math.RayHit;
import java.math.Ray;

public interface Intersectable {

	public abstract RayHit Intersects(Ray ray);

}
