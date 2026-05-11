package it.unisa.seicentools.persistence.interfaces;

import it.unisa.seicentools.models.Utente;

import java.sql.Connection;
import java.util.List;

public interface IUtenteDAO {
    public boolean registraUtente(Connection conn, Utente user, String pwd) throws Exception;
    public Utente getUtenteById(int id) throws Exception;
    public Utente getUtenteByEmail(String email) throws Exception;
    public List<Utente> getAllUtentiRegistrati() throws Exception;
    public void rimuoviUtente(Utente utente) throws Exception;
    public String recuperaPassword(String email) throws Exception;
    public boolean updateUtente(Utente utente) throws Exception;
}
