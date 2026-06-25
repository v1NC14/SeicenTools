package it.unisa.seicentools.persistence.DAOmodels;

import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DBConnection;
import it.unisa.seicentools.persistence.interfaces.ICarrelloDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO implements ICarrelloDAO {
    @Override
    public boolean creaCarrello(Carrello carrello, int qta) throws Exception{
        String query = "INSERT INTO CarrelloUtente (id_prodotto, id_utente, qta) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, carrello.getId_prodotto());
            ps.setInt(2, carrello.getId_utente());
            ps.setInt(3, qta);

            if (ps.execute()){
                System.out.println("Inserimento nuovo prodotto riuscito");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Prendo il primo campo generato (l'ID)
                    int idGenerato = generatedKeys.getInt(1);

                    // AGGIORNO L'OGGETTO JAVA
                    carrello.setId(idGenerato);
                    return true;
                } else {
                    throw new SQLException("Creazione prodotto fallita, nessun ID ottenuto.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public List<Prodotto> getByUtente(int id_utente) throws Exception{
        String query = "SELECT * FROM CarrelloUtente WHERE id_utente = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, id_utente);
            ResultSet rs = ps.executeQuery();

            List<Prodotto> carrello = new ArrayList<>();

            while(rs.next()){
                Prodotto p = new Prodotto();
                p.setId(rs.getInt("id_prodotto"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setPrezzo(rs.getBigDecimal("prezzo"));
                p.setImgPath(rs.getString("img_path"));
                p.setDisponibilita(rs.getInt("disponibilita"));

                carrello.add(p);
            }

            return carrello;
        }catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public boolean rmvFromCarrello(int id_utente, int id_Prd) throws Exception{
        String query = "DELETE FROM CarrelloUtente WHERE id_utente = ? AND id_prodotto = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id_utente);
            ps.setInt(2, id_Prd);

            if (ps.execute()){
                System.out.println("Prodotto eliminato dal carrello");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }

        return false;
    }

    @Override
    public boolean deleteCarrello(int id_utente) throws Exception{
        String query = "DELETE FROM CarrelloUtente WHERE id_utente = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id_utente);

            if (ps.execute()){
                System.out.println("Carrello eliminato");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }

        return false;
    }
}
