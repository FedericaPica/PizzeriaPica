package model.dao;

import model.ConPool;
import model.beans.Carrello;
import model.beans.CarrelloVisualizzato;
import model.beans.Categoria;
import model.beans.ProdottoCarrello;

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
                    con.prepareStatement("SELECT prodotto.nome, (prodotto.prezzo*prodotto_carrello.quantita) AS prezzoFinale, prodotto.sconto, prodotto_carrello.quantita" +
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
}
