
public class Ball {
	
	private final double weight, r;
	private double x, y, vx, vy, ax, ay;

	
	public Ball(double radius, double weight, double x, double y){
		this.weight = weight;
		this.x = x;
		this.y = y;
		this.vx = 2.3;
		this.vy = 1;
		this.r = radius;
		this.ax = 1;
		this.ay = -9.82;
		
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(double y) {
		this.y = y;
	}


	public double getVx() {
		return vx;
	}


	public void setVx(double vx) {
		this.vx = vx;
	}


	public double getVy() {
		return vy;
	}


	public void setVy(double vy) {
		this.vy = vy;
	}
	
	public double getAy() {
		return ay;
	}


	public void setAy(double ay) {
		this.ay = ay;
	}
	public double getAx() {
		return ax;
	}


	public void seAx(double ax) {
		this.ax = ax;
	}


	public double getR() {
		return r;
	}

	public double getWeight() {
		return weight;
	}
	

}
