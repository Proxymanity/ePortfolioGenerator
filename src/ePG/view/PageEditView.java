
package ePG.view;

import javafx.scene.layout.VBox;
import ePG.model.Page;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
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

import ssm.SlideShowMaker;

/**
 *This UI component has the controls for editing a single page
 * Left are controls, right is the components
 */
public class PageEditView extends HBox{
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
    Button AddList;
    
    
    PageEditView(GeneratorView initUI){
        ui = initUI;
        controls = new VBox();
        initControls();
        initPage();
        this.getChildren().addAll(controls, page.load());
    }
    
    private void initControls(){
        changeTitle = initChildButton(controls,"Change Title","CSS",false);
        changeName = initChildButton(controls, "Change Name", "CSS", false);
        changeBannerImage = initChildButton(controls, "Change Banner Image", "CSS", false);
        changeFooter = initChildButton(controls, "Change Footer", "CSS", false);
        final ToggleGroup font = new ToggleGroup();
        RadioButton font1 = new RadioButton("Font 1");
        font1.setToggleGroup(font);
        font1.setSelected(true);
        
        RadioButton font2 = new RadioButton("Font 2");
        font2.setToggleGroup(font);
        
        RadioButton font3 = new RadioButton("Font 3");
        font3.setToggleGroup(font);
        
        RadioButton font4 = new RadioButton("Font 4");
        font4.setToggleGroup(font);
        
        RadioButton font5 = new RadioButton("Font 5");
        font5.setToggleGroup(font);
        
        HBox fontBox = new HBox();
        fontBox.getChildren().addAll(font1,font2,font3,font4,font5);
        
        controls.getChildren().add(fontBox);
        
        final ToggleGroup layout = new ToggleGroup();
        RadioButton layout1 = new RadioButton("Layout 1");
        layout1.setToggleGroup(layout);
        layout1.setSelected(true);
        
        RadioButton layout2 = new RadioButton("Layout 2");
        layout2.setToggleGroup(layout);
 
        RadioButton layout3 = new RadioButton("Layout 3");
        layout3.setToggleGroup(layout);
        
        RadioButton layout4 = new RadioButton("Layout 4");
        layout4.setToggleGroup(layout);
        
        RadioButton layout5 = new RadioButton("Layout 5");
        layout5.setToggleGroup(layout);
        
        HBox layoutBox = new HBox();
        layoutBox.getChildren().addAll(layout1,layout2,layout3,layout4,layout5);
        
        controls.getChildren().add(layoutBox);
        
         final ToggleGroup color = new ToggleGroup();
        RadioButton color1 = new RadioButton("Color 1");
        color1.setToggleGroup(color);
        color1.setSelected(true);
        
        RadioButton color2 = new RadioButton("Color 2");
        color2.setToggleGroup(color);
        
        RadioButton color3 = new RadioButton("Color 3");
        color3.setToggleGroup(color);
        
        RadioButton color4 = new RadioButton("Color 4");
        color4.setToggleGroup(color);
        
        RadioButton color5 = new RadioButton("Color 5");
        color5.setToggleGroup(color);
        
        HBox colorBox = new HBox();
        colorBox.getChildren().addAll(color1,color2,color3,color4,color5);
        
        controls.getChildren().add(colorBox);
        
        
        AddHeader = initChildButton(controls, "Add Header", "CSS", false);
        AddText = initChildButton(controls, "Add Text", "CSS", false);
        AddImage = initChildButton(controls, "Add Image", "CSS", false);
        AddVideo = initChildButton(controls, "Add Video", "CSS", false);
        AddSlideshow = initChildButton(controls,"Add SlideShow", "CSS", false);
        AddList = initChildButton(controls, "Add a List", "CSS", false);
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
     
     AddVideo.setOnAction(e -> {
         VideoDialog();
     });
     
     AddList.setOnAction(e ->{
         ListDialog();
     });
     
     AddSlideshow.setOnAction(e->{
         SlideshowDialog();
     });
     
     changeTitle.setOnAction(e ->{
       titleDialog(); 
    });
     
    changeName.setOnAction(e -> {
       nameDialog(); 
    });
    
    changeBannerImage.setOnAction(e ->{
       bannerImageDialog();     
     });
    
    changeFooter.setOnAction(e -> {
        footerDialog();
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
        RadioButton font1 = new RadioButton("Font 1");
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
        dialog.setTitle("Image");
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

    private void VideoDialog() {
        Stage dialog = new Stage();
        HBox SelectImage = new HBox(); 
        Button Select = initChildButton(SelectImage, "Select Video", "CSS",false);
        
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
                

        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(SelectImage, captionBox, HeightBox, WidthBox, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Video");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void ListDialog() {
        Stage dialog = new Stage();
        Button add = new Button("Add Item");
        VBox listPane = new VBox();
        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                HBox item = new HBox();
                Button Remove = new Button("Remove");
                TextField text = new TextField();
                  Remove.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){  
                        listPane.getChildren().remove(item);
                    }
                  });

                item.getChildren().addAll(Remove, text);
                listPane.getChildren().add(item);
                }
            });
        
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(add,listPane, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("List");
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    private void SlideshowDialog() {
        //Run SlideShowMaker
        SlideShowMaker ss = new SlideShowMaker();
        
        try {
            ss.startEditor();
        } catch (Exception ex) {
            System.out.println("SS Error");
        }
    }
    
    public String getTitle(){
        return this.page.getTitle();
    }

    private void titleDialog() {
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
        dialog.setTitle("Title");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void nameDialog() {
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
        dialog.setTitle("Name");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void bannerImageDialog() {
        Stage dialog = new Stage();
        HBox SelectImage = new HBox(); 
        Button Select = initChildButton(SelectImage, "Select Banner Image", "CSS",false);
        
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(SelectImage, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Image");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void footerDialog() {
       Stage dialog = new Stage();
        HBox buttonBox = new HBox(); 
        Button Hyperlink = initChildButton(buttonBox, "Insert Hyperlink", "CSS",false);
        
                
        final ToggleGroup font = new ToggleGroup();
        RadioButton font1 = new RadioButton("Font 1");
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
        dialog.setTitle("Change Footer");
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
