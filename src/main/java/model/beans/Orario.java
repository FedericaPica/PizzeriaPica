package model.beans;

import java.sql.Time;
import java.sql.Timestamp;

public class Orario {
    private int id;
    private Time orario;

    public Orario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getOrario() {
        return orario;
    }

    public void setOrario(Time orario) {
        this.orario = orario;
    }
}
