package fpt.swd.assignment.Model;

public class Location {
	private double positionX;
	private double positionY;
	
	public Location(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public double getPositionX() {
		return positionX;
	}
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	public double getPositionY() {
		return positionY;
	}
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	
}
