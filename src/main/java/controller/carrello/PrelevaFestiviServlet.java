package controller.carrello;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Festivi;
import model.beans.Utente;
import model.dao.FestivoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "PrelevaFestiviServlet", value = "/PrelevaFestiviServlet")
public class PrelevaFestiviServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        FestivoDAO festivoDAO = new FestivoDAO();
        List<Festivi> festiviList = festivoDAO.doRetrieveAll();
        Gson json = new Gson();
        String newString = json.toJson(festiviList);
        PrintWriter out = new PrintWriter(response.getWriter());
        out.write(newString);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
