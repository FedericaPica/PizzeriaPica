package controller.admin.utente;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Message;
import model.dao.ProdottoDAO;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "EliminaUtenteServlet", value = "/EliminaUtenteServlet")
public class EliminaUtenteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int utenteId = Integer.parseInt(request.getParameter("utenteId"));
        UtenteDAO dao = new UtenteDAO();
        dao.doDelete(utenteId);
        Message message = new Message("", "", INFO);

        message.setType(SUCCESS);
        message.setBody("Eliminato con successo.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonCategorie = gson.toJson(message);
        out.write(jsonCategorie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
