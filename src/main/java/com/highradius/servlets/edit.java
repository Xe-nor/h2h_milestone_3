package com.highradius.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.highradius.connection.DbConnect;
import com.highradius.implementation.invdao;
import com.highradius.model.invoicee;


@WebServlet("/edit")
public class edit extends HttpServlet {
/**
	 * 
	 */
	private static final long serialVersionUID = 4116101615250242807L;

@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
		String slno= request.getParameter("slno");
		
		String Customer_order_id= request.getParameter("Customer_order_id");
		
		String sales_org= request.getParameter("sales_org");
		
		String DISTRIBUTION_CHANNEL= request.getParameter("DISTRIBUTION_CHANNEL");
		
		String DIVISION= request.getParameter("DIVISION");
		
		String RELEASED_CREDIT_VALUE= request.getParameter("RELEASED_CREDIT_VALUE");
		
		String PURCHASE_ORDER_TYPE= request.getParameter("PURCHASE_ORDER_TYPE");
		
		String COMPANY_CODE= request.getParameter("COMPANY_CODE");
		
		String ORDER_CREATION_DATE= request.getParameter("ORDER_CREATION_DATE");
		
		String ORDER_CREATION_TIME= request.getParameter("ORDER_CREATION_TIME");
		
		String CREDIT_CONTROL_AREA= request.getParameter("CREDIT_CONTROL_AREA");
		
		String SOLD_TO_PARTY= request.getParameter("SOLD_TO_PARTY");
		
		String ORDER_AMOUNT= request.getParameter("ORDER_AMOUNT");
		
		String REQUESTED_DELIVERY_DATE= request.getParameter("REQUESTED_DELIVERY_DATE");
		
		String ORDER_CURRENCY= request.getParameter("ORDER_CURRENCY");
		
		String CREDIT_STATUS= request.getParameter("CREDIT_STATUS");
		
		String CUSTOMER_NUMBER= request.getParameter("CUSTOMER_NUMBER");
		
		String AMOUNT_IN_USD= request.getParameter("AMOUNT_IN_USD");
		
		String UNIQUE_CUST_ID= request.getParameter("UNIQUE_CUST_ID");
		
	
		
		invoicee invvoice = new invoicee(slno,Customer_order_id,sales_org,DISTRIBUTION_CHANNEL,DIVISION,RELEASED_CREDIT_VALUE,PURCHASE_ORDER_TYPE,COMPANY_CODE,ORDER_CREATION_DATE,ORDER_CREATION_TIME,CREDIT_CONTROL_AREA,SOLD_TO_PARTY,ORDER_AMOUNT,REQUESTED_DELIVERY_DATE,ORDER_CURRENCY,CREDIT_STATUS,CUSTOMER_NUMBER,AMOUNT_IN_USD,UNIQUE_CUST_ID);

try {
	invdao dao = new invdao(DbConnect.getConn());
	HttpSession session = request.getSession();
	boolean f = dao.edit(invvoice);
	
	
	if (f) {
		session.setAttribute("succMSG","EDITTED SUCCESSFULLY");
		response.sendRedirect("add.jsp");
		System.out.println("data inserted");
	}
	else {
		session.setAttribute("erroMSG"," UNSUCCESSFULL");
		response.sendRedirect("add.jsp");
		System.out.println("error");
	}
	
	
} catch (SQLException e) {
	
	e.printStackTrace();
}

System.out.println(invvoice);
}
	
}
