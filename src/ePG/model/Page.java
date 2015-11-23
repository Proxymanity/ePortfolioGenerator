/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
        //Defaults
        Banner = ("Default Banner");
        Title = ("Title");
        BannerImage = ("Banner Image.png");
        Footer = ("Footer.png");       
        Items.addAll("TEXT", "IMAGE", "VIDEO", "SLIDESHOW");
        Text.add("SAMPLE TEXT");
        Image.add("Image.png");
        Video.add("Video.mp4");
        SlideShow.add("SlideShow");
    }        
            
    public VBox load() {
        VBox display = new VBox();
        int textCounter = 0;
        int imageCounter = 0;
        int videoCounter = 0;
        int SSCounter = 0;
        
        
        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(new Label("Title :") , new Label(this.Title));
        HBox bannerBox = new HBox();
        bannerBox.getChildren().addAll(new Label("Banner :") , new Label(this.Banner));
        HBox bannerImageBox = new HBox();
        bannerImageBox.getChildren().addAll(new Label("BannerImage :") , new Label(this.BannerImage));
        HBox footerBox = new HBox();
        footerBox.getChildren().addAll(new Label("Footer :") , new Label(this.Footer));
        
        display.getChildren().addAll(titleBox, bannerBox, bannerImageBox, footerBox);
        
        for(int i = 0; i< Items.size(); i++){
            VBox component = new VBox();
            Label componentName = new Label();
            Label content = new Label();
           if(Items.get(i).equals("TEXT")){
               componentName.setText("TEXT");
               content.setText(Text.get(textCounter));
               textCounter++;
           }
           else if(Items.get(i).equals("IMAGE")){
               componentName.setText("IMAGE");
               content.setText(Image.get(imageCounter));
               imageCounter++;
            }
           else if(Items.get(i).equals("VIDEO")){
               componentName.setText("VIDEO");
               content.setText(Video.get(videoCounter));
               videoCounter++;
            }
           else if(Items.get(i).equals("SLIDESHOW")){
               componentName.setText("SLIDESHOW");
               content.setText(SlideShow.get(SSCounter));
               SSCounter++;
            }
           
           
           component.getChildren().add(componentName);
           component.getChildren().add(content);
           display.getChildren().add(component);
        }
        
        
        return display;
    }
    
}
