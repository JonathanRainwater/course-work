/*
* File: HeaderHBox.java
* Author: Jonathan Rainwater
* Date: 2018-05-01
* Lab assignment 4 for Java II
*
* This class creates an HBox that contains four buttons that are labeled with
* each vacation package.
 */
package lab4;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class HeaderHBox extends HBox{
    // Create four buttons, one for each package type.
    private Button[] headerButtons = { new Button("Devil's Courthouse"),
        new Button("Bahama"), new Button("Colorado Springs"),
        new Button("Baron Cliff")};
    
    // Constructs an HEaderHBox and adds buttons to it.
    public HeaderHBox(){
        super(10); // Sets the inherited HBox to have a spacing of 10.
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5,0,10,0));
        getChildren().addAll(headerButtons[0], headerButtons[1],
            headerButtons[2],headerButtons[3]);
    }
    
    // Returns a Button that is in the headerButtons array, as determined by
    // the given array index number
    public Button getButton(int i) {
        return headerButtons[i];
    }
}
