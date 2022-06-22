package controller.admin.utente;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Prodotto;
import model.beans.Utente;
import model.dao.ProdottoDAO;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraUtentiServlet", value = "/MostraUtentiServlet")
public class MostraUtentiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Utente> utenti = new ArrayList<Utente>();
        UtenteDAO dao = new UtenteDAO();
        utenti = dao.doRetrieveAll();

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonCategorie = gson.toJson(utenti);
        out.write(jsonCategorie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
