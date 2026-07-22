package it.unisa.seicentools.application.productMGMT;

import it.unisa.seicentools.application.productMGMT.interfaces.IcommonProdService;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.PersistenceServiceImpl;
import it.unisa.seicentools.persistence.interfaces.IPersistenceService;
import it.unisa.seicentools.persistence.interfaces.IProdottoDAO;

import java.math.BigDecimal;
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
    public List<String> getCategorie() throws SQLException{
        IProdottoDAO prodottoDAO = service.getProdottoDAO();

        try {
            return prodottoDAO.getCategorie();
        }catch(Exception e){throw new SQLException(e);}
    }

    @Override
    public List<Prodotto> getRandProd(int limit) throws SQLException{
        IProdottoDAO prodottoDAO = service.getProdottoDAO();

        try{
            return prodottoDAO.getRandProd(limit);
        }catch(Exception e){throw new SQLException(e);}
    }

}
