package ssm.view;

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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
*   This class handles the WebView
*   Creates and sets the WebView
*/
public class WebViewer {
    WebView View;
    WebEngine ViewerEngine;
/**
 * 
 * @param Stage The Stage to set the WebView in
 * @param HTMLDirectory The Directory of the HTML document
 */
    public WebViewer(Stage Stage, String HTMLDirectory) {
        Stage stage = Stage;
        View = new WebView();
        ViewerEngine = View.getEngine();
       
        ViewerEngine.load(HTMLDirectory);
        java.net.CookieHandler.setDefault(new java.net.CookieManager());
     //Engine.setUserStyleSheetLocation("./index/public_html/slideshow_style.css");
       BorderPane ViewerPane = new BorderPane();
       ViewerPane.setCenter(View);
        Scene ViewerScene = new Scene(ViewerPane);
        Stage.setScene(ViewerScene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

    }
    public WebViewer(Stage Stage, String HTMLDirectory, int i) {
        Stage stage = Stage;
        View = new WebView();
        ViewerEngine = View.getEngine();
       
        ViewerEngine.load("");
     //Engine.setUserStyleSheetLocation("./index/public_html/slideshow_style.css");
       BorderPane ViewerPane = new BorderPane();
       ViewerPane.setCenter(View);
        Scene ViewerScene = new Scene(ViewerPane);
        Stage.setScene(ViewerScene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

    }
}

