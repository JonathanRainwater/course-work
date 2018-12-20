/*
* File: VacationMain.java
* Author: Jonathan Rainwater
* Date: 2018-05-01
* Lab assignment 4 for Java II
*
* This class creates ComboBoxes and a TextArea, where the values selected in
* the ComboBoxes determine the output diplayed in the TextArea.
 */
package lab4;

import java.text.DecimalFormat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

public class InputVBox extends VBox {
    // Strings to store the contents of the constructor's parameters.
    private String str1;
    private String str2;
    private String str3;
    // ComboBoxes that will allow user to select various vacation options.
    private ComboBox<String> cbo1;
    private ComboBox<String> cbo2;
    private ComboBox<String> cbo3;
    // A TextArea to sidplay info and pricing details for the package.
    private TextArea textArea;
    // The TextArea's prefered row count.
    private final byte TA_PREF_ROW_COUNT = 11;
    // A handler that will reset the text area to reflect changes from user input.
    private ResetTextArea taHandler = new ResetTextArea();
    // Variables to hold the original cost and total cost of each package option.
    private short baseCharge;
    private int totalBaseCharge;
    private final byte INSTRUCTION_COST = 100;
    private int totalInstructionCost;
    private final byte EQUIPMENT_COST = 40;
    private int totalEquipmentCost;
    private final byte LODGING_WILDERNESS = 65;
    private final byte LODGING_INN = 120;
    private int totalLodgingCost;
    private final float DISCOUNT = .1F;
    private int totalDiscount;
    private int totalCharge;
    private final float DEPOSIT = .5F;
    private int totalDeposit;
     // To format numbers into proper dollar-format.
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("$0.00");
    
