package controller.admin.prodotto;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Message;
import model.beans.Prodotto;
import model.dao.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.beans.MessageType.*;

@WebServlet(name = "MostraProdottiServlet", value = "/MostraProdottiServlet")
public class MostraProdottiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int c = Integer.parseInt(request.getParameter("categoria"));
        Message message = new Message("", "", INFO);


            List<Prodotto> prodotti = new ArrayList<Prodotto>();
            ProdottoDAO dao = new ProdottoDAO();
            prodotti = dao.doRetrieveByCategoria(c);

            if(prodotti == null) {
                message.setType(ERROR);
                message.setBody("Non è presente alcun prodotto.");
                message.setTitle("Ops!");
                request.setAttribute("message", message); }

        request.setAttribute("prodotti", prodotti);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/results/admin/mostra_prodotti.jsp");
        dispatcher.forward(request, response);

        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
