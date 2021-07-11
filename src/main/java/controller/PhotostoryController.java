package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.image.Image;
import model.PSImage;
import model.Photostory;
import view.PhotostoryGalleryView;
import view.PhotostoryTextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static com.google.gson.JsonParser.parseReader;

public class PhotostoryController {
    private File photostoryFile;
    private Photostory photostory;

    public PhotostoryController(File photostoryFile) {
        this.photostoryFile = photostoryFile;
        this.photostory = null;
        readPhotostory();
    }

    public void readPhotostory() {
        if (photostoryFile == null) {
            return;
        }
        try {
            JsonElement fileElement = parseReader(new FileReader(photostoryFile));
            JsonObject fileObject = fileElement.getAsJsonObject();
            String storyTitle = fileObject.get("storyTitle").getAsString();
            JsonArray jsonArrayPhotos = fileObject.get("photos").getAsJsonArray();
            photostory = new Photostory(storyTitle);
            for (JsonElement photosJsonElement : jsonArrayPhotos) {
                JsonObject photoJsonObject = photosJsonElement.getAsJsonObject();
                String imageUrl = photoJsonObject.get("imageUrl").getAsString();
                String title = photoJsonObject.get("title").getAsString();
                PSImage psImage = new PSImage(imageUrl, title);
                photostory.addPhoto(psImage);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getPhotostoryTitle() {
        return this.photostory.getStoryTitle();
    }

    public List<Image> viewPhotostory() {
        if (this.photostory != null) {
            PhotostoryGalleryView psImageList = new PhotostoryGalleryView(this.photostory);
            return psImageList.getImages();
        }
        return null;
    }
}
