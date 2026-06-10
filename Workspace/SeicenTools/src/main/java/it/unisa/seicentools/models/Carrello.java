package it.unisa.seicentools.models;

import java.util.List;

public class Carrello {
    private int id;
    private int id_utente;

    public Carrello(){}

    public Carrello(int id, int id_utente){
        this.id = id;
        this.id_utente = id_utente;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getId_utente() {return this.id_utente;}
    public void setId_utente(int id_utente) {this.id_utente = id_utente;}
}
