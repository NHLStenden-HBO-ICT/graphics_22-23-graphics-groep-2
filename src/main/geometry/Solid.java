package main.geometry;

import main.utils.Material;

public abstract class Solid implements Intersectable {

    private Material material;

    public Solid(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
