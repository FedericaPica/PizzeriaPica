package controller.admin.orario;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Message;
import model.beans.Orario;
import model.dao.CategoriaDAO;
import model.dao.OrarioDAO;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.beans.MessageType.*;

@WebServlet(name = "InserisciOrarioDBServlet", value = "/InserisciOrarioDBServlet")
public class InserisciOrarioDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orarioDaValidare = (String) request.getParameter("orario");
        Message message = new Message("", "", INFO);

        try {
            if (orarioDaValidare == null)
                throw new Exception("Tutti i campi sono obbligatori.");
            InserisciOrarioDBServlet.validateOrario("orario", Optional.of(orarioDaValidare), "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");
        } catch (Exception e) {
            message.setBody(e.getMessage());
            message.setTitle("Errore");
            message.setType(ERROR);
        }
        if (!message.getType().equals("ERROR")) {
            Time ora = Time.valueOf((request.getParameter("orario")) + ":00");

            Orario orario = new Orario();

            orario.setOrario(ora);
            OrarioDAO dao = new OrarioDAO();
            dao.doSave(orario);

            message.setType(SUCCESS);
            message.setBody("Inserito con successo.");
            message.setTitle("Ok!");
        }
        request.setAttribute("message", message);

        String address = "/WEB-INF/results/admin/form_orario.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private static void validateOrario(String fieldName, Optional<String> fieldValue, String regex) throws Exception{
        final Pattern pattern = Pattern.compile(regex);
        String realValue = fieldValue.orElse(null);
        final Matcher matcher = pattern.matcher(realValue);
        OrarioDAO orarioDAO = new OrarioDAO();

        if (orarioDAO.doRetrieveByOrario(realValue) != null)
            throw new Exception("L'orario è già presente nella lista degli orari disponibili.");

        if (realValue.isEmpty() || realValue == null)
            throw new Exception("Il campo " + fieldName + " ha una lunghezza non regolare.");


        if (!matcher.find())
            throw new Exception("Il campo " + fieldName +  " non rispetta il formato richiesto.");

    }
}
