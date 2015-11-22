/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eportfoliogenerator;

import ePG.file.GeneratorFileManager;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ePG.view.GeneratorView;

/**
 *
 * @author Cokers
 */
public class EPortfolioGenerator extends Application {
    // THIS WILL PERFORM SLIDESHOW READING AND WRITING
    GeneratorFileManager fileManager = new GeneratorFileManager();
    GeneratorView ui = new GeneratorView(fileManager);
    
    
    @Override
    public void start(Stage primaryStage){
        ui.startUI(primaryStage, "ePortfolioGenerator");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
