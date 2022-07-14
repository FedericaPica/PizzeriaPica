package model.dao;

import model.ConPool;
import model.beans.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    public List<Ordine> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, ritiroDt, totale, utenteid, stato FROM ordine");
            ResultSet rs = ps.executeQuery();
            ArrayList<Ordine> listaOrdini = new ArrayList<>();

            while (rs.next()) {
                Ordine o = new Ordine();
                o.setId(rs.getInt("id"));
                o.setRitiroDt(rs.getTimestamp("ritiroDt"));
                o.setTotale(rs.getDouble("totale"));
                o.setUtenteid(rs.getInt("utenteid"));
                o.setStato(StatoOrdine.valueOf((rs.getString("stato")).toUpperCase()));


                listaOrdini.add(o);
            }
            return (listaOrdini.isEmpty()) ? null : listaOrdini;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void doAnnulla(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE ordine SET stato='annullato' WHERE id=?");
            ps.setInt(1, ordine.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doConcludi(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE ordine SET stato='eseguito' WHERE id=?");
            ps.setInt(1, ordine.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ordine doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, ritiroDt, totale, utenteid, stato FROM ordine WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ordine o = new Ordine();
                o.setId(rs.getInt("id"));
                o.setRitiroDt(rs.getTimestamp("ritiroDt"));
                o.setTotale(rs.getDouble("totale"));
                o.setUtenteid(rs.getInt("utenteid"));
                o.setStato(StatoOrdine.valueOf((rs.getString("stato")).toUpperCase()));
                return o;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doSave(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine (ritiroDt, totale, utenteid, stato) VALUES(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, ordine.getRitiroDt());
            ps.setDouble(2, ordine.getTotale());
            ps.setInt(3, ordine.getUtenteid());
            ps.setString(4, ordine.getStato().name());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            ordine.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ordine> doRetrieveByUtenteId(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, ritiroDt, totale, utenteid, stato FROM ordine WHERE utenteid=? ORDER BY ordine.id DESC");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Ordine> ordini = new ArrayList<>();

            while (rs.next()) {
                Ordine o = new Ordine();
                o.setId(rs.getInt("id"));
                o.setRitiroDt(rs.getTimestamp("ritiroDt"));
                o.setTotale(rs.getDouble("totale"));
                o.setUtenteid(rs.getInt("utenteid"));
                o.setStato(StatoOrdine.valueOf((rs.getString("stato")).toUpperCase()));

                ordini.add(o);
            }
            return (ordini.isEmpty()) ? null : ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
