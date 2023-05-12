package sample.produit;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class ajouterProduit {
    @FXML
    BorderPane ajouter_produit_scene;
    @FXML
    private BorderPane produit_scene;
    @FXML
    public void ajouterProduit() throws IOException {
        ajouter_produit_scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ajouter_produit.fxml")));
        produit_scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../accueil.fxml")));
        produit_scene.getChildren().removeAll();
        produit_scene.getChildren().setAll(ajouter_produit_scene);
    }
}
