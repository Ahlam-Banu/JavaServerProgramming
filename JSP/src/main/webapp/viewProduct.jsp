<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>

    
<%
    // JDBC variables
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        // JDBC setup (replace with your database credentials)
        //String jdbcUrl = "jdbc:mariadb://localhost:3306/e2101083_jsp_db";
        String jdbcUrl = "jdbc:mariadb://mariadb.vamk.fi/e2101083_jsp_db";
        String dbUser = "e2101083";
        String dbPassword = "9SbjzjcK6hQ";

        // Load the JDBC driver
        Class.forName("org.mariadb.jdbc.Driver");

        // Establish the connection
        conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

        // SQL query to retrieve all products
        String sql = "SELECT * FROM products";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Products</title>
</head>
<body>
    <h2>Product List</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>ID</th>
            <th>Price</th>
        </tr>
        <% while (rs.next()) { %>
            <tr>
                <td><%= rs.getString("name") %></td>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getFloat("price") %></td>
            </tr>
        <% } %>
    </table>
    <br>
    <a href="addProduct.html">Add Product</a>
</body>
</html>
<%
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>