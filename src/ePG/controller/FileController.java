/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.controller;

import ePG.file.GeneratorFileManager;
import ePG.model.Page;
import ePG.model.Site;
import ePG.view.GeneratorView;
import ePG.view.PageViewer;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import ssm.LanguagePropertyType;
import static ssm.StartupConstants.PATH_SITES;
import ssm.error.ErrorHandler;

/**
 *
 * @author Cokers
 */
public class FileController {
    GeneratorView ui;
    private GeneratorFileManager generatorIO;
    
    public FileController(GeneratorView initUI, GeneratorFileManager fileManager) {
        ui = initUI;
        generatorIO = fileManager;
    }
    
    public void handleNewSiteRequest(){
        ui.refresh();
    }
   
    public void handleSaveSiteRequest(){
        try{
        //Get the site to save
        Site siteToSave = ui.getSite();
        
        //Save it to a file
        generatorIO.saveSite(siteToSave, false);
        
        //Mark as saved?
        
        //
        }catch (IOException ioe){
            System.out.println("OH SHIT!");
        }
    }
    
    public void handleSaveAsSiteRequest(){
        try{
        //Get the site to save
        Site siteToSave = ui.getSite();
        
        //Save it to a file
        generatorIO.saveSite(siteToSave, false);
        
        //Mark as saved?
        
        //
        }catch (IOException ioe){
            System.out.println("OH SHIT!");
        }
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
        FileChooser ePortfolioFileChooser = new FileChooser();
        ePortfolioFileChooser.setInitialDirectory(new File("./data/ePortfolios/"));
        File selectedFile = ePortfolioFileChooser.showOpenDialog(ui.getWindow());

        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
     //   if (selectedFile != null) {
            try {
                Site site = ui.getSite();
                generatorIO.loadSlideShow(site, selectedFile.getAbsolutePath());
              //  ui.reloadSlideShowPane(slideShowToLoad);
                ui.updateToolbarControls();
            } catch (Exception e) {
                System.out.println("Error in promptToOpen");
     //       }
        }
    }

    public void handleExportRequest(GeneratorView newUI) {
       ui = newUI;
       Site siteToExport = ui.getSite();
       if(pageNameChecker(siteToExport)){
           System.out.println("Duplicate Names not allowed!");
           return;
       }
       Tab viewTab = ui.getView();
       PageViewer pageview = new PageViewer(ui);
       Page pageToView = siteToExport.getPages().get(ui.getSelected());
       pageview.viewPage(pageToView);
       viewTab.setContent(pageview);

    }
    
    //If true, duplicate names exist. 
    public boolean pageNameChecker(Site site){
        boolean result = false;
        for(int i = 0; i < site.getPages().size()-1; i++){
           for(int j = 1; j < site.getPages().size(); j++){
               
               String title1 = site.getPages().get(i).getTitle();
               String title2 = site.getPages().get(j).getTitle();
               
               if(title1.equals(title2)){
                   return true;
               }
               
           }
       }
        return result;
    }
}
