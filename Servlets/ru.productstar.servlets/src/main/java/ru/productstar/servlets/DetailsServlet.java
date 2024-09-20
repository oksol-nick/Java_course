package ru.productstar.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transactions;
import java.io.IOException;
import java.util.List;

public class DetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();

        var session = req.getSession(false);
        if (session == null) {
            resp.getWriter().println("Not Authorized");
            resp.setHeader("Refresh", "3; /");
            return;
        }

        resp.getWriter().println("Incomes&Expenses\n");
        for(Transactions e : (List<Transactions>) context.getAttribute("transactions") ) {
            if(e.getSum() >= 0) {
                resp.getWriter().println(String.format("Income  + %s(%d)", e.getName(), e.getSum()));
            } else {
                resp.getWriter().println(String.format("Expense - %s(%d)", e.getName(), e.getSum()*(-1)));
            }
        }
        resp.getWriter().println("\n");
    }
}
