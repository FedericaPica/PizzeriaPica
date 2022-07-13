package controller.carrello;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.CarrelloDAO;
import model.dao.OrdineDAO;
import model.dao.ProdottoCarrelloDAO;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "MieiOrdiniServlet", value = "/MieiOrdiniServlet")
public class MieiOrdiniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utente utente = (Utente) session.getAttribute("utente");
        int utenteId = utente.getId();

        OrdineDAO oDao = new OrdineDAO();
        ProdottoCarrelloDAO pcDao = new ProdottoCarrelloDAO();

        LinkedHashMap<Ordine, ArrayList<OrdineVisualizzato>> map = new LinkedHashMap<>();

        List<Ordine> ordini = oDao.doRetrieveByUtenteId(utenteId);
            for (Ordine o : ordini) {
                ArrayList<OrdineVisualizzato> prodottiOrdine = (ArrayList<OrdineVisualizzato>) pcDao.doRetrieveByOrdineIdVisualizzato(o.getId());
                map.put(o,prodottiOrdine);
            }

        request.setAttribute("map", map);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/results/miei_ordini.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
