package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.Objects;

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
    BorderPane produit_scene;
    @FXML
    Button produit_btn;

    public Controller() throws IOException {
    }

    @FXML
    public void onClick_btn_Connexion(ActionEvent e) throws IOException {

        accueil_scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accueil.fxml")));
        authentification_scene.getChildren().removeAll();
        authentification_scene.getChildren().setAll(accueil_scene);
    }
    @FXML
    public void produit_OnClick(ActionEvent e) throws IOException{
        produit_scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("produit/produit_ui.fxml")));
        accueil_scene.getChildren().setAll(produit_scene);
    }

}
