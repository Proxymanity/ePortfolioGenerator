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
public class SlideShow {
    String SlideShowPath;
    String SlideShowName;
    
    public SlideShow(String name, String path){
        SlideShowName = name;
        SlideShowPath = path;
    }
    
    public void update(String name, String path){
        SlideShowName = name;
        SlideShowPath = path;
    }
    
    public String getName(){
        return this.SlideShowName;
    }
    
    public String getPath(){
        return this.SlideShowPath;
    }
}
