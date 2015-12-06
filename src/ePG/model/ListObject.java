/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Cokers
 */
public class ListObject {
    ObservableList<String> list;
    String font;
    
    public ListObject(ObservableList<String> initList, String initFont){
        list = initList;
        font = initFont;
    }
    
    
    public void update(ObservableList<String> initList, String initFont){
        list = initList;
        font = initFont;
    }
    
    public ObservableList<String> getList(){
        return this.list;
    }
    
    public String getFont(){
        return this.font;
    }
}
