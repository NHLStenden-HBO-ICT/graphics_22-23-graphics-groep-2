package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;
import main.maths.Vector3;
import main.utils.Material;

public class Sphere extends Solid implements Intersectable {

    private double radius;

    private double radiusSquared; //radius2 = radius * radius

    private Vector3 position;

    public Sphere(Material material, double radius, Vector3 position) {
        super(material);
        this.radius = radius;
        this.radiusSquared = radius * radius;
        this.position = position;

    }

    public double getradius() {
        return radius;
    }

    public Vector3 getposition() {
        return position;
    }

    public void setradius(double radius) {
        this.radius = radius;
    }

    public void setposition(Vector3 position) {
        this.position = position;
    }

    //this is an implementation of https://raytracing.github.io/books/RayTracingInOneWeekend.html#addingasphere/ray-sphereintersection
    @Override
    public RayHit Intersects(Ray ray) {

        Vector3 relativePosition = ray.getOrigin().Sub(position);
        double a = ray.getDirection().Normalise().Dot(ray.getDirection().Normalise());
        double b = ray.getDirection().Dot(relativePosition);
        double c = relativePosition.Dot(relativePosition) - radiusSquared;
        double discriminant = b * b - a * c;

        if (discriminant < 0.0) {
            return null; //no intersection
        }

        double distance = (-b - Math.sqrt(discriminant)) / a;
        return new RayHit(ray, this, distance);

    }
}
