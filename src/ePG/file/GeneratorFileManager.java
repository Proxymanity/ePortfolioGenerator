/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.file;

import ePG.model.Header;
import ePG.model.Image;
import ePG.model.ListObject;
import ePG.model.Page;
import ePG.model.Site;
import ePG.model.SlideShow;
import ePG.model.Text;
import ePG.model.Video;
import ePG.view.GeneratorView;
import ePG.view.PageEditView;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import static ssm.StartupConstants.PATH_SLIDE_SHOWS;
import static ssm.file.SlideShowFileManager.SLASH;



/**
 *
 * @author Cokers
 */
public class GeneratorFileManager {
    public static String JSON_TITLE = "title";
    public static String JSON_SLIDES = "slides";
    public static String JSON_SEV = "SlideEditView";
    public static String CAPTION = "CAPTION";
    public static String JSON_IMAGE_FILE_NAME = "image_file_name";
    public static String JSON_IMAGE_PATH = "image_path";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";
    public static String PATH_PORTFOLIOS = "./data/ePortfolios/";
    GeneratorView ui;
    
    public GeneratorFileManager(GeneratorView initUI){
        ui = initUI;
    }
    public void saveSite(Site siteToSave, boolean saveAs) throws IOException{
       // BUILD THE FILE PATH
        String ePortfolioTitle = "" + siteToSave.getSiteName();
        if(ePortfolioTitle.equals("") || saveAs == true){
            ePortfolioTitle = promptTitle();
            siteToSave.setSiteName(ePortfolioTitle);
        }
        String jsonFilePath = PATH_PORTFOLIOS + SLASH + ePortfolioTitle + JSON_EXT;
        
        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);  
           
        // BUILD THE SLIDES ARRAY
        JsonArray pagesJsonArray = makePagesJsonArray(siteToSave.getPages());
        
        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
        JsonObject courseJsonObject = Json.createObjectBuilder()
                                    .add(JSON_TITLE, siteToSave.getSiteName())
                                    .add("studentName", siteToSave.getName())
                                    .add("pages", pagesJsonArray)
                .build();
        
