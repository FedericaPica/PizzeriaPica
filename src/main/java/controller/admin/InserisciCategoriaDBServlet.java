package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Message;
import model.dao.CategoriaDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "InserisciCategoriaDBServlet", value = "/InserisciCategoriaDBServlet")
public class InserisciCategoriaDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        int priority = Integer.parseInt(request.getParameter("priority"));
        Message message = new Message("", "", INFO);

        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setPriority(priority);
        CategoriaDAO dao = new CategoriaDAO();

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
        request.setAttribute("message", message);

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
}
