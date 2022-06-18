package model.beans;

import java.util.Date;

public class Festivi {
    private int id;
    private Date giorno;

    public Festivi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGiorno() {
        return giorno;
    }

    public void setGiorno(Date giorno) {
        this.giorno = giorno;
    }
}
