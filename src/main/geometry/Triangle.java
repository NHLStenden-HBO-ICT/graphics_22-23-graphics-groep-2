package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;
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
    public RayHit intersects(Ray ray) {
        Vector3 vertex0 = this.getVertex(0);
        Vector3 vertex1 = this.getVertex(1);
        Vector3 vertex2 = this.getVertex(2);

        Vector3 edge1, edge2, h, s, q;

        double a, f, u, v;


        edge1 = vertex1.sub(vertex0);
        edge2 = vertex2.sub(vertex0);

        h = ray.getDirection().cross(edge2);
        a = edge1.dot(h);

        if (a > -EPSILON && a < EPSILON) {
            return null; //ray is parallel to this triangle
        }

        f = 1.0 / a;
        s = ray.getOrigin().sub(vertex0);
        u = f * (s.dot(h));

        if (u < 0.0 || u > 1.0) {
            return null;
        }

        q = s.cross(edge1);
        v = f * ray.getDirection().dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return null;
        }

        //at this stage we know a line intersection happens.
        double distance = f * edge2.dot(q);

        if (distance < EPSILON) {
            return null; //line intersection happens, but no ray intersection
        }

        return new RayHit(ray, this, ray.getPointAlongRay(distance));
    }
}
