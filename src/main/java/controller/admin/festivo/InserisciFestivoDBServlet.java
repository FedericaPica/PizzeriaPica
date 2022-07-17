package controller.admin.festivo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Festivi;
import model.beans.Message;
import model.beans.Orario;
import model.dao.FestivoDAO;
import model.dao.OrarioDAO;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.beans.MessageType.*;

@WebServlet(name = "InserisciFestivoDBServlet", value = "/InserisciFestivoDBServlet")
public class InserisciFestivoDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String giorno = request.getParameter("festivo");
        Message message = new Message("", "", INFO);
        try {
            InserisciFestivoDBServlet.validateField("giorno festivo", Optional.of(giorno), "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$");
        } catch (Exception e) {
            message.setBody(e.getMessage());
            message.setTitle("Errore");
            message.setType(ERROR);
        }
        Date giornoF = null;

        if (!message.getType().equals("ERROR")) {
            try {
                giornoF = new SimpleDateFormat("yyyy-MM-dd").parse(giorno);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Festivi festivo = new Festivi();
            java.sql.Date sqlDate = new java.sql.Date(giornoF.getTime());
            festivo.setGiorno(sqlDate);
            FestivoDAO dao = new FestivoDAO();
            dao.doSave(festivo);

            message.setType(SUCCESS);
            message.setBody("Inserito con successo.");
            message.setTitle("Ok!");
        }
        request.setAttribute("message", message);

        String address = "/WEB-INF/results/admin/form_festivo.jsp";
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
        FestivoDAO dao = new FestivoDAO();

        if (dao.doRetrieveByData(realValue) != null)
            throw new Exception("Il giorno inserito è già presente tra i festivi.");

        if (fieldValue == null || fieldValue.equals(""))
            throw new Exception("Il campo " + fieldName + " ha una lunghezza non regolare.");

        if (!matcher.find())
            throw new Exception("Il campo " + fieldName +  " non rispetta il formato richiesto.");

    }
}
