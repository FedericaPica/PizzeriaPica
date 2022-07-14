package model.dao;

import model.ConPool;
import model.beans.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoCarrelloDAO {

    public void doSave(ProdottoCarrello prodottoCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto_carrello (prodottoid, carrelloid, quantita) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prodottoCarrello.getProdottoid());
            ps.setInt(2, prodottoCarrello.getCarrelloid());
            ps.setInt(3, prodottoCarrello.getQuantita());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
//            int id = rs.getInt(1);
//            prodottoCarrello.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateQuantita(ProdottoCarrello prodottoCarrello, int quantita) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE prodotto_carrello SET quantita=? WHERE prodottoid=? AND carrelloid=?");
            ps.setInt(1, quantita);
            ps.setInt(2, prodottoCarrello.getProdottoid());
            ps.setInt(3, prodottoCarrello.getCarrelloid());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProdottoCarrello> doRetrieveByCarrelloId(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT prodottoid, carrelloid, quantita FROM prodotto_carrello WHERE carrelloid=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<ProdottoCarrello> prodottiCarrello = new ArrayList<>();

            while (rs.next()) {
                ProdottoCarrello pC = new ProdottoCarrello();
                pC.setProdottoid(rs.getInt("prodottoid"));
                pC.setCarrelloid(rs.getInt("carrelloid"));
                pC.setQuantita(rs.getInt("quantita"));

                prodottiCarrello.add(pC);
            }
            return (prodottiCarrello.isEmpty()) ? new ArrayList<ProdottoCarrello>() : prodottiCarrello;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CarrelloVisualizzato> doRetrieveByCarrelloIdVisualizzato(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT prodotto.nome, IF(prodotto.sconto > 0, (prodotto.prezzo*prodotto_carrello.quantita - (prodotto.prezzo*prodotto_carrello.quantita*prodotto.sconto)/100), prodotto.prezzo*prodotto_carrello.quantita) AS prezzoFinale, prodotto.sconto, prodotto_carrello.quantita" +
                            " FROM prodotto_carrello INNER JOIN prodotto ON prodotto.id=prodotto_carrello.prodottoid" +
                            " WHERE carrelloid=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<CarrelloVisualizzato> prodottiCarrello = new ArrayList<>();

            while (rs.next()) {
                CarrelloVisualizzato cV = new CarrelloVisualizzato();
                cV.setNome(rs.getString("prodotto.nome"));
                cV.setPrezzo(rs.getDouble("prezzoFinale"));
                cV.setSconto(rs.getInt("prodotto.sconto"));
                cV.setQuantita(rs.getInt("prodotto_carrello.quantita"));

                prodottiCarrello.add(cV);
            }
            return (prodottiCarrello.isEmpty()) ? new ArrayList<CarrelloVisualizzato>() : prodottiCarrello;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(ProdottoCarrello prodottoCarrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE prodotto_carrello SET prodottoid=?, carrelloid=?, quantita=?, prezzo_acquisto=? WHERE prodottoid=? AND carrelloid=?");
            ps.setInt(1,prodottoCarrello.getProdottoid());
            ps.setInt(2, prodottoCarrello.getCarrelloid());
            ps.setInt(3, prodottoCarrello.getQuantita());
            ps.setDouble(4, prodottoCarrello.getPrezzoAcquisto());
            ps.setInt(5, prodottoCarrello.getProdottoid());
            ps.setInt(6, prodottoCarrello.getCarrelloid());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrdineVisualizzato> doRetrieveByOrdineIdVisualizzato(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT ordine.id AS ordineid, prodotto_carrello.prezzo_acquisto, prodotto.nome, prodotto_carrello.quantita " +
                            "FROM carrello INNER JOIN ordine ON ordine.id=carrello.ordineid " +
                            "INNER JOIN prodotto_carrello ON prodotto_carrello.carrelloid=carrello.id " +
                            "INNER JOIN prodotto ON prodotto.id=prodotto_carrello.prodottoid " +
                            "WHERE ordine.id=? "+
                            "ORDER BY ordine.id DESC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<OrdineVisualizzato> ordiniUtente = new ArrayList<>();

            while (rs.next()) {
                OrdineVisualizzato oV = new OrdineVisualizzato();
                oV.setOrdineId(rs.getInt("ordineid"));
                oV.setPrezzoAcquisto(rs.getDouble("prodotto_carrello.prezzo_acquisto"));
                oV.setNomeProdotto(rs.getString("prodotto.nome"));
                oV.setQuantita(rs.getInt("prodotto_carrello.quantita"));

                ordiniUtente.add(oV);
            }
            return (ordiniUtente.isEmpty()) ? null : ordiniUtente;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
