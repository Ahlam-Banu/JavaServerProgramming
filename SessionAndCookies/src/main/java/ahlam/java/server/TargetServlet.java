package ahlam.java.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/targetServlet")
public class TargetServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession(false);
		PrintWriter out = res.getWriter();
		
		/*Cookie[] cookies = req.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			out.println("cookie name: " + cookies[i].getName() + "<br>");
			out.println("cookie value: " + cookies[i].getValue() + "<br>");
		}*/
		res.setContentType("text/html");
		if(session == null) {
			//there is no current session or session expired
			out.println("session expired");}
		else {
				String username = (String) session.getAttribute("username");
	            String password = (String) session.getAttribute("password");
	            
	            // Attempt to establish a MariaDB connection
	            Connection connection = null;
	            
	            try {
	            	out.println("Welcome "+ username + " your login was successful!<br>");
	            	Class.forName("org.mariadb.jdbc.Driver");
	                connection = DriverManager.getConnection("jdbc:mariadb://mariadb.vamk.fi/e2101083_java_server", username, password);
	                out.println("Connection to MariaDB successful!");
	            } catch (SQLException e) {
	                // If the connection fails, redirect back to the login form
	                //res.sendRedirect("user.html");
	            	e.printStackTrace();
	            } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
	                if (connection != null) {
	                    try {
	                        connection.close();
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
			}		
	}
}
