package it.unisa.seicentools.models;

import java.math.BigDecimal;

public class Prodotto {
    private int id;
    private String nome;
    private String categoria;
    private String descrizione;
    private BigDecimal prezzo;
    private String imgPath;
    private int disponibilita;

    //--- costruttori --//
    public Prodotto(){}

    public Prodotto(int id, String nome, String categoria, String descrizione, BigDecimal prezzo, String imgPath, int disponibilita){
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.imgPath = imgPath;
        this.disponibilita = disponibilita;
    }

    //--- getters && setters ---//

    public int getId() {return this.id;}
    public String getNome() {return this.nome;}
    public String getCategoria() {return this.categoria;}
    public String getDescrizione(){return this.descrizione;}
    public String getImgPath() {return this.imgPath;}
    public int getDisponibilita() {return this.disponibilita;}
    public BigDecimal getPrezzo() {return this.prezzo;}

    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setCategoria(String categoria) {this.categoria = categoria;}
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}
    public void setImgPath(String imgPath) {this.imgPath = imgPath;}
    public void setDisponibilita(int disponibilita) {this.disponibilita = disponibilita;}
    public void setPrezzo(BigDecimal prezzo) {this.prezzo = prezzo;}
}
