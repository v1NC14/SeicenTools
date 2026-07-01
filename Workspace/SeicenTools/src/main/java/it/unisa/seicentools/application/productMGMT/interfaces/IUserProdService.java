package it.unisa.seicentools.application.productMGMT.interfaces;

import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.Prodotto;

import java.sql.SQLException;
import java.util.List;

public interface IUserProdService {
    public Ordine cartToOrder(List<Prodotto> prodottiUtente, int id_utente, String numCarta, String indirizzo) throws SQLException;
}
