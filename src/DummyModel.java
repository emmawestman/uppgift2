import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class DummyModel implements IBouncingBallsModel {

	private final double areaWidth;
	private final double areaHeight;

	private double x, y, vx, vy, r;

	public DummyModel(double width, double height) {
		this.areaWidth = width;
		this.areaHeight = height;
		x = 1;
		y = 1;
		vx = 2.3;
		vy = 1;
		r = 1;
	}

	@Override
	public void tick(double deltaT) {
		if (x < r || x > areaWidth) {
			vx *= -1;
		}
		if (y < r || y > areaHeight) {
			vy *= -1;
		}
		x += vx * deltaT;
		y += vy * deltaT;
	}

	@Override
	public List<Ball> getBalls() {
//		List<Ball> myBalls = new LinkedList<Ellipse2D>();
//		myBalls.add(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
//		myBalls.add(new Ellipse2D.Double(areaWidth - r, y - r, r, r));
		return null;
	}
}