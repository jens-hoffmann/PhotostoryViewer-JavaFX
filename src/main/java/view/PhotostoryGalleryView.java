package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.PSImage;
import model.Photostory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PhotostoryGalleryView {
    private Photostory photostory;

    public PhotostoryGalleryView(Photostory photostory) {
        this.photostory = photostory;
    }

    public List<Image> getImages() {
        List<Image> imageList = new ArrayList<>();
        for (PSImage psImage : this.photostory.getPhotos()) {
            String url = psImage.getImageUrl();
            File imagefile = new File(url);
            if (imagefile.exists() && !imagefile.isDirectory()) {
                 imageList.add(new Image(getClass().getResource(url).toExternalForm()));
            } else {
                System.out.println("File does not exists: " + url);
            }

        }
        return imageList;
    }
}
