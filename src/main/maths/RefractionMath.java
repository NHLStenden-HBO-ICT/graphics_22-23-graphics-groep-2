package main.maths;

public final class RefractionMath {
    
    public static double clampDouble(double min, double max, double value){
        if (value < min){
            value = min;
        }

        if (value > max){
            value = max;
        }

        return value;
    }

}
