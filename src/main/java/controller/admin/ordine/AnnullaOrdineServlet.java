package controller.admin.ordine;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Message;
import model.beans.Ordine;
import model.dao.OrdineDAO;

import java.io.IOException;
import java.io.PrintWriter;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "AnnullaOrdineServlet", value = "/AnnullaOrdineServlet")
public class AnnullaOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ordine ordine = new Ordine();
        int id = Integer.parseInt(request.getParameter("idOrdine"));
        Message message = new Message("", "", INFO);

        OrdineDAO dao = new OrdineDAO();
        ordine = dao.doRetrieveById(id);
        dao.doUpdate(ordine);

        message.setType(SUCCESS);
        message.setBody("Ordine annullato.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        PrintWriter out = new PrintWriter(response.getWriter());
        Gson gson = new Gson();
        String jsonMess = gson.toJson(message);
        out.write(jsonMess);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
