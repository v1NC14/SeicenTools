package it.unisa.seicentools.persistence.interfaces;

import it.unisa.seicentools.models.Ordine;

import java.sql.Timestamp;
import java.util.List;

public interface IOrdineDAO {
    public boolean creaOrdine(Ordine ordine) throws Exception;

    public List<Ordine> getByUtente(int id_utente) throws Exception;

    public List<Ordine> getByIndirizzo(String indirizzo) throws Exception;

    public List<Ordine> getByData(Timestamp date) throws Exception;

    //public boolean updateOrdine(Ordine ordine) throws Exception;

    public boolean deleteOrdine(Ordine ordine) throws Exception;

    public int countOrdersByUser(int id_utente) throws Exception;
}
