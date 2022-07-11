package model.beans;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Ordine {
    private int id;
    private Timestamp ritiroDt;
    private double totale;
    private int utenteid;
    private StatoOrdine stato;

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

    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
    }
}
