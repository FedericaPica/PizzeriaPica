package controller.admin;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Prodotto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "MostraProdottiServlet", value = "/MostraProdottiServlet")
public class MostraProdottiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Categoria> categorie = new ArrayList<>();
        categorie = (ArrayList<Categoria>) getServletContext().getAttribute("categorie");

        ArrayList<Prodotto> prodotti = new ArrayList<>();
        ArrayList<Prodotto> prodottiDef = new ArrayList<>();
        for (Categoria c: categorie) {
            String nome = "lista" + c.getNome();
            prodotti = (ArrayList<Prodotto>) getServletContext().getAttribute(nome);
            if (prodotti != null) {
               for (Prodotto p: prodotti) {

                   prodottiDef.add(p);
               }
            }
        }
        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonProdotti = gson.toJson(prodottiDef);
        out.write(jsonProdotti);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
