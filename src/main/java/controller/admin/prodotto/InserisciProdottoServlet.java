package controller.admin.prodotto;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "InserisciProdottoServlet", value = "/InserisciProdottoServlet")
public class InserisciProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idCategoria = Integer.parseInt(request.getParameter("idCat"));
        String address;
        address = "/WEB-INF/results/admin/form_prodotto.jsp";

        String flag = "insert";
        request.setAttribute("flag", flag);
        request.setAttribute("idCategoria", idCategoria);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }
}
