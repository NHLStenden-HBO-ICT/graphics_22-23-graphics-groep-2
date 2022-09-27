package main.maths;
import java.lang.Math;

public class Vector3 {

	//variables
	private double x;
	private double y;
	private double z;

	//empty constructor. all variables are set to 0
	public Vector3(){
		x=0;
		y=0;
		z=0;
	}

	//constructor. all variables are set to the parameters
	public Vector3(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}

	//get methodes
	public double GetX() {
		return x;
	}

	public double GetY() {
		return y;
	}

	public double GetZ() {
		return z;
	}

	//Set methodes
	public void SetX(double x) {
		this.x=x;
	}

	public void SetY(double y ) {
		this.y=y;
	}

	public void SetZ(double z) {
		this.z=z;
	}

	//add method. doet deze vector plus de parameter vector
	public Vector3 Add(Vector3 vector3) {
		this.x+=vector3.x;
		this.z+=vector3.z;
		this.y+=vector3.y;
		return this;
	}

	//sub method. doet deze vector min de parameter vector
	public Vector3 Sub(Vector3 vector3) {
		this.x-=vector3.x;
		this.z-=vector3.z;
		this.y-=vector3.y;
		return this;
	}

	//Multi method. doet deze vector keer de parameter
	public Vector3 Multi(double multiply) {
		this.x=this.x * multiply;
		this.z=this.z * multiply;
		this.y=this.y * multiply;
		return this;
	}

	//Dot method. doet deze vector keer de parameter vector waar dus het inproduct uit komt
	public double Dot(Vector3 vectorA) {
		return vectorA.x*x+vectorA.y*y+vectorA.z*z;
	}

	//Normalise vector, maakt dus de normaal van de vector en return hem
	public Vector3 Normalise() {
		double magnitude = Lenght();
		x/=magnitude;
		y/=magnitude;
		z/=magnitude;
		return this;
	}

	//lengte van de vector en returnd hem
	public double Lenght() {
		return Math.sqrt(x*x+y*y+z*z);
	}

}
