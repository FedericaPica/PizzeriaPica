package controller.admin.orario;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Orario;
import model.beans.Utente;
import model.dao.OrarioDAO;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraOrariServlet", value = "/MostraOrariServlet")
public class MostraOrariServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Orario> orari = new ArrayList<Orario>();
        OrarioDAO dao = new OrarioDAO();
        orari = dao.doRetrieveAll();

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonOrari = gson.toJson(orari);
        out.write(jsonOrari);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
