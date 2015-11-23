/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.view;

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

/*
*   This class handles the WebView
*   Creates and sets the WebView
*/
public class WebViewer {
    
/**
 * 
 * @param Stage The Stage to set the WebView in
 * @param HTMLDirectory The Directory of the HTML document
 */
    public WebViewer(Pane ViewerPane ,String HTMLDirectory) {
       // Stage stage = Stage;
       WebView View = new WebView();
       WebEngine ViewerEngine = View.getEngine();
       
        ViewerEngine.load(HTMLDirectory);
        java.net.CookieHandler.setDefault(new java.net.CookieManager());
     //Engine.setUserStyleSheetLocation("./index/public_html/slideshow_style.css");
     //  BorderPane ViewerPane = new BorderPane();
          ViewerPane.getChildren().add(View);
       // Scene ViewerScene = new Scene(ViewerPane);
      //  Stage.setScene(ViewerScene);
     //   Screen screen = Screen.getPrimary();
    //    Rectangle2D bounds = screen.getVisualBounds();
     //   stage.setX(bounds.getMinX());
      //  stage.setY(bounds.getMinY());
     //   stage.setWidth(bounds.getWidth());
     //   stage.setHeight(bounds.getHeight());

    }
}

