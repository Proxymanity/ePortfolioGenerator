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
public class Header {
    String text;
    
    //Default text & font
    public Header(){
        text = "Enter Text";
    }
    
    public Header(String txt){
        this.text = txt;
    }
    
    public void update(String txt){
        this.text = txt;
    }
    
    public void setText(String str){
        this.text = str;
    }
    
    public String getText(){
        return this.text;
    }
    
   
}
