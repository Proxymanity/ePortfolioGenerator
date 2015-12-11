/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.view;


import ePG.model.Page;
import ePG.model.Site;
import ePG.model.Video;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.io.IOException;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.URI;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ssm.LanguagePropertyType;
import static ssm.StartupConstants.PATH_SITES;
/**
 * 
 * @author Cokers
 */
public class PageViewer extends Pane{
    WebViewer webBrowser;
    GeneratorView ui;
    Site site;
    ObservableList<Page> pages;
    String siteTitle;
    String siteDir;
    String imageDir;
    String cssDir;
    String jsDir;
    String jSonDir;
    String videoDir;
    public PageViewer(GeneratorView initui){
        // KEEP THIS FOR LATER
	ui = initui;
        
	// GET THE Pages
        site = ui.getSite();
        pages = site.getPages();
        
        //Create the sites directory if not exist
        File sites = new File(PATH_SITES);
        if(!sites.exists()){
            sites.mkdir();
       }
        siteTitle = (site.getSiteName());
        siteDir = PATH_SITES + siteTitle;
        File siteFolder = new File(siteDir);
        if(siteFolder.exists()){
            deleteDirectory(siteFolder.getAbsoluteFile());
        }else{
            siteFolder.mkdir();
        }
        cssDir = new String(siteDir + "/css/");
        jsDir = new String(siteDir + "/js/");
        imageDir = new String(siteDir + "/img/");
        videoDir = new String(siteDir + "/video/");
        jSonDir = new String(siteDir + "/jSon/");
        File css = new File(cssDir);
        File js = new File(jsDir);
        File images = new File(imageDir);
        File icon = new File(videoDir);
        File jSon = new File(jSonDir);
        boolean a = false,b = false,c = false, d= false, e = false;
            a = css.mkdir();
            b = js.mkdir();
            c = images.mkdir();
            d = jSon.mkdir();
            e = icon.mkdir();
            if(!a || !b || !c || !d || !e){
                System.out.println("A folder was not created!");
            }
    }
    
    public boolean deleteDirectory(File filePath) {
    if(filePath.exists()) {
      File[] files = filePath.listFiles();
      for(int i=0 ; i<files.length ; i++){
         if(files[i].isDirectory()) {
           deleteDirectory(files[i]);
         }else {
           files[i].delete();
         }}}
    return(filePath.delete());
  }
    
