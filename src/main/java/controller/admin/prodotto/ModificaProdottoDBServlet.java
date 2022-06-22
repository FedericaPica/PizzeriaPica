package controller.admin.prodotto;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Message;
import model.beans.Prodotto;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "ModificaProdottoDBServlet", value = "/ModificaProdottoDBServlet")
public class ModificaProdottoDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Prodotto prodotto = new Prodotto();
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        Double prezzo = Double.valueOf(request.getParameter("prezzo"));
        String descrizione = request.getParameter("descrizione");
        int sconto = Integer.parseInt(request.getParameter("sconto"));

        Message message = new Message("", "", INFO);

        prodotto.setId(id);
        prodotto.setNome(nome);
        prodotto.setDescrizione(descrizione);
        prodotto.setSconto(sconto);

        ProdottoDAO dao = new ProdottoDAO();
        dao.doUpdate(prodotto);

        message.setType(SUCCESS);
        message.setBody("Modifica effettuata.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        String address;
        request.setAttribute("prodotto", prodotto);
        address = "/WEB-INF/results/admin/form_prodotto.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
