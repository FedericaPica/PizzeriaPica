package controller.admin.categoria;

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

@WebServlet(name = "ModificaCategoriaDBServlet", value = "/ModificaCategoriaDBServlet")
public class ModificaCategoriaDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Categoria categoria = new Categoria();
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        int priority = Integer.parseInt(request.getParameter("priority"));
        Message message = new Message("", "", INFO);

        categoria.setId(id);
        categoria.setNome(nome);
        categoria.setPriority(priority);
        System.out.println(categoria.getPriority());
        CategoriaDAO dao = new CategoriaDAO();


        if(dao.doRetrieveByPriority(priority) != null) {
            dao.doIncreasePriority(priority);
        }
        dao.doUpdate(categoria);

        List<Categoria> listaCategorie = new ArrayList<Categoria>();
        listaCategorie = dao.doRetrieveAll();
        getServletContext().removeAttribute("categorie");
        getServletContext().setAttribute("categorie", listaCategorie);


        message.setType(SUCCESS);
        message.setBody("Modifica effettuata.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        String address;
        request.setAttribute("categoria", categoria);
        address = "/WEB-INF/results/admin/form_categoria.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
