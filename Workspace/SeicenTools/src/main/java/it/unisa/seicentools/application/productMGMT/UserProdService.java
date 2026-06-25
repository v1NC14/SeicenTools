package it.unisa.seicentools.application.productMGMT;

import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.PersistenceServiceImpl;
import it.unisa.seicentools.persistence.interfaces.ICarrelloDAO;
import it.unisa.seicentools.persistence.interfaces.IPersistenceService;

import java.sql.SQLException;
import java.util.List;

public class UserProdService implements IUserProdService {
    private final IPersistenceService service;

    public UserProdService() {
        this.service = PersistenceServiceImpl.getIstanza();
    }

    public UserProdService(IPersistenceService service) {
        if (service == null) {throw new IllegalArgumentException("Service cannot be null");}
        this.service = service;
    }

    @Override
    public boolean  aggiungiAlCarrello(Carrello cart, int qta) throws SQLException {
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

            try {
                return cartDAO.getByUtente(id_utente);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
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