        // AND SAVE EVERYTHING AT ONCE
        jsonWriter.writeObject(courseJsonObject);
    } 
    
    private String promptTitle(){
        Label title = new Label();
        Stage temp = new Stage();
           
           VBox pane = new VBox();
           Label text = new Label("Enter ePortfolio title");
           TextField input = new TextField();
           text.setAlignment(Pos.CENTER);
           pane.getChildren().addAll(text, input);
           
           Button Ok = new Button("Set Site Title");
           Ok.setOnAction(new EventHandler<ActionEvent>(){
               @Override
               public void handle(ActionEvent e){
                   if(input.getText() != ""){
                   title.setText(input.getText());
                   temp.close();
                   }
               }
           });
           
           HBox buttonBox = new HBox();  
           buttonBox.setAlignment(Pos.CENTER);
           buttonBox.getChildren().addAll(Ok);
           
           BorderPane main = new BorderPane();
           main.setTop(pane);
           main.setCenter(buttonBox);
           Scene scene = new Scene(main);
           temp.setScene(scene);
           temp.showAndWait();
        return title.getText();
    }
    
    
    private JsonArray makePagesJsonArray(List<Page> pages) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Page page : pages) {
	    JsonObject jso = makePageJsonObject(page);
	    jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;        
    }
    
    /**
     * Saves the page object
     * 
     * @param page Page object to save
     * @return JsonObject of Page
     **/
    private JsonObject makePageJsonObject(Page page) {
        JsonArray itemsJsonArray = makeItemsJonArray(page.getItems());
        JsonArray headerJsonArray = makeHeaderJsonArray(page.getHeader());
        JsonArray textJsonArray = makeTextJsonArray(page.getText());
        JsonArray listJsonArray = makeListJsonArray(page.getList());
        JsonArray imageJsonArray = makeImageJsonArray(page.getImage());
        JsonArray videoJsonArray = makeVideoJsonArray(page.getVideo());
        JsonObject jso = Json.createObjectBuilder()
                .add("title", page.getTitle())
                .add("font", page.getFont())
		.add("layout", page.getLayout())
		.add("color", page.getColor())
                .add("ITEMS", itemsJsonArray)
                .add("HEADER", headerJsonArray)
                .add("TEXT", textJsonArray)
                .add("LIST", listJsonArray)
                .add("IMAGE", imageJsonArray)
                .add("VIDEO", videoJsonArray)
		.build();
	return jso;
    }

    private JsonArray makeItemsJonArray(ObservableList<String> items) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(String item : items){
            jsb.add(item);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonArray makeHeaderJsonArray(ObservableList<Header> headers) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(Header header : headers){
            JsonObject jso = makeHeaderJsonObject(header);
	    jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makeHeaderJsonObject(Header header) {
        JsonObject jso = Json.createObjectBuilder()
		.add("text", header.getText())
		.build();
	return jso;
    }
    
    private JsonArray makeTextJsonArray(ObservableList<Text> Texts) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(Text text : Texts){
            JsonObject jso = makeTextJsonObject(text);
	    jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makeTextJsonObject(Text text) {
        JsonObject jso = Json.createObjectBuilder()
                .add("font", text.getFont())
		.add("text", text.getText())
		.build();
	return jso;
    }
    
    private JsonArray makeListJsonArray(ObservableList<ListObject> lists) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(ListObject list : lists){
            JsonObject jso = makeListJsonObject(list);
	    jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makeListJsonObject(ListObject list) {
        ObservableList<String> listItems = list.getList();
        JsonArray listItemsArray = makeListItemsJsonArray(list);
        JsonObject jso = Json.createObjectBuilder()
                .add("font", list.getFont())
                .add("listItems", listItemsArray)
		.build();
	return jso;
    }
    
    private JsonArray makeListItemsJsonArray(ListObject list){
        JsonArrayBuilder listItemArray = Json.createArrayBuilder();
        ObservableList<String> listItems = list.getList();
        for(String item : listItems){
            listItemArray.add(item);
        }
        JsonArray jA = listItemArray.build();
        return jA;
    }

    private JsonArray makeImageJsonArray(ObservableList<Image> images) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(Image image : images){
            JsonObject jso = makeImageJsonObject(image);
	    jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makeImageJsonObject(Image image) {
        JsonObject jso = Json.createObjectBuilder()
                .add("ImageName", image.getName())
		.add("ImagePath", image.getPath())
                .add("caption", image.getCaption())
                .add("width", image.getWidth())
                .add("height", image.getHeight())
                .add("alignment", image.getAlign())
		.build();
	return jso;
    }
    
    private JsonArray makeVideoJsonArray(ObservableList<Video> videos) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for(Video video : videos){
            JsonObject jso = makeVideoJsonObject(video);
	    jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;
    }

    private JsonObject makeVideoJsonObject(Video video) {
        JsonObject jso = Json.createObjectBuilder()
                .add("VideoName", video.getName())
		.add("VideoPath", video.getPath())
                .add("caption", video.getCaption())
                .add("width", video.getWidth())
                .add("height", video.getHeight())
		.build();
	return jso;
    }
    
    public void loadSlideShow(Site siteToLoad, String jsonFilePath) throws IOException {
        // LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(jsonFilePath);
        
        // NOW LOAD THE SITE
	siteToLoad.reset();
        siteToLoad.setSiteName(json.getString("title"));
        siteToLoad.setName(json.getString("studentName"));
        JsonArray jsonPagesArray = json.getJsonArray("pages");
        
        // Get and load each page
        Page tempPage;
        TabPane pageSelect = ui.getPageSelect();
        pageSelect.getTabs().clear();
           
        for (int i = 0; i < jsonPagesArray.size(); i++) { 
           // PageEditView pev = new PageEditView();
           // Page tempPage = new Page();
	    JsonObject PageJso = jsonPagesArray.getJsonObject(i);
            //Load in the data for the Page;
           Tab newPage = new Tab();
           PageEditView pev = new PageEditView(ui, newPage);
           siteToLoad.getPEV().add(pev);
           newPage.setText(pev.getTitle());
           newPage.setContent(pev); 
           pageSelect.getTabs().add(newPage);
            
           int pageIndex = siteToLoad.getPEV().indexOf(pev);
           tempPage = siteToLoad.getPages().get(pageIndex);
            
            
            ObservableList<String> items = tempPage.getItems();
            ObservableList<Header> Header = tempPage.getHeader();
            ObservableList<Text> Text = tempPage.getText();
            ObservableList<ListObject> List = tempPage.getList();
            ObservableList<Image> Image = tempPage.getImage();
             ObservableList<Video> Video = tempPage.getVideo();
           // ObservableList<SlideShow> SlideShow = tempPage.getSlideShow();
            tempPage.setTitle(PageJso.getString("title"));
            tempPage.setFont(PageJso.getString("font"));
            tempPage.setLayout(PageJso.getString("layout"));
            tempPage.setColor(PageJso.getString("color"));
            
            items.addAll(loadArrayFromJSONFile(jsonFilePath, "ITEMS", PageJso));
            
            JsonArray jsonHeaderArray = PageJso.getJsonArray("HEADER");
                for(i = 0; i < jsonHeaderArray.size(); i++){
                    Header tempHeader = new Header(
                            jsonHeaderArray.getJsonObject(i).getString("text"));
                    Header.add(tempHeader);
                }
                
            JsonArray jsonTextArray = PageJso.getJsonArray("TEXT");
                for(i = 0; i < jsonTextArray.size(); i++){
                    Text tempText = new Text(
                            jsonTextArray.getJsonObject(i).getString("font"),
                            jsonTextArray.getJsonObject(i).getString("text"));
                    Text.add(tempText);
                }
            
            JsonArray jsonListArray = PageJso.getJsonArray("LIST");
                for(i = 0; i < jsonListArray.size(); i++){
                    JsonObject jsonListObject = jsonListArray.getJsonObject(i);
                    String font = jsonListObject.getString("font");
                    ObservableList<String> ListItems = FXCollections.observableArrayList();
                    
                    
                    
                    JsonArray jsonArray = jsonListObject.getJsonArray("listItems");
                    for (JsonValue jsV : jsonArray) {
                         ListItems.add(jsV.toString());
                     }
                    ListObject tempList = new ListObject(ListItems, font);      
                    List.add(tempList);
                }  
            
            JsonArray jsonImageArray = PageJso.getJsonArray("IMAGE");
                for(i = 0; i < jsonImageArray.size(); i++){
                    Image tempImage = new Image(
                            jsonImageArray.getJsonObject(i).getString("ImageName"),
                            jsonImageArray.getJsonObject(i).getString("ImagePath"),
                            jsonImageArray.getJsonObject(i).getString("caption"),
                            jsonImageArray.getJsonObject(i).getString("width"),
                            jsonImageArray.getJsonObject(i).getString("height"),
                            jsonImageArray.getJsonObject(i).getString("alignment"));
                    Image.add(tempImage);
                }
             
            JsonArray jsonVideoArray = PageJso.getJsonArray("VIDEO");
                for(i = 0; i < jsonImageArray.size(); i++){
                    Video tempVideo = new Video(
                            jsonVideoArray.getJsonObject(i).getString("VideoName"),
                            jsonVideoArray.getJsonObject(i).getString("VideoPath"),
                            jsonVideoArray.getJsonObject(i).getString("caption"),
                            jsonVideoArray.getJsonObject(i).getString("width"),
                            jsonVideoArray.getJsonObject(i).getString("height"));
                    Video.add(tempVideo);
                }
             
	    //slideShowToLoad.addSlide(	slideJso.getString(JSON_IMAGE_FILE_NAME),
		//			slideJso.getString(JSON_IMAGE_PATH),
            //                            SEVJso.getString(CAPTION));
            
            
            //After making the page, add to site
                    // siteToLoad.add(tempPage);
	}
        ui.updateToolbarControls();
        siteToLoad.refresh();
    }

    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }    
    
    private ObservableList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName, JsonObject PageJso) throws IOException {
       // JsonObject json = loadJSONFile(jsonFilePath);
        ObservableList<String> items = FXCollections.observableArrayList();
        JsonArray jsonArray = PageJso.getJsonArray(arrayName);
        for (JsonValue jsV : jsonArray) {
            String temp = jsV.toString();
            items.add(
             temp.substring(1, temp.length()-1));
        }
        return items;
    }
    
}