    // Constructs an InputVBox that has two ComboBoxes.
    // The given Strings will be used to label the ComboBoxes.
    public InputVBox(String str1, String str2) {
        super(10); // Set spacing.
        this.str1 = str1;
        this.str2 = str2;
        
        // Set the cost of the baseCharge based on the vacation package.
        if (str2.contains("Climbing")) {
            // This is the Devil's Courthouse package.
            baseCharge = 350;
        }
        else if (str2.contains("Scuba")) {
            // This is the Bahama package.
            baseCharge = 1000;
        }
        else if (str2.matches("Colorado")) {
            // This is the Colorado Springs package.
            baseCharge = 400;
        }
        else if (str2.contains("Camping")) {
            // This is the Baron Cliff package.
            baseCharge = 700;
        }
        
        // Create ComboBox 1 and add to a Label.
        cbo1 = new ComboBox();
        cbo1.setOnAction(taHandler); // Register with handler.
        // Create values for the ComboBox, number 1 through 20
        for (byte i=1; i <= 20; i++) {
            cbo1.getItems().add(String.valueOf(i));
        }
        cbo1.setValue("1");
        // Add to label.
        Label lbl1 = new Label(str1,
                cbo1);
        lbl1.setContentDisplay(ContentDisplay.RIGHT);
        
        // Create ComboBox 2 and add to a label.
        Label lbl2;
        cbo2 = new ComboBox();
        cbo2.setOnAction(taHandler); // Register with handler.
        // The contents of the ComboBox for Colorado is unique.
        if (str2.equals("Colorado")) {
            // This ComboBox is for the Colorado package.
            cbo2.getItems().addAll("Luxury Inn", "Wilderness Lodge");
            cbo2.setValue("Luxury Inn");
            // Add the label.
            lbl2 = new Label("Choose lodging option",
                cbo2);
            this.str2 = "Lodging option: "; // For textArea reference.
        }
        else {
            // This ComboBox is for all other packages.
            // Create values for the ComboBox, number 0 through 20
            for (byte i=0; i <= 20; i++) {
                cbo2.getItems().add(String.valueOf(i));
            }
            cbo2.setValue("0");
            // Add to label.
            lbl2 = new Label(str2,
                cbo2);
        }
        lbl2.setContentDisplay(ContentDisplay.RIGHT);
        
        // Add label 1 and 2 to InputVBox.
        getChildren().addAll(lbl1, lbl2);
        
        // Add a textArea to InutVBox.
        // Start by displaying the base charge.
        totalBaseCharge = (Byte.parseByte(cbo1.getValue()) * baseCharge);
        textArea = new TextArea(str1 + cbo1.getValue() + "\n"
        + this.str2 + cbo2.getValue() + "\n\n"
        + "Base Charge: "
        + MONEY_FORMAT.format(totalBaseCharge));
        
        // Display insctuction, equipment, and lodging cost.
        if (str2.contains("Scuba")) {
            // This is the Bahama package.
            totalInstructionCost = (Byte.parseByte(cbo2.getValue()) * INSTRUCTION_COST);
            textArea.appendText("\nInstruction Cost: "
            + MONEY_FORMAT.format(totalInstructionCost));
        }
        else if (this.str2.contains("Lodging")) {
            // This is the Colorado package.
            if (cbo2.getValue().contains("Wilderness")) {
                // This is the Wilderness option.
                totalLodgingCost = (LODGING_WILDERNESS * 4
                            * Short.valueOf(cbo1.getValue()));
                textArea.appendText("\nLodging Option: "
                        + MONEY_FORMAT.format(totalLodgingCost));
            }
            else if (cbo2.getValue().contains("Luxury")) {
                // This is the luxury option.
                totalLodgingCost = (LODGING_INN * 4
                            * Short.valueOf(cbo1.getValue()));
                textArea.appendText("\nLodging Option: "
                        + MONEY_FORMAT.format(totalLodgingCost));
            }
        }
        else if (str2.contains("Camping")) {
                // This is the Baron Cliff package.
                totalEquipmentCost = (Byte.parseByte(cbo2.getValue())
                        * EQUIPMENT_COST * 8);
                textArea.appendText("\nEquipment Cost: "
                + MONEY_FORMAT.format(totalEquipmentCost));
        }
        // Display discount.
        textArea.appendText("\n\nDiscount: ");
        if (Byte.valueOf(cbo1.getValue()) >= 5) {
            // The discount applies.
            totalDiscount = (int)(DISCOUNT * totalBaseCharge);
            textArea.appendText(MONEY_FORMAT.format(totalDiscount));
        }
        else {
            // The discount does not apply.
            totalDiscount = 0;
            textArea.appendText(MONEY_FORMAT.format(totalDiscount));
        }
        // Display the total charge.
        totalCharge = (totalBaseCharge + totalInstructionCost
                + totalEquipmentCost  +totalLodgingCost - totalDiscount) ;
        textArea.appendText("\nTotal Charge: " + MONEY_FORMAT.format(totalCharge));
        //Display deposit required.
        totalDeposit = (int)(totalCharge * DEPOSIT);
        textArea.appendText("\nDeposit Required: " + 
                MONEY_FORMAT.format(totalDeposit));
        
        // Settings for all instances.
        textArea.setPrefRowCount(TA_PREF_ROW_COUNT);
        textArea.setMaxWidth(320);
        textArea.setEditable(false);
        setAlignment(Pos.TOP_RIGHT);
        getChildren().add(textArea); 
    }
    
