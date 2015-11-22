/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.view;

import ePG.controller.FileController;
import ePG.controller.GeneratorEditController;
import ePG.file.GeneratorFileManager;
import ePG.model.Site;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Cokers
 */
public class GeneratorView {
    Stage primaryStage;
    Scene primaryScene;
    
    
    BorderPane GenPane;
    

    
     //Workspace
    BorderPane Workspace;
    
    //FileToolBar
    FlowPane fileToolbarPane;
    Button newSiteButton;
    Button LoadSiteButton;
    Button SaveSiteButton;
    Button SaveAsSiteButton;
    Button ExportSiteButton;
    Button ExitButton;
    
    
    TabPane editOrView;
    Tab edit;
    Tab view;
    //Thiis is the site we're working on
    Site site;
    
    //SiteToolbar
    BorderPane siteToolbarPane;
    FlowPane pageAddRemove;
    TabPane pageSelect;
    Button AddPage;
    Button RemovePage;
    
    //This is for saving and loading sites
    GeneratorFileManager fileManager;
    
    // Routes the proper responses associated with the FileToolbar
    private FileController fileController;
    
    //This controller responds to page edit buttons
    private GeneratorEditController editController;
    
    
    
    public GeneratorView(GeneratorFileManager initFileManager){ 
        	// FIRST HOLD ONTO THE FILE MANAGER
	fileManager = initFileManager;
	
	// MAKE THE DATA MANAGING MODEL
	site = new Site(this);

	
    }
    
    public void startUI(Stage initPrimaryStage, String windowTitle) {
        initFileToolbar();
        initSiteToolbar();
        initWorkspace();
        initEventHandlers();
        primaryStage = initPrimaryStage;
        initWindow(windowTitle);
        
    }
    
    private void initWindow(String windowTitle) {
	// SET THE WINDOW TITLE
	primaryStage.setTitle(windowTitle);

	// GET THE SIZE OF THE SCREEN
	Screen screen = Screen.getPrimary();
	Rectangle2D bounds = screen.getVisualBounds();

	// AND USE IT TO SIZE THE WINDOW
	primaryStage.setX(bounds.getMinX());
	primaryStage.setY(bounds.getMinY());
	primaryStage.setWidth(bounds.getWidth());
	primaryStage.setHeight(bounds.getHeight());

        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER 
        GenPane = new BorderPane();
        GenPane.setTop(fileToolbarPane);
        
	primaryScene = new Scene(GenPane);
        
       // primaryScene = new Scene(ssmPane);
        
        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
	// WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
        
	//primaryScene.getStylesheets().add(STYLE_SHEET_UI);
        
      //  primaryScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
      //       @Override
      //       public void handle(MouseEvent mouseEvent) {
     //            fileController
  //  }
  //  });
	primaryStage.setScene(primaryScene);
     //  primaryStage.getIcons().add(new Image("logo.png"));   // Logo is in src folder
        System.out.println(editOrView.getSelectionModel().getSelectedIndex());
	primaryStage.show();
    }

    public FileController getFileController() {
        return this.fileController;
    }

 

    private void initFileToolbar() {
       fileToolbarPane = new FlowPane();
       
       newSiteButton = initChildButton(fileToolbarPane, "NEW BUTTON", "CSS", false);
       LoadSiteButton = initChildButton(fileToolbarPane, "Load BUTTON", "CSS", false);
       SaveSiteButton = initChildButton(fileToolbarPane, "Save BUTTON", "CSS", true);
       SaveAsSiteButton = initChildButton(fileToolbarPane, "SaveAs BUTTON", "CSS", true);
       ExportSiteButton = initChildButton(fileToolbarPane, "Export BUTTON", "CSS", true);
       ExitButton = initChildButton(fileToolbarPane, "Exit BUTTON", "CSS", false);
       
       
    }

    private void initEventHandlers() {
        //TODO
        fileController = new FileController(this, fileManager);
        //Set New and Load buttons to run updateToolbar
        newSiteButton.setOnAction(e -> {
         fileController.handleNewSiteRequest();
        });
        
        LoadSiteButton.setOnAction(e -> {
         fileController.handleLoadSiteRequest();
        });
        
        
        AddPage.setOnAction(e -> {
           Tab newPage = new Tab();
           PageEditView pev = new PageEditView(this);
           newPage.setContent(pev);
           pageSelect.getTabs().add(newPage);
           
        //   edit.setContent(siteToolbarPane);
         //  if(pageSelect.getTabs().size()== 1){
          //  Pane test = new Pane();
          //  test.getChildren().add(new Label("Hello"));

           //    if(test.getChildren().size() == 1){
           //        System.out.println("test is 1!");
           //        editOrView.getSelectionModel().select(view);
           //    }
        //   }
        });
        
        RemovePage.setOnAction(e -> {
            int selected = pageSelect.getSelectionModel().getSelectedIndex();
            pageSelect.getTabs().remove(selected);
        });
        
        ExportSiteButton.setOnAction(e -> {
           editOrView.getTabs().get(1).setDisable(false);
        });
        
        ExitButton.setOnAction(e -> {
         System.exit(0);
        });
    }
    
    /**
     * This helps initialize buttons in a toolbar, constructing a custom button
     * with a customly provided icon and tooltip, adding it to the provided
     * toolbar pane, and then returning it.
     */
    public Button initChildButton(
	    Pane toolbar, 
	    String iconFileName, 
	    String cssClass,
	    boolean disabled) {
	//String imagePath = "file:" + PATH_ICONS + iconFileName;
	//Image buttonImage = new Image(imagePath);
	Button button = new Button();
	//button.getStyleClass().add(cssClass);
        button.setText(iconFileName);
	button.setDisable(disabled);
	//button.setGraphic(new ImageView(buttonImage));
	//Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
	//button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;
    }

    private void initWorkspace() { 
        Workspace = new BorderPane();
        editOrView = new TabPane();
        editOrView.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        Tab edit = new Tab();
        Tab view = new Tab();
        edit.setText("Edit");
        view.setText("View");
        edit.setContent(siteToolbarPane);
        

        view.setDisable(true);
        editOrView.getTabs().addAll(edit, view);
        
        editOrView.getSelectionModel().select(edit);
        Workspace.setTop(editOrView);
        
   
    }
        //Should be hidden until a ePortfolio is created or loaded.
    private void initSiteToolbar() {
        siteToolbarPane = new BorderPane();
        pageAddRemove = new FlowPane();
        pageSelect = new TabPane();  
        
        AddPage = initChildButton(pageAddRemove, "Add BUTTON", "CSS", true);
        RemovePage = initChildButton(pageAddRemove, "Remove BUTTON", "CSS", true);
        
        Tab newPage = new Tab();
           PageEditView pev = new PageEditView(this);
           newPage.setContent(pev);
           pageSelect.getTabs().add(newPage);
        //Right now, PageSelect pane is empty
        siteToolbarPane.setTop(pageAddRemove);
        siteToolbarPane.setCenter(pageSelect);
        
        
    }
    
    public void updateToolbarControls() {
	// FIRST MAKE SURE THE WORKSPACE IS THERE
	GenPane.setCenter(Workspace);
	
	// NEXT ENABLE/DISABLE BUTTONS AS NEEDED IN THE FILE TOOLBAR

	SaveSiteButton.setDisable(false);
        SaveAsSiteButton.setDisable(false);
        ExportSiteButton.setDisable(false);
	
	// AND THE SLIDESHOW EDIT TOOLBAR
	AddPage.setDisable(false);
        RemovePage.setDisable(false);
    }
    
    public void refresh(){
        
    }
}
