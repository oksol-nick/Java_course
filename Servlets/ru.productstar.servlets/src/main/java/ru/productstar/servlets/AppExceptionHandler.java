package ru.productstar.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Throwable;

    @WebServlet("/AppExceptionHandler")
    public class AppExceptionHandler extends HttpServlet {

        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processError(req, resp);
        }

        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processError(req, resp);
        }

        private void processError(HttpServletRequest req, HttpServletResponse resp) throws IOException {

            Throwable throwable = (Throwable)req.getAttribute("jakarta.servlet.error.exception");
            String message =  (String) req.getAttribute("jakarta.servlet.error.message");
            Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");

            if(statusCode != 500){
                resp.getWriter().write(String.format("Error(%d) - %s", statusCode, message));
            }else{
                resp.getWriter().write(String.format("Error(%d) - %s: %s", statusCode, throwable.getClass().getName(), throwable.getMessage()));
            }
        }
    }



