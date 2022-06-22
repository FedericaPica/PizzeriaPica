package controller.admin.categoria;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "MostraCategorieServlet", value = "/MostraCategorieServlet")
public class MostraCategorieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Categoria> categorie = new ArrayList<>();
        categorie = (ArrayList<Categoria>) getServletContext().getAttribute("categorie");

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonCategorie = gson.toJson(categorie);
        out.write(jsonCategorie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
