package main.maths;

public class Ray {

	private Vector3 origin;

	private Vector3 direction;

	public  Ray(Vector3 direction, Vector3 origin)
	{
		this.direction = direction;
		this.origin = origin;
	}

	public Vector3 getOrigin() {
		return origin;
	}

	public Vector3 getDirection() {
		return direction;
	}

}
