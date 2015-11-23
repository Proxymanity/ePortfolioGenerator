package ssm;

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
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;
import static ssm.LanguagePropertyType.TITLE_WINDOW;
import static ssm.StartupConstants.PATH_DATA;
import static ssm.StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static ssm.StartupConstants.MAIN;
import static ssm.StartupConstants.English;
import static ssm.StartupConstants.LBox;
import static ssm.StartupConstants.Spanish;
import static ssm.StartupConstants.BBox;
import static ssm.StartupConstants.CENTER;
import static ssm.StartupConstants.CSS_TITLE;
import static ssm.StartupConstants.STYLE_SHEET_UI;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;
import ssm.view.SlideShowMakerView;

/**
 * SlideShowMaker is a program for making custom image slideshows. It will allow
 * the user to name their slideshow, select images to use, select captions for
 * the images, and the order of appearance for slides.
 *
 * @author McKilla Gorilla & _____________
 */
public class SlideShowMaker{
    // THIS WILL PERFORM SLIDESHOW READING AND WRITING
    SlideShowFileManager fileManager = new SlideShowFileManager();
    public String UI_PROPERTIES_FILE_NAME = English;
    // THIS HAS THE FULL USER INTERFACE AND ONCE IN EVENT
    // HANDLING MODE, BASICALLY IT BECOMES THE FOCAL
    // POINT, RUNNING THE UI AND EVERYTHING ELSE
    SlideShowMakerView ui = new SlideShowMakerView(fileManager);

    
    public void startEditor() throws Exception {
        // LOAD APP SETTINGS INTO THE GUI AND START IT UI
        Stage primaryStage = new Stage();
        boolean success = loadProperties();
        if (success) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty(TITLE_WINDOW);

	    // NOW START THE UI IN EVENT HANDLING MODE
	    ui.startUI(primaryStage, appTitle);
       
	} // THERE WAS A PROBLEM LOADING THE PROPERTIES FILE
	else {
	    // LET THE ERROR HANDLER PROVIDE THE RESPONSE
	    ui.getWindow().close();
	}
    }
    
    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
	    props.loadProperties(UI_PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ui.getErrorHandler();
            eH.processLanguageError("Error in loading language properties - default English Language used.");
            return false;
        }        
    }

    /**
     * This is where the application starts execution. We'll load the
     * application properties and then use them to build our user interface and
     * start the window in event handling mode. Once in that mode, all code
     * execution will happen in response to user requests.
     *
     * @param args This application does not use any command line arguments.
     */
}
