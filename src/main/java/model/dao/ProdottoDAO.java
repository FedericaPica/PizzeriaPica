package model.dao;

import model.ConPool;
import model.beans.Categoria;
import model.beans.Prodotto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {

    public List<Prodotto> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, prezzo, descrizione, categoriaid, sconto FROM prodotto");
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> listaProdotti = new ArrayList<>();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCategoriaid(rs.getInt("categoriaid"));
                p.setSconto(rs.getInt("int"));

                listaProdotti.add(p);
            }
            return (listaProdotti.isEmpty()) ? null : listaProdotti;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public List<Prodotto> doRetrieveByCategoria(int idCategoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, prezzo, descrizione, categoriaid, sconto FROM prodotto WHERE categoriaid=?");
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> listaProdotti = new ArrayList<>();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCategoriaid(rs.getInt("categoriaid"));
                p.setSconto(rs.getInt("sconto"));

                listaProdotti.add(p);
            }
            return (listaProdotti.isEmpty()) ? null : listaProdotti;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE prodotto SET nome=?, prezzo=?, descrizione=?, sconto=? WHERE id=?");
            ps.setString(1, prodotto.getNome());
            ps.setDouble(2, prodotto.getPrezzo());
            ps.setString(3, prodotto.getDescrizione());
            ps.setInt(4, prodotto.getSconto());
            ps.setInt(5, prodotto.getId());
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
                    con.prepareStatement("DELETE FROM prodotto WHERE id=?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto (nome, prezzo, descrizione, categoriaid, sconto) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prodotto.getNome());
            ps.setDouble(2, prodotto.getPrezzo());
            ps.setString(3, prodotto.getDescrizione());
            ps.setInt(4, prodotto.getCategoriaid());
            ps.setInt(5, prodotto.getSconto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            prodotto.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prodotto doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, prezzo, descrizione, categoriaid, sconto FROM prodotto WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Prodotto p = new Prodotto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCategoriaid(rs.getInt("categoriaid"));
                p.setSconto(rs.getInt("sconto"));
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}