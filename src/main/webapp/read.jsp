<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Read Cities</title>
    <%@include file="bootstrap.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
    <h1>City List</h1>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Country Code</th>
            <th>District</th>
            <th>Population</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <% try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/world";
            String username = "your-username";
            String password = "your-password";

            // Establish database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepare SQL statement
            String sql = "SELECT * FROM city";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute SQL query
            ResultSet resultSet = statement.executeQuery();

            // Iterate over the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String countryCode = resultSet.getString("CountryCode");
                String district = resultSet.getString("District");
                int population = resultSet.getInt("Population");

                // Display the city data in a table row
                %>
                <tr>
                    <td><%= id %></td>
                    <td><%= name %></td>
                    <td><%= countryCode %></td>
                    <td><%= district %></td>
                    <td><%= population %></td>
                    <td><a href="editCity?id=<%= id %>">Edit</a></td>
                    <td><a href="deleteCity?id=<%= id %>">Delete</a></td>
                </tr>
                <%
            }

            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        %>
    </table>

    <a href="addCity.jsp">Add City</a>
</body>
</html>
