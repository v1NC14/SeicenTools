package it.unisa.seicentools.models;

public class ProdottiCarrello {
    private int id;
    private int id_carrello;
    private int id_prodotto;

    public ProdottiCarrello() {};

    public ProdottiCarrello(int id, int id_carrello, int id_prodotto) {
        this.id = id;
        this.id_carrello = id_carrello;
        this.id_prodotto = id_prodotto;
    }

    public int getId() {return this.id;}
    public int getId_carrello() {return this.id_carrello;}
    public int getId_prodotto() {return this.id_prodotto;}

    public void setId(int id) {this.id = id;}
    public void setId_carrello(int id) {this.id_carrello = id;}
    public void setId_prodotto(int id) {this.id_prodotto = id;}
}
