package fpt.swd.assignment.view;

import javax.swing.*;

import fpt.swd.assignment.Controller.AGVController;
import fpt.swd.assignment.Model.AGV;
import fpt.swd.assignment.Model.MapObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapView extends JPanel {
	private int width;
	private int height;
	public List<MapObject> objects = new ArrayList<MapObject>();
	private int squareSize = 8;
	private List<AGV> agvList;
	AGVController agvController = new AGVController();

	public MapView(int width, int height) {
		this.width = width * squareSize;
		this.height = height * squareSize;
		setPreferredSize(new Dimension(this.width, this.height));
	}

	public void setAGVList(List<AGV> agvList) {
		this.agvList = agvList;
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    g.setColor(Color.LIGHT_GRAY);
	    for (int x = 0; x <= width; x += squareSize) {
	        for (int y = 0; y <= height; y += squareSize) {
	            g.fillRect(x, y, squareSize, squareSize);
	        }
	    }

	    for (MapObject obj : objects) {
	        if (obj.getName().startsWith("AGV")) {
	            AGV agv = findAGVByName(obj.getName());
	            if (agv != null) {
	                g.setColor(Color.RED);
	                List<double[]> path = agv.getWarehouse().getPath();
	                if (path != null && path.size() > 1) {
	                    for (int i = 0; i < path.size() - 1; i++) {
	                        int x1 = (int) (path.get(i)[0] * squareSize);
	                        int y1 = (int) (path.get(i)[1] * squareSize);
	                        int x2 = (int) (path.get(i + 1)[0] * squareSize);
	                        int y2 = (int) (path.get(i + 1)[1] * squareSize);
	                        g.drawLine(x1, y1, x2, y2);
	                    }
	                }

	                g.setColor(Color.RED);
	                List<double[]> returnPath = agv.getWarehouse().getBackPath();
	                if (returnPath != null && returnPath.size() > 1) {
	                    for (int i = 0; i < returnPath.size() - 1; i++) {
	                        int x1 = (int) (returnPath.get(i)[0] * squareSize);
	                        int y1 = (int) (returnPath.get(i)[1] * squareSize);
	                        int x2 = (int) (returnPath.get(i + 1)[0] * squareSize);
	                        int y2 = (int) (returnPath.get(i + 1)[1] * squareSize);
	                        g.drawLine(x1, y1, x2, y2);
	                    }
	                }
	            }
	        }
	    }

	    for (MapObject obj : objects) {
	        int x = (int) (obj.getPosition().getPositionX() * squareSize);
	        int y = (int) (obj.getPosition().getPositionY() * squareSize);

	        Image image = obj.getImage();
	        
	        if (image != null) {
	            g.drawImage(image, x, y, 40, 40, this);
	        } else {
	            g.setColor(Color.BLUE);
	            g.fillOval(x, y, 10, 10);
	        }
	        
	        g.drawString(obj.getName(), x, y + 50);
	    }
	}




	// Helper function to find AGV by name
	private AGV findAGVByName(String name) {
		for (AGV agv : agvList) {
			if (name.equals("AGV " + agv.getId())) {
				return agv;
			}
		}
		return null;
	}

	public void updateObjects(List<MapObject> newObjects) {
		this.objects = newObjects; // Update the list of objects
		repaint(); // Redraw the panel
	}
}
