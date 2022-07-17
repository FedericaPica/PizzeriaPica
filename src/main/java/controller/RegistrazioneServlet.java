package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Message;
import model.beans.MessageType;
import model.beans.Utente;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.beans.MessageType.*;

@WebServlet(name = "RegistrazioneServlet", value = "/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("emailRegistrati");
        String password = request.getParameter("passwordRegistrati");
        String passwordConferma = request.getParameter("passwordConferma");
        String telefono = request.getParameter("telefono");
        Message message = new Message("", "", INFO);
        UtenteDAO dao = new UtenteDAO();

        HttpSession session = request.getSession(true);

        try {
            if (nome == null || cognome == null ||  email == null || password == null || passwordConferma == null || telefono == null)
                throw new Exception("Tutti i campi sono obbligatori.");
            if (dao.doRetrieveByEmail(email) != null)
                throw new Exception("Questa email è già stata registrata.");
            RegistrazioneServlet.validateField("Nome", Optional.of(nome), "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", 3, 255);
            RegistrazioneServlet.validateField("Cognome", Optional.of(cognome), "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", 3, 255);
            RegistrazioneServlet.validateField("Email", Optional.of(email), "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", 5, 255);
            RegistrazioneServlet.validateField("Password", Optional.of(password), "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})", 8, 255);
            RegistrazioneServlet.validateField("Telefono", Optional.of(telefono), "^\\d{3,4}-\\d{5,9}$", 2, 255);
            if (!(password.equals(passwordConferma)))
                throw new Exception("Le due password devono coincidere.");
        } catch (Exception e) {
            message.setBody(e.getMessage());
            message.setTitle("Errore");
            message.setType(ERROR);
        }

        if (!message.getType().equals("ERROR")) {
            Utente utente = new Utente();

            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setPassword(password);
            utente.setTelefono(telefono);
            dao.doSave(utente);

            message.setType(SUCCESS);
            message.setBody("Registrazione effettuata correttamente. Ora puoi effettuare il login.");
            message.setTitle("Ok!");
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/results/accedi_registrati.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }


    private static void validateField(String fieldName, Optional<String> fieldValue, String regex, int minLength, int maxLength) throws Exception{
        final Pattern pattern = Pattern.compile(regex);
        String realValue = null;
        if (fieldValue.isPresent())
            realValue = fieldValue.get();

        final Matcher matcher = pattern.matcher(realValue);

        if (realValue.isEmpty() || realValue.length() < minLength || realValue.length() > maxLength)
            throw new Exception("Il campo " + fieldName + " ha una lunghezza non regolare.");


        if (!matcher.find())
            throw new Exception("Il campo " + fieldName +  " non rispetta il formato richiesto.");

    }
}
