package ui.gui;

import java.awt.Image;

import javax.swing.ImageIcon;

// Handles image addition to panels
public class ImageHandler {

    // EFFECTS: creates an ImageHandler object, doesnt initialize anything
    public ImageHandler() {
        
    }

    // EFFECTS: produces an image for the user, with given width and height at the specified file path
    // ATTRIBUTION: https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    public ImageIcon getImage(String filePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(filePath);
        Image img = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

}
