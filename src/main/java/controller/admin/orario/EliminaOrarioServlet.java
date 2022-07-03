package controller.admin.orario;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Message;
import model.dao.OrarioDAO;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "EliminaOrarioServlet", value = "/EliminaOrarioServlet")
public class EliminaOrarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orarioId = Integer.parseInt(request.getParameter("orarioId"));
        OrarioDAO dao = new OrarioDAO();
        dao.doDelete(orarioId);
        Message message = new Message("", "", INFO);

        message.setType(SUCCESS);
        message.setBody("Eliminato con successo.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonMess = gson.toJson(message);
        out.write(jsonMess);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
