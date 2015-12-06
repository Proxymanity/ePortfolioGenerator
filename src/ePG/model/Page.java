/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.model;

import ePG.controller.ImageSelectionController;
import ePG.controller.VideoSelectionController;
import ePG.view.PageEditView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ssm.SlideShowMaker;

/**
 *  This will hold the data for the page and have controls for editing components
 * 
 */
public class Page {
    PageEditView pev;
    ObservableList<String> Items;
    ObservableList<Text> Text;
    ObservableList<Image> Image;
    ObservableList<Video> Video;
    ObservableList<SlideShow> SlideShow;
    ObservableList<List> List;
    ObservableList<Header> Header;
    String Banner;
    String Title;
    String BannerImageName;
    String BannerImagePath;
    String Footer;
    String footerFont;
    String pageFont;
    String pageLayout;
    String pageColor;
    ImageSelectionController imageController;
    VideoSelectionController videoController;
    
    public Page(PageEditView initPEV){
        Items = FXCollections.observableArrayList();
        Text = FXCollections.observableArrayList();
        Image = FXCollections.observableArrayList();
        Video = FXCollections.observableArrayList();
        SlideShow = FXCollections.observableArrayList();
        List = FXCollections.observableArrayList();
        Header = FXCollections.observableArrayList();
        
        ImageSelectionController imageController = new ImageSelectionController();
        VideoSelectionController videoController = new VideoSelectionController();
        pev = initPEV;
        
        //Defaults
          Banner = ("Namey Namington");
          Title = ("Title");
          BannerImageName = ("BannerImage.png");
          BannerImagePath = ("./BannerImage.png");
          Footer = ("FooterTEXT");
          footerFont = "1";   
          pageFont = "1";
          pageColor = "1";
          pageLayout = "1";         
          Items.addAll("HEADER","TEXT", "IMAGE", "VIDEO", "SLIDESHOW", "LIST");
          
          Header hdr = new Header("Sample Header");
          Header.add(hdr);
          
          Text txt = new Text("Sample Paragraph", "1");
          Text.add(txt);
          
          Image img = new Image("imgName", "imgPath", "Caption", "width", "height", "leftAlign");
          Image.add(img);
          
          Video vd = new Video("vdName", "vdPath", "caption", "width", "height");
          Video.add(vd);
          
          SlideShow  ss = new SlideShow("SlideShowName", "SlideShowPath"); 
          SlideShow.add(ss);
          
          ObservableList<String> listItems = FXCollections.observableArrayList();
          listItems.addAll("List1", "List2", "List 3", "List 4");
          
          List list = new List(listItems, "1");
          List.add(list);
          
    }        
    public void setName(String text){
        this.Banner = text;
    }      
    
    public void setBannerName(String text){
        this.BannerImageName = text;
    }
    
    public void setBannerPath(String text){
        this.BannerImagePath = text;
    }
    
    public void setFooterText(String text){
        this.Footer = text;
    }
    
    public void setFooterFont(String text){
        this.footerFont = text;
    }
    
    public void setFont(String text){
        this.pageFont = text;
    }
    
    public void setColor(String text){
        this.pageColor = text;
    }
    
