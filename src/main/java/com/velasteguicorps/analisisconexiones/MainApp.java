package com.velasteguicorps.analisisconexiones;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author luis velastegui
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ScenePrincipal.fxml"));
        Scene scene = new Scene(root);
        stage.setOnCloseRequest((e) -> System.exit(0));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Proyecto Seguridad");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon-principal.png")));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
