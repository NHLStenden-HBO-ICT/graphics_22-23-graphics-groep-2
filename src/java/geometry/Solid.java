package java.geometry;

import java.utils.Material;
import java.math.RayHit;
import java.math.Ray;

import java.scene.Material;

public abstract class Solid implements Intersectable {

	private Material material;

	public Material getmaterial() {
		return null;
	}

	public void setmaterial(Material material) {

	}


	/**
	 * @see java.geometry.Intersectable#Intersects(java.math.Ray)
	 *  
	 */
	public RayHit Intersects(Ray ray) {
		return null;
	}

}
