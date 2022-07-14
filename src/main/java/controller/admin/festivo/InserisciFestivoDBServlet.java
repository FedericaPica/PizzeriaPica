package controller.admin.festivo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.Festivi;
import model.beans.Message;
import model.beans.Orario;
import model.dao.FestivoDAO;
import model.dao.OrarioDAO;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static model.beans.MessageType.INFO;
import static model.beans.MessageType.SUCCESS;

@WebServlet(name = "InserisciFestivoDBServlet", value = "/InserisciFestivoDBServlet")
public class InserisciFestivoDBServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String giorno = request.getParameter("festivo");
        Date giornoF = null;
        try {
            giornoF = new SimpleDateFormat("yyyy-MM-dd").parse(giorno);
            System.out.println(giornoF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Message message = new Message("", "", INFO);

        Festivi festivo = new Festivi();
        java.sql.Date sqlDate = new java.sql.Date(giornoF.getTime());
        festivo.setGiorno(sqlDate);
        FestivoDAO dao = new FestivoDAO();
        dao.doSave(festivo);

        message.setType(SUCCESS);
        message.setBody("Inserito con successo.");
        message.setTitle("Ok!");
        request.setAttribute("message", message);

        String address = "/WEB-INF/results/admin/form_festivo.jsp";
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
