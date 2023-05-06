package sample;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private Text login;
    @FXML
    private Text password;
    @FXML
    BorderPane authentification_scene;
    @FXML
    BorderPane accueil_scene;

    @FXML
    public void onClick_btn_Connexion(ActionEvent e) throws IOException {

        accueil_scene = FXMLLoader.load(getClass().getResource("accueil.fxml"));
        authentification_scene.getChildren().removeAll();
        authentification_scene.getChildren().setAll(accueil_scene);
        System.out.println("hello");
    }

}
