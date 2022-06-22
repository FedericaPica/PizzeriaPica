package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Prodotto;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InitServlet", value = "/InitServlet", loadOnStartup = 0)
public class InitServlet extends HttpServlet {
    public void init() throws ServletException{
        super.init();

        List<Categoria> categorie= new ArrayList<Categoria>();
        CategoriaDAO cDao = new CategoriaDAO();
        categorie = cDao.doRetrieveAll();
        ProdottoDAO pDao = new ProdottoDAO();

        getServletContext().setAttribute("categorie", categorie);

    }
}