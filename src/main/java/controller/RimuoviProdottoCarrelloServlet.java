package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Carrello;
import model.beans.Prodotto;
import model.beans.ProdottoCarrello;
import model.beans.Utente;
import model.dao.CarrelloDAO;
import model.dao.ProdottoCarrelloDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RimuoviProdottoCarrelloServlet", value = "/RimuoviProdottoCarrelloServlet")
public class RimuoviProdottoCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int prodottoId = Integer.parseInt(request.getParameter("idProdotto"));
        HttpSession session = request.getSession(true);
        String type = "session";
        List<Prodotto> prodottiSession = (List<Prodotto>) session.getAttribute("prodottiCarrello");
        if (prodottiSession != null) {
            for (Prodotto p : prodottiSession) {
                if (p.getId() == prodottoId) {
                    prodottiSession.remove(p);
                    break;
                }
            }
        }


        if (session.getAttribute("utente") != null) {
            Utente utente = (Utente) session.getAttribute("utente");
            type = "db";
            CarrelloDAO carDao = new CarrelloDAO();
            Carrello car = carDao.doRetrieveByUtenteidAttivo(utente.getId());
            ProdottoCarrelloDAO pcDao = new ProdottoCarrelloDAO();
            List<ProdottoCarrello> prodotti = pcDao.doRetrieveByCarrelloId(car.getId());

            for (ProdottoCarrello pC : prodotti) {
                if (pC.getProdottoid() == prodottoId) {
                    if (pC.getQuantita() == 1)
                        pcDao.doDelete(pC.getProdottoid(), pC.getCarrelloid());
                    else
                        pcDao.doUpdateQuantita(pC, pC.getQuantita()-1);
                }
            }

        }
        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String json = gson.toJson(type);
        out.write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
