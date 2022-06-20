package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Message;
import model.beans.Prodotto;

import java.io.IOException;
import java.util.ArrayList;

import static model.beans.MessageType.*;

@WebServlet(name = "MostraProdottiServlet", value = "/MostraProdottiServlet")
public class MostraProdottiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String c = request.getParameter("categoria");
        Message message = new Message("", "", INFO);


        ArrayList<Prodotto> prodotti = new ArrayList<>();
            String nome = "lista" + c;
            prodotti = (ArrayList<Prodotto>) getServletContext().getAttribute(nome);

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
