package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;
import main.utils.Material;

public abstract class Solid {

    private Material material;

    public Solid(Material material) {
        this.material = material;
    }

    public Material getmaterial() {
        return material;
    }

    public void setmaterial(Material material) {
        this.material = material;
    }
}
