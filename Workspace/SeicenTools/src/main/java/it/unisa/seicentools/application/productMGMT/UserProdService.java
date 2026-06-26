package it.unisa.seicentools.application.productMGMT;

import it.unisa.seicentools.application.productMGMT.interfaces.IUserProdService;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.ProdottiOrdinati;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.PersistenceServiceImpl;
import it.unisa.seicentools.persistence.interfaces.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @Override
    public Ordine cartToOrder(List<Prodotto> prodottiUtente, int id_utente, String numCarta, String indirizzo){
        //1. creare l'oggetto ordine
        //2. utilizzando l'id dell'ordine, creare le entità ProdottiOrdinati
        //3. restituire l'oggetto ordine
        IOrdineDAO orderDAO = service.getOrdineDAO();
        ICarrelloDAO cartDAO = service.getCarrelloDAO();
        IProdottiOrdinatiDAO prodOrderDAO = service.getProdottiOrdinatiDAO();

        int qta = 0;
        BigDecimal tot = new BigDecimal("0.00");

        for(int i = 0; i <  prodottiUtente.size(); i++){
            tot.add(prodottiUtente.get(i).getPrezzo());
            qta++;
        }

        Ordine tmp = new Ordine(id_utente, tot, qta, indirizzo, numCarta);

        try{
            List<Carrello> carrello = cartDAO.getByUtente(id_utente);
            if(orderDAO.creaOrdine(tmp)){
                for(Prodotto p : prodottiUtente){
                    for(Carrello c : carrello){
                        if(c.getId_prodotto() == p.getId()){
                            ProdottiOrdinati po = new ProdottiOrdinati(tmp.getId(), p.getId());
                            prodOrderDAO.addProdottoOrdinato(po);
                        }
                    }
                }
                cartDAO.deleteCarrello(id_utente);
                return tmp;
            }else{
                throw new SQLException("Problemi di connessione con il database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
