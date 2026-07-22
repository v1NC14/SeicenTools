package it.unisa.seicentools.persistence.DAOmodels;

import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.ProdottiOrdinati;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.DBConnection;
import it.unisa.seicentools.persistence.interfaces.IProdottiOrdinatiDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottiOrdinatiDAO implements IProdottiOrdinatiDAO {

    @Override
    public boolean addProdottoOrdinato(ProdottiOrdinati tmp, Connection conn) throws Exception{
        String query = "INSERT INTO prodottiordinati (id_ordine, id_prodotto, qta) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, tmp.getIdOrdine());
            ps.setInt(2, tmp.getIdProdotto());
            ps.setInt(3, tmp.getQta());

            if (ps.execute()){
                System.out.println("Inserimento nuovo prodotto riuscito");
                return true;
            }else{
                return false;
            }
        }catch (Exception e) {
            if(conn!=null)
                conn.rollback();

            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prodotto> getProdottiByOrdine(Ordine ordine) throws Exception {
        List<Prodotto> lista = new ArrayList<>();
        String query = "SELECT * FROM prodottiordinati INNER JOIN prodottiordinati ON prodottiordinati.id_prodotto = prodotto.id WHERE id_ordine = ?";

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
            throw new SQLException("Connessione con il database fallita...");
        }
        return lista;
    }

    //revisionare in futuro
}
