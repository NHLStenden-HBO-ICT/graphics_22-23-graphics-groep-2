package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;
import main.utils.Material;

public abstract class Solid implements Intersectable {

    private Material material;

    public Material getMaterial() {
        return null;
    }

    public void setMaterial(Material material) {

    }


    /**
     * @see Intersectable#intersects(Ray)
     */
    public RayHit intersects(Ray ray) {
        return null;
    }

}
