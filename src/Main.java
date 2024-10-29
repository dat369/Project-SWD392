
// Main.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.Timer;

import fpt.swd.assignment.Controller.AGVController;
import fpt.swd.assignment.Model.AGV;
import fpt.swd.assignment.Model.Location;
import fpt.swd.assignment.Model.WareHouse;
import fpt.swd.assignment.view.MapView;

public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Map View Example");
		List<AGV> agv = new ArrayList<AGV>();
		List<WareHouse> wareHouse = new ArrayList<WareHouse>();
		int mapW = 160;
		int mapH = 90;
		
		Location deliveryLocation= new Location(3,3);
		Location receivingLocation1= new Location(120,80);
		Location receivingLocation2= new Location(50,70);
		wareHouse.add(new WareHouse(deliveryLocation,receivingLocation1));
		wareHouse.add(new WareHouse(deliveryLocation,receivingLocation2));
		
		agv.add(new AGV(1, new Location(10,10) , 10.5, wareHouse.get(0)));
		agv.add(new AGV(2, new Location(11,11) , 5.5, wareHouse.get(1)));
		
		MapView mapView = new MapView(mapW, mapH);
		AGVController agvController = new AGVController(wareHouse, agv, mapView, deliveryLocation);
		
		frame.add(mapView);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
     // Use a Timer to update the AGVs periodically
        Timer timer = new Timer(200, e -> agvController.run());
        timer.start();
	}
}
