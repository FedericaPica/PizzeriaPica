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

@WebServlet(name = "InserisciCategoriaServlet", value = "/InserisciCategoriaServlet")
public class InserisciCategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address;
        address = "/WEB-INF/results/admin/form_categoria.jsp";

        String flag = "insert";
        request.setAttribute("flag", flag);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
