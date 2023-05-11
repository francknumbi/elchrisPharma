package sample;

import java.sql.*;

public class ConnexionMySQL {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_elchrispharma";
        String utilisateur = "root";
        String motDePasse = "";

        try {
            // Charger le pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Se connecter à la base de données
            Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            // Tester la connexion
            if (connexion != null) {
                System.out.println("Connexion réussie !");
            } else {
                System.out.println("La connexion a échoué.");
            }

            // Fermer la connexion
            connexion.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur : le pilote JDBC n'a pas été trouvé.");
        } catch (SQLException e) {
            System.out.println("Erreur : la connexion à la base de données a échoué.");
            e.printStackTrace();
        }
    }
}
