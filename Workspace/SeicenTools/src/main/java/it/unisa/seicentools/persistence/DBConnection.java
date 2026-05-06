package it.unisa.seicentools.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {

    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            InputStream input = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("database.properties");

            if (input == null) {
                throw new RuntimeException("database.properties non trovato nel classpath");
            }

            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il caricamento della configurazione DB");
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }
}