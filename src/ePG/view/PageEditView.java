
package ePG.view;

import ePG.controller.ImageSelectionController;
import ePG.controller.VideoSelectionController;
import ePG.model.Header;
import ePG.model.Image;
import ePG.model.ListObject;
import javafx.scene.layout.VBox;
import ePG.model.Page;
import ePG.model.Site;
import ePG.model.Text;
import ePG.model.Video;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
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
    Site site;
    GeneratorView ui;
    VBox controls;
    VBox pagePreview;
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
    
    Tab parentTab;
    
    
    ImageSelectionController imageController;
    VideoSelectionController videoController;
    
    public PageEditView(GeneratorView initUI, Tab parent){
        ui = initUI;
        controls = new VBox();
        initPage();
        initControls(page);
        site = ui.getSite();
        page.setName(site.getName());
        site.getPages().add(page);
        parentTab = parent;
        imageController = new ImageSelectionController();
        videoController = new VideoSelectionController();
        pagePreview = page.load();
        this.getChildren().addAll(controls, pagePreview);
    }
    
    private void initControls(Page pageToEdit){
        changeTitle = initChildButton(controls,"Change Title","CSS",false);
        changeName = initChildButton(controls, "Change Name", "CSS", false);
        changeBannerImage = initChildButton(controls, "Change Banner Image", "CSS", false);
        changeFooter = initChildButton(controls, "Change Footer", "CSS", false);
        final ToggleGroup font = new ToggleGroup();
        RadioButton font1 = new RadioButton("Font 1");
        font1.setToggleGroup(font);
        font1.setSelected(true);
        font1.setOnAction(e -> {
           pageToEdit.setFont("1");
           reload();
        });
            
        RadioButton font2 = new RadioButton("Font 2");
        font2.setToggleGroup(font);
        font2.setOnAction(e -> {
           pageToEdit.setFont("2");
           reload();
        });
        
        RadioButton font3 = new RadioButton("Font 3");
        font3.setToggleGroup(font);
        font3.setOnAction(e -> {
           pageToEdit.setFont("3");
           reload();
        });
        
        RadioButton font4 = new RadioButton("Font 4");
        font4.setToggleGroup(font);
        font4.setOnAction(e -> {
           pageToEdit.setFont("4");
           reload();
        });
        
        RadioButton font5 = new RadioButton("Font 5");
        font5.setToggleGroup(font);
        font5.setOnAction(e -> {
           pageToEdit.setFont("5");
           reload();
        });
        
        HBox fontBox = new HBox();
        fontBox.getChildren().addAll(font1,font2,font3,font4,font5);
        
        controls.getChildren().add(fontBox);
        
        final ToggleGroup layout = new ToggleGroup();
        RadioButton layout1 = new RadioButton("Layout 1");
        layout1.setToggleGroup(layout);
        layout1.setSelected(true);
        layout1.setOnAction(e -> {
           pageToEdit.setLayout("1");
           reload();
        });
        
        RadioButton layout2 = new RadioButton("Layout 2");
        layout2.setToggleGroup(layout);
        layout2.setOnAction(e -> {
           pageToEdit.setLayout("2");
           reload();
        });
        
        RadioButton layout3 = new RadioButton("Layout 3");
        layout3.setToggleGroup(layout);
        layout3.setOnAction(e -> {
           pageToEdit.setLayout("3");
           reload();
        });
        
        RadioButton layout4 = new RadioButton("Layout 4");
        layout4.setToggleGroup(layout);
        layout4.setOnAction(e -> {
           pageToEdit.setLayout("4");
           reload();
        });
        
        RadioButton layout5 = new RadioButton("Layout 5");
        layout5.setToggleGroup(layout);
        layout5.setOnAction(e -> {
           pageToEdit.setLayout("5");
           reload();
        });
        
        HBox layoutBox = new HBox();
        layoutBox.getChildren().addAll(layout1,layout2,layout3,layout4,layout5);
        
        controls.getChildren().add(layoutBox);
        
         final ToggleGroup color = new ToggleGroup();
        RadioButton color1 = new RadioButton("Color 1");
        color1.setToggleGroup(color);
        color1.setSelected(true);
        color1.setOnAction(e -> {
           pageToEdit.setColor("1");
           reload();
        });
        
        RadioButton color2 = new RadioButton("Color 2");
        color2.setToggleGroup(color);
        color2.setOnAction(e -> {
           pageToEdit.setColor("2");
           reload();
        });
        
        RadioButton color3 = new RadioButton("Color 3");
        color3.setToggleGroup(color);
        color3.setOnAction(e -> {
           pageToEdit.setColor("3");
           reload();
        });
        
        RadioButton color4 = new RadioButton("Color 4");
        color4.setToggleGroup(color);
        color4.setOnAction(e -> {
           pageToEdit.setColor("4");
           reload();
        });
        
        RadioButton color5 = new RadioButton("Color 5");
        color5.setToggleGroup(color);
        color5.setOnAction(e -> {
           pageToEdit.setColor("5");
           reload();
        });
        
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
        HeaderDialog(this.page); 
     });
        
     AddText.setOnAction(e ->{
         TextDialog(this.page);
     });
     
     AddImage.setOnAction(e -> {
         ImageDialog(this.page);
     });
     
     AddVideo.setOnAction(e -> {
         VideoDialog(this.page);
     });
     
     AddList.setOnAction(e ->{
         ListDialog(this.page);
     });
     
     AddSlideshow.setOnAction(e->{
         SlideshowDialog();
     });
     
     changeTitle.setOnAction(e ->{
       titleDialog(this.page); 
    });
     
    changeName.setOnAction(e -> {
       nameDialog(); 
    });
    
    changeBannerImage.setOnAction(e ->{
       bannerImageDialog(this.page);     
     });
    
    changeFooter.setOnAction(e -> {
        footerDialog(this.page);
    });
     
    }
    
  
    private void initPage() {
        this.page = new Page(this);
    }

      private void TextDialog(Page pageToEdit){
        Stage dialog = new Stage();
        HBox buttonBox = new HBox();
        Button Hyperlink = initChildButton(buttonBox, "Insert Hyperlink", "CSS",false);
                
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
        
        
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextArea input = new TextArea();
        
        Hyperlink.setOnAction(new EventHandler<ActionEvent>(){
            @Override
             public void handle(ActionEvent e){  
                String text = input.getText();
                String selected = input.getSelectedText();
                int start = input.getSelection().getStart();
                int end = input.getSelection().getEnd();
                
                HyperlinkDialog(input,text,selected, start, end);
                }
        });
         
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                ObservableList<Text> textArray = pageToEdit.getText();
                ObservableList<String> itemArray = pageToEdit.getItems();
                
                String fontText;
                
                if(font1.isSelected()){
                    fontText = "1";
                }else if(font2.isSelected()){
                    fontText = "2";
                }else if(font3.isSelected()){
                    fontText = "3";
                }else if(font4.isSelected()){
                    fontText = "4";
                }else{
                    fontText = "5";
                }
                itemArray.add("TEXT");
                Text newText = new Text(input.getText(), fontText);
                
               textArray.add(newText); 
                reload();
                dialog.close();
                }
            });
        mainPane.getChildren().addAll(buttonBox,fontBox,input, Accept);
        Scene scene = new Scene(mainPane, 400, 250);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Text");
        dialog.setScene(scene);
        dialog.showAndWait();
    }
      
    public void HyperlinkDialog(TextArea textComponent,String text, String selected, int start, int end){
       Stage dialoug = new Stage();
        Label textArea = new Label("Your text is : " + text);
        Label selectedText = new Label("Your selected text is : " + selected);
        Label startIndex = new Label("Start: " + start);
        Label endIndex = new Label("End: " + end);
                
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
                String newText = ("<a href='" + link.getText() + "'>" + selected + "</a>");
                String front = text.substring(0, start);
                String back = text.substring(end);
                
                String text = front + newText + back;
                textComponent.setText(text);
                dialoug.close();
                }
            });
        mainPane.getChildren().addAll(textArea, selectedText,startIndex, endIndex, linkBox, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialoug.setTitle("HyperLink");
        dialoug.setScene(scene);
        dialoug.showAndWait();    
    }  
    
    private void HeaderDialog(Page pageToEdit) {
        Stage dialog = new Stage();
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextField input = new TextField();
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                ObservableList<Header> headerArray = pageToEdit.getHeader();
                ObservableList<String> itemArray = pageToEdit.getItems();
                
                
                itemArray.add("HEADER");
                Header newHeader = new Header(input.getText());
                
                headerArray.add(newHeader); 
                reload();
                dialog.close();
                }
            });
        mainPane.getChildren().addAll(input, Accept);
        Scene scene = new Scene(mainPane, 250, 200);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Header");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void ImageDialog(Page pageToEdit) {
        Stage dialog = new Stage();
        HBox SelectImage = new HBox(); 
        Button Select = initChildButton(SelectImage, "Select Image", "CSS",false);
        Label imageName = new Label("Image Name: ");
        Label imagePath = new Label ("Image Path: ");
        
        HBox captionBox = new HBox();
        Label captionLabel = new Label("Caption : ");
        TextField caption = new TextField();
        captionBox.getChildren().addAll(captionLabel, caption);
        
        HBox WidthBox = new HBox();
        Label widthLabel = new Label("Width :   ");
        TextField width = new TextField();
        WidthBox.getChildren().addAll(widthLabel, width);
        
        HBox HeightBox = new HBox();
        Label heightLabel = new Label("Height :  ");
        TextField height = new TextField();
        HeightBox.getChildren().addAll(heightLabel, height);
        
                
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
        
        Select.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
            imageController.processSelectImage(imagePath, imageName); 
            }
        });
        
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                ObservableList<Image> imageArray = pageToEdit.getImage();
                ObservableList<String> itemArray = pageToEdit.getItems();

                itemArray.add("IMAGE");
                String align = "middle";
                if(left.isSelected()){
                    align = "left";
                }else if(middle.isSelected()){
                    align = "middle";
                }else if(right.isSelected()){
                    align = "right";
                }
                
                Image newImage = new Image(imageName.getText(), imagePath.getText(), caption.getText(), width.getText(),height.getText(), align);
                
                imageArray.add(newImage); 
                reload(); 
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(SelectImage,imageName, imagePath, captionBox, WidthBox,HeightBox, alignmentBox, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Image");
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    

    private void VideoDialog(Page pageToEdit) {
        Stage dialog = new Stage();
        HBox SelectVideo = new HBox(); 
        Button Select = initChildButton(SelectVideo, "Select Video", "CSS",false);
        Label videoName = new Label("Video Name: ");
        Label videoPath = new Label ("Video Path: ");
        
        HBox captionBox = new HBox();
        Label captionLabel = new Label("Caption : ");
        TextField caption = new TextField();
        captionBox.getChildren().addAll(captionLabel, caption);
        
        HBox WidthBox = new HBox();
        Label widthLabel = new Label("Width :   ");
        TextField width = new TextField();
        WidthBox.getChildren().addAll(widthLabel, width);
        
        HBox HeightBox = new HBox();
        Label heightLabel = new Label("Height :  ");
        TextField height = new TextField();
        HeightBox.getChildren().addAll(heightLabel, height);
        
                

        Select.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
            videoController.processSelectVideo(videoPath, videoName); 
            }
        });
        
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                ObservableList<Video> videoArray = pageToEdit.getVideo();
                ObservableList<String> itemArray = pageToEdit.getItems();

                itemArray.add("VIDEO");
                
                Video newVideo = new Video(videoName.getText(), videoPath.getText(), caption.getText(), width.getText(),height.getText());
                
                videoArray.add(newVideo); 
                reload(); 
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(SelectVideo,videoName, videoPath, captionBox, WidthBox,HeightBox, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Image");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void ListDialog(Page pageToEdit) {
        Stage dialog = new Stage();
        Button add = new Button("Add Item");
        VBox listPane = new VBox();
        
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
        
        ObservableList<TextField> listField = FXCollections.observableArrayList();
        add.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                HBox item = new HBox();
                Button Remove = new Button("Remove");
                TextField text = new TextField();
                listField.add(text);
                
                  Remove.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent e){  
                        listPane.getChildren().remove(item);
                        listField.remove(text);
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
                ObservableList<ListObject> listArray = pageToEdit.getList();
                ObservableList<String> itemArray = pageToEdit.getItems();

                itemArray.add("LIST");
                
                String fontText = "1";
                String temp;
                
                if(font1.isSelected()){
                    fontText = "1";
                }else if(font2.isSelected()){
                    fontText = "2";
                }else if(font3.isSelected()){
                    fontText = "3";
                }else if(font4.isSelected()){
                    fontText = "4";
                }else{
                    fontText = "5";
                }
                
                ObservableList<String> listItems = FXCollections.observableArrayList();
                
                for(int x = 0; x < listField.size(); x++){
                    listItems.add(listField.get(x).getText());
                }
                
                
                ListObject newList = new ListObject(listItems, fontText);
                listArray.add(newList);
                reload();
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(add, fontBox, listPane, Accept);
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

    private void titleDialog(Page pageToEdit) {
        Stage dialog = new Stage();
        Button Accept = new Button ("Accept"); 
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextField input = new TextField(pageToEdit.getTitle());
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){ 
                pageToEdit.setTitle(input.getText());
                parentTab.setText(pageToEdit.getTitle());
                reload();
                //Change tab
                dialog.close();
                }
            });
        mainPane.getChildren().addAll(input, Accept);
        Scene scene = new Scene(mainPane, 250, 200);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Title");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void nameDialog() {
        Stage dialog = new Stage();
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextField input = new TextField();
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                site.setName(input.getText());
                reload();
                dialog.close();
                }
            });
        mainPane.getChildren().addAll(input, Accept);
        Scene scene = new Scene(mainPane, 250, 200);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Name");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void bannerImageDialog(Page pageToEdit) {
        Stage dialog = new Stage();
        HBox SelectImage = new HBox(); 
        Button Select = initChildButton(SelectImage, "Select Image", "CSS",false);
        Label imageName = new Label("Banner Image Name: ");
        Label imagePath = new Label ("Banner Image Path: ");
        
    
        Select.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
            imageController.processSelectImage(imagePath, imageName); 
            }
        });
        
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                pageToEdit.setBannerName(imageName.getText());
                pageToEdit.setBannerPath(imagePath.getText());
                reload(); 
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(SelectImage,imageName, imagePath, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Banner Image");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void footerDialog(Page pageToEdit) {
       Stage dialog = new Stage();
        HBox buttonBox = new HBox();
        Button Hyperlink = initChildButton(buttonBox, "Insert Hyperlink", "CSS",false);
                
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
        
        
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextArea input = new TextArea();
        
        Hyperlink.setOnAction(new EventHandler<ActionEvent>(){
            @Override
             public void handle(ActionEvent e){  
                String text = input.getText();
                String selected = input.getSelectedText();
                int start = input.getSelection().getStart();
                int end = input.getSelection().getEnd();
                
                HyperlinkDialog(input,text,selected, start, end);
                }
        });
         
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                String fontText;
                
                if(font1.isSelected()){
                    fontText = "1";
                }else if(font2.isSelected()){
                    fontText = "2";
                }else if(font3.isSelected()){
                    fontText = "3";
                }else if(font4.isSelected()){
                    fontText = "4";
                }else{
                    fontText = "5";
                }
                Text newText = new Text(input.getText(), fontText);
                
                pageToEdit.setFooterText(input.getText());
                pageToEdit.setFooterFont(fontText);
                reload();
                dialog.close();
                }
            });
        mainPane.getChildren().addAll(buttonBox,fontBox,input, Accept);
        Scene scene = new Scene(mainPane, 400, 250);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Change Footer");
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    public void reload(){
        this.getChildren().clear();
        pagePreview = page.load();
        this.getChildren().addAll(controls, pagePreview);
    }
}
