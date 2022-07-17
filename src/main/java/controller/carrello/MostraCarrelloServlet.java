package controller.carrello;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.CarrelloDAO;
import model.dao.ProdottoCarrelloDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraCarrelloServlet", value = "/MostraCarrelloServlet")
public class MostraCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);


        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String json = "";

        if (session.getAttribute("utente") != null) {
            Utente utente = (Utente) session.getAttribute("utente");
            int idUtente = utente.getId();
            CarrelloDAO cDao = new CarrelloDAO();
            Carrello carrelloDb = cDao.doRetrieveByUtenteidAttivo(idUtente);
            ProdottoCarrelloDAO pcDao = new ProdottoCarrelloDAO();
            ArrayList<CarrelloVisualizzato> prodottiCarrelloDb = (ArrayList<CarrelloVisualizzato>) pcDao.doRetrieveByCarrelloIdVisualizzato(carrelloDb.getId());
            json = gson.toJson(prodottiCarrelloDb);
        } else {
            List<Prodotto> prodottiCarrelloSession = (ArrayList<Prodotto>) session.getAttribute("prodottiCarrello");
            json = gson.toJson(prodottiCarrelloSession);
        }

        out.write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
