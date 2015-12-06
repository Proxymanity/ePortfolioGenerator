/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.model;

/**
 *
 * @author Cokers
 */
public class Image {
    String imageName;
    String imagePath;
    String caption;
    String width;
    String height;
    String alignment;
    
    
    public Image(String initName, String initPath,String initCaption, String initWidth, String initHeight, String initAlign){
        imageName = initName;
        imagePath = initPath;
        caption = initCaption;
        width = initWidth;
        height = initHeight;
        alignment = initAlign;
    }
    
    /* Used when user edits image component */
    public void update(String newName, String newPath,String newCaption, String newWidth, String newHeight, String newAlign){
        imageName = newName;
        imagePath = newPath;
        caption = newCaption;
        width = newWidth;
        height = newHeight;
        alignment = newAlign;
    }
    
    public String getName(){
        return this.imageName;
    }
    
    public String getPath(){
        return this.imagePath;
    }
    
    public String getCaption(){
        return this.caption;
    }
    public String getWidth(){
        return this.width;
    }
    
    public String getHeight(){
        return this.height;
    }
    
    public String getAlign(){
        return this.alignment;
    }
}
