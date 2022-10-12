package main.geometry;

import main.maths.Vector3;
import main.utils.VectorColor;
import main.utils.Material;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ModelLoader {

    public Model readFile(File file, Vector3 startPosition) throws Exception {

        ArrayList<Triangle> triangles = new ArrayList<>();
        ArrayList<Vector3> vertices = new ArrayList<>();

        //creates bufferreader that reads the file
        BufferedReader bufferfile =new BufferedReader(new FileReader(file));

        String line;
        //reads line as long as it is not empty
        while ((line =bufferfile.readLine()) !=null){

            //the file is broken with a space
            //for example : line 1 is a b c  >line1.split(" ")
            //data[0]=a
            //data[1]=b
            //data[2]=c
            String[] data = line.split(" ");

            //the first part determines what the rest of the lines info is for
            switch (data[0])
            {
                //v stands for vertices aka the points of a triangle
                case "v" :
                    vertices.add(setVertice(data));
                    break;
                //face data for each point of the face example of face data 2/2/3 which can be translated to vertex/vertexTexture/vertexNormal
                case "f" :
                    if (data.length>4){
                        System.out.println("this object's faces are made out squaires and not triangles so this can not be used");
                        triangles.addAll(squareToTriangle(data,vertices));
                        break;
                        //todo maybe add code to deal with squaires that in turn gets turned into triangles.
                    }
                    else {
                        triangles.add(setFace(vertices,data));
                        break;
                    }
                //todo more option can be added for extra data from the file
            }
        }

        return new Model(triangles,startPosition);

    }

    //sets the data(string) from the file over to a vector3 using double.parsedouble.
    //also changes adds the position if it needs to at certain position.
    //this can later be done through a modelobject
    //it return the vector3 which can be seen as the vertex of a 3d object
    public Vector3 setVertice(String[] data){
        return new Vector3(Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]));
    }

    //sets the color of the triangle and gets the correct vertex that is a part of the face.
    //a face can be seen as the triangles of object
    //it return a triangle which is a face of the object, later textures and normals can be added
    public Triangle setFace(ArrayList<Vector3> vertices, String[] data){

        return new Triangle(new Material(new VectorColor(new Vector3(255,255,255)), 0,1), setTriangleVertex(data[1].split("/"),vertices),setTriangleVertex(data[2].split("/"),vertices),setTriangleVertex(data[3].split("/"),vertices));
    }


    //gets the correct vertex(vector) of triangle, using the data and vertices list and returns the vector that is equale to one of the vertices
    public static Vector3 setTriangleVertex(String[] data, ArrayList<Vector3> vertices) {
        Vector3 vertex = null;
        //checks if data is empty otherwise vertex empty
        if (!data[0].isEmpty()) {
            //index of list are always one less so index value of the second vertex is 2-1 =1
            //also the first item of data of the obj file is the number of the vertex which starts at 1
            int vertexIndex = Integer.parseInt(data[0]) - 1;
            vertex = vertices.get(vertexIndex);
        }
        return vertex;
    }

    //in case an object exists out of squares it creates 2 triangles to simulate the square
    public ArrayList<Triangle> squareToTriangle(String[] data, ArrayList<Vector3> vertices){
       ArrayList<Triangle> squareTraingles = new ArrayList<>();

       squareTraingles.add(new Triangle(new Material(new VectorColor(new Vector3(255,255,255)), 0,1), setTriangleVertex(data[1].split("/"),vertices),setTriangleVertex(data[2].split("/"),vertices),setTriangleVertex(data[3].split("/"),vertices)));
       squareTraingles.add(new Triangle(new Material(new VectorColor(new Vector3(255,255,255)), 0,1), setTriangleVertex(data[1].split("/"),vertices),setTriangleVertex(data[3].split("/"),vertices),setTriangleVertex(data[4].split("/"),vertices)));
       return squareTraingles;

    }

}