package main.geometry;

import main.maths.*;
import main.utils.Material;

public class Triangle extends Solid implements Intersectable {

    private Vector3[] vertices;
    private Vector3[] texturemap;
    private Vector3[] verticesorigin; //location of the vertices when triangle is at 0
    private Vector3 surfaceNormal;
    private Orientation orientation;
    private Vector3 position;

    //not part of an in loaded model
    public Triangle(Material material, Vector3 point1, Vector3 point2, Vector3 point3) {
        super(material);
        this.vertices = new Vector3[]{
                point1,
                point2,
                point3,
        };
        this.surfaceNormal = calculateSurfaceNormal();

    }

    //triangle of a loaded model
    public Triangle(Material material, Vector3 point1, Vector3 point2, Vector3 point3, Vector3 normal) {
        super(material);
        this.vertices = new Vector3[]{
                point1,
                point2,
                point3,
        };
        this.verticesorigin = new Vector3[]{
                point1,
                point2,
                point3,
        };
        this.surfaceNormal =normal;
    }

    public Triangle(Material material, Vector3 point1, Vector3 point2, Vector3 point3, Vector3 normal, Vector3 pointT1, Vector3 pointT2, Vector3 pointT3) {
        super(material);
        this.vertices = new Vector3[]{
                point1,
                point2,
                point3,
        };
        this.verticesorigin = new Vector3[]{
                point1,
                point2,
                point3,
        };
        this.texturemap = new Vector3[]{
                pointT1,
                pointT2,
                pointT3,
        };
        this.surfaceNormal =normal;
    }

    public void setTexturemap(Vector3[] texturemap) {
        this.texturemap = texturemap;
    }

    public Vector3[] getTexturemap() {
        return texturemap;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getVertex(int index) {
        return vertices[index];
    }

    public Vector3 getVertexOrigin(int index) {
        return verticesorigin[index];
    }

    public Vector3[] getVertices() {
        return vertices;
    }

    public Vector3[] getVerticesorigin() {
        return verticesorigin;
    }

    public void setVertex(int index, Vector3 vertex) {
        this.vertices[index] = vertex;
    }

    // calculates the surface normal of this triangle.
    // by calculating this when the triangle gets generated we avoid having to continuously recalculate this
    // while ray tracing
    private Vector3 calculateSurfaceNormal() {
        //find two edges
        Vector3 u = vertices[0].sub(vertices[1]);
        Vector3 v = vertices[2].sub(vertices[0]);

        Vector3 normal = new Vector3();

        //use the edges to find the surface normal for the triangle
        normal.setX((u.getY() * v.getZ()) - (u.getZ() * v.getY()));
        normal.setY((u.getZ() * v.getX()) - (u.getX() * v.getZ()));
        normal.setZ((u.getX() * v.getY()) - (u.getY() * v.getX()));

        //return the surface normal
        return normal.normalise();
    }

    @Override
    public Vector3 getSurfaceNormal(Vector3 point) {
        return surfaceNormal;
    }

    //non overide version to work with rotations
    public Vector3 getSurfaceNormal() {
        return surfaceNormal;
    }

    public void setSurfaceNormal(Vector3 surfaceNormal){
        this.surfaceNormal =surfaceNormal;
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

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    //going to try and make this happen
    // https://en.wikipedia.org/wiki/M%C3%B6ller%E2%80%93Trumbore_intersection_algorithm

    //returns the distance if an intersection happens
    //return -1.0 if no intersection happens
    private double findDistance(Ray ray) {
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

        h = ray.getDirection().cross(edge2);
        a = edge1.dot(h);

        //a describes the angle between the ray direction and the triangle direction
        //if the angle is sufficiently small then the ray is parallel to the triangle
        //we don't  intersect with a plane that's parallel
        if (a > -Constants.EPSILON && a < Constants.EPSILON) {
            return -1.0;
        }

        f = 1.0 / a;
        s = ray.getOrigin().sub(vertex0);
        u = f * (s.dot(h));

        //in case of an intersection u & v should be between 0 and 1.
        //if this is not the case no intersection happens, and we can eject from the algorithm early.
        if (u < 0.0 || u > 1.0) {
            return -1.0;
        }

        q = s.cross(edge1);
        v = f * ray.getDirection().dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return -1.0;
        }

        //at this stage we know a line intersection happens.
        //let's calculate the distance
        double distance = f * edge2.dot(q);

        //if the distance is negative the intersection doesn't lay on the ray.
        //it instead lies on the same line as the ray but before the origin.
        if (distance < Constants.EPSILON) {
            return -1.0; //line intersection happens, but no ray intersection
        }

        //all conditions where a ray intersection doesn't happen have been ruled out
        //a ray intersection happens at the distance we've calculated
        return distance;
    }

    @Override
    public RayHit intersects(FullRay fullRay) {
        double distance = findDistance(fullRay);
        if (distance < Constants.EPSILON) {
            //we can return a RayHit object because a collision happens.
            return null;
        }
        return new RayHit(fullRay, this, fullRay.getPointAlongRay(distance), distance, surfaceNormal);
    }

    @Override
    public boolean intersectsFast(ShadowRay shadowRay) {
        double distance = findDistance(shadowRay);

        //we can return a RayHit object because a collision happens.
        return distance > Constants.EPSILON;
    }
}
