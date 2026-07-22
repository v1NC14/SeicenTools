package it.unisa.seicentools.persistence;

import it.unisa.seicentools.persistence.DAOmodels.*;
import it.unisa.seicentools.persistence.interfaces.IPersistenceService;

public class PersistenceServiceImpl implements IPersistenceService {
    private static PersistenceServiceImpl istanza;

    private PersistenceServiceImpl(){}

    public static PersistenceServiceImpl getIstanza() {
        if (istanza == null) {
            istanza = new PersistenceServiceImpl();
        }
        return istanza;
    }

    @Override
    public OrdineDAO getOrdineDAO(){return new OrdineDAO();}

    //@Override
    //public CarrelloDAO getCarrelloDAO(){return new CarrelloDAO();}

    @Override
    public ProdottoDAO getProdottoDAO(){return new ProdottoDAO();}

    @Override
    public UtenteDAO getUtenteDAO(){return new UtenteDAO();}

    @Override
    public ProdottiOrdinatiDAO getProdottiOrdinatiDAO(){return new ProdottiOrdinatiDAO();}
}
