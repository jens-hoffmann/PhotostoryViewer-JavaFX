package controller;

import model.PSImage;
import model.Photostory;

import java.util.ArrayList;
import java.util.List;

public class PhotoController {
    private Photostory photostory;
    private List<PSImage> photos;

    public PhotoController(Photostory photostory) {
        this.photostory = photostory;
        this.photos = photostory.getPhotos();
    }

}
