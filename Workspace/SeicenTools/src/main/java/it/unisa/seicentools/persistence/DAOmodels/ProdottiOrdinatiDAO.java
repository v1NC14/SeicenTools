package it.unisa.seicentools.persistence.DAOmodels;

import it.unisa.seicentools.models.ProdottiOrdinati;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottiOrdinatiDAO {

    public boolean addProdottoOrdinato(ProdottiOrdinati tmp) throws Exception{
        String query = "INSERT INTO prodottiordinati (qta, id_ordine, id_prodotto) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, tmp.getQta());
            ps.setInt(2, tmp.getIdOrdine());
            ps.setInt(3, tmp.getIdProdotto());

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Prendo il primo campo generato (l'ID)
                    int idGenerato = generatedKeys.getInt(1);

                    // AGGIORNO L'OGGETTO JAVA
                    tmp.setId(idGenerato);
                    return true;
                } else {
                    throw new SQLException("Creazione fallita, nessun ID ottenuto.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    public List<Prodotto> getProdottiByOrdine(int id) throws Exception {
        //MODIFCIA IL CODICE; CI SONO DEGLI ERRORI
        List<Prodotto> lista = new ArrayList<>();
        String query = "SELECT * FROM prodottiordinati WHERE id_ordine = ?"; //CAMBIA QUERY (deve restituire la lista di prodotti legati all'ordine, non oggetti di tipo prodottoordinato)

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prodotto tmp = new Prodotto();

                /*ERROR*/
                tmp.setId(rs.getInt("id"));
                tmp.setIdOrdine(rs.getInt("id_ordine"));
                tmp.setIdProdotto(rs.getInt("id_prodotto"));
                tmp.setQta(rs.getInt("qta"));

                lista.add(tmp);
            }

            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    //modificare i metodi, le query sono errate
}