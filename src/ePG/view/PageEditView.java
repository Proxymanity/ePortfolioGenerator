
package ePG.view;

import javafx.scene.layout.VBox;
import ePG.model.Page;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
        HeaderDialog(); 
     });
        
     AddText.setOnAction(e ->{
         TextDialog();
     });
     
     AddImage.setOnAction(e -> {
         ImageDialog();
     });
     
     
    }
    
  
    private void initPage() {
        page = new Page();
    }

      private void TextDialog(){
        Stage dialog = new Stage();
        HBox buttonBox = new HBox(); 
        Button Hyperlink = initChildButton(buttonBox, "Insert Hyperlink", "CSS",false);
        
                
        final ToggleGroup font = new ToggleGroup();
        RadioButton font1 = new RadioButton("Font1");
        font1.setToggleGroup(font);

        RadioButton font2 = new RadioButton("Font 2");
        font2.setToggleGroup(font);
        font2.setSelected(true);
 
        RadioButton font3 = new RadioButton("Font 3");
        font3.setToggleGroup(font);
        
        RadioButton font4 = new RadioButton("Font 4");
        font4.setToggleGroup(font);
        
        RadioButton font5 = new RadioButton("Font 5");
        font5.setToggleGroup(font);
        
        HBox fontBox = new HBox();
        fontBox.getChildren().addAll(font1,font2,font3,font4,font5);
        
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextArea input = new TextArea();
        
         Hyperlink.setOnAction(new EventHandler<ActionEvent>(){
            @Override
             public void handle(ActionEvent e){  
                String text = input.getText();
                String selected = input.getSelectedText();
                HyperlinkDialog(text,selected);
                }
        });
        mainPane.getChildren().addAll(buttonBox,fontBox,input, Accept);
        Scene scene = new Scene(mainPane, 400, 250);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Text");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    
    private void HeaderDialog() {
        Stage dialog = new Stage();
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextField input = new TextField();
        mainPane.getChildren().addAll(input, Accept);
        Scene scene = new Scene(mainPane, 250, 200);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Header");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void ImageDialog() {
        Stage dialog = new Stage();
        HBox SelectImage = new HBox(); 
        Button Select = initChildButton(SelectImage, "Select Image", "CSS",false);
        
        HBox captionBox = new HBox();
        Label captionLabel = new Label("Caption : ");
        TextField caption = new TextField();
        captionBox.getChildren().addAll(captionLabel, caption);
        
        HBox HeightBox = new HBox();
        Label heightLabel = new Label("Height :  ");
        TextField height = new TextField();
        HeightBox.getChildren().addAll(heightLabel, height);
        
        HBox WidthBox = new HBox();
        Label widthLabel = new Label("Width :   ");
        TextField width = new TextField();
        WidthBox.getChildren().addAll(widthLabel, width);
                
        final ToggleGroup alignment = new ToggleGroup();
        RadioButton left = new RadioButton("Left Align");
        left.setToggleGroup(alignment);

        RadioButton middle = new RadioButton("Center Align");
        middle.setToggleGroup(alignment);
        middle.setSelected(true);
 
        RadioButton right = new RadioButton("Right Align");
        right.setToggleGroup(alignment);
        
        HBox alignmentBox = new HBox();
        alignmentBox.getChildren().addAll(left, middle, right);
        
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(SelectImage, captionBox, HeightBox, WidthBox, alignmentBox, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Text");
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    public void HyperlinkDialog(String text, String selected){
       Stage dialoug = new Stage();
        Label textArea = new Label("Your text is : " + text);
        Label selectedText = new Label("Your selected text is : " + selected);

        Label linkLabel = new Label("Enter Link :");
        TextField link = new TextField();
        HBox linkBox = new HBox();
        linkBox.getChildren().addAll(linkLabel, link);
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialoug.close();
                }
            });
        mainPane.getChildren().addAll(textArea, selectedText, linkBox, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialoug.setTitle("HyperLink");
        dialoug.setScene(scene);
        dialoug.showAndWait(); 
    }
}
