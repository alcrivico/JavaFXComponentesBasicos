import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.text.Font;

/**
 * cd
 *
 * @author amatr
 */
public class JavaFXComponentesBasicos extends Application {

    @Override
    public void start(Stage principalStage) throws Exception {
        Font poppins = Font.loadFont(getClass().getResourceAsStream("./resources/fonts/Poppins-Regular.ttf"), 12);

        Parent root = null;

        root = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));

        Scene scene = new Scene(root);

        String css = this.getClass().getResource("FXMLPrincipal.css").toExternalForm();

        scene.getStylesheets().add(css);

        principalStage.setScene(scene);

        principalStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
