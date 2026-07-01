package it.unisa.seicentools.application.productMGMT.interfaces;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;

import java.sql.SQLException;
import java.util.List;

public interface IcommonProdService {
    public Prodotto getProdotto(int id_Prd) throws SQLException;
    public List<Prodotto> getAllProdotti() throws SQLException;
    public List<Prodotto> getProdByCategoria(String categoria) throws SQLException;
    public boolean aggiungiAlCarrello(Carrello cart, int qta) throws SQLException;
    public List<Prodotto> getProdByUtente(int id_utente) throws SQLException;
    public boolean rmvFromCarrello(int id_utente, int id_Prd) throws SQLException;
    public boolean cancellaCarrello(int id_utente)  throws SQLException;
}
