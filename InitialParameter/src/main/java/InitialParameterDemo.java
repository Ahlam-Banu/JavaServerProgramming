import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/initParam", initParams = {
		@WebInitParam(name = "init", value = "hello"),
		@WebInitParam(name = "name", value = "ahlam"),
		@WebInitParam(name = "dbURL", value = "jdbc:mariadb://mariadb.vamk.fi/mydb")
})
public class InitialParameterDemo extends HttpServlet {
	
	private String initParam;
	private String name;
	private String demoContext;
	
	@Override
	public void init(ServletConfig config) {
		initParam = config.getInitParameter("init");
		name = config.getInitParameter("name");
		ServletContext context = config.getServletContext();
		demoContext = context.getInitParameter("demo");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println(initParam + " " + name);
		out.println("demo:" + " " + demoContext);
		
	}

}
