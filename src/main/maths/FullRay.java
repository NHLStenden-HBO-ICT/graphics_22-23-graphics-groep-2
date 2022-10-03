package main.maths;

public class FullRay extends Ray {

    private Vector3 origin;

    private Vector3 direction;

    public FullRay(Vector3 direction, Vector3 origin) {
        super(direction, origin);
    }

    //some overloaded constructors to reduce boilerplate code
    public FullRay(double dirX, double dirY, double dirZ, double originX, double originY, double originZ) {
        super(dirX, dirY, dirZ, originX, originY, originZ);
    }

}