    // Constructs an InputVBox that has three ComboBoxes.
    // The given Strings will be used to label the ComboBoxes.
    public InputVBox(String str1, String str2, String str3) {
        // User the first contructor the handle the first two parameters.
        this(str1, str2);
        this.str3 = str3;
        
        // Create ComboBox 3 and add to a Label.
        cbo3 = new ComboBox();
        cbo3.setOnAction(taHandler); // Register with handler.
        // Create values for the ComboBox, number 0 through 20
        for (byte i=0; i <= 20; i++) {
            cbo3.getItems().add(String.valueOf(i));
        }
        cbo3.setValue("0");
        // Add to a label.
        Label lbl3 = new Label(str3,
                cbo3);
        lbl3.setContentDisplay(ContentDisplay.RIGHT);
        // Add label 3 to inputVBox.
        getChildren().remove(textArea);
        getChildren().add(lbl3);
        
        // Add textArea to InutVBox.
        // Start by displaying the base charge.
        totalBaseCharge = (Byte.parseByte(cbo1.getValue()) * baseCharge);
        totalInstructionCost = (Byte.parseByte(cbo2.getValue()) * INSTRUCTION_COST);
        totalEquipmentCost = (Byte.parseByte(cbo3.getValue()) * EQUIPMENT_COST
                * 3);
        textArea = new TextArea(str1 + cbo1.getValue() + "\n"
        + str2 + cbo2.getValue() + "\n"
        + str3 + cbo3.getValue() + "\n\n"
        + "Base Charge: "
        + MONEY_FORMAT.format(totalBaseCharge));
        if (str2.contains("Climbing")) {
            // This is the Devil's Courthouse package.
            textArea.appendText("\nInstruction Cost: "
            + MONEY_FORMAT.format(totalInstructionCost)
            + "\nEquipment Cost: "
                + MONEY_FORMAT.format(totalEquipmentCost));
        }
        // Display discount.
        textArea.appendText("\n\nDiscount: ");
        if (Byte.valueOf(cbo1.getValue()) >= 5) {
            // The discount applie.
            totalDiscount = (int)(DISCOUNT * totalBaseCharge);
            textArea.appendText(MONEY_FORMAT.format(totalDiscount));
        }
        else {
            // The discount does not apply.
            totalDiscount = 0;
            textArea.appendText(MONEY_FORMAT.format(totalDiscount));
        }
        // Display the total charge.
        totalCharge = (totalBaseCharge + totalInstructionCost
                + totalEquipmentCost  +totalLodgingCost - totalDiscount) ;
        textArea.appendText("\nTotal Charge: " + MONEY_FORMAT.format(totalCharge));
        //Display deposit required.
        totalDeposit = (int)(totalCharge * DEPOSIT);
        textArea.appendText("\nDeposit Required: " + 
                MONEY_FORMAT.format(totalDeposit));

        textArea.setPrefRowCount(TA_PREF_ROW_COUNT);
        textArea.setMaxWidth(320);
        textArea.setEditable(false);
        getChildren().add(textArea);
    }
    
    // Returns a ComboBox as determined by the given number.
    public ComboBox getCbo(int i) {
        switch (i) {
            case 1: return cbo1;
            case 2: return cbo2;
            case 3: return cbo3;
        }
        return null;
    }
    
    // An event handler that runs every time a ComboBox value is changed.
    // PartA checks that the selected cbo value is valid and changes
    // it if it is not valid.
    // PartB resets the TextArea to reflect the
    // values that where changed in the source cbo.
    class ResetTextArea implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            // PartA
            // Reset cbo2 and cbo3 if they are over the value of cbo1.
            if (e.getSource().equals(cbo1) && ! str2.contains("Lodging")) {
                // cbo1 was the event source and the package is not Colorado.
                if (Integer.valueOf(cbo1.getValue()) < Integer.valueOf(cbo2.getValue())) {
                    // cbo1 was lowered below cbo2, so lower cbo2.
                    cbo2.setValue(cbo1.getValue());
                }
                if (cbo3 != null &&
                        Integer.valueOf(cbo1.getValue()) < Integer.valueOf(cbo3.getValue())) {
                    // cbo3 has been initialized and cbo1 was lowered below cbo3,
                    // so lower cbo3.
                    cbo3.setValue(cbo1.getValue());
                }
            }
            else if (e.getSource().equals(cbo2) && ! str2.contains("Lodging")) {
                // cbo2 was the source and the package is not Colorado.
                if (Integer.valueOf(cbo2.getValue()) > Integer.valueOf(cbo1.getValue())) {
                    // cbo2 was raised above cbo1, so lower cbo2.
                    Platform.runLater(new ChangeCbo2()); // To avoid an exception.
                }
            }
            else if (e.getSource().equals(cbo3) &&
                    Integer.valueOf(cbo3.getValue()) > Integer.valueOf(cbo1.getValue())) {
                // cbo3 was the source. cbo3 was raise above cbo1, so lower cbo3.
                Platform.runLater(new ChangeCbo3()); // To avoid an exception.
            }
            
