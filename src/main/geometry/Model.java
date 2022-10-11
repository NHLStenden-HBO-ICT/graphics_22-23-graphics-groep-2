package main.geometry;

import main.maths.Vector3;

import java.util.ArrayList;

public class Model {

    private ArrayList<Triangle> triangles; //also called faces

    private Vector3 position; //the over al position of the object

    //constructor with general position at 0,0,0
    public Model(ArrayList<Triangle> triangles){
        this.triangles=triangles;
        this.position =new Vector3();
    }

    //constructor with  position
    public Model(ArrayList<Triangle> triangles,Vector3 position){
        this.triangles=triangles;
        this.position =position;
        move();
    }

    public Vector3 getPosition() {
        return position;
    }

    public ArrayList<Triangle> getTriangles() {
        return triangles;
    }

    public void setPosition(Vector3 position){
        this.position =position;
        move();
    }

    public void setTriangles(ArrayList<Triangle> triangles) {
        this.triangles=triangles; //if triangles change the vertice need to change and be moved to correct position
        move();
    }

    //if the position of the object changes all the vectors need to change
    public void move() {
        for (Triangle triangle:triangles) {
            Vector3[] vertices =triangle.getVertices();
            for(int i=0;i<triangle.getVertices().length;i++){
                triangle.setVertex(i,(triangle.getVertex(i).add(position)));
            }

        }


    }



}
