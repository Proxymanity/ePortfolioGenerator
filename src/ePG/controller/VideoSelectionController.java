/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.controller;


import ePG.model.Page;
import java.io.File;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import static ssm.StartupConstants.PATH_SLIDE_SHOW_IMAGES;

/**
 * This controller provides a controller for when the user chooses to
 * select an image for the slide show.
 * 
 * @author McKilla Gorilla & _____________
 */
public class VideoSelectionController {
    
    /**
     * Default contstructor doesn't need to initialize anything
     */
    public VideoSelectionController() {    }
    
    /**
     * This function provides the response to the user's request to
     * select an image.
     * 
     * @param slideToEdit - Slide for which the user is selecting an image.
     * 
     * @param view The user interface control group where the image
     * will appear after selection.
     */
    public void processSelectVideo(Label videoPath, Label videoName) {
	FileChooser imageFileChooser = new FileChooser();
	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOW_IMAGES));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*.mp4");
	FileChooser.ExtensionFilter webmFilter = new FileChooser.ExtensionFilter("WEBM files (*.webm)", "*.webm");
	FileChooser.ExtensionFilter oggFilter = new FileChooser.ExtensionFilter("OGG files (*.ogg)", "*.ogg");
	imageFileChooser.getExtensionFilters().addAll(mp4Filter, webmFilter, oggFilter);
	
	// LET'S OPEN THE FILE CHOOSER
	File file = imageFileChooser.showOpenDialog(null);
	if (file != null) {
	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    String fileName = file.getName();
            videoPath.setText(path);
            videoName.setText(fileName);
	}
    }
}