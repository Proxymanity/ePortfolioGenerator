
package ePG.view;

import javafx.scene.layout.VBox;
import ePG.model.Page;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *This UI component has the controls for editing a single page
 * Left are controls, right is the components
 */
public class PageEditView extends BorderPane{
    Page page;
    GeneratorView ui;
    VBox controls;
    Button changeTitle;
    Button changeName;
    Button changeLayout;
    Button changeColor; 
    Button changeBannerImage;
    Button changeFooter;
    Button AddHeader;
    Button AddText;
    Button AddImage;
    Button AddVideo;
    Button AddSlideshow;
    Button AddLink;
    
    
    PageEditView(GeneratorView initUI){
        ui = initUI;
        controls = new VBox();
        initControls();
        initPage();
        this.setLeft(controls);
        this.setRight(page.load());
    }
    
    private void initControls(){
        changeTitle = initChildButton(controls,"Change Title","CSS",false);
        changeName = initChildButton(controls, "Change Name", "CSS", false);
        changeLayout = initChildButton(controls, "Change Layout", "CSS", false);
        changeBannerImage = initChildButton(controls, "Change Name", "CSS", false);
        changeFooter = initChildButton(controls, "Change Footer", "CSS", false);
        AddHeader = initChildButton(controls, "Add Header", "CSS", false);
        AddText = initChildButton(controls, "Add Text", "CSS", false);
        AddImage = initChildButton(controls, "Add Image", "CSS", false);
        AddVideo = initChildButton(controls, "Add Video", "CSS", false);
        
        initHandlers();
    }
    
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
    private void initHandlers(){
     AddHeader.setOnAction(e ->{
        HeaderDialouge(); 
     });
        
     AddText.setOnAction(e ->{
         TextDialouge();
     });
     
     AddImage.setOnAction(e -> {
         ImageDialouge();
     });
     
     
    }
    
  
    private void initPage() {
        page = new Page();
    }

      private void TextDialouge(){
        Stage dialouge = new Stage();
        HBox buttonBox = new HBox(); 
        Button Hyperlink = initChildButton(buttonBox, "Insert Hyperlink", "CSS",false);
        Button changeTextFont = initChildButton(buttonBox, "Select Font", "CSS", false);
         
         
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialouge.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextArea input = new TextArea();
        mainPane.getChildren().addAll(input, Accept);
        Scene scene = new Scene(mainPane, 250, 200);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialouge.setTitle("Text");
        dialouge.setScene(scene);
        dialouge.showAndWait();
    }

    
    private void HeaderDialouge() {
        Stage dialouge = new Stage();
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialouge.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextField input = new TextField();
        mainPane.getChildren().addAll(input, Accept);
        Scene scene = new Scene(mainPane, 250, 200);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialouge.setTitle("Header");
        dialouge.setScene(scene);
        dialouge.showAndWait();
    }

    private void ImageDialouge() {
        Stage dialouge = new Stage();
        HBox SelectImage = new HBox(); 
        Button Select = initChildButton(SelectImage, "Select Image", "CSS",false);
         
         
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialouge.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextArea input = new TextArea();
        mainPane.getChildren().addAll(input, Accept);
        Scene scene = new Scene(mainPane, 550, 500);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialouge.setTitle("Text");
        dialouge.setScene(scene);
        dialouge.showAndWait();
    }
    
}
