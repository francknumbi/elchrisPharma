package sample.produit;

import sample.produit.ProduitUi;

import java.util.SplittableRandom;

public class Produit {
    public String id_produit;
    public String designation;
    public String type;
    public float prix_usd;
    public float prix_cdf;
    public String description;

    public Produit(String id_produit, String designation, String type, float prix_usd, float prix_cdf) {
        setId_produit(id_produit);
        setDesignation(designation);
        setType(type);
        setPrix_usd(prix_usd);
        setPrix_cdf(prix_cdf);
        //this.description = description;
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
