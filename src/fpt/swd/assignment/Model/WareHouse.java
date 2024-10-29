package fpt.swd.assignment.Model;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
    private List<double[]> path;
    private List<double[]> backPath;
    private Location wareHouse;
    private int packages= 0;
    private int timer;
    private int spawnRate=10000/200;

    public WareHouse(Location home,Location wareHouse) {
        this.wareHouse= wareHouse;
        this.path = generatePath(home, wareHouse);
        this.backPath = generatePathBack(wareHouse, home);
    }

    public List<double[]> getPath() {
        return path;
    }
    
    public List<double[]> getBackPath() {
        return backPath;
    }


	public Location getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(Location wareHouse) {
		this.wareHouse = wareHouse;
	}

	public void setPath(List<double[]> path) {
		this.path = path;
	}
	

	public int getPackages() {
		return packages;
	}

	public void setPackages(int packages) {
		this.packages = packages;
	}

	private List<double[]> generatePath(Location home,Location wareHouse) {
        List<double[]> generatedPath = new ArrayList<>();
        double currentX = home.getPositionX();
        double currentY = home.getPositionY();

        // Move in the x direction first
        while (currentX != wareHouse.getPositionX() || currentY != wareHouse.getPositionY()) {
            // Add the current position to the path
            generatedPath.add(new double[]{currentX, currentY});

            // Move horizontally towards the warehouse
            if (currentX < wareHouse.getPositionX()) {
                currentX++;
            } else if (currentX > wareHouse.getPositionX()) {
                currentX--;
            }

            // Move vertically towards the warehouse
            if (currentY <  wareHouse.getPositionY()) {
                currentY++;
            } else if (currentY >  wareHouse.getPositionY()) {
                currentY--;
            }
        }

        // Add the final position (warehouse)
        generatedPath.add(new double[]{wareHouse.getPositionX(),  wareHouse.getPositionY()});

        return generatedPath;
    }
	
	private List<double[]> generatePathBack(Location home,Location wareHouse) {
	    List<double[]> generatedPath = new ArrayList<>();
	    double currentX = home.getPositionX();
	    double currentY = home.getPositionY();

	    // Move in the y direction first
	    while (currentX != wareHouse.getPositionX() || currentY !=  wareHouse.getPositionY()) {
	        // Add the current position to the path
	        generatedPath.add(new double[]{currentX, currentY});

	        // Move vertically towards the warehouse first
	        if (currentY <  wareHouse.getPositionY()) {
	            currentY++;
	        } else if (currentY >  wareHouse.getPositionY()) {
	            currentY--;
	        }

	        // Move horizontally towards the warehouse
	        if (currentX < wareHouse.getPositionX()) {
	            currentX++;
	        } else if (currentX > wareHouse.getPositionX()) {
	            currentX--;
	        }
	    }

	    // Add the final position (warehouse)
	    generatedPath.add(new double[]{wareHouse.getPositionX(),  wareHouse.getPositionY()});

	    return generatedPath;
	}

    public void generatePackage() {
    	
    	timer++;
    	if(timer>spawnRate) {
    		timer=0;
    		packages++;
    	}
    	
    }
    
}


