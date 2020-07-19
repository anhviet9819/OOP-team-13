package SortVisualisation.Controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
public class MainController extends Application {

    @FXML private TabPane tabPane;
    public static void bootSuperController(String[] args) {
        MainController.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("From Viet Nhat C with love <3");
        Parent root = FXMLLoader.load(getClass().getResource("../View/Example.fxml"));
        stage.setScene(new Scene(root, 1000, 500));
        stage.show();
    }
    @SuppressWarnings("unused")
    public void initialize() {
        try {
            this.dynamicLoadSceneTabs();
        } catch (IOException | URISyntaxException e) {
            // @TODO: create proper error messages
            System.out.println("Error while loading the Scene Tabs:");
            e.printStackTrace();
        }
    }

    private void dynamicLoadSceneTabs() throws IOException, URISyntaxException {
        File folder = new File(getClass().getResource("../View/Tabs/").toURI());
        File[] listOfFiles = folder.listFiles((File f) -> {
            return f.getName().endsWith(".fxml");
        });
        for (File file : listOfFiles) {
            System.out.println("Loaded fxml Tab file:"+getClass().getResource("../View/Tabs/"+file.getName()));
            tabPane.getTabs().add(
                FXMLLoader.load(
                    new URL(getClass().getResource("../View/Tabs/") + file.getName())
                )
            );
        }
    }
}
