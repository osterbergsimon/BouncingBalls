import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class BallModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private Ball ball1,ball2;
	private Ball[] balls;
	private final double g;

	public BallModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
		balls = new Ball[2];
		balls[0] = new Ball(1,3,1,5);
		balls[1] = new Ball(3,5,10,10);
		this.g = -9.82;
	}

	@Override
	public void tick(double deltaT) {
		for(Ball b : balls){
			if (b.getX() < b.getR() || b.getX() > areaWidth - b.getR()) {
				b.setVx(-1*b.getVx());
			}
			if (b.getY() < b.getR() || b.getY() > areaHeight - b.getR()) {
				b.setVy(-1*b.getVy());
			}
			
			b.setX(b.getX()+b.getVx()*deltaT);
			b.setY(b.getY()+(b.getVy()+g*deltaT)*deltaT);
		}
	

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
