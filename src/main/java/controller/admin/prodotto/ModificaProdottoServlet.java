package controller.admin.prodotto;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Prodotto;
import model.dao.CategoriaDAO;
import model.dao.ProdottoDAO;

import java.io.IOException;

@WebServlet(name = "ModificaProdottoServlet", value = "/ModificaProdottoServlet")
public class ModificaProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int prodottoId = Integer.parseInt(request.getParameter("idProdotto"));
        ProdottoDAO dao = new ProdottoDAO();
        Prodotto prodotto = dao.doRetrieveById(prodottoId);

        String flag = "update";
        request.setAttribute("flag", flag);
        request.setAttribute("prodotto", prodotto);
        String address;
        address = "/WEB-INF/results/admin/form_prodotto.jsp";


        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
