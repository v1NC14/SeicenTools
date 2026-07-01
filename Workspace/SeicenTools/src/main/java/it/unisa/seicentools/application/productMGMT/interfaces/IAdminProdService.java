package it.unisa.seicentools.application.productMGMT.interfaces;

import it.unisa.seicentools.models.Carrello;
import it.unisa.seicentools.models.Prodotto;

import java.sql.SQLException;
import java.util.List;

public interface IAdminProdService {
    public boolean creaProdotto(Prodotto p) throws SQLException;
    public boolean deleteProdotto(Prodotto p) throws SQLException;
    public boolean updateProdotto(Prodotto p) throws SQLException;
}
