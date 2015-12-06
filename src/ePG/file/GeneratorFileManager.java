/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ePG.file;

import ePG.model.Page;
import ePG.model.Site;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;



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

   /** 
    public void saveSite(Site siteToSave) throws IOException{
       // BUILD THE FILE PATH
        String slideShowTitle = "" + siteToSave.getTitle();
        String jsonFilePath = PATH_SITES + SLASH + slideShowTitle + JSON_EXT;
        
        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);  
           
        // BUILD THE SLIDES ARRAY
        JsonArray slidesJsonArray = makePagesJsonArray(siteToSave.getPages());
        
        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
        JsonObject courseJsonObject = Json.createObjectBuilder()
                                    .add(JSON_TITLE, slideShowToSave.getTitle())
                                    .add(JSON_SLIDES, slidesJsonArray)
                                    .add(JSON_SEV, SEVJsonArray)
                .build();
        
        // AND SAVE EVERYTHING AT ONCE
        jsonWriter.writeObject(courseJsonObject);
    } 
    
    private JsonArray makeSlidesJsonArray(List<Page> pages) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Page slide : pages) {
	    JsonObject jso = makePageJsonObject(slide);
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
     
    private JsonObject makePageJsonObject(Page page) {
        JsonObject jso = Json.createObjectBuilder()
		.add(JSON_IMAGE_FILE_NAME, slide.getImageFileName())
		.add(JSON_IMAGE_PATH, slide.getImagePath())
		.build();
	return jso;
    }

  **/  
    
}