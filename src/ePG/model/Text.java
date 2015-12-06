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
public class Text {
    String text;
    String font;
    
    //Default text & font
    public Text(){
        text = "Enter Text";
        font = "1";
    }
    
    public Text(String txt, String fnt){
        this.text = txt;
        this.font = fnt;
    }
    
    public void update(String txt, String fnt){
        this.text = txt;
        this.font = fnt;
    }
    
    public void setText(String str){
        this.text = str;
    }
    
    public void setFont(String str){
        this.font = str;
    }
    
    public String getText(){
        return this.text;
    }
    
    public String getFont(){
        return this.font;
    }
}
