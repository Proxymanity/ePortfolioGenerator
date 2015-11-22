/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.model;

import ePG.controller.FileController;
import ePG.view.GeneratorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Cokers
 */
public class Site {
    GeneratorView ui;
    ObservableList<Page> pages;
    FileController FileControl;
    
    public Site(GeneratorView initUI){
        ui = initUI;
        FileControl = ui.getFileController();
        pages = FXCollections.observableArrayList();
        reset();
    }
    
        /**
     * Resets the slide show to have no slides and a default title.
     */
    public void reset() {
	pages.clear();
	
    }
}
