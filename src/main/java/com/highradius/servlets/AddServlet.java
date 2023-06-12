package com.highradius.servlets;
import com.highradius.connection.DbConnect;   
import com.highradius.implementation.*;
import com.highradius.model.invoicee;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("unused")
@WebServlet("/add")
public class AddServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4674127683867288331L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String slno= req.getParameter("slno");
		
		String Customer_order_id= req.getParameter("Customer_order_id");
		
		String sales_org= req.getParameter("sales_org");
		
		String DISTRIBUTION_CHANNEL= req.getParameter("DISTRIBUTION_CHANNEL");
		
		String DIVISION= req.getParameter("DIVISION");
		
		String RELEASED_CREDIT_VALUE= req.getParameter("RELEASED_CREDIT_VALUE");
		
		String PURCHASE_ORDER_TYPE= req.getParameter("PURCHASE_ORDER_TYPE");
		
		String COMPANY_CODE= req.getParameter("COMPANY_CODE");
		
		String ORDER_CREATION_DATE= req.getParameter("ORDER_CREATION_DATE");
		
		String ORDER_CREATION_TIME= req.getParameter("ORDER_CREATION_TIME");
		
		String CREDIT_CONTROL_AREA= req.getParameter("CREDIT_CONTROL_AREA");
		
		String SOLD_TO_PARTY= req.getParameter("SOLD_TO_PARTY");
		
		String ORDER_AMOUNT= req.getParameter("ORDER_AMOUNT");
		
		String REQUESTED_DELIVERY_DATE= req.getParameter("REQUESTED_DELIVERY_DATE");
		
		String ORDER_CURRENCY= req.getParameter("ORDER_CURRENCY");
		
		String CREDIT_STATUS= req.getParameter("CREDIT_STATUS");
		
		String CUSTOMER_NUMBER= req.getParameter("CUSTOMER_NUMBER");
		
		String AMOUNT_IN_USD= req.getParameter("AMOUNT_IN_USD");
		
		String UNIQUE_CUST_ID= req.getParameter("UNIQUE_CUST_ID");
		
		invoicee invvoice = new invoicee(slno,Customer_order_id,sales_org,DISTRIBUTION_CHANNEL,DIVISION,RELEASED_CREDIT_VALUE,PURCHASE_ORDER_TYPE,COMPANY_CODE,ORDER_CREATION_DATE,ORDER_CREATION_TIME,CREDIT_CONTROL_AREA,SOLD_TO_PARTY,ORDER_AMOUNT,REQUESTED_DELIVERY_DATE,ORDER_CURRENCY,CREDIT_STATUS,CUSTOMER_NUMBER,AMOUNT_IN_USD,UNIQUE_CUST_ID);
		
		try {
			invdao dao = new invdao(DbConnect.getConn());
			HttpSession session = req.getSession();
			boolean f = dao.add(invvoice);
			
			
			if (f) {
				session.setAttribute("succMSG","ADDED SUCCESSFULLY");
				resp.sendRedirect("add.jsp");
				System.out.println("data inserted");
			}
			else {
				session.setAttribute("erroMSG"," UNSUCCESSFULL");
				resp.sendRedirect("add.jsp");
				System.out.println("error");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		System.out.println(invvoice);
	}
	
	
}

