package main.geometry;

import main.maths.FullRay;
import main.maths.RayHit;
import main.maths.ShadowRay;
import main.maths.Vector3;
import main.utils.Material;

public class Triangle extends Solid implements Intersectable {

    private Vector3[] vertices;
    private final double EPSILON = 0.0000001; //todo: make this a global constant somewhere

    public Triangle(Material material, Vector3 point1, Vector3 point2, Vector3 point3) {
        super(material);
        this.vertices = new Vector3[]{
                point1,
                point2,
                point3,
        };
    }

    public Vector3 getVertex(int index) {
        return vertices[index];
    }

    public Vector3[] getVertices() {
        return vertices;
    }

    public void setVertex(int index, Vector3 vertex) {
        this.vertices[index] = vertex;
    }

    //this method would normally allow us to change the length of the Triangle.Vertices array
    //to prevent this we need to ensure the correct length of the provided array
    public void setVertices(Vector3[] vertices) {
        if (vertices.length != 3) {
            throw new RuntimeException(
                    "A triangle can only have 3 vertices but " + vertices.length + " were provided");
        }

        this.vertices = vertices;
    }

    //going to try and make this happen
    // https://en.wikipedia.org/wiki/M%C3%B6ller%E2%80%93Trumbore_intersection_algorithm
    @Override
    public RayHit intersects(FullRay fullRay) {

        //get all the vertices of the triangle for easier referencing
        Vector3 vertex0 = this.getVertex(0);
        Vector3 vertex1 = this.getVertex(1);
        Vector3 vertex2 = this.getVertex(2);

        //declare all the variables we'll need to calculate
        Vector3 edge1, edge2, h, s, q;

        double a, f, u, v;

        //
        edge1 = vertex1.sub(vertex0);
        edge2 = vertex2.sub(vertex0);

        h = fullRay.getDirection().cross(edge2);
        a = edge1.dot(h);

        //a describes the angle between the ray direction and the triangle direction
        //if the angle is sufficiently small then the ray is parallel to the triangle
        //we don't  intersect with a plane that's parallel
        if (a > -EPSILON && a < EPSILON) {
            return null;
        }

        f = 1.0 / a;
        s = fullRay.getOrigin().sub(vertex0);
        u = f * (s.dot(h));

        //in case of an intersection u & v should be between 0 and 1.
        //if this is not the case no intersection happens, and we can eject from the algorithm early.
        if (u < 0.0 || u > 1.0) {
            return null;
        }

        q = s.cross(edge1);
        v = f * fullRay.getDirection().dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return null;
        }

        //at this stage we know a line intersection happens.
        //let's calculate the distance
        double distance = f * edge2.dot(q);

        //if the distance is negative the intersection doesn't lay on the ray.
        //it instead lies on the same line as the ray but before the origin.
        if (distance < EPSILON) {
            return null; //line intersection happens, but no ray intersection
        }

        //all conditions where a ray intersection doesn't happen have been ruled out
        //a ray intersection happens at the distance we've calculated
        //we can now return a RayHit object because a collision does happen.
        return new RayHit(fullRay, this, fullRay.getPointAlongRay(distance), distance);
    }

    @Override
    public boolean intersectsFast(ShadowRay shadowRay) {
        //get all the vertices of the triangle for easier referencing
        Vector3 vertex0 = this.getVertex(0);
        Vector3 vertex1 = this.getVertex(1);
        Vector3 vertex2 = this.getVertex(2);

        //declare all the variables we'll need to calculate
        Vector3 edge1, edge2, h, s, q;

        double a, f, u, v;

        //
        edge1 = vertex1.sub(vertex0);
        edge2 = vertex2.sub(vertex0);

        h = shadowRay.getDirection().cross(edge2);
        a = edge1.dot(h);

        //a describes the angle between the ray direction and the triangle direction
        //if the angle is sufficiently small then the ray is parallel to the triangle
        //we don't  intersect with a plane that's parallel
        if (a > -EPSILON && a < EPSILON) {
            return false;
        }

        f = 1.0 / a;
        s = shadowRay.getOrigin().sub(vertex0);
        u = f * (s.dot(h));

        //in case of an intersection u & v should be between 0 and 1.
        //if this is not the case no intersection happens, and we can eject from the algorithm early.
        if (u < 0.0 || u > 1.0) {
            return false;
        }

        q = s.cross(edge1);
        v = f * shadowRay.getDirection().dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return false;
        }

        //at this stage we know a line intersection happens.
        //let's calculate the distance
        double distance = f * edge2.dot(q);

        //if the distance is negative the intersection doesn't lay on the ray.
        //it instead lies on the same line as the ray but before the origin.
        return (distance > EPSILON);
    }
}
