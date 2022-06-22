package controller.admin.categoria;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Message;
import model.dao.CategoriaDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "EliminaCategoriaServlet", value = "/EliminaCategoriaServlet")
public class EliminaCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
        CategoriaDAO dao = new CategoriaDAO();
        dao.doDelete(categoriaId);
        Message message = new Message("", "", INFO);

        message.setType(SUCCESS);
        message.setBody("Eliminato con successo.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        List<Categoria> listaCategorie = new ArrayList<Categoria>();
        listaCategorie = dao.doRetrieveAll();
        getServletContext().removeAttribute("categorie");
        getServletContext().setAttribute("categorie", listaCategorie);

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonCategorie = gson.toJson(message);
        out.write(jsonCategorie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
