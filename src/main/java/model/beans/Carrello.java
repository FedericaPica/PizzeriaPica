package model.beans;

public class Carrello{
    private int id;
    private StatoCarrello stato;
    private String session_id;
    private int ordineid;
    private int utenteid;

    public Carrello() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatoCarrello getStato() {
        return stato;
    }

    public void setStato(StatoCarrello stato) {
        this.stato = stato;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public int getOrdineid() {
        return ordineid;
    }

    public void setOrdineid(int ordineid) {
        this.ordineid = ordineid;
    }

    public int getUtenteid() {
        return utenteid;
    }

    public void setUtenteid(int utenteid) {
        this.utenteid = utenteid;
    }
}
