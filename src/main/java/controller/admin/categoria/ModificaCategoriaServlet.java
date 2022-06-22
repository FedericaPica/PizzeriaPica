package controller.admin.categoria;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.dao.CategoriaDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ModificaCategoriaServlet", value = "/ModificaCategoriaServlet")
public class ModificaCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoriaId = Integer.parseInt(request.getParameter("id"));
        CategoriaDAO dao = new CategoriaDAO();
        Categoria categoria = dao.doRetrieveById(categoriaId);

        String flag = "update";
        request.setAttribute("flag", flag);
        request.setAttribute("categoria", categoria);
        String address;
        address = "/WEB-INF/results/admin/form_categoria.jsp";


        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
