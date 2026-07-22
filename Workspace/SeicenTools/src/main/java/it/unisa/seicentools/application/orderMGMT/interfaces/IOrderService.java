package it.unisa.seicentools.application.orderMGMT.interfaces;

import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.ProdottiOrdinati;
import it.unisa.seicentools.models.Prodotto;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService {
    public int getNumOrders(int id_utente) throws Exception;
    public boolean creaOrdine(Ordine ordine, List<Carrello> carrello) throws Exception;
    public List<Ordine> getOrdiniUtente(int id_utente) throws Exception;
    List<Prodotto> cartToProds(List<Carrello> carrello) throws Exception;
    BigDecimal getTotalFromCart(List<Carrello> carrello) throws Exception;
    int getTotQta(List<Carrello> carrello);
}
