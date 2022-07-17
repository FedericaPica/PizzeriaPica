package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dao.CarrelloDAO;
import model.dao.ProdottoCarrelloDAO;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.beans.MessageType.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("emailAccedi");
        String password = request.getParameter("passwordAccedi");
        Message message = new Message("", "", INFO);
        HttpSession session = request.getSession(true);

        try {
            if (email == null || password == null)
                throw new Exception("Tutti i campi sono obbligatori.");
            LoginServlet.validateField("Email", Optional.of(email), "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", 3, 255);
            LoginServlet.validateField("Password", Optional.of(password), "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})", 3, 255);
        } catch (Exception e) {
            message.setBody(e.getMessage());
            message.setTitle("Errore");
            message.setType(ERROR);
        }

        if (!message.getType().equals("ERROR")) {
            Utente utente = new Utente();
            UtenteDAO dao = new UtenteDAO();
            utente = dao.doRetrieveByEmailPassword(email, password);

            if (utente != null && !utente.isAdmin()) {
                session.setAttribute("utente", utente);
                message.setType(SUCCESS);
                message.setBody("Bentornato");
                request.setAttribute("message", message);
                List<Prodotto> carrelloSession = (List<Prodotto>) session.getAttribute("prodottiCarrello");

                CarrelloDAO cDao = new CarrelloDAO();
                Carrello carrelloDb = cDao.doRetrieveByUtenteidAttivo(utente.getId());
                if (carrelloSession != null && carrelloDb != null) {
                    if (carrelloDb.getSession_id() != session.getId()) {
                        cDao.doDelete(carrelloDb.getId());
                        carrelloDb = null;
                    }
                }

                if (carrelloSession != null && carrelloDb == null) {
                    Carrello carrelloDef = new Carrello();
                    carrelloDef.setStato(StatoCarrello.valueOf("ATTIVO"));
                    carrelloDef.setSession_id(session.getId());
                    carrelloDef.setUtenteid(utente.getId());
                    cDao.doSave(carrelloDef);

                    ProdottoCarrelloDAO pcDao = new ProdottoCarrelloDAO();
                    boolean exitLoop = false;
                    ArrayList<ProdottoCarrello> prodottiCarrelloDef = (ArrayList<ProdottoCarrello>) pcDao.doRetrieveByCarrelloId(carrelloDef.getId());
                    for (Prodotto p : carrelloSession) {
                        if (prodottiCarrelloDef.size() != 0) {
                            for (ProdottoCarrello pC : prodottiCarrelloDef) {
                                if (p.getId() == pC.getProdottoid()) {
                                    pcDao.doUpdateQuantita(pC, (pC.getQuantita() + 1));

                                    exitLoop = true;
                                }
                                continue;
                            }
                        }
                        if (exitLoop)
                            continue;
                        ProdottoCarrello newProduct = new ProdottoCarrello();
                        newProduct.setProdottoid(p.getId());
                        newProduct.setCarrelloid(carrelloDef.getId());
                        newProduct.setQuantita(1);
                        pcDao.doSave(newProduct);
                        prodottiCarrelloDef.add(newProduct);
                    }
                }

                if (carrelloSession == null && carrelloDb == null) {
                    Carrello carrelloDef = new Carrello();
                    carrelloDef.setStato(StatoCarrello.valueOf("ATTIVO"));
                    carrelloDef.setSession_id(session.getId());
                    carrelloDef.setUtenteid(utente.getId());
                    cDao.doSave(carrelloDef);
                }

            } else {
                message.setType(ERROR);
                message.setBody("Email o password errate");
                message.setTitle("Ops");
                request.setAttribute("message", message);
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("WEB-INF/results/accedi_registrati.jsp");
                dispatcher.forward(request, response);
            }


            if (!utente.isAdmin()) {
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("WEB-INF/results/admin/a_homepage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/results/accedi_registrati.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private static void validateField(String fieldName, Optional<String> fieldValue, String regex, int minLength, int maxLength) throws Exception{
        final Pattern pattern = Pattern.compile(regex);
        String realValue = fieldValue.orElse(null);
        final Matcher matcher = pattern.matcher(realValue);

        if (realValue.isEmpty() || realValue == null || realValue.length() < minLength || realValue.length() > maxLength)
            throw new Exception("Il campo " + fieldName + " ha una lunghezza non regolare.");


        if (!matcher.find())
            throw new Exception("Il campo " + fieldName +  " non rispetta il formato richiesto.");

    }
}
