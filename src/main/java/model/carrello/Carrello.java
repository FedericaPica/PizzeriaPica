package model.carrello;

public class Carrello {
    private int id;
    private boolean stato;
    private String sessionId;
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

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
