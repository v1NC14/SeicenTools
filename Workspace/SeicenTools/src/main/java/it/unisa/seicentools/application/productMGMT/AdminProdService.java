package it.unisa.seicentools.application.productMGMT;

import it.unisa.seicentools.application.productMGMT.interfaces.IAdminProdService;
import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;
import it.unisa.seicentools.persistence.DAOmodels.ProdottoDAO;
import it.unisa.seicentools.persistence.interfaces.ICarrelloDAO;
import it.unisa.seicentools.persistence.interfaces.IProdottoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminProdService implements IAdminProdService{

    @Override
    public boolean creaProdotto(Prodotto p) throws SQLException{
        if(p == null){
            throw new IllegalArgumentException("il prodotto non può essere null");
        }else{
            IProdottoDAO prodottoDAO = new ProdottoDAO();

            try {
                return prodottoDAO.addProdotto(p);
            }catch(Exception e){
                throw new SQLException(e);
            }
        }
    }

    @Override
    public boolean deleteProdotto(Prodotto p) throws SQLException{
        if(p == null){
            throw new IllegalArgumentException("il prodotto non può essere null");
        }else{
            IProdottoDAO prodottoDAO = new ProdottoDAO();

            try {
                return prodottoDAO.deleteProdotto(p);
            }catch(Exception e){
                throw new SQLException(e);
            }
        }
    }

    @Override
    public boolean updateProdotto(Prodotto p) throws SQLException{
        if(p == null){
            throw new IllegalArgumentException("il prodotto non può essere null");
        }else{
            IProdottoDAO prodottoDAO = new ProdottoDAO();

            try {
                return prodottoDAO.updateProdotto(p);
            }catch(Exception e){
                throw new SQLException(e);
            }
        }
    }
}
