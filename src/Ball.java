import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class Ball extends Ellipse2D {

	private double x;
	private double y;
	private double vx;
	private double vy;
	private double r;
	private double m;
	
	public Ball(double posX, double posY, double vx, double vy, double r, double m) {
		this.setX(posX);
		this.setY(posY);
		this.setVx(vx);
		this.setVy(vy);
		this.r = r;
		this.m = m;
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

	public double getM() {
		return m;
	}
	
	@Override
	public Rectangle2D getBounds2D() {
		return super.getBounds();
	}

	@Override
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getWidth() {
		return r*2;
	}

	@Override
	public double getHeight() {
		return r*2;
	}

	
	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void setFrame(double x, double y, double w, double h) {
		
		
	}
		
}
