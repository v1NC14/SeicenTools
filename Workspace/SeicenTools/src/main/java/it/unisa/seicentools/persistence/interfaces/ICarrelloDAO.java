package it.unisa.seicentools.persistence.interfaces;

import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;

import java.sql.Timestamp;
import java.util.List;

public interface ICarrelloDAO {
    public boolean creaCarrello(Carrello carrello, int qta) throws Exception;

    public List<Carrello> getByUtente(int id_utente) throws Exception;

    public boolean rmvFromCarrello(int id_utente, int id_Prd) throws Exception;

    public boolean deleteCarrello(int id_utente) throws Exception;
}