    public void setLayout(String text){
        this.pageLayout = text;
    }
    public VBox load() {
        VBox display = new VBox();
        int textCounter = 0;
        int imageCounter = 0;
        int videoCounter = 0;
        int SSCounter = 0;
        int headerCounter = 0;
        int listCounter = 0;
        
        
        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(new Label("Title :") , new Label(this.Title));
        HBox bannerBox = new HBox();
        bannerBox.getChildren().addAll(new Label("Name:") , new Label(this.Banner));
        HBox bannerImageBox = new HBox();
        bannerImageBox.getChildren().addAll(new Label("BannerImage :") , new Label(this.BannerImageName));
        HBox footerBox = new HBox();
        footerBox.getChildren().addAll(new Label("Footer font:" + footerFont),new Label("           Footer :") , new Label(this.Footer));
        Label mainFont = new Label("Page font: " + this.pageFont);
        Label mainLayout = new Label("Page layout: " +this.pageLayout);
        Label mainColor = new Label("Page color: " +this.pageColor);
        
        display.getChildren().addAll(titleBox, bannerBox, bannerImageBox, footerBox, mainFont, mainLayout, mainColor);
        
        for(int i = 0; i< Items.size(); i++){
            Button Remove = new Button("Remove Component");
            HBox componentBox = new HBox();
            VBox component = new VBox();
            Label componentName = new Label();
            
            
           if(Items.get(i).equals("HEADER")){
               
               componentName.setText("Header");
               Header tempHeader = Header.get(headerCounter);
               Label content1 = new Label("Header :" + tempHeader.getText());
               headerCounter++;
               
               component.getChildren().add(componentName);
               component.getChildren().addAll(content1);
 
               int componentType = i;
               
            component.setOnMouseClicked(e -> {
                   HeaderDialog(tempHeader);
                });   
               
            Remove.setOnAction(e -> {
               Items.remove(componentType);
               Header.remove(tempHeader);
               display.getChildren().remove(componentBox);
           });            
           }
           
           
           else if(Items.get(i).equals("TEXT")){
               componentName.setText("TEXT"); 
               Text tempText = Text.get(textCounter);
               Label content1 = new Label("Font :" + tempText.getFont());
               Label content2 = new Label(tempText.getText());
               textCounter++;
               
               component.getChildren().add(componentName);
               component.getChildren().addAll(content1, content2);
               
               int componentType = i;
            component.setOnMouseClicked(e -> {
                   TextDialog(tempText);
                });   
               
            Remove.setOnAction(e -> {
               Items.remove(componentType);
               Header.remove(tempText);
               display.getChildren().remove(componentBox);
           });             
           }
           
           
           else if(Items.get(i).equals("IMAGE")){
               componentName.setText("IMAGE");
               
               Image tempImage = Image.get(imageCounter);
               Label content1 = new Label("Image Name: "+tempImage.getName());
               Label content2 = new Label("Caption: "+tempImage.getCaption());
               Label content3 = new Label("Width: "+tempImage.getWidth());
               Label content4 = new Label("Height: "+tempImage.getHeight());
               Label content5 = new Label("Alignment: " + tempImage.getAlign());
               imageCounter++;
               
               component.getChildren().add(componentName);
               component.getChildren().addAll(content1, content2, content3, content4, content5);
               
               int componentType = i;
               
            component.setOnMouseClicked(e -> {
                   ImageDialog(tempImage);
                });   
            
            Remove.setOnAction(e -> {
               Items.remove(componentType);
               Image.remove(tempImage);
               display.getChildren().remove(componentBox);
           });      
            }
           
           
           else if(Items.get(i).equals("VIDEO")){
               componentName.setText("VIDEO");
            
               Video tempVid = Video.get(videoCounter);
               Label content1 = new Label("Video Name: "+tempVid.getName());
               Label content2 = new Label("Caption: "+tempVid.getCaption());
               Label content3 = new Label("Width: "+tempVid.getWidth());
               Label content4 = new Label("Height: "+tempVid.getHeight());
               videoCounter++;
               
               component.getChildren().add(componentName);
               component.getChildren().addAll(content1, content2, content3, content4);
               
               int componentType = i;
           component.setOnMouseClicked(e -> {
                   VideoDialog(tempVid);
                });   
            Remove.setOnAction(e -> {
               Items.remove(componentType);
               Video.remove(tempVid);
               display.getChildren().remove(componentBox);
           });     
            }
           
           else if(Items.get(i).equals("SLIDESHOW")){
               componentName.setText("SLIDESHOW");
               
               SlideShow tempSS = SlideShow.get(SSCounter);
               Label content1 = new Label(tempSS.getName());
               SSCounter++;
               
               component.getChildren().add(componentName);
               component.getChildren().addAll(content1);
               
               int componentType = i;
            component.setOnMouseClicked(e -> {
                   SlideshowDialog(tempSS);
                });   
            Remove.setOnAction(e -> {
               Items.remove(componentType);
               Video.remove(tempSS);
               display.getChildren().remove(componentBox);
           });     
            }
           
           
           else if(Items.get(i).equals("LIST")){
               Label ListcomponentName = new Label("LIST");
               VBox listBox = new VBox();
               
               List tempList = List.get(listCounter);
               listCounter++;
               Label content1 = new Label("Font: " +tempList.getFont());
               
               ObservableList<String> ListArray = tempList.getList();
               
               for(int x = 0; x < ListArray.size(); x++){
                   
                   Label listItem = new Label(ListArray.get(x));
                   listBox.getChildren().add(listItem);
                   }
               component.getChildren().addAll(ListcomponentName, content1,listBox);
               
               int componentType = i;
            component.setOnMouseClicked(e -> {
                   ListDialog(tempList);
                });
            Remove.setOnAction(e -> {
               Items.remove(componentType);
               List.remove(tempList);
               display.getChildren().remove(componentBox);
           }); 
           }
           
           
          
           
           componentBox.getChildren().addAll(Remove, component);
           display.getChildren().add(componentBox);
        }
        
        
        return display;
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
    
    private void TextDialog(Text paragraph){
        Stage dialog = new Stage();
        HBox buttonBox = new HBox();
        Button Hyperlink = initChildButton(buttonBox, "Insert Hyperlink", "CSS",false);
                
        final ToggleGroup font = new ToggleGroup();
        RadioButton font1 = new RadioButton("Font 1");
        font1.setToggleGroup(font);

        RadioButton font2 = new RadioButton("Font 2");
        font2.setToggleGroup(font);
        
        RadioButton font3 = new RadioButton("Font 3");
        font3.setToggleGroup(font);
        
        RadioButton font4 = new RadioButton("Font 4");
        font4.setToggleGroup(font);
        
        RadioButton font5 = new RadioButton("Font 5");
        font5.setToggleGroup(font);
        
        String fontTxt = paragraph.getFont();
        if(fontTxt.equals("1")){
            font1.setSelected(true);
        }else if(fontTxt.equals("2")){
            font2.setSelected(true);
        }else if(fontTxt.equals("3")){
            font3.setSelected(true);
        }else if(fontTxt.equals("4")){
            font4.setSelected(true);
        }else if(fontTxt.equals("5")){
            font5.setSelected(true);
        }
        
        
        HBox fontBox = new HBox();
        fontBox.getChildren().addAll(font1,font2,font3,font4,font5);
        
        
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextArea input = new TextArea(paragraph.getText());
        
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
                
                paragraph.setText(input.getText());
                paragraph.setFont(fontText);
                pev.reload();
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
    
    private void HeaderDialog(Header header) {
        Stage dialog = new Stage();
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        TextField input = new TextField(header.getText());
        Button Accept = new Button ("Accept");
        Accept.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){  
                
                header.setText(input.getText());
                pev.reload();
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

    private void ImageDialog(Image imageFile) {
        Stage dialog = new Stage();
        HBox SelectImage = new HBox(); 
        Button Select = initChildButton(SelectImage, "Select Image", "CSS",false);
        Label imageName = new Label(imageFile.getName());
        Label imagePath = new Label (imageFile.getPath());
        
        HBox captionBox = new HBox();
        Label captionLabel = new Label("Caption : ");
        TextField caption = new TextField(imageFile.getCaption());
        captionBox.getChildren().addAll(captionLabel, caption);
        
        HBox WidthBox = new HBox();
        Label widthLabel = new Label("Width :   ");
        TextField width = new TextField(imageFile.getWidth());
        WidthBox.getChildren().addAll(widthLabel, width);
        
        HBox HeightBox = new HBox();
        Label heightLabel = new Label("Height :  ");
        TextField height = new TextField(imageFile.getHeight());
        HeightBox.getChildren().addAll(heightLabel, height);
        
                
        final ToggleGroup alignment = new ToggleGroup();
        RadioButton left = new RadioButton("Left Align");
        left.setToggleGroup(alignment);

        RadioButton middle = new RadioButton("Center Align");
        middle.setToggleGroup(alignment);
 
        RadioButton right = new RadioButton("Right Align");
        right.setToggleGroup(alignment);
        
        String alignTxt = imageFile.getAlign();
        if(alignTxt.equals("left")){
            left.setSelected(true);
        }else if(alignTxt.equals("middle")){
            middle.setSelected(true);
        }else if(alignTxt.equals("right")){
            right.setSelected(true);
        }
        
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
                
                String align = imageFile.getAlign();
                if(left.isSelected()){
                    align = "left";
                }else if(middle.isSelected()){
                    align = "middle";
                }else if(right.isSelected()){
                    align = "right";
                }
                
                imageFile.update(imageName.getText(), imagePath.getText(), caption.getText(), width.getText(),height.getText(), align);
                pev.reload(); 
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
    

    private void VideoDialog(Video videoFile) {
        Stage dialog = new Stage();
        HBox SelectVideo = new HBox(); 
        Button Select = initChildButton(SelectVideo, "Select Video", "CSS",false);
        Label videoName = new Label(videoFile.getName());
        Label videoPath = new Label (videoFile.getPath());
        
        HBox captionBox = new HBox();
        Label captionLabel = new Label("Caption : ");
        TextField caption = new TextField(videoFile.getCaption());
        captionBox.getChildren().addAll(captionLabel, caption);
        
        HBox WidthBox = new HBox();
        Label widthLabel = new Label("Width :   ");
        TextField width = new TextField(videoFile.getWidth());
        WidthBox.getChildren().addAll(widthLabel, width);
        
        HBox HeightBox = new HBox();
        Label heightLabel = new Label("Height :  ");
        TextField height = new TextField(videoFile.getHeight());
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
                videoFile.update(videoName.getText(), videoPath.getText(), caption.getText(), width.getText(),height.getText());
                pev.reload(); 
                dialog.close();
                }
            });
        
        VBox mainPane = new VBox();
       // mainPane.getStyleClass().add(CENTER);
        mainPane.getChildren().addAll(SelectVideo,videoName, videoPath, captionBox, WidthBox,HeightBox, Accept);
        Scene scene = new Scene(mainPane, 250, 600);
       // scene.getStylesheets().add(STYLE_SHEET_UI);
        dialog.setTitle("Video");
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void ListDialog(List listObject) {
        Stage dialog = new Stage();
        Button add = new Button("Add Item");
        VBox listPane = new VBox();
        
        final ToggleGroup font = new ToggleGroup();
        RadioButton font1 = new RadioButton("Font 1");
        font1.setToggleGroup(font);

        RadioButton font2 = new RadioButton("Font 2");
        font2.setToggleGroup(font);
        
        RadioButton font3 = new RadioButton("Font 3");
        font3.setToggleGroup(font);
        
        RadioButton font4 = new RadioButton("Font 4");
        font4.setToggleGroup(font);
        
        RadioButton font5 = new RadioButton("Font 5");
        font5.setToggleGroup(font);
        
        String fontTxt = listObject.getFont();
        if(fontTxt.equals("1")){
            font1.setSelected(true);
        }else if(fontTxt.equals("2")){
            font2.setSelected(true);
        }else if(fontTxt.equals("3")){
            font3.setSelected(true);
        }else if(fontTxt.equals("4")){
            font4.setSelected(true);
        }else if(fontTxt.equals("5")){
            font5.setSelected(true);
        }
        
        HBox fontBox = new HBox();
        fontBox.getChildren().addAll(font1,font2,font3,font4,font5);
         
        ObservableList<TextField> listField = FXCollections.observableArrayList();
        ObservableList<String> listObjectItems = listObject.getList();
        for(int x = 0; x < listObjectItems.size(); x++){
                HBox item = new HBox();
                Button Remove = new Button("Remove");
                TextField text = new TextField(listObjectItems.get(x));
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
                String fontText = listObject.getFont();
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
                
                listObject.update(listItems, fontText);
                
                pev.reload();
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
    
    private void SlideshowDialog(SlideShow SlideShowObject) {
        //Run SlideShowMaker
        SlideShowMaker ss = new SlideShowMaker();
        //SlideShowMaker ss = new SlideShowMaker(SlideShowObject.getName(),SlideShowObject.getPath());
        
        try {
            ss.startEditor();
        } catch (Exception ex) {
            System.out.println("SS Error");
        }
    }
    
    public String getTitle(){
        return this.Title;
    }
    
    public void setTitle(String str){
        Title = str;
    }

    public ObservableList<Text> getText() {
        return this.Text;
    }
    
    public ObservableList<String> getItems(){
        return this.Items;
    }

    public ObservableList<Header> getHeader() {
        return this.Header;
    }

    public ObservableList<Image> getImage() {
        return this.Image;
    }

    public ObservableList<Video> getVideo() {
        return this.Video;
    }

    public ObservableList<List> getList() {
        return this.List;
    }
}
