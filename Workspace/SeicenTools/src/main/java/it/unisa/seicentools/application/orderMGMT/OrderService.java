package it.unisa.seicentools.application.orderMGMT;

import it.unisa.seicentools.application.orderMGMT.interfaces.IOrderService;
import it.unisa.seicentools.models.Ordine;
import it.unisa.seicentools.persistence.PersistenceServiceImpl;
import it.unisa.seicentools.persistence.interfaces.IOrdineDAO;
import it.unisa.seicentools.persistence.interfaces.IPersistenceService;

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
    public int getNumOrders(int id_utente) throws Exception{
        IOrdineDAO orderDAO = service.getOrdineDAO();

        try {
            return orderDAO.countOrdersByUser(id_utente);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Ordine> getOrdiniUtente(int id_utente) throws Exception{
        IOrdineDAO  orderDAO = service.getOrdineDAO();

        try{
            return orderDAO.getByUtente(id_utente);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
