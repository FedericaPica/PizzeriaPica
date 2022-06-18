package model.beans;

import java.sql.Timestamp;

public class Ordine {
    private int id;
    private Timestamp ritiroDt;
    private double totale;
    private int utenteid;

    public Ordine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getRitiroDt() {
        return ritiroDt;
    }

    public void setRitiroDt(Timestamp ritiroDt) {
        this.ritiroDt = ritiroDt;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public int getUtenteid() {
        return utenteid;
    }

    public void setUtenteid(int utenteid) {
        this.utenteid = utenteid;
    }
}
