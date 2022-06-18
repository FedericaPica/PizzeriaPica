package model.beans;

public class ProdottoCarrello {
    private int prodottoid;
    private int carrelloid;
    private int quantita;
    private double prezzoAcquisto;

    public ProdottoCarrello() {
    }

    public int getProdottoid() {
        return prodottoid;
    }

    public void setProdottoid(int prodottoid) {
        this.prodottoid = prodottoid;
    }

    public int getCarrelloid() {
        return carrelloid;
    }

    public void setCarrelloid(int carrelloid) {
        this.carrelloid = carrelloid;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public void setPrezzoAcquisto(double prezzoAcquisto) {
        this.prezzoAcquisto = prezzoAcquisto;
    }
}
