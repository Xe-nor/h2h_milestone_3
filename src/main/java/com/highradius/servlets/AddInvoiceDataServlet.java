package com.highradius.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.connection.DbConnect;
import com.highradius.implementation.invdao;
import com.highradius.model.invoicee;

@WebServlet("/api/invoices")
public class AddInvoiceDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Read the request body
            BufferedReader reader = request.getReader();
            Gson gson = new Gson();
            invoicee invoice = gson.fromJson(reader, invoicee.class);

            invdao dao = new invdao(DbConnect.getConn());
            boolean success = dao.add(invoice);

            if (success) {
                // Convert the added invoice to JSON
                String jsonInvoice = gson.toJson(invoice);

                // Set the response content type to JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Send the JSON response
                response.getWriter().write(jsonInvoice);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
