package com.company;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private final JFrame mainFrame;
    private final TextField floorTextField;
    private final TextField peopleMovingTextField;

    public GUI() {
        mainFrame = new JFrame("Main Frame");
        floorTextField = new TextField("Floor: 0", 8);
        peopleMovingTextField = new TextField("People in elevator: 0");
        configureMainFrame();
        configureFloorTextField();
        configurePeopleMovingTextField();
        mainFrame.setVisible(true);
    }

    private void configureMainFrame() {
        mainFrame.setBounds(0, 0, 400, 400);
        mainFrame.setDefaultCloseOperation(3);
    }

    private void configureFloorTextField() {
        final JPanel panel = new JPanel(new FlowLayout());
        floorTextField.setEditable(false);
        panel.add("North", floorTextField);
        mainFrame.add("North", panel);
    }

    private void configurePeopleMovingTextField() {
        final JPanel panel = new JPanel(new GridLayout(2, 1));
        peopleMovingTextField.setEditable(false);
        peopleMovingTextField.setBackground(Color.red);
        panel.add("Center", peopleMovingTextField);
        mainFrame.add("Center", panel);
    }

    public void updateFloorNumber(int number) {
        floorTextField.setText("Floor: " + number);
    }
}
