package it.unisa.seicentools.application.accessMGMT.interfaces;

import it.unisa.seicentools.models.Utente;

public interface ISessionService {
    public boolean login(String email, String password);
    public Utente getUtente(String email);
}
