package controller.admin.ordine;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Ordine;
import model.beans.Prodotto;
import model.beans.Utente;
import model.dao.OrdineDAO;
import model.dao.ProdottoDAO;
import model.dao.UtenteDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraOrdiniServlet", value = "/MostraOrdiniServlet")
public class MostraOrdiniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ordine> ordini = new ArrayList<Ordine>();
        OrdineDAO dao = new OrdineDAO();
        ordini = dao.doRetrieveAll();

//        UtenteDAO uDao = new UtenteDAO();
//        List<Utente> nomiClienti = new ArrayList<>();
//        for (Ordine o : ordini) {
//            nomiClienti.add(uDao.doRetrieveById(o.getUtenteid()));
//        }

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonOrdini = gson.toJson(ordini);
//        String jsonNomi = gson.toJson(nomiClienti);
        out.write(jsonOrdini);
//        out.write(jsonNomi);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
