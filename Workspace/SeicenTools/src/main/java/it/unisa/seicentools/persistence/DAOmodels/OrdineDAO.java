package it.unisa.seicentools.persistence.DAOmodels;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.persistence.DBConnection;
import it.unisa.seicentools.persistence.interfaces.IOrdineDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO implements IOrdineDAO {

    @Override
    public boolean creaOrdine(Ordine ordine) throws Exception{
        String query = "INSERT INTO ordini (user_id, tot, qta, indirizzoConsegna) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, ordine.getId_utente());
            ps.setBigDecimal(2, ordine.getTotale());
            ps.setInt(3, ordine.getQta());
            ps.setString(4, ordine.getIndirizzoConsegna());


            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Prendo il primo campo generato (l'ID)
                    int idGenerato = generatedKeys.getInt(1);

                    // AGGIORNO L'OGGETTO JAVA
                    ordine.setId(idGenerato);
                    return true;
                } else {
                    throw new SQLException("Creazione ordine fallita, nessun ID ottenuto.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public List<Ordine> getByUtente(int id_utente) throws Exception{
        List<Ordine> ordini = new ArrayList<>();
        String query = "SELECT * FROM ordine WHERE id_utente = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id_utente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ordine tmp = new Ordine();

                tmp.setId(rs.getInt("id"));
                tmp.setId_utente(rs.getInt("id_utente"));
                tmp.setTotale(rs.getBigDecimal("tot"));
                tmp.setQta(rs.getInt("qta"));
                tmp.setIndirizzoConsegna(rs.getString("indirizzoConsegna"));
                tmp.setDataCreazione(rs.getTimestamp("dataCreazione"));


                ordini.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
        return ordini;
    }

    @Override
    public List<Ordine> getByIndirizzo(String indirizzo) throws Exception{
        List<Ordine> ordini = new ArrayList<>();
        String query = "SELECT * FROM ordine WHERE indirizzoConsegna = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, indirizzo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ordine tmp = new Ordine();

                tmp.setId(rs.getInt("id"));
                tmp.setId_utente(rs.getInt("id_utente"));
                tmp.setTotale(rs.getBigDecimal("tot"));
                tmp.setQta(rs.getInt("qta"));
                tmp.setIndirizzoConsegna(rs.getString("indirizzoConsegna"));
                tmp.setDataCreazione(rs.getTimestamp("dataCreazione"));

                ordini.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
        return ordini;
    }

    @Override
    public List<Ordine> getByData(Timestamp date) throws Exception{
        List<Ordine> ordini = new ArrayList<>();
        String query = "SELECT * FROM ordine WHERE dataCreazione = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setTimestamp(1, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ordine tmp = new Ordine();

                tmp.setId(rs.getInt("id"));
                tmp.setId_utente(rs.getInt("id_utente"));
                tmp.setTotale(rs.getBigDecimal("tot"));
                tmp.setQta(rs.getInt("qta"));
                tmp.setIndirizzoConsegna(rs.getString("indirizzoConsegna"));
                tmp.setDataCreazione(rs.getTimestamp("dataCreazione"));

                ordini.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
        return ordini;
    }

    @Override
    public boolean updateOrdine(Ordine ordine) throws Exception{
        //id_utente, qta, totale, indirizzo
        String query = "UPDATE ordine SET (id_utente, qta, tot, indirizzoConsegna) VALUES (?, ?, ?, ?) WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, ordine.getId_utente());
            ps.setBigDecimal(2, ordine.getTotale());
            ps.setInt(3, ordine.getQta());
            ps.setString(4, ordine.getIndirizzoConsegna());
            ps.setInt(5, ordine.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public boolean deleteOrdine(Ordine ordine) throws Exception{
        String query = "DELETE FROM ordine WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, ordine.getId());
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }
}