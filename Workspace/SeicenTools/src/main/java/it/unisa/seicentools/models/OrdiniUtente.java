package it.unisa.seicentools.models;

public class OrdiniUtente {
    private int id;
    private int id_ordine;
    private int id_utente;

    public OrdiniUtente(){};

    public OrdiniUtente(Ordine ordine, Utente utente){
        this.id_ordine = ordine.getId();
        this.id_utente = utente.getId();
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getIdOrdine() {return id_ordine;}
    public void setIdOrdine(int id_ordine) {this.id_ordine = id_ordine;}
    public int getIdUtente() {return id_utente;}
    public void setIdUtente(int id_utente) {this.id_utente = id_utente;}
}
