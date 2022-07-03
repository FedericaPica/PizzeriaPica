package model.dao;

import model.ConPool;
import model.beans.Categoria;
import model.beans.Orario;
import model.beans.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrarioDAO {

    public List<Orario> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, orario FROM orario ORDER BY orario ASC");
            ResultSet rs = ps.executeQuery();
            ArrayList<Orario> listaOrari = new ArrayList<>();

            while (rs.next()) {
                Orario o = new Orario();
                o.setId(rs.getInt("id"));
                o.setOrario(rs.getTime("orario"));

                listaOrari.add(o);
            }
            return (listaOrari.isEmpty()) ? null : listaOrari;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM orario WHERE id=?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Orario orario) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO orario (orario) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setTime(1, orario.getOrario());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            orario.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
