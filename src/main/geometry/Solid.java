package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;
import main.utils.Material;

public abstract class Solid implements Intersectable {

    private Material material;

    public Material getmaterial() {
        return null;
    }

    public void setmaterial(Material material) {

    }


    /**
     * @see Intersectable#intersects(Ray)
     */
    public RayHit intersects(Ray ray) {
        return null;
    }

}
