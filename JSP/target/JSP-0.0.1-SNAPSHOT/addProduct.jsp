<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
    
<%
    // Retrieve form parameters
    String productName = request.getParameter("productName");
    int productId = Integer.parseInt(request.getParameter("productId"));
    float productPrice = Float.parseFloat(request.getParameter("productPrice"));

    // JDBC variables
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        // JDBC setup (replace with your database credentials)
        String jdbcUrl = "jdbc:mariadb://localhost:3306/your_database";
        String dbUser = "your_username";
        String dbPassword = "your_password";

        // Load the JDBC driver
        Class.forName("org.mariadb.jdbc.Driver");

        // Establish the connection
        conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

        // SQL query to insert a new product
        String sql = "INSERT INTO products (name, id, price) VALUES (?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, productName);
        pstmt.setInt(2, productId);
        pstmt.setFloat(3, productPrice);

        // Execute the query
        pstmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product Result</title>
</head>
<body>
	<h2>Product Added Successfully!</h2>
    <a href="addProduct.html">Add Another Product</a>
    <br>
    <a href="viewProduct.jsp">View Products</a>

</body>
</html>