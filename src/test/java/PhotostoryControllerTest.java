import controller.PhotostoryController;
import javafx.scene.image.Image;
import model.Photostory;
import org.junit.jupiter.api.Test;
import view.PhotostoryTextView;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhotostoryControllerTest {

    @Test
    void loadPhotostory() {
        File ps1 = new File("src/test/Photostory_1615141596_Curiosity.json");
        PhotostoryController psControl = new PhotostoryController(ps1);
        List<Image> imageList = psControl.viewPhotostory();
        assertTrue(imageList.size() > 0);
    }
}
