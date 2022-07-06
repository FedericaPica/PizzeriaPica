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
import java.util.ArrayList;

@WebServlet(name = "MostraCarrelloServlet", value = "/MostraCarrelloServlet")
public class MostraCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utente utente = (Utente) session.getAttribute("utente");
        int idUtente = utente.getId();

        CarrelloDAO cDao = new CarrelloDAO();
        Carrello carrelloDb = cDao.doRetrieveByUtenteidAttivo(idUtente);
        ProdottoCarrelloDAO pcDao = new ProdottoCarrelloDAO();
        ArrayList<CarrelloVisualizzato> prodottiCarrelloDb = (ArrayList<CarrelloVisualizzato>) pcDao.doRetrieveByCarrelloIdVisualizzato(carrelloDb.getId());

//        ArrayList<Prodotto> prodottiDb = new ArrayList<>();
//        ProdottoDAO pDao = new ProdottoDAO();
//        if (prodottiCarrelloDb != null) {
//            for (ProdottoCarrello pC : prodottiCarrelloDb) {
//                prodottiDb.add(pDao.doRetrieveById(pC.getProdottoid()));
//            }
//        }

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String json = gson.toJson(prodottiCarrelloDb);
        out.write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
