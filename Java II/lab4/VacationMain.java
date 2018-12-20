/*
* File: VacationMain.java
* Author: Jonathan Rainwater
* Date: 2018-05-01
* Lab assignment 4 for Java II
*
* The purpose of this program is to launch a GUI that will allow the user to
* select between various vacation packages and then select various options
* within each package. Prices for each package and its options with be displayed
* as a result of the user modifying options for each package. The program
* continues until the user chooses to end the program.

 1. Create the necessary class-level variables that will be needed by all
    all innter classes.
 2. Create headerHbox that will hold the main navigation buttons for selecting
     between the various vacation packages.
 3. Create handlders for each button in headerHBox and register them.
 4. Create Images from local files related to each packege and load those Images
    into ImageViews.
 5. Create an HBox for each package to hold related ImageViews, load those
    ImageViews into the relevant HBox, and add that HBox into the top of a
    BorderPane for each package.
 6. Create a VBox for each package, add to them a TextField that contains the
    title of each package and a TextArea that contains detailed information
    about each package, and add each VBox to the top of its related BorderPane
    for each package.
 7. Add the inputVBox, which contains the ComboBoxes and its associated TextArea,
    to the right of its related BorderPane.
 8. Add the headerHBox with the main navigation buttons to the top of
    BOARDER_PANE_MAIN and add the BoarderPane related to the Devil's Courthouse
    package to the bottom of that pane.
 9. Set BOARDER_PANE_MAIN to a new Scene, set that Scene to the primaryStage,
    and show that stage.
*/
package lab4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VacationMain extends Application {
    // The pane that will be set to the primary scene and stage.
    private final BorderPane BORDER_PANE_MAIN = new BorderPane();
    // The four input/output panes for each package package.
    private InputVBox inputPaneDevil = new InputVBox("Number of people going: ",
        "Climbing instruction needed: ", "Camping rentals needed: ");
    private InputVBox inputPaneBahama = new InputVBox("Number of people going: ",
        "Scuba instruction needed: ");
    private InputVBox inputPaneColorado = new InputVBox("Number of people going: ",
    "Colorado");
    private InputVBox inputPaneBaron = new InputVBox("Number of people going: ",
    "Camping rentals needed: ");
    // Create BorderPanes that will hold information for each vacation package.
    private BorderPane inputBorderPaneDevil = new BorderPane();
    private BorderPane inputBorderPaneBahama = new BorderPane();
    private BorderPane inputBorderPaneColorado = new BorderPane();
    private BorderPane inputBorderPaneBaron = new BorderPane();
    // A String to fill in the dicount portion of the info text area.
    private static final String DISCOUNT_DEPOSIT = 
            "Discount:\t10% off the base charge for a"
            + " party of five or more.\n\n"
            + "Deposit:\tA 50% deposit is required to reserve the package.";
    // Presets for text area values.
    private static final short TA_MAX_WIDTH = 320;
    private static final byte TA_PREF_ROW_COUNT = 22;
    private static final String TF_TITLE_STYLE = "-fx-font-weight: bold;"
            + " -fx-font-size: 17; -fx-font-style: italic;";
    
    @Override // Override the start method in the Application class.
    public void start(Stage primaryStage) {
        // Create an HBox that contains the main navigation buttons that select
        // between the different vacation packages.
        HeaderHBox headerHBox = new HeaderHBox();
        // Create handlers for each Button in the HeaderHBox.
        // When clicked, they will replace the pane below them with a pnae that
        // contains information relevent to the button that was clicked.
        ShowPaneDevil handlerDevil = new ShowPaneDevil();
        headerHBox.getButton(0).setOnAction(handlerDevil);
        ShowPaneBahama handlerBahama = new ShowPaneBahama();
        headerHBox.getButton(1).setOnAction(handlerBahama);
        ShowPaneColorado handlerColorado = new ShowPaneColorado();
        headerHBox.getButton(2).setOnAction(handlerColorado);
        ShowPaneBaron handlerBaron = new ShowPaneBaron();
        headerHBox.getButton(3).setOnAction(handlerBaron);
        // Load images for each vacation package.
        Image devil_01 = new Image("img/Devil_01.jpg");
        Image devil_02 = new Image("img/Devil_02.png");
        Image bahama_01 = new Image("img/Bahama_01.jpg");
        Image bahama_02 = new Image("img/Bahama_02.jpg");
        Image colorado_01 = new Image("img/Colorado_01.jpg");
        Image colorado_02 = new Image("img/Colorado_02.png");
        Image baron_01 = new Image("img/Baron_01.jpg");
        Image baron_02 = new Image("img/Baron_02.jpg");
        // Place each image into an ImageView.
        ImageView devilView_01 = new ImageView(devil_01);
        ImageView devilView_02 = new ImageView(devil_02);
        ImageView bahamaView_01 = new ImageView(bahama_01);
        ImageView bahamaView_02 = new ImageView(bahama_02);
        ImageView coloradoView_01 = new ImageView(colorado_01);
        ImageView coloradoView_02 = new ImageView(colorado_02);
        ImageView baronView_01 = new ImageView(baron_01);
        ImageView baronView_02 = new ImageView(baron_02);
        // Create an HBox to hold related images for each vacation package.
        HBox devilViewHBox = new HBox(3);
        HBox bahamaViewHBox = new HBox(3);
        HBox coloradoViewHBox = new HBox(3);
        HBox baronViewHBox = new HBox(3);
        // Load each HBox.
        devilViewHBox.getChildren().addAll(devilView_01, devilView_02);
        bahamaViewHBox.getChildren().addAll(bahamaView_01, bahamaView_02);
        coloradoViewHBox.getChildren().addAll(coloradoView_01, coloradoView_02);
        baronViewHBox.getChildren().addAll(baronView_01, baronView_02);
        // Add each HBox into a pane that will hold all info for each vacation.
        inputBorderPaneDevil.setTop(devilViewHBox);
        inputBorderPaneBahama.setTop(bahamaViewHBox);
        inputBorderPaneColorado.setTop(coloradoViewHBox);
        inputBorderPaneBaron.setTop(baronViewHBox);
        // Create VBoxes to hold title and info for each vacation package.
        VBox devilInfoVBox = new VBox();
        VBox bahamaInfoVBox = new VBox();
        VBox coloradoInfoVBox = new VBox();
        VBox baronInfoVBox = new VBox();
        // Add a preloaded TextField and TestArea into each VBox.
        devilInfoVBox.getChildren().addAll(getDevilTitle(), getDevilInfo());
        bahamaInfoVBox.getChildren().addAll(getBahamaTitle(), getBahamaInfo());
        coloradoInfoVBox.getChildren().addAll(getColoradoTitle(), getColoradoInfo());
        baronInfoVBox.getChildren().addAll(getBaronTitle(), getBaronInfo());
        // Add the InfoVBoxes that to the BorderPane for each vacation package.
        inputBorderPaneDevil.setLeft(devilInfoVBox);
        inputBorderPaneBahama.setLeft(bahamaInfoVBox);
        inputBorderPaneColorado.setLeft(coloradoInfoVBox);
        inputBorderPaneBaron.setLeft(baronInfoVBox);
        // Add the InputVBoxs that contains ComboBoxes and its related TextAreas.
        inputBorderPaneDevil.setRight(inputPaneDevil);
        inputBorderPaneBahama.setRight(inputPaneBahama);
        inputBorderPaneColorado.setRight(inputPaneColorado);
        inputBorderPaneBaron.setRight(inputPaneBaron);
        
        // Add the HBox with the main navigation buttons to the primary pane.
        BORDER_PANE_MAIN.setTop(headerHBox);
        // Add the pane the contains all info for the first package to display.
        BORDER_PANE_MAIN.setBottom(inputBorderPaneDevil);
        Scene scene = new Scene(BORDER_PANE_MAIN);
        primaryStage.setTitle("Vacation Package Selector");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // Returns a TextField with a title for the Devil's Courthouse package.
    private static TextField getDevilTitle() {
        TextField tf = new TextField("Devil's Courthouse Adventure");
        tf.setEditable(false);
        tf.setStyle(TF_TITLE_STYLE);
        tf.setBackground(null);
        return tf;
    }
    
    // Returns a TextField with a title for the Bahama package.
    private static TextField getBahamaTitle() {
        TextField tf = new TextField("Bahama Scuba Diving");
        tf.setEditable(false);
        tf.setStyle(TF_TITLE_STYLE);
        tf.setBackground(null);
        return tf;
    }
    
    // Returns a TextField with a title for the Colorado Springs package.
    private static TextField getColoradoTitle() {
        TextField tf = new TextField("Colorado Sky Diving");
        tf.setEditable(false);
        tf.setStyle(TF_TITLE_STYLE);
        tf.setBackground(null);
        return tf;
    }
    
    // Returns a TextField with a title for the Baron Cliff package.
    private static TextField getBaronTitle() {
        TextField tf = new TextField("Baron Cliff Wilderness");
        tf.setEditable(false);
        tf.setStyle(TF_TITLE_STYLE);
        tf.setBackground(null);
        return tf;
    }
    
    // Returns a TextArea with info for the Devil's Courthouse package.
    private static TextArea getDevilInfo() {
        TextArea ta = new TextArea("Enjoy an action packed three-day weekend"
                + " of camping, rock climbing, and rappelling at Devil's"
                + " CourtHouse, North Carolina.\n\n"
                + "This getaway is for novices and experts alike.\n\n"
                + "Climbing instructors are available to"
                + " beginners at an optional low price.\n\n"
                + "Camping equipment rental is also available.\n\n"
                + "Rates:\n"
                + "Base Carge:\t$350 per person\n"
                + "Climbing Instruction:\t$100 per person\n"
                + "Equipment Rental:\t$40 per person per day\n\n"
                + DISCOUNT_DEPOSIT);
        ta.setMaxWidth(TA_MAX_WIDTH);
        ta.setWrapText(true);
        ta.setPrefRowCount(TA_PREF_ROW_COUNT);
        ta.setEditable(false);
        return ta;
    }
    
    // Returns a TextArea with info for the Bahama package.
    private static TextArea getBahamaInfo() {
        TextArea ta = new TextArea("Enjoy a week-long cruise to Bahama with"
                + " three days of scuba diving.\n\n"
                + "Those with prior experience may dive right in, while"
                + " beginners should choose to take the optional but very"
                + " affordable lesson.\n\n"
                + "Rates:\n"
                + "Base Carge:\t$1,000 per person\n"
                + "Scuba Instruction:\t$100 per person\n\n"
                + DISCOUNT_DEPOSIT);
        ta.setMaxWidth(TA_MAX_WIDTH);
        ta.setWrapText(true);
        ta.setPrefRowCount(TA_PREF_ROW_COUNT);
        ta.setEditable(false);
        return ta;
    }
    
    // Returns a TextArea with info for the Colorado Springs package.
    private static TextArea getColoradoInfo() {
        TextArea ta = new TextArea("Enjoy four thrilling days with expert"
                + " skydiving instructors in Colorado Springs.\n\n"
                + "For lodging, you may choose either the Wilderness Lodge or"
                + " Luxury Inn.\n\n"
                + "Rates:\n"
                + "Base Carge:\t$400 per person\n"
                + "Wilderness Lodge:\t$65 per person per day\n"
                + "Luxury Inn:\t$120 per person per day\n\n"
                + DISCOUNT_DEPOSIT);
        ta.setMaxWidth(TA_MAX_WIDTH);
        ta.setWrapText(true);
        ta.setPrefRowCount(TA_PREF_ROW_COUNT);
        ta.setEditable(false);
        return ta;
    }
    
    // Returns a TextArea with info for the Baron Cliff package.
    private static TextArea getBaronInfo() {
        TextArea ta = new TextArea("Enjoy eight days hiking and exploring caves"
                + " in the Barron Cliff Wilderness in Tennessee.\n\n"
                + "Camping equipment rental is available.\n\n"
                + "Rates:\n"
                + "Base Carge:\t$700 per person\n"
                + "Equipment Rental:\t$40 per person per day\n\n"
                + DISCOUNT_DEPOSIT);
        ta.setMaxWidth(TA_MAX_WIDTH);
        ta.setWrapText(true);
        ta.setPrefRowCount(TA_PREF_ROW_COUNT);
        ta.setEditable(false);
        return ta;
    }
    
    // For launching the program.
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    // An event handler for the buttons in the HeaderHBox.
    // This will replace the pane below those buttons with a pane that contains
    // information relevent to the button that was clicked.
    class ShowPaneDevil implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            BORDER_PANE_MAIN.setBottom(inputBorderPaneDevil);
        }
    }
    
    // An event handler for the buttons in the HeaderHBox.
    // This will replace the pane below those buttons with a pane that contains
    // information relevent to the button that was clicked.
    class ShowPaneBahama implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            BORDER_PANE_MAIN.setBottom(inputBorderPaneBahama);
        }
    }
    
    // An event handler for the buttons in the HeaderHBox.
    // This will replace the pane below those buttons with a pane that contains
    // information relevent to the button that was clicked.
    class ShowPaneColorado implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            BORDER_PANE_MAIN.setBottom(inputBorderPaneColorado);
        }
    }
    
    // An event handler for the buttons in the HeaderHBox.
    // This will replace the pane below those buttons with a pane that contains
    // information relevent to the button that was clicked.
    class ShowPaneBaron implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            BORDER_PANE_MAIN.setBottom(inputBorderPaneBaron);
        }
    }

}