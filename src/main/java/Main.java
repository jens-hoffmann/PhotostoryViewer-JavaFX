import controller.PhotostoryController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    private ListView<String> photoStoryListView = new ListView<String>();
    private List<String> photoStoryTitleList;
    private ObservableList<String> observableList;
    private List<File> fileList;
    private HashMap<String, PhotostoryController> psControllerList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        SplitPane splitPane = new SplitPane();
        StackPane stackPane1 = new StackPane();
        StackPane stackPane2 = new StackPane();
        FlowPane flowPane = new FlowPane();
        VBox vbox = new VBox();

        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);
        splitPane.getItems().addAll(stackPane1, stackPane2);
        root.setCenter(splitPane);
        stackPane1.getChildren().add(photoStoryListView);
        stackPane2.getChildren().add(flowPane);

        Menu menu = new Menu("Datei");
        MenuItem menuItem1 = new MenuItem("Öffnen");
        MenuItem menuItem2 = new MenuItem("Exit");
        menu.getItems().addAll(menuItem1, menuItem2);
        menuBar.getMenus().add(menu);

        photoStoryTitleList = new ArrayList<>();
        psControllerList = new HashMap<String, PhotostoryController>();
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("json", "*.json"));
                fileList = fileChooser.showOpenMultipleDialog(primaryStage);
                if (fileList != null) {
                    for (File file : fileList) {
                        PhotostoryController psController = new PhotostoryController(file);
                        String storyTitle = psController.getPhotostoryTitle();
                        psControllerList.put(storyTitle, psController);
                        photoStoryTitleList.add(storyTitle);
                    }
                }
                observableList = FXCollections.observableArrayList(photoStoryTitleList);
                photoStoryListView.setItems(observableList);

            }
        });

        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });

        photoStoryListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedPS = photoStoryListView.getSelectionModel().getSelectedItem();
                //flowPane.getChildren().clear();
                PhotostoryController currentController = psControllerList.get(selectedPS);
                List<Image> photoList = currentController.viewPhotostory();
                for (Image photo : photoList) {
                    final ImageView imageView = new ImageView(photo);
                    Label lab = new Label("Hello");
                    flowPane.getChildren().add(imageView);
                }

            }
        });


        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
