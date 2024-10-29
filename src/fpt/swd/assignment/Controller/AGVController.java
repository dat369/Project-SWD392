package fpt.swd.assignment.Controller;

//AGVManager.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fpt.swd.assignment.Model.AGV;
import fpt.swd.assignment.Model.Location;
import fpt.swd.assignment.Model.MapObject;
import fpt.swd.assignment.Model.WareHouse;
import fpt.swd.assignment.view.MapView;

public class AGVController {
	private List<AGV> agvList;
	private List<WareHouse> warehouse;
	private MapView mapView;
	private Location home;

	public AGVController(List<WareHouse> warehouse, List<AGV> agvList, MapView mapView, Location home) {
		this.agvList = agvList;
		this.warehouse = warehouse;
		this.mapView = mapView;
		this.home=home;
		mapView.setAGVList(agvList);
	}

	public AGVController() {
		// TODO Auto-generated constructor stub
	}

	public List<AGV> getAgvList() {
		return agvList;
	}

	public void setAgvList(List<AGV> agvList) {
		this.agvList = agvList;
	}

	public void run() {
		for (AGV agv : agvList) {
			agv.move();
		}
		for (WareHouse ware : warehouse) {
			ware.generatePackage();
		}
		draw();
	}

	public void draw() {
		List<MapObject> updatedObjects = new ArrayList<>();

		// Add home position to updated objects
		updatedObjects.add(new MapObject("Delivery location", home));

		// Add AGVs to updated objects
		for (AGV agv : agvList) {
			updatedObjects.add(new MapObject("AGV " + agv.getId(), agv.getPosition()));
		}

		// Add Warehouses to updated objects
		for (WareHouse ware : warehouse) {
			if(ware.getPackages()>=0) {
				updatedObjects.add(new MapObject("Receiving location: " + ware.getPackages(), ware.getWareHouse()));
			}
		}

		// Update the MapView objects and repaint
		mapView.updateObjects(updatedObjects);
	}

}
