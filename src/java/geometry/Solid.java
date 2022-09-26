package java.geometry;

import java.maths.Ray;
import java.maths.RayHit;
import java.utils.Material;

public abstract class Solid implements Intersectable {

	private Material material;

	public Material getmaterial() {
		return null;
	}

	public void setmaterial(Material material) {

	}


	/**
	 * @see java.geometry.Intersectable#Intersects(Ray)
	 *  
	 */
	public RayHit Intersects(Ray ray) {
		return null;
	}

}
