package sample.produit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import modele.Produit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ProduitUi implements Initializable {

        @FXML
        private TableView<Produit> produit_table;
        @FXML
        private TableColumn<Produit, String> designation_produit_colonne;
        @FXML
        private TableColumn<Produit, String> type_produit_colonne;
        @FXML
        private TableColumn<Produit, Float> prix_usd_produit_colonne;
        @FXML
        private TableColumn<Produit, Float> prix_cdf_produit_colonne;
        @FXML
        private TableColumn<Produit, LocalDate> date_expiration_colonne;
        @FXML
        private TableColumn<Produit, String> description_produit_colonne;
        @FXML
        private TextField motcle_text_field;
        @FXML
        private BorderPane ajouter_produit_scene;
        @FXML
        private BorderPane produit_scene;
        @FXML
        private ToggleGroup type_Menu_medicament;

        @FXML
        private TextField designation_textField;
        @FXML
        private TextField prix_usd_textField;
        @FXML
        private TextField prix_cdf_textField;
        @FXML
        private RadioButton siro_type_radioButton;
        @FXML
        private RadioButton comprime_type_radioButton;
        @FXML
        private RadioButton injectable_type_radioButton;
        @FXML
        private RadioButton perfusion_type_radioButton;
        @FXML
        private DatePicker date_expiration_Date;
        @FXML
        private TextArea description_textArea;
        @FXML
        private Button accueil_button;
        @FXML
        private Button produits_button;
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            //System.out.println();
            if(produit_scene!=null){
                try {
                    ObservableList<Produit>  produits = FXCollections.observableList(Produit.getAllProduit());

                    designation_produit_colonne.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
                    type_produit_colonne.setCellValueFactory(new PropertyValueFactory<>("type"));
                    prix_usd_produit_colonne.setCellValueFactory(new PropertyValueFactory<>("prix_usd"));
                    prix_cdf_produit_colonne.setCellValueFactory(new PropertyValueFactory<>("prix_cdf"));
                    date_expiration_colonne.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
                    designation_produit_colonne.setCellValueFactory(new PropertyValueFactory<>("description"));


                    produit_table.setItems(produits);

                    FilteredList<Produit> produitsFiltres = new FilteredList<>(produits,b -> true);
                    motcle_text_field.textProperty().addListener((observable, old_value, new_value)->{
                        produitsFiltres.setPredicate(produit -> {
                            if (new_value.isEmpty() || new_value.isBlank() || new_value==null){
                                return true;
                            }
                            String motcherche = new_value.toLowerCase();
                            if (produit.getDesignation().toLowerCase().indexOf(motcherche) > -1){
                                return true;
                            }
                            else if(produit.getType().toLowerCase().indexOf(motcherche) > -1){
                                return true;
                            }
                            else{
                                return false;
                            }
                        });
                    });
                    SortedList<Produit> sorted_produits = new SortedList<>(produitsFiltres);
                    sorted_produits.comparatorProperty().bind(produit_table.comparatorProperty());
                    produit_table.setItems(sorted_produits);

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }


        }
    @FXML
    public void ajouterProduit() throws IOException {
        ajouter_produit_scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ajouter_produit.fxml")));
        produit_scene.getChildren().removeAll();
        produit_scene.getChildren().setAll(ajouter_produit_scene);
    }

    @FXML
    public void enregisterProduit() throws SQLException {
        String type =null;Date date_expiration = null;
        String id_produit = UUID.randomUUID().toString();
        float prix_cdf = 0; float prix_usd =0 ;

        if(date_expiration_Date.getValue()!=null){
           date_expiration  = Date.valueOf((LocalDate) date_expiration_Date.getValue());
        }
        if(!prix_cdf_textField.getText().isEmpty()){
            prix_cdf = Float.parseFloat(prix_cdf_textField.getText());
        }
        if(!prix_usd_textField.getText().isEmpty()){
            prix_usd = Float.parseFloat(prix_usd_textField.getText());
        }
        for (int i=1; i<=4;i++) {
            if(siro_type_radioButton.isSelected()){
                type = "siro";break;
            }
            if(comprime_type_radioButton.isSelected()){
                type = "comprimé";break;
            }
            if(injectable_type_radioButton.isSelected()){
                type = "injectable";break;
            }
            if(perfusion_type_radioButton.isSelected()) {
                type = "perfusion";
                break;
            }
        }
        if(type != null && date_expiration != null){
            Produit produit = new Produit(id_produit,designation_textField.getText(),type,prix_usd,prix_cdf,date_expiration,"");
            Produit.insertProduit(produit);
            System.out.println(produit.toString());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le champ ne peut pas être vide !");
            alert.showAndWait();
        }

    }

    @FXML
    public void getAccueilScene() throws IOException {
        if(ajouter_produit_scene!=null){
            BorderPane acceuil_scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../accueil.fxml")));
            ajouter_produit_scene.getChildren().removeAll();
            ajouter_produit_scene.getChildren().setAll(acceuil_scene);
        }
        else{
            BorderPane acceuil_scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../accueil.fxml")));
            produit_scene.getChildren().removeAll();
            produit_scene.getChildren().setAll(acceuil_scene);
        }
    }
    @FXML
    public void getProduitScene() throws IOException {
        BorderPane acceuil_scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("produit_ui.fxml")));
        ajouter_produit_scene.getChildren().removeAll();
        ajouter_produit_scene.getChildren().setAll(acceuil_scene);
    }
}




