package main.geometry;

import main.maths.Ray;
import main.maths.RayHit;
import main.maths.Vector3;
import main.utils.Material;

public class Triangle extends Solid implements Intersectable {

    private Vector3[] vertices;

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


    @Override
    public RayHit intersects(Ray ray) {
        return null;
    }
}
