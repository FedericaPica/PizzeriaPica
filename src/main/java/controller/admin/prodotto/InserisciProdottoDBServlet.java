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

@WebServlet(name = "InserisciProdottoDBServlet", value = "/InserisciProdottoDBServlet")
public class InserisciProdottoDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        Double prezzo = Double.valueOf(request.getParameter("prezzo"));
        String descrizione = request.getParameter("descrizione");
        int categoriaId = Integer.parseInt(request.getParameter("idCat"));
        int sconto = Integer.parseInt(request.getParameter("sconto"));

        Message message = new Message("", "", INFO);

        Prodotto prodotto = new Prodotto();
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        prodotto.setDescrizione(descrizione);
        prodotto.setCategoriaid(categoriaId);
        prodotto.setSconto(sconto);

        ProdottoDAO dao = new ProdottoDAO();
        dao.doSave(prodotto);

        message.setType(SUCCESS);
        message.setBody("Inserito con successo.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        request.setAttribute("check", "categoria");

        request.setAttribute("prodotto", prodotto);
        String address = "/WEB-INF/results/admin/form_prodotto.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
