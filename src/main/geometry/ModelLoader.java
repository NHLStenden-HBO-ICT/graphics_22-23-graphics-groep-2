package main.geometry;

import main.maths.Vector3;
import main.utils.Color;
import main.utils.Material;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ModelLoader {

    public ArrayList<Triangle> parseFile(File file, Vector3 location) throws Exception {

        ArrayList<Triangle> triangles = new ArrayList<>();
        ArrayList<Vector3> vertices = new ArrayList<>();

        BufferedReader bufferfile =new BufferedReader(new FileReader(file)); //creates buffer file

        String line;
        while ((line =bufferfile.readLine()) !=null){//reads line aslong as its not empty
            String[] data = line.split(" ");// the file is broken with a space
            switch (data[0]) //the first part determines what the rest of the lines info is for
            {
                case "v" : //v stands for vertices aka things needed to make the triangles
                    vertices.add(parseVertice(data,location));
                    break;

                case "f" : //face data for each point of the face example of face data 2/2/3 which can be translated to vertex/vertex texture/vertex normal
                    triangles.add(parseFace(vertices,data));
                    break;
                //todo more option can be added for extra data from the file
            }
        }




        return triangles;

    }

    public Vector3 parseVertice(String[] data, Vector3 location){//set the data from file over to a double
        return new Vector3(Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3])).add(location);
    }

    public Triangle parseFace(ArrayList<Vector3> vertices, String[] data){//creates the triangle with al its data

        return new Triangle(new Material(new Color(new Vector3(255,255,255)), 0,0), parseTriangleVertex(data[1].split("/"),vertices),parseTriangleVertex(data[2].split("/"),vertices),parseTriangleVertex(data[3].split("/"),vertices));
    }


    public static Vector3 parseTriangleVertex(String[] data, ArrayList<Vector3> vertices) { //gets the correct vertex of triangle
        Vector3 vertex = null;
        if (!data[0].isEmpty()) { //checks if data is empty otherwise vertex empty
            int vertexIndex = Integer.parseInt(data[0]) - 1;//index of list are always one less so index value of the second vertex is 2-1 =1
            vertex = vertices.get(vertexIndex);
        }
        return vertex;
    }

}
