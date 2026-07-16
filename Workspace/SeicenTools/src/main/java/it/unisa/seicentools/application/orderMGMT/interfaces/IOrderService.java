package it.unisa.seicentools.application.orderMGMT.interfaces;

import it.unisa.seicentools.models.Ordine;

import java.util.List;

public interface IOrderService {
    int getNumOrders(int id_utente) throws Exception;

    List<Ordine> getOrdiniUtente(int id_utente) throws Exception;
}
