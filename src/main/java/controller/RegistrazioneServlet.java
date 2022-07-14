package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Message;
import model.beans.MessageType;
import model.beans.Utente;
import model.dao.UtenteDAO;

import java.io.IOException;
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

        HttpSession session = request.getSession(true);

        try {
            RegistrazioneServlet.validateField("Nome", nome, "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/u", 3, 255);
            RegistrazioneServlet.validateField("Cognome", cognome, "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$/u", 3, 255);
            RegistrazioneServlet.validateField("Email", email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", 5, 255);
            RegistrazioneServlet.validateField("Password", password, "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})", 8, 255);
            RegistrazioneServlet.validateField("Telefono", telefono, "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[\\s0-9]*$", 2, 255);
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

            UtenteDAO dao = new UtenteDAO();
            dao.doSave(utente);

            session.setAttribute("utente", utente);
            message.setType(SUCCESS);
            message.setBody("Registrazione effettuata correttamente.");
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


    private static void validateField(String fieldName, String fieldValue, String regex, int minLength, int maxLength) throws Exception{
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(fieldValue);

        if (fieldValue.isEmpty() || fieldValue == null || fieldValue.length() < minLength || fieldValue.length() > maxLength)
            throw new Exception("Il campo " + fieldName + " ha una lunghezza non regolare.");


        if (!matcher.find())
            throw new Exception("Il campo " + fieldName +  " non rispetta il formato richiesto.");

    }
}
