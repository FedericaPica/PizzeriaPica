package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.dao.CategoriaDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MenuServlet", value = "/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ArrayList<Categoria> categorie = new ArrayList<>();
        categorie = (ArrayList<Categoria>) getServletContext().getAttribute("categorie");

        for (Categoria x: categorie)
            response.getWriter().println("<li> <a href='index.jsp#" + x.getNome() + "'>" + x.getNome() + "</a></li>");
    }


}
