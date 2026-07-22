package it.unisa.seicentools.application.orderMGMT;

import it.unisa.seicentools.application.orderMGMT.interfaces.IOrderService;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.models.ProdottiOrdinati;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.PersistenceServiceImpl;
import it.unisa.seicentools.persistence.interfaces.IOrdineDAO;
import it.unisa.seicentools.persistence.interfaces.IPersistenceService;
import it.unisa.seicentools.persistence.interfaces.IProdottoDAO;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    private final IPersistenceService service;

    public OrderService() {
        this.service = PersistenceServiceImpl.getIstanza();
    }

    public OrderService(IPersistenceService service) {
        if (service == null) {throw new IllegalArgumentException("Service cannot be null");}
        this.service = service;
    }

    @Override
    public boolean creaOrdine(Ordine ordine, List<Carrello> carrello) throws Exception{
        IOrdineDAO orderDAO = service.getOrdineDAO();

        try{
            return orderDAO.creaOrdine(ordine, carrello);
        } catch (Exception e) {
            throw new SQLException(e);
        }/////////////////////////////si devono creare i prodotti ordinati insieme all'ordine
    }

    @Override
    public int getNumOrders(int id_utente) throws Exception{
        IOrdineDAO orderDAO = service.getOrdineDAO();

        try {
            return orderDAO.countOrdersByUser(id_utente);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new SQLException(ex);

        }
    }

    @Override
    public List<Ordine> getOrdiniUtente(int id_utente) throws Exception{
        IOrdineDAO  orderDAO = service.getOrdineDAO();

        try{
            return orderDAO.getByUtente(id_utente);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    //UTILITY

    @Override
    public List<Prodotto> cartToProds(List<Carrello> carrello) throws Exception{
        IProdottoDAO prodottoDAO = service.getProdottoDAO();
        List<Prodotto> prodotti = new ArrayList<>();

        try{
            for(Carrello c : carrello){
                prodotti.add(prodottoDAO.getProdottoById(c.getId()));
            }

            return prodotti;
        }catch(Exception ex){
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public BigDecimal getTotalFromCart(List<Carrello> carrello) throws Exception{
        BigDecimal total = BigDecimal.ZERO;
        IProdottoDAO prodottoDAO = service.getProdottoDAO();

        try{
            for(Carrello c : carrello){
                Prodotto p =  prodottoDAO.getProdottoById(c.getId_prodotto());
                total = total.add(p.getPrezzo().multiply(BigDecimal.valueOf(c.getQta())));
            }
            return total;
        }catch(Exception ex){
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public int getTotQta(List<Carrello> carrello){
        int sum = 0;

        if(carrello != null && !carrello.isEmpty())
            for(Carrello c : carrello)
                sum += c.getQta();

        return sum;
    }
}
