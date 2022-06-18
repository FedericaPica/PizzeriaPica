package model.beans;

import java.sql.Timestamp;

public class Orario {
    private int id;
    private Timestamp orario;

    public Orario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOrario() {
        return orario;
    }

    public void setOrario(Timestamp orario) {
        this.orario = orario;
    }
}
