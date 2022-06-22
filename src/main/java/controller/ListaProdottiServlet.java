package controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Prodotto;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListaProdottiServlet", value = "/ListaProdottiServlet")
public class ListaProdottiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));


        ProdottoDAO pDao = new ProdottoDAO();
        List<Prodotto> prodotti = new ArrayList<>();
        prodotti = pDao.doRetrieveByCategoria(idCategoria);

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonCategorie = gson.toJson(prodotti);
        out.write(jsonCategorie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
