package model.beans;

import java.sql.Timestamp;

public class OrdineVisualizzato {
    private int ordineId;
    private double prezzoAcquisto;
    private String nomeProdotto;
    private int quantita;

    public OrdineVisualizzato() {
    }

    public int getOrdineId() {
        return ordineId;
    }

    public void setOrdineId(int ordineId) {
        this.ordineId = ordineId;
    }

    public double getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public void setPrezzoAcquisto(double prezzoAcquisto) {
        this.prezzoAcquisto = prezzoAcquisto;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

}
