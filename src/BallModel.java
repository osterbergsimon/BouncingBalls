import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class BallModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;
	private Ball ball1,ball2;

	public BallModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
		ball1 = new Ball(1,3,1,1);
		ball2 = new Ball(1,5,10,10);
	}

	@Override
	public void tick(double deltaT) {
		if (ball1.getX() < ball1.getR() || ball1.getX() > areaWidth - ball1.getR()) {
			ball1.setVx(-1*ball1.getVx());
		}
		if (ball1.getY() < ball1.getR() || ball1.getY() > areaHeight - ball1.getR()) {
			ball1.setVy(-1*ball1.getVy());
		}
		ball1.setVx(ball1.getVx()*deltaT);
		ball1.setVy(ball1.getVy()*deltaT);

	}

	@Override
	public List<Ellipse2D> getBalls() {
		List<Ellipse2D> myBalls = new LinkedList<Ellipse2D>();
		myBalls.add(new Ellipse2D.Double(ball1.getX() - ball1.getR(), ball1.getY() - ball1.getR(), 2 * ball1.getR(), 2 * ball1.getR()));
		return myBalls;
	}
}
