package controller.admin.categoria;

import controller.RegistrazioneServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Message;
import model.dao.CategoriaDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static model.beans.MessageType.*;

@WebServlet(name = "InserisciCategoriaDBServlet", value = "/InserisciCategoriaDBServlet")
public class InserisciCategoriaDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");

        Message message = new Message("", "", INFO);
        CategoriaDAO dao = new CategoriaDAO();

        try {
            if (nome == null || request.getParameter("priority") == null)
                throw new Exception("Tutti i campi sono obbligatori.");
            if (dao.doRetrieveByNome(nome) != null)
                throw new Exception("Questa categoria già esiste.");
            InserisciCategoriaDBServlet.validateField("Nome", Optional.of(nome), "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", 3, 255);
            InserisciCategoriaDBServlet.validateField("Priority", Optional.of(request.getParameter("priority")), "^\\d+$", 1, 10);

        } catch (Exception e) {
            message.setBody(e.getMessage());
            message.setTitle("Errore");
            message.setType(ERROR);
        }

        Categoria categoria = new Categoria();
        if (!message.getType().equals("ERROR")) {
            int priority = Integer.parseInt(request.getParameter("priority"));
        categoria.setNome(nome);
        categoria.setPriority(priority);


        if(dao.doRetrieveByPriority(priority) != null) {
            dao.doIncreasePriority(priority);
        }
        dao.doSave(categoria);

        List<Categoria> listaCategorie = new ArrayList<Categoria>();
        listaCategorie = dao.doRetrieveAll();
        getServletContext().removeAttribute("categorie");
        getServletContext().setAttribute("categorie", listaCategorie);


        message.setType(SUCCESS);
        message.setBody("Inserito con successo.");
        message.setTitle("Ok!");
        }
        request.setAttribute("message", message);

        request.setAttribute("check", "categoria");

        request.setAttribute("categoria", categoria);
        String address = "/WEB-INF/results/admin/form_categoria.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
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
