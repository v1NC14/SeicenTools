package it.unisa.seicentools.persistence.DAOmodels;

import it.unisa.seicentools.persistence.interfaces.IProdottoDAO;
import it.unisa.seicentools.persistence.DBConnection;
import it.unisa.seicentools.models.Prodotto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO implements IProdottoDAO {
    @Override
    public List<Prodotto> getAllProdotti() throws Exception{
        List<Prodotto> prodotti = new ArrayList<>();
        String query = "SELECT * FROM prodotto";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prodotto tmp = new Prodotto();

                tmp.setId(rs.getInt("id"));
                tmp.setNome(rs.getString("nome"));
                tmp.setCategoria(rs.getString("categoria"));
                tmp.setDescrizione(rs.getString("descrizione"));
                tmp.setPrezzo(rs.getBigDecimal("prezzo"));
                tmp.setImgPath(rs.getString("imgPath"));

                prodotti.add(tmp);
            }

            return prodotti;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public Prodotto getProdottoById(int id) throws Exception{
        String query = "SELECT * FROM prodotto WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Prodotto tmp = new Prodotto();

            if (rs.next()) {
                tmp.setId(rs.getInt("id"));
                tmp.setNome(rs.getString("nome"));
                tmp.setCategoria(rs.getString("categoria"));
                tmp.setDescrizione(rs.getString("descrizione"));
                tmp.setPrezzo(rs.getBigDecimal("prezzo"));
                tmp.setImgPath(rs.getString("imgPath"));

            }

            return tmp;
        } catch (SQLException e) {
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public List<Prodotto> getProdottoByCategoria(String cat) throws Exception{

        int c = 0, p = 0;
        List<String> filtri = new ArrayList<>();
        String tmp = "", query;
        for(int i= 0; i < cat.length(); i++){
            tmp += cat.charAt(i);
            if (!Character.isLetterOrDigit(cat.charAt(i))
                    && !Character.isWhitespace(cat.charAt(i))) {
                c++;
                filtri.add(tmp);
                tmp = "";
            }
        }

        if(c == 0){query = "SELECT * FROM prodotto WHERE categoria = ?";}
        else{
            query = "SELECT * FROM prodotti WHERE categoria LIKE '"+ filtri.get(0) +"'";
            for(int i = 1; i < filtri.size(); i++){
                query += " AND categoria LIKE '"+ filtri.get(i)+"'";
            }
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            if(c==0)
                ps.setString(1, cat);

            ResultSet rs = ps.executeQuery();

            List<Prodotto> tmplist = new ArrayList<>();

            while (rs.next()) {
                Prodotto temp = new Prodotto();

                temp.setId(rs.getInt("id"));
                temp.setNome(rs.getString("nome"));
                temp.setCategoria(rs.getString("categoria"));
                temp.setDescrizione(rs.getString("descrizione"));
                temp.setPrezzo(rs.getBigDecimal("prezzo"));
                temp.setImgPath(rs.getString("imgPath"));

                tmplist.add(temp);
            }

            return tmplist;
        } catch (SQLException e) {
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public boolean addProdotto(Prodotto p) throws Exception{
        String query = "INSERT INTO prodotto(nome, categoria, descrizione, prezzo, imgPath, disponibilita) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getCategoria());
            ps.setString(3, p.getDescrizione());
            ps.setBigDecimal(4, p.getPrezzo());
            ps.setString(5, p.getImgPath());
            ps.setInt(6, p.getDisponibilita());

            if (ps.execute()){
                System.out.println("Inserimento nuovo prodotto riuscito");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Prendo il primo campo generato (l'ID)
                    int idGenerato = generatedKeys.getInt(1);

                    // AGGIORNO L'OGGETTO JAVA
                    p.setId(idGenerato);
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
    public boolean updateProdotto(Prodotto p) throws Exception{
        String query = "UPDATE prodotto SET nome = ?, categoria = ?, descrizione = ?, prezzo = ?, imgPath = ?, disponibilita = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getCategoria());
            ps.setString(3, p.getDescrizione());
            ps.setBigDecimal(4, p.getPrezzo());
            ps.setString(5, p.getImgPath());
            ps.setInt(6, p.getDisponibilita());
            ps.setInt(7, p.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public boolean deleteProdotto(Prodotto p) throws Exception{
        String query = "DELETE FROM prodotto WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, p.getId());
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    //UTILITIES

    @Override
    public List<String> getCategorie() throws Exception{
        String query = "SELECT categoria FROM prodotto";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {

            List<String> lista = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                if(!lista.contains(rs.getString("categoria")))
                    lista.add(rs.getString("categoria"));
            }

            return lista;
        }catch(Exception e){
            throw new SQLException("Connessione con il database fallita...");
        }
    }

    @Override
    public List<Prodotto> getRandProd(int limit) throws Exception{
        String query = "SELECT * FROM prodotto ORDER BY RANDOM() LIMIT ?";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            List<Prodotto> tmplist = new ArrayList<>();

            while (rs.next()) {
                Prodotto temp = new Prodotto();

                temp.setId(rs.getInt("id"));
                temp.setNome(rs.getString("nome"));
                temp.setCategoria(rs.getString("categoria"));
                temp.setDescrizione(rs.getString("descrizione"));
                temp.setPrezzo(rs.getBigDecimal("prezzo"));
                temp.setImgPath(rs.getString("imgPath"));

                tmplist.add(temp);
            }

            return tmplist;
        }catch(Exception e){
            throw new SQLException("Connessione con il database fallita...");
        }
    }
}