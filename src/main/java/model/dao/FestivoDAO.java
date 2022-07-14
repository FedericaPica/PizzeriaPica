package model.dao;

import model.ConPool;
import model.beans.Festivi;
import model.beans.Orario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FestivoDAO {
    public List<Festivi> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, giorno FROM festivi ORDER BY giorno ASC");
            ResultSet rs = ps.executeQuery();
            ArrayList<Festivi> listaFestivi = new ArrayList<>();

            while (rs.next()) {
                Festivi f = new Festivi();
                f.setId(rs.getInt("id"));
                f.setGiorno(rs.getDate("giorno"));

                listaFestivi.add(f);
            }
            return (listaFestivi.isEmpty()) ? null : listaFestivi;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM festivi WHERE id=?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Festivi festivo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO festivi (giorno) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, festivo.getGiorno());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            festivo.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
