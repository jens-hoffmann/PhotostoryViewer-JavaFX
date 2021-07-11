package view;

import model.PSImage;
import model.Photostory;

public class PhotostoryTextView {
    private Photostory photostory;
    private TextView textView;

    public PhotostoryTextView(Photostory photostory) {
        this.photostory = photostory;
        this.textView = new TextView();
    }

    public String viewStoryTitle() {
        this.textView.addLine(this.photostory.getStoryTitle());
        for (PSImage psImage : this.photostory.getPhotos()) {
            this.textView.addLine("Image: "+ psImage.getTitle());
        }
        return this.textView.getView();
    }
}
