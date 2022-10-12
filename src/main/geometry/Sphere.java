package main.geometry;

import main.maths.*;
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

    public double getRadius() {
        return radius;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    //returns the surface normal for a given point
    public Vector3 getSurfaceNormal(Vector3 point) {
        return point.sub(position);
    }

    private double findDistance(Ray ray) {
        Vector3 relativePosition = position.sub(ray.getOrigin());

        //double a = fullRay.getDirection().length() * fullRay.getDirection().length();
        double a = relativePosition.dot(ray.getDirection());
        if (a < 0) {
            return -1; //sphere is behind the ray origin
        }

        double b = relativePosition.dot(relativePosition) - a * a;
        if (b > radiusSquared) {
            return -1; //ray is outside of sphere
        }
        double c = Math.sqrt(radiusSquared - b);
        //there are two possible intersections, let's calculate both
        double distance1 = a - c;
        double distance2 = a + c;

        //if distance 1 is bigger than distance 2, swap the two variables
        //this makes for easier logic because we can assume distance 1 to be the smaller of the two
        //we always want to return the closest intersection, i.e. the intersection with the lowest distance value
        if (distance1 > distance2) {
            double i = distance1;
            distance1 = distance2;
            distance2 = i;
        }

        //if distance 1 is less than 0, we can use distance 2 instead
        if (distance1 < 0) {
            distance1 = distance2;
        }
        //if both are less than 0 no intersection takes place
        if (distance1 < 0) {
            return -1;
        }

        return distance1;
    }

    // this is an implementation of https://raytracing.github.io/books/RayTracingInOneWeekend.html#addingasphere/ray-sphereintersection
    // It takes a Ray and returns a RayHit containing:
    // a reference to this object,
    // the ray that intersected it
    // and the distance along the ray where the intersection happened.
    @Override
    public RayHit intersects(FullRay fullRay) {
        double distance = findDistance(fullRay);
        if (distance == -1) {
            return null;
        }
        Vector3 hitPoint = fullRay.getPointAlongRay(distance);
        return new RayHit(fullRay, this, hitPoint, distance, getSurfaceNormal(hitPoint));
    }

    @Override
    public boolean intersectsFast(ShadowRay shadowRay) {
        double distance = findDistance(shadowRay);
        return distance != -1;
    }
}
