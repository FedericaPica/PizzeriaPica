package controller.admin.orario;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Categoria;
import model.beans.Message;
import model.beans.Orario;
import model.dao.CategoriaDAO;
import model.dao.OrarioDAO;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "InserisciOrarioDBServlet", value = "/InserisciOrarioDBServlet")
public class InserisciOrarioDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Time ora = Time.valueOf((request.getParameter("orario")) + ":00");
        Message message = new Message("", "", INFO);

        Orario orario = new Orario();
        orario.setOrario(ora);
        OrarioDAO dao = new OrarioDAO();
        dao.doSave(orario);

        message.setType(SUCCESS);
        message.setBody("Inserito con successo.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        String address = "/WEB-INF/results/admin/form_orario.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
