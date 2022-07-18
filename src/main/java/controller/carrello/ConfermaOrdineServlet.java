package controller.carrello;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.*;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.beans.MessageType.*;

@WebServlet(name = "ConfermaOrdineServlet", value = "/ConfermaOrdineServlet")
public class ConfermaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utente utente = (Utente) session.getAttribute("utente");
        Message message = new Message("", "", INFO);
        String dataRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        String orarioRegex = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$";
        int idUtente = utente.getId();
        String address = "/ProcediOrdineServlet";

        String dataRitiro = request.getParameter("dataRitiro") + " " + request.getParameter("oraRitiro");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = null;
        try {
            if (request.getParameter("dataRitiro").equals("") || request.getParameter("oraRitiro").equals(""))
                throw new Exception("Tutti i campi sono obbligatori.");
            OrdineDAO ordineDAO = new OrdineDAO();
            Date oggi = new Date();
            Date ordineDt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataRitiro"));
            System.out.println(oggi);
            FestivoDAO festivoDAO = new FestivoDAO();
            if (oggi.after(ordineDt))
                throw new Exception("La data di ordine non può essere precedente a quella odierna!");
            if (festivoDAO.doRetrieveByData(request.getParameter("dataRitiro")) != null)
                throw new Exception("Non puoi effettuare l'ordine in un giorno di chiusura.");

            String dateToCheck = request.getParameter("dataRitiro") + " " + request.getParameter("oraRitiro");
            if (ordineDAO.doRetrieveByDataRitiro(dateToCheck) != null)
                throw new Exception("E' già presente un ordine per questo orario.");

            ConfermaOrdineServlet.validateField("data", Optional.of(request.getParameter("dataRitiro")), dataRegex);
            ConfermaOrdineServlet.validateField("ora", Optional.of(request.getParameter("oraRitiro")), orarioRegex);
        } catch(Exception e) {
            message.setBody(e.getMessage());
            message.setTitle("Errore");
            message.setType(ERROR);
        }

        if (!message.getType().equals("ERROR")) {
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
            for (ProdottoCarrello pC : prodottiCarrello) {
                Prodotto prodotto = pDao.doRetrieveById(pC.getProdottoid());
                double price = prodotto.getPrezzo() * pC.getQuantita();
                if (prodotto.getSconto() != 0) {
                    price -= (price * prodotto.getSconto()) / 100;
                }
                pC.setPrezzoAcquisto(price);
                pcDao.doUpdate(pC);
            }

            address = "/MieiOrdiniServlet";
            message.setType(SUCCESS);
            message.setBody("Ordine effettuato.");
            message.setTitle("Ok!");
        }
        request.setAttribute("message", message);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private static void validateField(String fieldName, Optional<String> fieldValue, String regex) throws Exception{
        final Pattern pattern = Pattern.compile(regex);
        String realValue = fieldValue.orElse(null);
        final Matcher matcher = pattern.matcher(realValue);


        if (realValue.isEmpty() || realValue == null)
            throw new Exception("Il campo " + fieldName + " non può essere vuoto.");


        if (!matcher.find())
            throw new Exception("Il campo " + fieldName +  " non rispetta il formato richiesto.");

    }
}
