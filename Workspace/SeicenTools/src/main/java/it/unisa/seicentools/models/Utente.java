package it.unisa.seicentools.models;

public class Utente {
    private int id;
    private String nome;
    private String Pwd;
    private String email;
    private Ruolo ruolo;

    public Utente(){};

    public Utente(int id, String nome, String email, Ruolo ruolo){
        this.id = id;
        this.nome = nome;
        this.Pwd = null;
        this.email = email;
        this.ruolo = ruolo;
    }

    public Utente(String nome, String email, Ruolo ruolo){
        this.nome = nome;
        this.Pwd = null;
        this.email = email;
        this.ruolo = ruolo;
    }

    public int getId() {return this.id;}
    public String getNome() {return this.nome;}
    public String getPwd() {return this.Pwd;}
    public String getEmail() {return this.email;}
    public Ruolo getRuolo() {return this.ruolo;}

    public void setId(int id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setHashpwd(String hashpwd) {this.Pwd = hashpwd;}
    public void setEmail(String email) {this.email = email;}
    public void setRuolo(Ruolo ruolo) {this.ruolo = ruolo;}
}

