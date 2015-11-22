/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.controller;

import ePG.file.GeneratorFileManager;
import ePG.view.GeneratorView;
import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author Cokers
 */
public class FileController {
    GeneratorView ui;
    public FileController(GeneratorView initUI, GeneratorFileManager fileManager) {
        ui = initUI;
    }
    
    public void handleNewSiteRequest(){
        //TODO
        promptToOpen();
    }
    
    public void handleLoadSiteRequest(){
        promptToOpen();
    }
    
       /**
     * This helper method asks the user for a file to open. The user-selected
     * file is then loaded and the GUI updated. Note that if the user cancels
     * the open process, nothing is done. If an error occurs loading the file, a
     * message is displayed, but nothing changes.
     */
    private void promptToOpen() {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN
        FileChooser slideShowFileChooser = new FileChooser();
       // slideShowFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOWS));
       // File selectedFile = slideShowFileChooser.showOpenDialog(ui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
     //   if (selectedFile != null) {
            try {
		//Site site = ui.getSlideShow();
              //  slideShowIO.loadSlideShow(slideShowToLoad, selectedFile.getAbsolutePath());
              //  ui.reloadSlideShowPane(slideShowToLoad);
                ui.updateToolbarControls();
            } catch (Exception e) {
                System.out.println("Error in promptToOpen");
     //       }
        }
    }
}
