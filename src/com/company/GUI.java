package com.company;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private final JFrame mainFrame;
    private final TextField floorTextField;
    private final TextField peopleMovingTextField;
    private final TextField peopleFlowTextField;

    public GUI() {
        mainFrame = new JFrame("Main Frame");
        floorTextField = new TextField("Floor: 0", 8);
        peopleMovingTextField = new TextField("People in elevator: 0");
        peopleFlowTextField = new TextField("People left: 0 People entered: 0");
        configureMainFrame();
        configureFloorTextField();
        configurePeopleMovingTextField();
        configurePeopleFlowTextField();
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
        final JPanel panel = new JPanel(new GridLayout(1, 1));
        peopleMovingTextField.setEditable(false);
        peopleMovingTextField.setBackground(Color.red);
        panel.add("Center", peopleMovingTextField);
        mainFrame.add("Center", panel);
    }

    private void configurePeopleFlowTextField() {
        final JPanel panel = new JPanel(new FlowLayout());
        peopleFlowTextField.setEditable(false);
        panel.add("South", peopleFlowTextField);
        mainFrame.add("South", panel);
    }

    public void updateFloorNumber(int number) {
        floorTextField.setText("Floor: " + number);
    }

    public void updatePeopleInElevatorCount(int number) {
        peopleMovingTextField.setText("People in elevator: " + number);
    }

    public void updateDoorColor(boolean isOpen) {
        peopleMovingTextField.setBackground(isOpen ? Color.green : Color.red);
    }

    public void updatePeopleFlowNumbers(int left, int entered) {
        final String text = String.format("People left: %d People entered: %d", left, entered);
        peopleFlowTextField.setText(text);
    }
}
