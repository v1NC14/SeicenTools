package it.unisa.seicentools.persistence.interfaces;

import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.ProdottiOrdinati;

import java.util.List;

public interface IProdottiOrdinatiDAO {
    public boolean addProdottoOrdinato(ProdottiOrdinati tmp) throws Exception;
    public List<ProdottiOrdinati> getProdottiByOrdine(Ordine ordine) throws Exception;
}
