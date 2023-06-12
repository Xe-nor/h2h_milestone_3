<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.highradius.model.invoicee" %>
<%@ page import="com.highradius.implementation.invdao" %>
<%@ page import="com.highradius.connection.DbConnect" %>
<%@ page import="com.google.gson.Gson" %>

<%
// Retrieve invoice data
invdao dao = new invdao(DbConnect.getConn());
List<invoicee> invoiceList = dao.get20Invoice();

// Convert invoice data to JSON
Gson gson = new Gson();
String jsonInvoiceList = gson.toJson(invoiceList);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Invoice Data</title>
    <%@include file="bootstrap.jsp"%>
    <style>
        pre {
           
            overflow: auto;
            background-color: #f7f7f7;
            padding: 10px;
        }
    </style>
</head>
<body>
    <%@include file="navbar.jsp" %>
    
    <div class="container ">
        <h1>Invoice Data</h1>
        
        <h3>JSON Data:</h3>
       <%= jsonInvoiceList %>
        
        <hr>
        
        <h3>Add Invoice</h3>
        <form action="add" method="POST">
            <div class="form-group">
                <label for="customerOrderId">Customer Order ID:</label>
                <input type="text" class="form-control" id="customerOrderId" name="customerOrderId" required>
            </div>
            <!-- Add more form fields for other invoice properties -->
            
            <button type="submit" class="btn btn-primary">Add Invoice</button>
        </form>
    </div>
</body>
</html>
