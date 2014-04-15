
import java.util.LinkedList;
import java.util.List;

public class BallController implements IBouncingBallsModel{

	private List<Ball> ballList;
	private final double width;
	private final double height;
	private int f = 0;

	public BallController(double width, double height) {
		this.width = width;
		this.height = height;
		ballList = new LinkedList<Ball>();
		ballList.add(new Ball(0 + 2*1, 0 + 2*1, 2.3, 0, 1, 2));
		ballList.add(new Ball(width - 2*0.5, height - 2*0.5, -1.2, 0, 0.5, 1));
	}
	@Override
	public List<Ball> getBalls() {
		return ballList;
	}

	@Override
	public void tick(double deltaT) {
		for(Ball b : ballList) {
			edgeCollision(deltaT, b);
			gravity(deltaT, b);
			if(f <= 0) {
				ballCollision(deltaT, b);
			}
			f--;
		}

	}

	private void edgeCollision(double deltaT, Ball b) {
		if (b.getX() < 0 || b.getX() > width - 2*b.getR()) {
			b.setVx(b.getVx()*-1);
		}
		if (b.getY() < 0 || b.getY() > height - 2*b.getR()) {
			b.setVy(b.getVy()*-1);
		}
		b.setX(b.getX()+b.getVx()*deltaT);
		b.setY(b.getY()+b.getVy()*deltaT);
	}

	private void gravity(double deltaT, Ball b) {
		/*if(b.getY()>0 /* && b.getY()<height -2*b.getR()) {*/
		b.setVy(b.getVy()+(-9.82)*deltaT);
		/*}*/
	}

	private void ballCollision(double deltaT, Ball b) {
		for(Ball b2 : ballList) {
			if(isOverlappingX(b, b2) && isOverlappingY(b, b2)) {
				double angel = Math.atan((b.getY()+b.getR()-(b2.getY()+b2.getR()))/(b.getX()+b.getR()-(b2.getX()+b2.getR())));
				
				double b2RotatedX = b2.getX()*Math.cos(angel)+b2.getY()*Math.sin(angel);
				double b2RotatedY = -b2.getX()*Math.sin(angel)+b2.getY()*Math.cos(angel);
				double bRotatedX = b.getX()*Math.cos(angel)+b.getY()*Math.sin(angel);
				double bRotatedY = -b.getX()*Math.sin(angel)+b.getY()*Math.cos(angel);
				
				double vX[] = setNewVelocity(bRotatedX, b2RotatedX, b.getM(), b2.getM());
				double vY[] = setNewVelocity(bRotatedY, b2RotatedY, b.getM(), b2.getM());
				
				b.setVx(vX[0]*Math.cos(angel) - vY[0]*Math.sin(angel));
				b2.setVx(vX[1]*Math.cos(angel) - vY[1]*Math.sin(angel));
				b.setVy(vX[0]*Math.sin(angel) + vY[0]*Math.cos(angel));
				b2.setVy(vX[1]*Math.sin(angel) + vY[1]*Math.cos(angel));
				
				f = 100;
				System.out.println("Krock!");
			}
		}

	}

	private boolean isOverlappingX(Ball b, Ball b2) {
		return (b2.getX()<(b.getX()+b.getWidth()) && b2.getX()>b.getX()) || 
				((b2.getX()+b2.getWidth())>b.getX() && ((b2.getX()+b2.getWidth())<(b.getX()+b.getWidth())));
	}

	private boolean isOverlappingY(Ball b, Ball b2) {
		return (((b2.getY()-b2.getHeight())<b.getY() && (b2.getY()-b2.getHeight())>(b.getY()-b.getHeight())) ||
				(b2.getY()>(b.getY()-b.getHeight()) && b2.getY()<b.getY()));
	}

	private double[] setNewVelocity(double u1, double u2, double m1, double m2) {
		double i = m1*u1 + m2*u2;
		double r = -(u2 - u1);
		double v2 = (i + m1*r)/(m1 + m2);
		double v[] = {v2-r,v2};
		return v;
	}
}
