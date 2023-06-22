package com.highradius.servlets;

import com.highradius.connection.DbConnect;
import com.highradius.implementation.invdao;
import com.highradius.model.invoicee;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private static final long serialVersionUID = 4674127683867288331L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle POST request
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle GET request
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*"); // Allow requests from any origin
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET"); // Allow POST and GET requests
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Allow only Content-Type header

        // Retrieve data from request parameters
        String customerOrderId = req.getParameter("Customer_order_id");
        String salesOrg = req.getParameter("sales_org");
        String distributionChannel = req.getParameter("DISTRIBUTION_CHANNEL");
        String customerNumber = req.getParameter("CUSTOMER_NUMBER");
        String companyCode = req.getParameter("COMPANY_CODE");
        String orderCurrency = req.getParameter("ORDER_CURRENCY");
        String amountInUSD = req.getParameter("AMOUNT_IN_USD");
        String orderCreationDate = req.getParameter("ORDER_CREATION_DATE");

        // Create invoicee object
        invoicee invoice = new invoicee();
        invoice.setCustomer_order_id(customerOrderId);
        invoice.setSales_org(salesOrg);
        invoice.setDistribution_channel(distributionChannel);
        invoice.setCustomer_number(customerNumber);
        invoice.setCompany_code(companyCode);
        invoice.setOrder_currency(orderCurrency);
        invoice.setAmount_in_usd(amountInUSD);
        invoice.setOrder_creation_date(orderCreationDate);

        try {
            invdao dao = new invdao(DbConnect.getConn());
            HttpSession session = req.getSession();
            boolean isAdded = dao.add(invoice);

            if (isAdded) {
                session.setAttribute("succMSG", "ADDED SUCCESSFULLY");
                resp.sendRedirect("add.jsp");
                System.out.println("Data inserted successfully");
            } else {
                session.setAttribute("erroMSG", "UNSUCCESSFUL");
                resp.sendRedirect("add.jsp");
                System.out.println("Error occurred while inserting data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(invoice);
    }
}
