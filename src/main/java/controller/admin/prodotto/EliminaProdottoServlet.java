package controller.admin.prodotto;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Message;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "EliminaProdottoServlet", value = "/EliminaProdottoServlet")
public class EliminaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int prodottoId = Integer.parseInt(request.getParameter("idProdotto"));
        ProdottoDAO dao = new ProdottoDAO();
        dao.doDelete(prodottoId);
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
