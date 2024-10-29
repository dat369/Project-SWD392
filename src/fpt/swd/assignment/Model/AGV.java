package fpt.swd.assignment.Model;

import java.util.List;
import java.util.concurrent.Semaphore;

public class AGV {
	private int id;
	private Location position;
	private double velocity;
	private WareHouse warehouse;
	private int direction = 1;
	private List<double[]> currentPath;
	private int currentPathIndex = 0;
	private int timer;
	private int timeAction = 2000 / 200;
	private int packages;

	public AGV(int id, Location position, double velocity, WareHouse warehouse) {
		this.id = id;
		this.position= position;
		this.velocity = velocity;
		this.warehouse = warehouse;
		this.currentPath = warehouse.getPath();
		this.currentPathIndex = (int) velocity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Location getPosition() {
		return position;
	}

	public void setPosition(Location position) {
		this.position = position;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public WareHouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WareHouse warehouse) {
		this.warehouse = warehouse;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public List<double[]> getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(List<double[]> currentPath) {
		this.currentPath = currentPath;
	}

	public int getCurrentPathIndex() {
		return currentPathIndex;
	}

	public void setCurrentPathIndex(int currentPathIndex) {
		this.currentPathIndex = currentPathIndex;
	}

	public void move() {
		if (currentPath == null || currentPath.isEmpty())
			return; // Check if path is valid

		// Check if the AGV has reached the end of the path
		if (currentPathIndex >= currentPath.size() || currentPathIndex < 0) {
			if (warehouse.getPackages() > 0 && packages == 0 && direction == 1) {
				takePackage();
				return;
			}
			if (direction == -1 && packages>0) {
				dropPackage();
				return;
			}
			direction *= -1; // Reverse direction
			if (direction > 0)
				currentPath = warehouse.getPath();
			else
				currentPath = warehouse.getBackPath();
			currentPathIndex = 0; // Correct the index after reversing
		}
		// Update position based on current path index
		double[] nextPosition = currentPath.get(currentPathIndex);
		position.setPositionX(nextPosition[0]);
		position.setPositionY(nextPosition[1]);

		// Update currentPathIndex
		currentPathIndex += velocity;
		currentPathIndex = Math.min(currentPathIndex, currentPath.size());
		System.out.println("Location: (x, y)" + position.getPositionX() + position.getPositionY());
	}

	public void takePackage() {

		if (warehouse.getPackages() > 0) {
			timer++;
			if (timer > timeAction) {
				timer = 0;
				warehouse.setPackages(warehouse.getPackages() - 1);
				packages++;
			}
		}
	}

	public void dropPackage() {
		timer++;
		if (timer > timeAction) {
			timer = 0;
			packages--;
		}
	}

}
