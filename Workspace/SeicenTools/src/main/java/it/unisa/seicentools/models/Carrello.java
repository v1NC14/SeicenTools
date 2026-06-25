package it.unisa.seicentools.models;

import java.math.BigDecimal;
import java.util.List;

public class Carrello {
    private int id;
    private int id_utente;
    private int id_prodotto;
    private int qta;

    public Carrello(){}

    public Carrello(int id_utente, int id_prodotto, int qta){
        this.id_prodotto = id_prodotto;
        this.id_utente = id_utente;
        this.qta = qta;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getId_prodotto() {return this.id_prodotto;}
    public void setId_prodotto(int id_prodotto) {this.id_prodotto = id_prodotto;}

    public int getQta() {return qta;}
    public void setQta(int qta) {this.qta = qta;}

    public int getId_utente() {return this.id_utente;}
    public void setId_utente(int id_utente) {this.id_utente = id_utente;}
}
