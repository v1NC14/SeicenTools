package it.unisa.seicentools.application.profileMGMT.interfaces;

import it.unisa.seicentools.models.Utente;

import java.sql.SQLException;

public interface IUserService {
    public boolean deleteUser(Utente user) throws SQLException;
    public boolean addUser(Utente user, String password) throws SQLException;
    public boolean updateUser(Utente user) throws SQLException;
}
