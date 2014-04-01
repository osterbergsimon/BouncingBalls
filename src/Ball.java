
public class Ball {
	
	private final double weight, r;
	private double x, y, vx, vy;

	
	public Ball(double radius, double weight, double x, double y){
		this.weight = weight;
		this.x = x;
		this.y = y;
		this.vx = 2.3;
		this.vy = 1;
		this.r = radius;
		
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


	public double getR() {
		return r;
	}

	public double getWeight() {
		return weight;
	}
	

}