            // PartB
            // Reset the TextArea.
            // Start by adding information related to the ComboBox values.
            totalBaseCharge = (Byte.parseByte(cbo1.getValue()) * baseCharge);
            if (str3 != null) {
                // Package is Devil's Courthouse.
                getChildren().remove(textArea);
                textArea = new TextArea(str1 + cbo1.getValue() + "\n"
                        + str2 + cbo2.getValue() + "\n"
                        + str3 + cbo3.getValue() + "\n\n"
                        + "Base Charge: "
                        + MONEY_FORMAT.format(totalBaseCharge));
                                getChildren().add(textArea);
            }
            else {
                // Package is not Devil's Courthouse.
                getChildren().remove(textArea);
                textArea = new TextArea(str1 + cbo1.getValue() + "\n"
                        + str2 + cbo2.getValue() + "\n\n"
                        + "Base Charge: "
                        + MONEY_FORMAT.format(totalBaseCharge));
                getChildren().add(textArea);
            }
            
            // Add the remaining information to the TextArea.
            if (str2.contains("Climbing") || str2.contains("Scuba")) {
                // This is either Devil's Courthouse or Bahama.
                totalInstructionCost = (Byte.parseByte(cbo2.getValue()) * INSTRUCTION_COST);
                textArea.appendText("\nInstruction Cost: "
                + MONEY_FORMAT.format(totalInstructionCost));
            }
            if (str2.contains("Climbing")) {
                // This is the Devil's Courthouse package.
                // Display equipment cost for three days.
                totalEquipmentCost = (Byte.parseByte(cbo3.getValue()) *
                        EQUIPMENT_COST * 3);
                textArea.appendText("\nEquipment Cost: "
                + MONEY_FORMAT.format(totalEquipmentCost));
            }
            else if (str2.contains("Lodging")) {
                // This is the Colorado package.
                // Display lodging cost for four days per person.
                if (cbo2.getValue().contains("Wilderness")) {
                    totalLodgingCost = (LODGING_WILDERNESS * 4
                            * Short.valueOf(cbo1.getValue()));
                    textArea.appendText("\nLodging Option: "
                            + MONEY_FORMAT.format(totalLodgingCost));
                }
                else if (cbo2.getValue().contains("Luxury")) {
                    totalLodgingCost = (LODGING_INN * 4
                            * Short.valueOf(cbo1.getValue()));
                    textArea.appendText("\nLodging Option: "
                            + MONEY_FORMAT.format(totalLodgingCost));
                }
            }
            else if (str2.contains("Camping")) {
                // This is the Baron Cliff package.
                // Display equipment cost for eight days.
                totalEquipmentCost = (Byte.parseByte(cbo2.getValue()) *
                        EQUIPMENT_COST * 8);
                textArea.appendText("\nEquipment Cost: "
                    + MONEY_FORMAT.format(totalEquipmentCost));
            }
            // Display discount.
            textArea.appendText("\n\nDiscount: ");
            if (Byte.valueOf(cbo1.getValue()) >= 5) {
                totalDiscount = (int)(DISCOUNT * totalBaseCharge);
                textArea.appendText(MONEY_FORMAT.format(totalDiscount));
            }
            else {
                totalDiscount = 0;
                textArea.appendText(MONEY_FORMAT.format(totalDiscount));
            }
            // Display the total charge.
            totalCharge = (totalBaseCharge + totalInstructionCost
                    + totalEquipmentCost  +totalLodgingCost - totalDiscount) ;
            textArea.appendText("\nTotal Charge: " + MONEY_FORMAT.format(
                    totalCharge));
            //Display deposit required.
            totalDeposit = (int)(totalCharge * DEPOSIT);
            textArea.appendText("\nDeposit Required: " + 
                    MONEY_FORMAT.format(totalDeposit));
            
            textArea.setPrefRowCount(TA_PREF_ROW_COUNT);
            textArea.setMaxWidth(320);
            textArea.setEditable(false);
        }
    
    }
    
    // A class to deley the setting of cbo2 to avoid an exception being thrown.
    class ChangeCbo2 implements Runnable {
        @Override
        public void run() {
            cbo2.setValue(cbo1.getValue());
        }
    }
    
    // A class to deley the setting of cbo3 to avoid an exception being thrown.
    class ChangeCbo3 implements Runnable {
        @Override
        public void run() {
            cbo3.setValue(cbo1.getValue());
        }
    }
}
