package com.company;

import javax.swing.*;
import java.awt.*;

public class GUI {
private final JFrame mainFrame = new JFrame();
private final JTextField floorTextField = new JTextField("Floor: 0");

   public GUI() {
       mainFrame.setBounds(0, 0, 400, 400);
       configureFloorTextField();
       mainFrame.setVisible(true);
    }

    private void configureFloorTextField() {
       final JPanel panel = new JPanel(new FlowLayout());
        floorTextField.setEditable(false);
       panel.add(floorTextField);
       mainFrame.add(panel);
    }

    public void updateFloor(int floor) {
       floorTextField.setText("Floor: " + floor);
    }
}
