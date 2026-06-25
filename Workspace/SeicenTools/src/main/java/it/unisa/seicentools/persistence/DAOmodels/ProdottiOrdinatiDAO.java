package it.unisa.seicentools.persistence.DAOmodels;

import it.unisa.seicentools.models.Ordine;
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

    public List<Prodotto> getProdottiByOrdine(Ordine ordine) throws Exception {
        List<Prodotto> lista = new ArrayList<>();
        String query = "SELECT * FROM prodotto INNER JOIN prodottiordinati ON prodottiordinati.id_prodotto = prodotto.id WHERE id_ordine = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, ordine.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Prodotto tmp = new Prodotto();

                tmp.setId(rs.getInt("id"));
                tmp.setNome(rs.getString("nome"));
                tmp.setCategoria(rs.getString("categoria"));
                tmp.setDescrizione(rs.getString("descrizione"));
                tmp.setPrezzo(rs.getBigDecimal("prezzo"));
                tmp.setImgPath(rs.getString("imgPath"));
                tmp.setDisponibilita(rs.getInt("disponibilita"));

                lista.add(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
        return lista;
    }

    //revisionare in futuro
}
