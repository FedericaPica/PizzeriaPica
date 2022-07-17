package controller.carrello;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Orario;
import model.dao.OrarioDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PrelevaOrariDisponibiliServlet", value = "/PrelevaOrariDisponibiliServlet")
public class PrelevaOrariDisponibiliServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        PrintWriter out = new PrintWriter(response.getWriter());
        OrarioDAO orarioDAO = new OrarioDAO();
        String ritiroDt = request.getParameter("ritiroDt");
        List<Orario> orarioList = (ArrayList<Orario>) orarioDAO.doRetrieveByOrdineRitiroDt(ritiroDt);
        if (orarioList.size() == 0)
            orarioList = orarioDAO.doRetrieveAll();
        ArrayList<String> orariCorretti = new ArrayList<String>();
        for (Orario o: orarioList) {
            String orario = o.getOrario().toLocalTime().toString();
            orariCorretti.add(orario);
        }
        System.out.println(orariCorretti);
        String jsonResponse = gson.toJson(orariCorretti);
        out.print(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
