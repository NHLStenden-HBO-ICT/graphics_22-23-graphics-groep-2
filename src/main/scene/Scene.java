package main.scene;

import main.geometry.Solid;
import main.maths.Ray;
import main.maths.RayHit;

public class Scene {

	private Solid[] geometry;

	private PointLight[] lights;

	private Camera camera;

	private float skyEmmision;

	public Solid[] getgeometry() {
		return null;
	}

	public PointLight[] getlights() {
		return null;
	}

	public Camera getcamera() {
		return null;
	}

	public float getskyEmmision() {
		return 0;
	}

	public void AddSolid(Solid solid) {

	}

	public void ClearSolid() {

	}

	public void RemoveSolid(Solid solid) {

	}

	public void RemoveSolid(int index) {

	}

	public void Addlights(PointLight light) {

	}

	public void Clearlights() {

	}

	public void Removelights(PointLight light) {

	}

	public void RemoveLights(int index) {

	}

	public RayHit RayCast(Ray ray) {
		return null;
	}

}
