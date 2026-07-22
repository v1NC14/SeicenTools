package it.unisa.seicentools.persistence.interfaces;


import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.ProdottiOrdinati;
import it.unisa.seicentools.models.Prodotto;

import java.sql.Connection;
import java.util.List;

public interface IProdottiOrdinatiDAO {
    public boolean addProdottoOrdinato(ProdottiOrdinati tmp, Connection conn) throws Exception;
    public List<Prodotto> getProdottiByOrdine(Ordine ordine) throws Exception;
}
