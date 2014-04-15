
import java.util.LinkedList;
import java.util.List;

public class BallController implements IBouncingBallsModel{

	private List<Ball> ballList;
	private final double width;
	private final double height;
	
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
			ballCollision(deltaT, b);
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
		if(b.getY()>0 && b.getY()<height -2*b.getR()) {
			b.setVy(b.getVy()+(-9.82)*deltaT);
		}
	}
	
	private void ballCollision(double deltaT, Ball b) {
		for(Ball b2 : ballList) {
			if(isOverlappingX(b, b2) && isOverlappingY(b, b2)) {
				double angel = Math.atan(Math.abs(b2.getY()-b.getY())/Math.abs(b2.getX()-b.getX()));
//				setNewVelocityX(b, b2, angel);
//				setNewVelocityY(b, b2, angel);
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
	
	private void setNewVelocityX(Ball b, Ball b2, double angel) {
		double i = b.getM()*b.getVx() + b2.getM()*b2.getVx();
		double r = -(b2.getVx() - b2.getVx());
		double v2 = (i + b.getM()*r)/(b.getM() + b2.getM());
		b2.setVx(v2*Math.sin(angel));
		b.setVx((v2-r)*Math.sin(angel));
	}
	
	private void setNewVelocityY(Ball b, Ball b2, double angel) {
		double i = b.getM()*b.getVy() + b2.getM()*b2.getVy();
		double r = -(b2.getVy() - b2.getVy());
		double v2 = (i + b.getM()*r)/(b.getM() + b2.getM());
		b2.setVy(v2*Math.cos(angel));
		b.setVy((v2-r)*Math.cos(angel));
	}

}
