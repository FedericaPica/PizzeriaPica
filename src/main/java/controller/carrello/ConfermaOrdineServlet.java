package controller.carrello;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.CarrelloDAO;
import model.dao.OrdineDAO;
import model.dao.ProdottoCarrelloDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "ConfermaOrdineServlet", value = "/ConfermaOrdineServlet")
public class ConfermaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utente utente = (Utente) session.getAttribute("utente");
        Message message = new Message("", "", INFO);

        int idUtente = utente.getId();
        String dataRitiro = request.getParameter("dataRitiro") + " " + request.getParameter("oraRitiro");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dataRitiro);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime();

        String totale = request.getParameter("totale");

        Ordine ordine = new Ordine();
        OrdineDAO oDao = new OrdineDAO();

        ordine.setRitiroDt(new Timestamp(time));
        ordine.setTotale(Double.parseDouble(totale));
        ordine.setUtenteid(idUtente);
        ordine.setStato(StatoOrdine.valueOf("ATTIVO"));
        oDao.doSave(ordine);

        CarrelloDAO carDao = new CarrelloDAO();
        Carrello carrello = carDao.doRetrieveById(Integer.parseInt(request.getParameter("carrelloId")));
        carrello.setStato(StatoCarrello.valueOf("ORDINATO"));
        carrello.setOrdineid(ordine.getId());
        carDao.doUpdate(carrello);

        Carrello newCarrello = new Carrello();
        newCarrello.setStato(StatoCarrello.valueOf("ATTIVO"));
        newCarrello.setSession_id(session.getId());
        newCarrello.setUtenteid(utente.getId());
        carDao.doSave(newCarrello);

        ProdottoCarrelloDAO pcDao = new ProdottoCarrelloDAO();
        ArrayList<ProdottoCarrello> prodottiCarrello = (ArrayList<ProdottoCarrello>) pcDao.doRetrieveByCarrelloId(Integer.parseInt(request.getParameter("carrelloId")));
        ProdottoDAO pDao = new ProdottoDAO();
        for( ProdottoCarrello pC: prodottiCarrello) {
            Prodotto prodotto = pDao.doRetrieveById(pC.getProdottoid());
            double price = prodotto.getPrezzo()*pC.getQuantita();
            if (prodotto.getSconto() != 0) {
                price -= (price * prodotto.getSconto()) / 100;
            }
            pC.setPrezzoAcquisto(price);
            pcDao.doUpdate(pC);
        }



        message.setType(SUCCESS);
        message.setBody("Ordine effettuato.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/MieiOrdiniServlet");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
