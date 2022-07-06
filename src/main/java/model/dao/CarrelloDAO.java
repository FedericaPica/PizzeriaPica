package model.dao;

import model.ConPool;
import model.beans.*;

import java.sql.*;

public class CarrelloDAO {

    public void doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO carrello (stato, session_id, utenteid) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, carrello.getStato().name());
            ps.setString(2, carrello.getSession_id());
            ps.setInt(3, carrello.getUtenteid());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            carrello.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carrello doRetrieveByUtenteidAttivo(int utenteId) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, stato, session_id, ordineid, utenteid FROM carrello WHERE utenteid=? AND stato='attivo'");
            ps.setInt(1, utenteId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Carrello c = new Carrello();
                c.setId(rs.getInt("id"));
                c.setStato(StatoCarrello.valueOf((rs.getString("stato")).toUpperCase()));
                c.setSession_id(rs.getString("session_id"));
                c.setOrdineid(rs.getInt("ordineid"));
                c.setUtenteid(rs.getInt("utenteid"));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM carrello WHERE id=?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
