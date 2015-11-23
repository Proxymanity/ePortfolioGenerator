/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.model;

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
    ObservableList<String> Items;
    ObservableList<String> Text;
    ObservableList<String> Image;
    ObservableList<String> Video;
    ObservableList<String> SlideShow;
    ObservableList<String> List;
    ObservableList<String> Header;
    String Banner;
    String Title;
    String BannerImage;
    String Footer;
    
    public Page(){
        Items = FXCollections.observableArrayList();
        Text = FXCollections.observableArrayList();
        Image = FXCollections.observableArrayList();
        Video = FXCollections.observableArrayList();
        SlideShow = FXCollections.observableArrayList();
        List = FXCollections.observableArrayList();
        Header = FXCollections.observableArrayList();
        //Defaults
        Banner = ("Namey Namington");
        Title = ("Title");
        BannerImage = ("Banner Image.png");
        Footer = ("Footer.png");       
        Items.addAll("Header","TEXT", "IMAGE", "VIDEO", "SLIDESHOW", "List","List", "List", "List");
        Text.add("SAMPLE TEXT");
        Image.add("Image.png");
        Video.add("Video.mp4");
        SlideShow.add("SlideShow");
        List.addAll("List1", "List2", "List 3", "List 4");
        Header.add("HeadingText");
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
        bannerImageBox.getChildren().addAll(new Label("BannerImage :") , new Label(this.BannerImage));
        HBox footerBox = new HBox();
        footerBox.getChildren().addAll(new Label("Footer :") , new Label(this.Footer));
        
        display.getChildren().addAll(titleBox, bannerBox, bannerImageBox, footerBox);
        
        for(int i = 0; i< Items.size(); i++){
            Button Remove = new Button("Remove Component");
            HBox componentBox = new HBox();
            VBox component = new VBox();
            Label componentName = new Label();
            Label content = new Label();
           if(Items.get(i).equals("Header")){
               component.setOnMouseClicked(e -> {
                   HeaderDialog();
                });
               componentName.setText("Header");
               content.setText(Text.get(headerCounter));
               headerCounter++;
           }
           else if(Items.get(i).equals("TEXT")){
               component.setOnMouseClicked(e -> {
                   TextDialog();
                });
               componentName.setText("TEXT");
               content.setText(Text.get(textCounter));
               textCounter++;
           }
           else if(Items.get(i).equals("IMAGE")){
               componentName.setText("IMAGE");
               component.setOnMouseClicked(e -> {
                   ImageDialog();
                });
               content.setText(Image.get(imageCounter));
               imageCounter++;
            }
           else if(Items.get(i).equals("VIDEO")){
               componentName.setText("VIDEO");
               component.setOnMouseClicked(e -> {
                   VideoDialog();
                });
               content.setText(Video.get(videoCounter));
               videoCounter++;
            }
           else if(Items.get(i).equals("SLIDESHOW")){
               componentName.setText("SLIDESHOW");
               component.setOnMouseClicked(e -> {
                   SlideshowDialog();
                });
               content.setText(SlideShow.get(SSCounter));
               SSCounter++;
            }
           else if(Items.get(i).equals("List")){
               component.setOnMouseClicked(e -> {
                   ListDialog();
                });
               Label ListcomponentName = new Label("LIST");
               VBox listBox = new VBox();
               int x;
               ListLoop:
               for(x = i;x < Items.size(); x++){
                   if(Items.get(x).equals("List")){
                   Label listItem = new Label(List.get(listCounter));
                   listCounter++;
                   listBox.getChildren().add(listItem);
                   }else{
                       break ListLoop;
                   }
               }
               i = x-1;
               
               component.getChildren().add(ListcomponentName);
               component.getChildren().add(listBox);
               
               
           }
           
           
           Remove.setOnAction(e -> {
               display.getChildren().remove(componentBox);
           });
           
           component.getChildren().add(componentName);
           component.getChildren().add(content);
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
        return this.Title;
    }
}
