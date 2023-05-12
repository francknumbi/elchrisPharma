package modele;

import sample.produit.ProduitUi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.ConnexionMySQL;

public class Produit {
    private String id_produit;
    private String designation;
    private String type;
    private float prix_usd;
    private float prix_cdf;
    private Date date_expiration;
    private String description;


    public Produit(String id_produit, String designation, String type, float prix_usd, float prix_cdf,Date date_expiration,String description) {
        setId_produit(id_produit);
        setDesignation(designation);
        setType(type);
        setPrix_usd(prix_usd);
        setPrix_cdf(prix_cdf);
        this.date_expiration = date_expiration;
        this.description = description;
    }

    public String getId_produit() {
        return id_produit;
    }

    public void setId_produit(String id_produit) {
        this.id_produit = id_produit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }
    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix_usd() {
        return prix_usd;
    }

    public void setPrix_usd(float prix_usd) {
        this.prix_usd = prix_usd;
    }

    public float getPrix_cdf() {
        return prix_cdf;
    }

    public void setPrix_cdf(float prix_cdf) {
        this.prix_cdf = prix_cdf;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public static List<Produit> getAllProduit() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        Connection bdd_connexion = ConnexionMySQL.getConnexion();
        String requette = "SELECT * FROM produit";
        Statement objet = bdd_connexion.createStatement();
        ResultSet resultat = objet.executeQuery(requette);

        while (resultat.next()){
            produits.add(new Produit(resultat.getString("id_produit"),resultat.getString("nom_produit"),resultat.getString("type"),resultat.getFloat("prix_usd"),resultat.getFloat("prix_cdf"),resultat.getDate("date_expiration"),resultat.getString("description")));
        }
        System.out.println(produits.size());
        return produits;
    }
    public static void insertProduit(Produit produit) throws SQLException {
        String requette = "INSERT INTO produit(id_produit, nom_produit, type, prix_usd,prix_cdf,date_expiration,description)VALUES(?,?,?,?,?,?,?)";
        Connection bdd_connexion = ConnexionMySQL.getConnexion();
        PreparedStatement objet = bdd_connexion.prepareStatement(requette);

        objet.setString(1,produit.getId_produit());
        objet.setString(2, produit.getDesignation());
        objet.setString(3,produit.getType());
        objet.setFloat(4,produit.getPrix_usd());
        objet.setFloat(5,produit.getPrix_cdf());
        objet.setDate(6,produit.getDate_expiration());
        objet.setString(7,produit.getDescription());

        objet.executeUpdate();
        System.out.println("Data inserted successfully.");
    }
    @Override
    public String toString() {
        return "Produit{" +
                "id_produit='" + id_produit + '\'' +
                ", designation='" + designation + '\'' +
                ", type='" + type + '\'' +
                ", prix_usd=" + prix_usd +
                ", prix_cdf=" + prix_cdf +
                ", description='" + description + '\'' +
                '}';
    }
}
