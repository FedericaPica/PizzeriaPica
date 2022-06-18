package model.dao;

import model.ConPool;
import model.beans.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<Categoria> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, priority FROM categoria ORDER BY priority ASC ");
            ResultSet rs = ps.executeQuery();
            ArrayList<Categoria> listaCategorie = new ArrayList<>();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setPriority(rs.getInt("priority"));

                listaCategorie.add(c);
            }
            return (listaCategorie.isEmpty()) ? null : listaCategorie;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}
