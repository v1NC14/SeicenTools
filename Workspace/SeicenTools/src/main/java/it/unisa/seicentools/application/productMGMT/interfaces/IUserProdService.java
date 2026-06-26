package it.unisa.seicentools.application.productMGMT.interfaces;

import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Prodotto;

import java.sql.SQLException;
import java.util.List;

public interface IUserProdService {
    public boolean aggiungiAlCarrello(Carrello cart, int qta) throws SQLException;
    public List<Prodotto> getProdByUtente(int id_utente) throws SQLException;
    public boolean rmvFromCarrello(int id_utente, int id_Prd) throws SQLException;
    public boolean cancellaCarrello(int id_utente)  throws SQLException;
    public Ordine cartToOrder(List<Prodotto> prodottiUtente, int id_utente, String numCarta, String indirizzo) throws SQLException;
}
