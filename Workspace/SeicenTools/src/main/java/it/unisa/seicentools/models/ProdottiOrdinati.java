package it.unisa.seicentools.models;

public class ProdottiOrdinati {
    private int id;
    private int id_prodotto;
    private int id_ordine;
    private int qta;

    public ProdottiOrdinati() {};

    public ProdottiOrdinati(int id_ordine, int id_prodotto, int qta) {
        this.id_prodotto = id_prodotto;
        this.id_ordine = id_ordine;
        this.qta = qta;
    }

    public int getId() {return this.id;}
    public int getIdProdotto() {return this.id_prodotto;}
    public int getIdOrdine() {return this.id_ordine;}
    public int getQta() {return this.qta;}


    public void setId(int id) {this.id = id;}
    public void setIdProdotto(int id_prodotto) {this.id_prodotto = id_prodotto;}
    public void setIdOrdine(int id_ordine) {this.id_ordine = id_ordine;}
    public void setQta(int qta) {this.qta = qta;}
}
