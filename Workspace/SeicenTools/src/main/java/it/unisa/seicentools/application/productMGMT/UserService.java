package it.unisa.seicentools.application.productMGMT;

import it.unisa.seicentools.application.productMGMT.interfaces.IUserService;
import it.unisa.seicentools.models.Ruolo;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.UtenteDAO;
import it.unisa.seicentools.persistence.interfaces.IPersistenceService;
import it.unisa.seicentools.persistence.interfaces.IUtenteDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService implements IUserService {
    private final IPersistenceService service;
    Connection connection;
    public UserService() {this.service = service;}

    public UserService(IPersistenceService service) {
        if (service == null) {throw new IllegalArgumentException("Service cannot be null");}
        this.service = service;
    }

    @Override
    public boolean addUser(Utente user, String pwd) throws SQLException{
        if(user==null){
            throw new IllegalArgumentException("Errore");
        }else{
            IUtenteDAO userDAO = service.getUtenteDAO();
            try{
                return userDAO.registraUtente(connection,user,pwd );
            }
            catch (Exception e) {
                throw new SQLException(e);
            }
        ]
    }


    public boolean deleteUser(Utente user) throws SQLException{
        if(user==null){
            throw new IllegalArgumentException("Errore");
        }
        else{
            IUtenteDAO userDAO = service.getUtenteDAO();
            try{
                return userDAO.rimuoviUtente(user);
            }
            catch(Exception e){
                throw new SQLException(e);
            }
        }
    }

    public boolean updateUser(Utente user) throws SQLException{
            if(user==null){
                throw new IllegalArgumentException("Errore");
            }else{
                IUtenteDAO userDAO = service.getUtenteDAO();
                try{
                    return userDAO.updateUtente(user);
                }
                catch(Exception e){
                    throw new SQLException(e);
                }
            }
    }

    }
