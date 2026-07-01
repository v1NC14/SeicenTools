package it.unisa.seicentools.application.productMGMT;

import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.PersistenceServiceImpl;
import it.unisa.seicentools.persistence.interfaces.ICarrelloDAO;
import it.unisa.seicentools.persistence.interfaces.IPersistenceService;
import it.unisa.seicentools.persistence.interfaces.IProdottoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class commonProdService implements IcommonProdService {
    private final IPersistenceService service;

    public commonProdService() {
        this.service = PersistenceServiceImpl.getIstanza();
    }

    public commonProdService(IPersistenceService service) {
        if (service == null) {throw new IllegalArgumentException("Service cannot be null");}
        this.service = service;
    }

    //CRUD prodotto

    @Override
    public Prodotto getProdotto(int id_Prd) throws SQLException {
        if(id_Prd<= 0){
            throw new IllegalArgumentException("L'id non può essere null, vuoto o negativo");
        }else{

            IProdottoDAO prodDAO = service.getProdottoDAO();

            try{
                return prodDAO.getProdottoById(id_Prd);
            }catch(Exception e){throw new SQLException(e);}
        }
    }

    @Override
    public List<Prodotto> getAllProdotti() throws SQLException{
        IProdottoDAO prodDAO = service.getProdottoDAO();

        try {
            return prodDAO.getAllProdotti();
        }catch(Exception e){throw new SQLException(e);}
    }

    @Override
    public List<Prodotto> getProdByCategoria(String categoria) throws SQLException {
        IProdottoDAO prodDAO = service.getProdottoDAO();

        try {
            return prodDAO.getProdottoByCategoria(categoria);
        }catch(Exception e){throw new SQLException(e);}
    }

    @Override
    public boolean aggiungiAlCarrello(Carrello cart, int qta) throws SQLException {
        if(cart.getId_prodotto() < 0 || cart.getId_utente()<0){
            throw new IllegalArgumentException("error placeholder");
        }else{
            ICarrelloDAO cartDAO = service.getCarrelloDAO();

            try {
                return cartDAO.creaCarrello(cart, qta);
            }catch(Exception e){throw new SQLException(e);}
        }
    }

    @Override
    public List<Prodotto> getProdByUtente(int id_utente){
        if(id_utente<0){throw new IllegalArgumentException("error placeholder");}
        else{
            ICarrelloDAO cartDAO = service.getCarrelloDAO();
            IProdottoDAO prodDAO = service.getProdottoDAO();

            try {
                List<Carrello> carrello = cartDAO.getByUtente(id_utente);
                List<Prodotto> prodottiUtente = new ArrayList<>();
                for(Carrello c: carrello){
                    Prodotto p = prodDAO.getProdottoById(c.getId_prodotto());
                    prodottiUtente.add(p);
                }

                return prodottiUtente;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    public boolean rmvFromCarrello(int id_utente, int id_Prd) throws SQLException{
        if(id_utente<0){throw new IllegalArgumentException("error placeholder");}
        else{
            ICarrelloDAO cartDAO = service.getCarrelloDAO();

            try {
                return cartDAO.rmvFromCarrello(id_utente, id_Prd);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean cancellaCarrello(int id_utente) throws SQLException{
        if(id_utente<0){throw new IllegalArgumentException("error placeholder");}
        else{
            ICarrelloDAO cartDAO = service.getCarrelloDAO();

            try {
                return cartDAO.deleteCarrello(id_utente);
            } catch (Exception e) {
                throw new SQLException(e);
            }
        }
    }
}
