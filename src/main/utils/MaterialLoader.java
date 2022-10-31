package main.utils;

import main.maths.Vector3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MaterialLoader {


    private Material material;

    public Material MaterialFile(File file) throws Exception {

        material = new Material(new VectorColor(new Vector3(255,255,255)));
        //creates bufferreader that reads the file
        BufferedReader bufferfile = new BufferedReader(new FileReader(file));

        String line;
        //reads line as long as it is not empty
        while ((line = bufferfile.readLine()) != null) {
            String[] data = line.split(" ");
            switch (data[0]){
                case "Kd": //diffuse color also known as the normal color
                    material.setColor(setColor(data));
                break;
                case "Ns": //reflection number goes from 0-1000
                    material.setReflectivity(Double.parseDouble(data[1])/1000.0);
                    break;
                case "Ni": //refraction
                    material.setIor(Double.parseDouble(data[1]));
                    break;
                case "map_Kd":
                    material.setTexturemap(getImage(data));
                    break;
                //more cases for Ambient(Ka), incase the object gives light/emision(Ke) and maybe more texture ma options
            }

        }

        return material;
    }

    private VectorColor setColor(String[] data){
        //in a mtl file color is represent with 3 numbers between 0 and 1
        //in our code color is represented using 3 numbers of 0 to 255
        //because of this we need to do the number out the file times the max number we have, in this case 255
        return new VectorColor(new Vector3(Double.parseDouble(data[1])*255, Double.parseDouble(data[2])*255, Double.parseDouble(data[3])*255));
    }

    private BufferedImage getImage(String[] data){
        String[] line = data[1].split("//");
        try {
            return ImageIO.read(new File("objfiles/"+data[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
