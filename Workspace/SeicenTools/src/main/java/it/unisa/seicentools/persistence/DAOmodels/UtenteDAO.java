package it.unisa.seicentools.persistence.DAOmodels;

import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.persistence.DBConnection;
import it.unisa.seicentools.persistence.interfaces.IUtenteDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO implements IUtenteDAO {

    // REGISTRAZIONE UTENTE
    @Override
    public boolean registraUtente(Connection conn, Utente user, String pwd) throws Exception{
        String query = "INSERT INTO utente (nome, email, ruolo, hashPWd) VALUES (?, ?, ?, ?)";

        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                String hashedPassword = BCrypt.hashpw(user.getPwd(), BCrypt.gensalt());

                ps.setString(1, user.getNome());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getRuolo().toString());
                ps.setString(4, hashedPassword);


                if (ps.execute()){
                    System.out.println("Inserimento nuovo utente riuscito");
                }
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Prendo il primo campo generato (l'ID)
                        int idGenerato = generatedKeys.getInt(1);

                        // AGGIORNO L'OGGETTO JAVA
                        // Fondamentale: ora l'oggetto 'utente' ha la matricola vera
                        user.setId(idGenerato);
                        user.setHashpwd(hashedPassword);
                        return true;
                    } else {
                        throw new SQLException("Creazione utente fallita, nessun ID ottenuto.");
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
        throw new SQLException("Connessione al database fallita...");
        }
        return false;
    }

    // TROVA UTENTE PER ID
    @Override
    public Utente getUtenteById(int id) throws Exception{
        if(id <= 0){
            throw new IllegalArgumentException("Matricola inesistente");
        }
        String query = "SELECT * FROM utente WHERE id = ?";

        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Utente utente = new Utente();

            if (rs.next()) {
                Ruolo ruolo = Ruolo.valueOf(rs.getString("ruolo"));

                utente.setId(rs.getInt("id"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setRuolo(ruolo);
            }

            return utente;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connessione al database fallita...");
        }
    }

    // TROVA UTENTE PER EMAIL
    @Override
    public Utente getUtenteByEmail(String email) throws Exception{
        String query = "SELECT * FROM utente WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            Utente utente = new Utente();

            if (rs.next()) {
                utente.setId(rs.getInt("id"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setRuolo(Ruolo.valueOf(rs.getString("ruolo")));
            }

            return utente;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connessione al database fallita...");
        }
    }

    // TROVA TUTTI GLI UTENTI REGISTRATI
    @Override
    public List<Utente> getAllUtentiRegistrati() throws Exception{
        String query = "SELECT * FROM utente";

        List<Utente> lista = new ArrayList<>();

        try(Connection conn = DBConnection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Utente utente = new Utente();

                utente.setId(rs.getInt("id"));
                utente.setEmail(rs.getString("email"));
                utente.setNome(rs.getString("nome"));
                utente.setRuolo(Ruolo.valueOf(rs.getString("ruolo")));

                lista.add(utente);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
        return lista;
    }

    // RIMUOVE UN UTENTE
    @Override
    public void rimuoviUtente(Utente utente) throws Exception {
        // Grazie al CASCADE sul DB, basta cancellare dalla tabella padre.
        if (utente == null) {
            throw new IllegalArgumentException("I dati non possono essere null");
        }

        if (utente.getId() <= 0){
            throw new IllegalArgumentException("Matricola inserita non valida");
        }
        String sql = "DELETE FROM utente WHERE matricola = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, utente.getId());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                // Opzionale: lanciare eccezione se l'utente non esisteva
                throw new SQLException("Impossibile cancellare: nessun utente trovato con matricola " + utente.getId());
            }
        } catch (SQLException e) {
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    // RECUPERO DELLA PASSWORD OVE SERVISSE
    @Override
    public String recuperaPassword(String email) throws Exception {

        if (email == null) {
            throw new IllegalArgumentException("Email non può essere null");
        }

        String sql = "SELECT hashPwd FROM utente WHERE email = ?";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("hashPwd");
            }
            else {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public boolean updateUtente(Utente utente) throws Exception{
        String query = "UPDATE ordine SET (nome, email, ruolo) VALUES (?, ?, ?) WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getEmail());
            ps.setString(3, utente.getRuolo().toString());
            ps.setInt(4, utente.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }
}