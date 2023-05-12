package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionMySQL {
    private static String url = "jdbc:mysql://localhost:3306/db_elchrispharma";
    private static String utilisateur = "root";
    private static String motDePasse = "";
    ConnexionMySQL(){
        try {
            // Charger le pilote JDBC pour MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static Connection getConnexion() throws SQLException {

        // Se connecter à la base de données
        Connection connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

        // Tester la connexion
        if (connexion != null) {
            System.out.println("Connexion réussie !");

        } else {
            System.out.println("La connexion a échoué.");
        }
        return connexion;
    }
}
