package model.dao;

import model.ConPool;
import model.beans.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
