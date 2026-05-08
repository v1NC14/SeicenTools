package it.unisa.seicentools.models;

public class ProdottiOrdinati {
    private int id;
    private int id_prodotto;
    private int id_ordine;
    private int qta;

    public ProdottiOrdinati() {};

    public ProdottiOrdinati(Ordine ordine, Prodotto prodotto) {
        this.id_prodotto = prodotto.getId();
        this.id_ordine = ordine.getId();
        this.qta = ordine.getQta();
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
