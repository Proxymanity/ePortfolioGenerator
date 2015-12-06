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
public class Video {
    String videoName;
    String videoPath;
    String caption;
    String width;
    String height;
    
    public Video(String initName, String initPath,String initCaption, String initWidth, String initHeight){
        videoName = initName;
        videoPath = initPath;
        caption = initCaption;
        width = initWidth;
        height = initHeight;
    }
    
     /* Used when user edits image component */
    public void update(String newName, String newPath,String newCaption, String newWidth, String newHeight){
        videoName = newName;
        videoPath = newPath;
        caption = newCaption;
        width = newWidth;
        height = newHeight;
    }
    
    public String getName(){
        return this.videoName;
    }
    
    public String getPath(){
        return this.videoPath;
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
    
}