    public void viewPage(Page selectedPage){
        File siteIndex = new File(siteDir + "/" + selectedPage.getTitle() + ".html");
        boolean b = true;
        // Now copy over the images
        int i = 1;
        for(Page page: site.getPages()){
            if(i > 0){
             File BnrImg = new File(imageDir + page.getBannerImageName());
            try {
                BnrImg.createNewFile();
            } catch (IOException ex) {
                
            }
            Path source =  Paths.get(page.getBannerImagePath() + page.getBannerImageName());
            Path dest = Paths.get(imageDir);
            CopyOption A = StandardCopyOption.REPLACE_EXISTING;
            try {
                Path destination = dest.resolve(page.getBannerImageName());
                Files.copy(source, destination, A);
            } catch (IOException ex) {
                
            }
           }
            for(ePG.model.Image imageObject : page.getImage()){
                
            File newImg = new File(imageDir + imageObject.getName());
            try {
                newImg.createNewFile();
            } catch (IOException ex) {
                
            }
            Path source =  Paths.get(imageObject.getPath() + imageObject.getName());
            Path dest = Paths.get(imageDir);
            CopyOption A = StandardCopyOption.REPLACE_EXISTING;
            try {
                Path destination = dest.resolve(imageObject.getName());
                Files.copy(source, destination, A);
            } catch (IOException ex) {
                
            }
        }
        //Videos
            for(Video video : page.getVideo()){
            File newImg = new File(imageDir + video.getName());
            try {
                newImg.createNewFile();
            } catch (IOException ex) {
                
            }
            Path source =  Paths.get(video.getPath() + video.getName());
            Path dest = Paths.get(videoDir);
            CopyOption A = StandardCopyOption.REPLACE_EXISTING;
            try {
                Path destination = dest.resolve(video.getName());
                Files.copy(source, destination, A);
            } catch (IOException ex) {
                
            }
        }
        //HTML files
           File index = new File(siteDir + "/" + page.getTitle() + ".html");
            try {
                index.createNewFile();
            } catch (IOException ex) {
            }
            Path source =  Paths.get("baseLayout/page"+ i +".html");
            i++;
            Path dest = Paths.get(siteDir);
            CopyOption A = StandardCopyOption.REPLACE_EXISTING;
            try {
                Path destination = dest.resolve(page.getTitle()+ ".html");
                Files.copy(source, destination, A);
            } catch (IOException ex) {
            }
        }
        
        //Now copy over the JSON object
        if(b){
        File JSON = new File(jSonDir + siteTitle + ".json");
            try {
                JSON.createNewFile();
            } catch (IOException ex) {
            }
            Path source =  Paths.get("data/ePortfolios/" + siteTitle + ".json");
            Path dest = Paths.get(jSonDir);
            CopyOption A = StandardCopyOption.REPLACE_EXISTING;
            try {
                Path destination = dest.resolve(siteTitle + ".json");
                Files.copy(source, destination, A);
            } catch (IOException ex) {
            }
         }
        //Now copy over the javascript
        if(b){
           File JS = new File(jsDir + "siteController.js");
            try {
                JS.createNewFile();
            } catch (IOException ex) {
            }
            Path source =  Paths.get("baseLayout/js/siteController.js");
            Path dest = Paths.get(jsDir);
            CopyOption A = StandardCopyOption.REPLACE_EXISTING;
            try {
                Path destination = dest.resolve("siteController.js");
                Files.copy(source, destination, A);
            } catch (IOException ex) {
            } 
        }
         //JQuery file
        if(b){
           File JQ = new File(jsDir + "jquery-2.1.4.min.js");
            try {
                JQ.createNewFile();
            } catch (IOException ex) {
            }
            Path source =  Paths.get("baseLayout/js/jquery-2.1.4.min.js");
            Path dest = Paths.get(jsDir);
            CopyOption A = StandardCopyOption.REPLACE_EXISTING;
            try {
                Path destination = dest.resolve("jquery-2.1.4.min.js");
                Files.copy(source, destination, A);
            } catch (IOException ex) {
            } 
        }
        //CSS file
        if(b){
           ObservableList<String> cssList =  FXCollections.observableArrayList();
           cssList.addAll("color1.css", "color2.css", "color3.css", "color4.css", "color5.css",
                "font1.css", "font2.css", "font3.css", "font4.css", "font5.css",
                "layout1.css", "layout2.css", "layout3.css", "layout4.css", "layout5.css", "backgroud.png");
           for(String cssFile: cssList){
           File CSS = new File(cssDir + cssFile);
            try {
                CSS.createNewFile();
            } catch (IOException ex) {
            }
            Path source =  Paths.get("baseLayout/css/" + cssFile);
            Path dest = Paths.get(cssDir);
            CopyOption A = StandardCopyOption.REPLACE_EXISTING;
            try {
                Path destination = dest.resolve(cssFile);
                Files.copy(source, destination, A);
            } catch (IOException ex) {
            } 
            }
        }
        // Writes the JSON file name to a javaScript file
            FileWriter fWriter = null;
            BufferedWriter writer = null;
        try {
           fWriter = new FileWriter(siteDir +"/js/jsonName.js");
           writer = new BufferedWriter(fWriter);
           writer.write("var SiteName = '"+ siteTitle +".json';");
           writer.newLine(); 
           writer.close(); 
        } catch (IOException ex) {
        }   
        
        try {
        String url = new String( siteIndex.toURI().toURL().toString());
        java.net.CookieHandler.setDefault(new com.sun.webkit.network.CookieManager());
            WebView View = new WebView();
            WebEngine ViewerEngine = View.getEngine();
            
            ViewerEngine.load(url);
            java.net.CookieHandler.setDefault(new java.net.CookieManager());
            // Engine.setUserStyleSheetLocation("./index/public_html/slideshow_style.css");

            this.getChildren().add(View);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PageViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
  
    }
