package model;

import java.util.ArrayList;
import java.util.List;

public class Photostory {
    private String storyTitle;
    private PSImage storyTitleImage;
    private List<PSImage> photos;

    public Photostory(String storyTitle) {
        this.storyTitle = storyTitle;
        this.storyTitleImage = null;
        this.photos = new ArrayList<>();
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public PSImage getStoryTitleImage() {
        return storyTitleImage;
    }

    public void setStoryTitleImage(PSImage storyTitleImage) {
        this.storyTitleImage = storyTitleImage;
    }

    public void addPhoto(PSImage psImage) {
        this.photos.add(psImage);
    }

    public List<PSImage> getPhotos() {
        return this.photos;
    }
}
