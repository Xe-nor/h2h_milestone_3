package com.highradius.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import com.highradius.connection.DbConnect;
import com.highradius.implementation.invdao;
import com.highradius.model.invoicee;


@WebServlet("/read-invoice-data")
public class ReadInvoiceDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            invdao dao = new invdao(DbConnect.getConn());
            List<invoicee> invoices = dao.get20Invoice();

            // Convert the list of invoices to JSON
            Gson gson = new Gson();
            String jsonInvoices = gson.toJson(invoices);

            // Set the response content type to JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Send the JSON response
            response.getWriter().write(jsonInvoices);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
