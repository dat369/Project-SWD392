package fpt.swd.assignment.Model;

import java.awt.Image;
import javax.swing.ImageIcon;

public class MapObject {
    private String name;
    private Location position;
    
    private Image deliveryLocationImage;
    private Image receivingLocationImage;
    private Image agvImage;

    public MapObject(String name, Location position) {
        this.name = name;
        this.position = position;
        loadImages();
    }

    public String getName() {
        return name;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    private void loadImages() {
        deliveryLocationImage = new ImageIcon("img\\Kho.png").getImage();
        receivingLocationImage = new ImageIcon("img\\Package.png").getImage();
        agvImage = new ImageIcon("img\\agv.png").getImage();
    }

    public Image getImage() {
        if ("Delivery location".equals(name)) {
            return deliveryLocationImage;
        } else if (name.startsWith("Receiving location")) {
            return receivingLocationImage;
        } else if (name.startsWith("AGV")) {
            return agvImage;
        }
        return null;
    }
}
