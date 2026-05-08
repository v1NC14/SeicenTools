package it.unisa.seicentools.persistence.interfaces;

import it.unisa.seicentools.models.ProdottiOrdinati;

import java.util.List;

public interface IProdottiOrdinatiDAO {
    public boolean addProdottoOrdinato(ProdottiOrdinati tmp) throws Exception;
    public List<ProdottiOrdinati> getProdottiByOrdine(int id) throws Exception;
}
