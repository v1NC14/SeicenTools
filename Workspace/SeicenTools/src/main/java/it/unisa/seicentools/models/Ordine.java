package it.unisa.seicentools.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

public class Ordine {
    private int id;
    private int id_utente;
    private BigDecimal totale;
    private int qta;
    private Timestamp dataCreazione;
    private String indirizzoConsegna;
    private String numCarta;


    public Ordine(){};

    public Ordine(int id_utente, BigDecimal totale, int qta, String indirizzoConsegna, String numCarta) {
        this.id_utente = id_utente;
        this.totale = totale;
        this.qta = qta;
        this.dataCreazione = Timestamp.valueOf(String.valueOf(Instant.now()));
        this.indirizzoConsegna = indirizzoConsegna;
    }

    public int getId() {return this.id;}
    public int getId_utente() {return this.id_utente;}
    public BigDecimal getTotale() {return this.totale;}
    public int getQta() {return this.qta;}
    public Timestamp getDataCreazione() {return this.dataCreazione;}
    public String getIndirizzoConsegna() {return this.indirizzoConsegna;}
    public String getNumCarta() {return this.numCarta;}

    public void setId(int id) {this.id = id;}
    public void setId_utente(int id_utente) {this.id_utente = id_utente;}
    public void setTotale(BigDecimal totale) {this.totale = totale;}
    public void setQta(int qta) {this.qta = qta;}
    public void setDataCreazione(Timestamp dataCreazione){this.dataCreazione = dataCreazione;}
    public void setIndirizzoConsegna(String indirizzoConsegna) {this.indirizzoConsegna = indirizzoConsegna;}
    public void setNumCarta(String numCarta) {this.numCarta = numCarta;}
}

