package Addition_app;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;


@WebServlet(urlPatterns = "/DemoServlet")
public class DemoServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		 // Get the input numbers from the HTML form
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));
		
     // Calculate the sum
        int sum = num1 + num2;

        // Set the response content type to HTML
        res.setContentType("text/html");

		PrintWriter out = res.getWriter();
		// Print the result as a response
        res.getWriter().println("<html><body>");
        res.getWriter().println("<h1>Result</h1>");
        res.getWriter().println("First Number : " + num1 + "<br>");
        res.getWriter().println("Second Number : " + num2 + "<br>");
        res.getWriter().println("Sum: " + sum + "<br>");
        res.getWriter().println("</body></html>");
	}

}
