import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class BallModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private Ball b1,b2;
	private Ball[] balls;
	private final double g;

	public BallModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
		b1 = new Ball(1,3,1,5);
		b2 = new Ball(3,5,10,10);
		balls = new Ball[2];
		balls[0] = b1;
		balls[1] = b2;
		this.g = -9.82;
	}

	@Override
	public void tick(double deltaT) {
		for(Ball b : balls){
			
			
			System.out.println("1:"+b.getVy());
			if (b.getX() < b.getR() || b.getX() > areaWidth - b.getR()) {
				b.setVx(-1*b.getVx());
			}
			if (b.getY() < b.getR() || b.getY() > areaHeight - b.getR()) {
				b.setVy(-1*b.getVy());
				System.out.println("BLÖH!");
			}else{
				b.setVy(calculateSpeed(b.getVy(),b.getAy(),deltaT));
			}
			
			
			b.setY(b.getY()+b.getVy()*deltaT);
			b.setX(b.getX()+b.getVx()*deltaT);
			
			
			//System.out.println(b.getVy());
		}
		
		if(Math.sqrt(
				Math.pow((b1.getX()-b2.getX()),2)+Math.pow((b1.getY()-b2.getY()),2)) 
				<= b1.getR()+b2.getR()){
			resolveCollision(b1,b2);
		}
	

	}
	
	private void resolveCollision(Ball b1, Ball b2){
		double   dx = b1.getX() - b2.getX(), dy = b1.getY() - b2.getY();
	    double   d = Math.sqrt(dx*dx + dy*dy);
	    double   vp1, vp2, vx1, vx2, vy1, vy2;
	    vx1 = b1.getVx();
	    vx2 = b2.getVx();
	    vy1 = b1.getVy();
	    vy2 = b2.getVy();
	    vp1 = vx1 * dx / d + vy1 * dy / d;
	    vp2 = vx2 * dx / d + vy2 * dy / d;

	//  Unit vector in the direction of the collision.
	    double   ax = dx / d, ay = dy / d;

	//  Projection of the velocities in these axes.
	    double   va1 = vx1 * ax + vy1 * ay, vb1 = -vx1 * ay + vy1 * ax;
	    double   va2 = vx2 * ax + vy2 * ay, vb2 = -vx2 * ay + vy2 * ax;

	//  New velocities in these axes (after collision): edmass / b2->mass);
	    double   vaP1 = va1 + (va2 - va1) / (b1.getWeight() / b2.getWeight());
	    double   vaP2 = va2 + (va1 - va2) / (b2.getWeight() / b1.getWeight());

	//  Undo the projections.
	    vx1 = vaP1 * ax - vb1 * ay; 
	    vy1 = vaP1 * ay + vb1 * ax; // new vx,vy for ball 1 after collision.
	    vx2 = vaP2 * ax - vb2 * ay; 
	    vy2 = vaP2 * ay + vb2 * ax; // new vx,vy for ball 2 after collision.

	    b1.setVx(vx1);
	    b1.setVy(vy1);
	    b2.setVx(vx2);
	    b2.setVy(vy2);
	  
	}
	
	
	private double getSpeedAngle(Ball b){
		return Math.atan2(b.getVy(),b.getVx());
	}
	
	private double getCollisionAngle(Ball b1, Ball b2){
		return Math.atan2((b1.getY()-b2.getY()),(b1.getX()-b2.getX()));
	}
	
	
	private double calculateSpeed(double speed, double acc, double deltaT){
		return speed+(acc*deltaT);
	}

	@Override
	public List<Ellipse2D> getBalls() {
		List<Ellipse2D> myBalls = new LinkedList<Ellipse2D>();
		for(Ball b : balls){
			myBalls.add(new Ellipse2D.Double(b.getX() - b.getR(), b.getY() - b.getR(), 2 * b.getR(), 2 * b.getR()));
		}
		return myBalls;
	}
}
