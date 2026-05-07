package it.unisa.seicentools.persistence.interfaces;

import it.unisa.seicentools.models.Prodotto;

import java.util.List;

public interface IProdottoDAO {
    public List<Prodotto> getAllProdotti() throws Exception;
    public Prodotto getProdottoById(int id) throws Exception;
    public boolean addProdotto(Prodotto p) throws Exception;
}
