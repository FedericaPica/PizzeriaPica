package controller.admin.ordine;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Message;
import model.beans.OrdineVisualizzato;
import model.beans.Prodotto;
import model.dao.ProdottoCarrelloDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.beans.MessageType.ERROR;
import static model.beans.MessageType.INFO;

@WebServlet(name = "MostraProdottiOrdineServlet", value = "/MostraProdottiOrdineServlet")
public class MostraProdottiOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int o = Integer.parseInt(request.getParameter("ordine"));


        List<OrdineVisualizzato> prodottiOrdine = new ArrayList<OrdineVisualizzato>();
        ProdottoCarrelloDAO dao = new ProdottoCarrelloDAO();
        prodottiOrdine = dao.doRetrieveByOrdineIdVisualizzato(o);


        request.setAttribute("prodottiOrdine", prodottiOrdine);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/results/admin/mostra_prodotti_ordine.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
