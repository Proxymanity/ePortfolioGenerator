
package ePG.view;

import javafx.scene.layout.VBox;
import ePG.model.Page;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
/**
 *This UI component has the controls for editing a single page
 * Left are controls, right is the components
 */
public class PageEditView extends BorderPane{
    GeneratorView ui;
    VBox controls;
    Button changeTitle;
    Button changeName;
    Button changeLayout;
    Button changeColor; 
    Button changeBannerImage;
    Button changeFooter;
    Button AddText;
    Button AddImage;
    Button AddVideo;
    Button AddSlideshow;
    Button AddLink;
    
    
    PageEditView(GeneratorView initUI){
        ui = initUI;
        controls = new VBox();
        initControls();
        this.setLeft(controls);
    }
    
    private void initControls(){
        changeTitle = initChildButton(controls,"Change Title","CSS",false);
        changeName = initChildButton(controls, "Change Name", "CSS", false);
        changeLayout = initChildButton(controls, "Change Layout", "CSS", false);
        changeBannerImage = initChildButton(controls, "Change Name", "CSS", false);
        changeFooter = initChildButton(controls, "Change Footer", "CSS", false);
        AddText = initChildButton(controls, "Add Text", "CSS", false);
        AddImage = initChildButton(controls, "Add Image", "CSS", false);
        AddVideo = initChildButton(controls, "Add Video", "CSS", false);
        
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
        
    }
    
    private void TextDialouge(){
        
    }
    
}
