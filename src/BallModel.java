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
		balls = new Ball[1];
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
			
			/*if(b.getY() <= b.getR()){
				if(b.getVy() <= 0){
					b.setVy(-1*b.getVy());
					System.out.println("2:"+b.getVy());
				}
			}else{
				b.setVy(calculateSpeed(b.getVy(),b.getAy(),deltaT));
				
			}
			
			if(b.getY() > areaHeight - b.getR()){
				if(b.getVy() > 0){
					b.setVy(-1*b.getVy());
				}
			}else{
				b.setVy(calculateSpeed(b.getVy(),b.getAy(),deltaT));
			}*/
			
			b.setY(b.getY()+b.getVy()*deltaT);
			b.setX(b.getX()+b.getVx()*deltaT);
			
			
			//System.out.println(b.getVy());
		}
		
		if(Math.sqrt(
				Math.pow((b1.getX()-b2.getX()),2)+Math.pow((b1.getY()-b2.getY()),2)) 
				<= b1.getR()+b2.getR()){
			
		}
	

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
