package it.unisa.seicentools.application.productMGMT.interfaces;

import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;

import java.sql.SQLException;

public interface IUserService {
    public boolean deleteUser(Utente user, String password);
    public boolean addUser(Utente user, String password) throws SQLException;
    public boolean updateUser(Utente user);

}
