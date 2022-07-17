package model.dao;

import model.ConPool;
import model.beans.Categoria;

import java.sql.*;
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

    public Categoria doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, priority FROM categoria WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setPriority(rs.getInt("priority"));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria doRetrieveByNome(String nome) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, priority FROM categoria WHERE nome=?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setPriority(rs.getInt("priority"));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String doRetrieveName(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT nome FROM categoria WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                return nome;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE categoria SET nome=?, priority=? WHERE id=?");
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getPriority());
            ps.setInt(3, categoria.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM categoria WHERE id=?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria doRetrieveByPriority(int priority) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, priority FROM categoria WHERE priority=?");
            ps.setInt(1, priority);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setPriority(rs.getInt("priority"));
                return c;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doIncreasePriority(int priority) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE categoria SET priority=priority+1 WHERE priority>=?");
            ps.setInt(1, priority);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO categoria (nome, priority) VALUES(?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getPriority());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            categoria.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
