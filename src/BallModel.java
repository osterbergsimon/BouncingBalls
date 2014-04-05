import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class BallModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private Ball[] balls;
	private final double g;
	boolean thisTick = true;

	public BallModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;

		balls = new Ball[2];
		balls[0] = new Ball(1,1,1,3);
		balls[1] = new Ball(2,8,10,10);
		//balls[2] = new Ball(1.3,2.5,7,7);
		this.g = -9.82;
	}

	@Override
	public void tick(double deltaT) {
		
		
		
		for (int i = 0; i < balls.length; i++)  
		{  
		    for (int j = i + 1; j < balls.length; j++)  
		    {  
		        if (balls[i].isColliding(balls[j])){
		        	System.out.println(balls[j].getMass()*balls[j].getVy()+
						balls[i].getMass()*balls[i].getVy()+
						balls[j].getMass()*balls[j].getVx()+
						balls[i].getMass()*balls[i].getVx());
		            resolveCollision(balls[i],balls[j]);
					System.out.println(balls[j].getMass()*balls[j].getVy()+
							balls[i].getMass()*balls[i].getVy()+
							balls[j].getMass()*balls[j].getVx()+
							balls[i].getMass()*balls[i].getVx());
					thisTick = false;
		        }
		    }
		}
				
		//old = null;
		
		for(Ball b : balls){
						
			if (b.getX() < b.getR() || b.getX() > areaWidth - b.getR()) {
				b.setVx(-1*b.getVx());
				thisTick = false;
			}
			if (b.getY() < b.getR() || b.getY() > areaHeight - b.getR()) {
				b.setVy(-1*b.getVy());
				thisTick = false;
			}	
			if(thisTick){
				b.setVy(calculateSpeed(b.getVy(),b.getAy(),deltaT));
			}
			
			
			b.setY(b.getY()+b.getVy()*deltaT);
			b.setX(b.getX()+b.getVx()*deltaT);
			
			
			old = b;
		}
		
		thisTick = true;
	

	}
	
	
	private void resolveCollision(Ball b1, Ball b2){
		double dx = b1.getX() - b2.getX();
		double dy = b1.getY() - b2.getY();
	    double d = Math.sqrt(dx*dx + dy*dy);
	    double vx1, vx2, vy1, vy2;
	    double va1, va2, vb1, vb2;
	    double r,m, vP1, vP2;
	    double ax = dx / d, ay = dy / d; // Enhetsvektorer
	    
	    vx1 = b1.getVx();
	    vx2 = b2.getVx();
	    vy1 = b1.getVy();
	    vy2 = b2.getVy();

		//Projicera hastigheten
	    va1 = vx1 * ax + vy1 * ay; 
	    va2 = vx2 * ax + vy2 * ay; 
	    vb1 = -vx1 * ay + vy1 * ax;
	    vb2 = -vx2 * ay + vy2 * ax;
	     
		//Beräkna ny hastighet
	    m = b1.getMass()*va1 + b2.getMass()*va2;
	    r = -(va2-va1);
	    vP1 = (m-b2.getMass()*r)/(b1.getMass()+b2.getMass());
	    vP2 = (m-b2.getMass()*r)/(b1.getMass()+b2.getMass()) +r;
	    
	    
	    //"Snurra" tillbaka
	    vx1 = vP1 * ax - vb1 * ay; 
	    vy1 = vP1 * ay + vb1 * ax;
	    vx2 = vP2 * ax - vb2 * ay; 
	    vy2 = vP2 * ay + vb2 * ax;

	    b1.setVx(vx1);
	    b1.setVy(vy1);
	    b2.setVx(vx2);
	    b2.setVy(vy2);
	    
	  
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
