/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.model;

import ePG.controller.FileController;
import ePG.view.GeneratorView;
import ePG.view.PageEditView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Cokers
 */
public class Site {
    GeneratorView ui;
    ObservableList<Page> pages;
    ObservableList<PageEditView> pevs;
    String siteName;
    String StudentName;
    FileController FileControl;
    
    public Site(GeneratorView initUI){
        ui = initUI;
        FileControl = ui.getFileController();
        pages = FXCollections.observableArrayList();
        pevs =  FXCollections.observableArrayList();
        siteName = "";
    }
    
    public ObservableList<PageEditView> getPEV(){
        return this.pevs;
    }
    
    public ObservableList<Page> getPages(){
        return this.pages;
    }
    
        /**
     * Resets the slide show to have no slides and a default title.
     */
    public void reset() {
        siteName = "";
	pages.clear();
	pevs.clear();
    }
    
    public void refresh(){
        for(PageEditView pev : pevs){
            pev.reload();
        }
    }

    public void setName(String text) {
         this.StudentName = text;
         for(int x = 0; x < pages.size(); x++){
            pages.get(x).setName(text);
            pevs.get(x).reload();
         }
    }
    
    public void setSiteName(String text){
        this.siteName = text;
    }
    public String getSiteName(){
        return this.siteName;
    }
    
    public String getName(){
        return this.StudentName;
    }
    
    public void add(Page page){
        this.pages.add(page);
    }
}
