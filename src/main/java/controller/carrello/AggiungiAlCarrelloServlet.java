package controller.carrello;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Carrello;
import model.beans.Prodotto;
import model.beans.ProdottoCarrello;
import model.beans.Utente;
import model.dao.CarrelloDAO;
import model.dao.ProdottoCarrelloDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AggiungiAlCarrelloServlet", value = "/AggiungiAlCarrelloServlet")
public class AggiungiAlCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int prodottoId = Integer.parseInt(request.getParameter("idProdotto"));
        HttpSession session = request.getSession(true);
        List<Prodotto> prodottiCarrello = (List<Prodotto>) session.getAttribute("prodottiCarrello");
        if(prodottiCarrello == null) {
            prodottiCarrello = new ArrayList<Prodotto>();
        }

        Prodotto prodotto = new Prodotto();
        ProdottoDAO dao = new ProdottoDAO();
        prodotto = dao.doRetrieveById(prodottoId);
        if (prodotto != null) {
            prodottiCarrello.add(prodotto);
        }
        session.setAttribute("prodottiCarrello", prodottiCarrello);
        double totale=0;

        if (prodottiCarrello != null) {
            for (Prodotto p:prodottiCarrello) {
                totale += p.getPrezzo();
            }
            session.setAttribute("totale", totale);
        }

        ArrayList<ProdottoCarrello> prodottiCarrelloDb = new ArrayList<>();
        if (session.getAttribute("utente") != null) {
            Utente utente = (Utente) session.getAttribute("utente");
            CarrelloDAO cDao = new CarrelloDAO();
            Carrello carrelloDb = cDao.doRetrieveByUtenteidAttivo(utente.getId());
            ProdottoCarrelloDAO pcDao = new ProdottoCarrelloDAO();
            prodottiCarrelloDb = (ArrayList<ProdottoCarrello>) pcDao.doRetrieveByCarrelloId(carrelloDb.getId());

            boolean updated = false;
            if (prodottiCarrelloDb.size() != 0) {
                for (ProdottoCarrello pC : prodottiCarrelloDb) {
                    if (prodottoId == pC.getProdottoid()) {
                        pcDao.doUpdateQuantita(pC, (pC.getQuantita() + 1));
                        updated = true;
                    }
                }
            }
            if (prodottiCarrelloDb.size() == 0 || (prodottiCarrelloDb.size()!= 0 && updated == false)) {
                ProdottoCarrello newProduct = new ProdottoCarrello();
                newProduct.setProdottoid(prodottoId);
                newProduct.setCarrelloid(carrelloDb.getId());
                newProduct.setQuantita(1);
                pcDao.doSave(newProduct);
                prodottiCarrelloDb.add(newProduct);
            }


        }

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(prodotto);
        jsonElement.getAsJsonObject().addProperty("totale", totale);
        String newJson = gson.toJson(jsonElement);
        out.write(newJson);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
