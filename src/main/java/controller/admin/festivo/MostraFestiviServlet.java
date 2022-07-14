package controller.admin.festivo;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Festivi;
import model.beans.Orario;
import model.dao.FestivoDAO;
import model.dao.OrarioDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraFestiviServlet", value = "/MostraFestiviServlet")
public class MostraFestiviServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Festivi> festivi = new ArrayList<Festivi>();
        FestivoDAO dao = new FestivoDAO();
        festivi = dao.doRetrieveAll();

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonOrari = gson.toJson(festivi);
        out.write(jsonOrari);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
