package ahlam.java.server;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/sourceServlet")
public class SourceServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String username = req.getParameter("username");
		String  password = req.getParameter("password");
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(60);
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		PrintWriter out = res.getWriter();
		
		/*Cookie[] cookies = req.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			out.println("cookie name: " + cookies[i].getName() + "<br>");
			out.println("cookie value: " + cookies[i].getValue() + "<br>");
		}*/
		
		//res.sendRedirect("targetServlet"); //new
		res.setContentType("text/html");
		out.println("<a href='targetServlet'> Click here to get the user name </a>");
	}
}
