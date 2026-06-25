package it.unisa.seicentools.persistence.interfaces;

import it.unisa.seicentools.persistence.DAOmodels.*;

public interface IPersistenceService {
    public OrdineDAO getOrdineDAO();
    public ProdottoDAO getProdottoDAO();
    public UtenteDAO getUtenteDAO();
    public ProdottiOrdinatiDAO getProdottiOrdinatiDAO();
    public CarrelloDAO getCarrelloDAO();
}