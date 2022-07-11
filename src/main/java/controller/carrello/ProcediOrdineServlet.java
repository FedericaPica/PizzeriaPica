package controller.carrello;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.CarrelloDAO;
import model.dao.OrarioDAO;
import model.dao.ProdottoCarrelloDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.beans.MessageType.*;

@WebServlet(name = "ProcediOrdineServlet", value = "/ProcediOrdineServlet")
public class ProcediOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    Utente utente = (Utente) session.getAttribute("utente");
    Message message = new Message("", "", INFO);
        CarrelloDAO cDao = new CarrelloDAO();

    if (cDao.doRetrieveByUtenteidAttivo(utente.getId()) == null) {
        message.setType(ERROR);
        message.setBody("Non ci sono prodotti nel carrello.");
        request.setAttribute("message", message);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

        if (utente == null) {
            message.setType(ERROR);
            message.setBody("Per ordinare accedi o registrati.");
            request.setAttribute("message", message);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            int idUtente = utente.getId();

            Carrello carrelloDb = cDao.doRetrieveByUtenteidAttivo(idUtente);
            ProdottoCarrelloDAO pcDao = new ProdottoCarrelloDAO();
            ArrayList<CarrelloVisualizzato> prodottiCarrelloDb = (ArrayList<CarrelloVisualizzato>) pcDao.doRetrieveByCarrelloIdVisualizzato(carrelloDb.getId());
            request.setAttribute("prodottiCarrelloDb", prodottiCarrelloDb);

            int carrelloId = carrelloDb.getId();
            request.setAttribute("carrelloId", carrelloId);

            double totale = 0;
            for (CarrelloVisualizzato c: prodottiCarrelloDb) {
                totale += c.getPrezzo();
            }
            request.setAttribute("totale", totale);

            OrarioDAO oDao = new OrarioDAO();
            List<Orario> orariDisponibili = oDao.doRetrieveAll();
            request.setAttribute("orariDisponibili", orariDisponibili);


            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/results/conferma_ordine.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
