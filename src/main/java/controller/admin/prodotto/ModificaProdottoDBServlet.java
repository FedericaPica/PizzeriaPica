package controller.admin.prodotto;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Message;
import model.beans.Prodotto;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.beans.MessageType.*;

@WebServlet(name = "ModificaProdottoDBServlet", value = "/ModificaProdottoDBServlet")
public class ModificaProdottoDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Message message = new Message("", "", INFO);
        try {
            if (request.getParameter("descrizione") == null || request.getParameter("prezzo") == null ||  request.getParameter("nome") == null || request.getParameter("sconto") == null)
                throw new Exception("Tutti i campi sono obbligatori.");
            ModificaProdottoDBServlet.validateField("descrizione", Optional.of(request.getParameter("descrizione")), "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", 3, 500);
            ModificaProdottoDBServlet.validateField("prezzo", Optional.of(request.getParameter("prezzo")), "^[0-9]+(\\.)[0-9]+$", 0, 255);
            ModificaProdottoDBServlet.validateField("nome", Optional.of(request.getParameter("nome")), "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", 3, 255);
            ModificaProdottoDBServlet.validateField("sconto", Optional.of(request.getParameter("sconto")), "^[0-9][0-9]?$|^100$", 1, 3);
        } catch (Exception e) {
            message.setBody(e.getMessage());
            message.setTitle("Errore");
            message.setType(ERROR);
        }

        Prodotto prodotto = new Prodotto();
        if (!message.getType().equals("ERROR")) {

            int id = Integer.parseInt(request.getParameter("idProd"));
            String nome = request.getParameter("nome");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            String descrizione = request.getParameter("descrizione");
            int sconto = Integer.parseInt(request.getParameter("sconto"));

            prodotto.setId(id);
            prodotto.setNome(nome);
            prodotto.setPrezzo(prezzo);
            prodotto.setDescrizione(descrizione);
            prodotto.setSconto(sconto);

            ProdottoDAO dao = new ProdottoDAO();
            dao.doUpdate(prodotto);

            message.setType(SUCCESS);
            message.setBody("Modifica effettuata.");
            message.setTitle("Ok!");
        }
        request.setAttribute("message", message);
        request.setAttribute("check", "categoria");

        String address;
        request.setAttribute("prodotto", prodotto);
        address = "/WEB-INF/results/admin/form_prodotto.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
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
