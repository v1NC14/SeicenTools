package it.unisa.seicentools.application.accessMGMT;

import it.unisa.seicentools.application.accessMGMT.interfaces.ISessionService;
import it.unisa.seicentools.models.Utente;
import it.unisa.seicentools.persistence.DAOmodels.UtenteDAO;
import it.unisa.seicentools.persistence.PersistenceServiceImpl;
import it.unisa.seicentools.persistence.interfaces.IPersistenceService;
import it.unisa.seicentools.persistence.interfaces.IUtenteDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class SessionService implements ISessionService {
    private final IPersistenceService service;

    public SessionService(){this.service = PersistenceServiceImpl.getIstanza();}

    @Override
    public boolean login(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            throw new IllegalArgumentException("Email o password vuote");
        }

        IUtenteDAO utenteDAO = service.getUtenteDAO();

        try{
            String pwdEffettiva = utenteDAO.recuperaPassword(email);

            if(pwdEffettiva != null){
                return BCrypt.checkpw(password, pwdEffettiva);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Utente getUtente(String email) {

        if (email == null) {
            throw new IllegalArgumentException("Email non può essere null");
        }
        IUtenteDAO utenteDAO = service.getUtenteDAO();

        //Inizializzato come null, nella servlet controllo e se ci sono errori reindirizzo sulla login page
        Utente utente = null;
        try {

            utente = utenteDAO.getUtenteByEmail(email);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return utente;
    }
}