/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.view;


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
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * 
 * @author Cokers
 */
public class PageViewer extends Pane{
    WebViewer webBrowser;
    
    
    public PageViewer(GeneratorView ui){
        try {
            File pageHTML = new File("./sites/page3.html");
            
            String url = new String(pageHTML.toURI().toURL().toString());
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
