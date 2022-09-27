package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;
import main.maths.Vector3;

public class Triangle extends Solid implements Intersectable {

    private Vector3[] Vertices;

    public Triangle(Vector3 point1, Vector3 point2, Vector3 point3) {
        Vertices = new Vector3[]{
                point1,
                point2,
                point3,
        };
    }

    public Vector3 getvertex(int index) {
        return Vertices[index];
    }

    public Vector3[] getvertices() {
        return Vertices;
    }

    public void setvertex(int index, Vector3 vertex) {
        this.Vertices[index] = vertex;
    }

    //this method would normally allow us to change the length of the Triangle.Vertices array
    //to prevent this we need to ensure the correct length of the provided array
    public void setVertices(Vector3[] vertices) {
        if (vertices.length != 3) {
            throw new RuntimeException(
                    "A triangle can only have 3 vertices but " + vertices.length + " were provided");
        }

        this.Vertices = vertices;
    }


    @Override
    public RayHit Intersects(Ray ray) {
        return null;
    }
}
