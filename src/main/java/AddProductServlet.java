

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String n = request.getParameter("prodname");
		String p = request.getParameter("prodquantity");
		try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root", "password");
			PreparedStatement ps = con.prepareStatement("insert into inventory values(?,?)");
		 ps.setString(1, n);
		 ps.setString(2, p);
		 int i = ps.executeUpdate();
		 if (i > 0){
			 response.sendRedirect(request.getContextPath() + "/inventoryservlet/dashboard");
		} 
		}
		catch (Exception exception) {
		 System.out.println(exception);
		 out.close();
		}
		doGet(request, response);
	}

}
