package ru.productstar.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.productstar.servlets.model.Transactions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/incomes/add")
public class IncomesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var context = req.getServletContext();
        context.log("[IncomesServlet] doGet");

        var session = req.getSession(false);
        if (session == null) {
            resp.getWriter().println("Not Authorized");
            resp.setHeader("Refresh", "5; /");
            return;
        }

        var transaction = new ArrayList<Transactions>((List)context.getAttribute("transactions"));

        int freeMoney = (int)context.getAttribute("freeMoney");
        for(var k : req.getParameterMap().keySet()) {
            int value = Integer.parseInt(req.getParameter(k));
            freeMoney += value;
            transaction.add(new Transactions(k, value));
        }

        context.setAttribute("freeMoney", freeMoney);
        context.setAttribute("transactions", transaction);
        resp.sendRedirect("/summary");
    }
}
