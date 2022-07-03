package model.dao;

import model.ConPool;
import model.beans.Categoria;
import model.beans.Ordine;
import model.beans.StatoOrdine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void doUpdate(Ordine ordine) {
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
}
