package model.dao;

import model.ConPool;
import model.beans.Categoria;
import model.beans.Prodotto;
import model.beans.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    public void doSave(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO utente (nome, cognome, email, password, telefono) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getPassword());
            ps.setString(5, utente.getTelefono());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            utente.setId(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Utente> doRetrieveAll() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, cognome, email, telefono FROM utente");
            ResultSet rs = ps.executeQuery();
            ArrayList<Utente> listaUtenti = new ArrayList<>();

            while (rs.next()) {
                Utente u = new Utente();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setTelefono(rs.getString("telefono"));

                listaUtenti.add(u);
            }
            return (listaUtenti.isEmpty()) ? null : listaUtenti;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("DELETE FROM utente WHERE id=?");
            ps.setInt(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public Utente doRetrieveById(int id) {
//        try (Connection con = ConPool.getConnection()) {
//            PreparedStatement ps =
//                    con.prepareStatement("SELECT id, nome, cognome, email, telefono FROM utente WHERE id=?");
//            ps.setInt(1, id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Utente u = new Utente();
//                u.setId(rs.getInt("id"));
//                u.setNome(rs.getString("nome"));
//                u.setCognome(rs.getString("cognome"));
//                u.setEmail(rs.getString("email"));
//                u.setTelefono(rs.getString("telefono"));
//                return u;
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public Utente doRetrieveByEmailPassword(String email, String pass) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, cognome, email, telefono, password, admin FROM utente WHERE email=? AND password=SHA1 (?)");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setTelefono(rs.getString("telefono"));
                u.setPassword(rs.getString("password"));
                u.setAdmin(rs.getBoolean("admin"));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Utente doRetrieveByEmail(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id, nome, cognome, email, telefono, password, admin FROM utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente u = new Utente();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setCognome(rs.getString("cognome"));
                u.setEmail(rs.getString("email"));
                u.setTelefono(rs.getString("telefono"));
                u.setPassword(rs.getString("password"));
                u.setAdmin(rs.getBoolean("admin"));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
