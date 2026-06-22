package it.unisa.seicentools.persistence.interfaces;

import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;

import java.sql.Timestamp;
import java.util.List;

public interface ICarrelloDAO {
    public boolean creaCarrello(List<Prodotto> carrello) throws Exception;

    public List<Prodotto> getByUtente(int id_utente) throws Exception;

    public boolean updateCarrello(List<Prodotto> carrello) throws Exception;

    public boolean deleteCarrello(List<Prodotto> carrello) throws Exception;
}
