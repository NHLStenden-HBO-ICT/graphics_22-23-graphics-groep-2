package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;
import main.maths.Vector3;
import main.utils.Material;

public class Sphere extends Solid implements Intersectable {

    private double radius;

    private final double radiusSquared; //radius2 = radius * radius

    private Vector3 position;

    public Sphere(Material material, double radius, Vector3 position) {
        super(material);
        this.radius = radius;
        this.radiusSquared = radius * radius;
        this.position = position;
    }

    public double GetRadius() {
        return radius;
    }

    public Vector3 GetPosition() {
        return position;
    }

    public void SetRadius(double radius) {
        this.radius = radius;
    }

    public void SetPosition(Vector3 position) {
        this.position = position;
    }

    // this is an implementation of https://raytracing.github.io/books/RayTracingInOneWeekend.html#addingasphere/ray-sphereintersection
    // It takes a Ray and returns a RayHit containing:
    // a reference to this object,
    // the ray that intersected it
    // and the distance along the ray where the intersection happened.
    @Override
    public RayHit Intersects(Ray ray) {

        Vector3 relativePosition = ray.getOrigin().Sub(position);

        double a = ray.getDirection().Dot(ray.getDirection());
        double b = ray.getDirection().Dot(relativePosition);
        double c = relativePosition.Dot(relativePosition) - radiusSquared;
        double discriminant = b * b - a * c;

        if (discriminant < 0.0) {
            return null; //no intersection
        }

        //calculate the distance
        double distance = (-b - Math.sqrt(discriminant)) / a;
        return new RayHit(ray, this, distance);
    }


}
