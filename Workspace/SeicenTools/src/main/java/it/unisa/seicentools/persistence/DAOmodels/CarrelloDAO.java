package it.unisa.seicentools.persistence.DAOmodels;

import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.interfaces.ICarrelloDAO;

import java.util.List;

public class CarrelloDAO implements ICarrelloDAO {
    @Override
    public boolean creaCarrello(List<Prodotto> carrello) throws Exception;

    @Override
    public List<Prodotto> getByUtente(int id_utente) throws Exception;

    @Override
    public boolean updateCarrello(List<Prodotto> carrello) throws Exception;

    @Override
    public boolean deleteCarrello(List<Prodotto> carrello) throws Exception;
}
