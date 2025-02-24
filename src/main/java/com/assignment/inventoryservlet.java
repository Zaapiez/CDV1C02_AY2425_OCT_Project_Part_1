package com.assignment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class inventoryservlet
 */
@WebServlet("/inventoryservlet")
public class inventoryservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL = "jdbc:mysql://localhost:3306/inventory";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	// Step 2: Prepare list of SQL prepared statements to perform CRUD to our
	// database
	private static final String INSERT_PRODUCT_SQL = "INSERT INTO inventory" + " (prodname, prodquantity) VALUES "
			+ " (?, ?);";
	private static final String SELECT_PRODUCT_BY_ID = "select prodname,prodquantity from inventory where prodname = ?";
	private static final String SELECT_ALL_PRODUCTS = "select * from inventory ";
	private static final String DELETE_PRODUCTS_SQL = "delete from inventory where prodname = ?;";
	private static final String UPDATE_PRODUCTS_SQL = "update inventory set prodname = ?, prodquantity = ? where prodname = ?;";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public inventoryservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stubString action = request.getServletPath();
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/inventoryservlet/delete":
				deleteProduct(request, response);
				break;
			case "/inventoryservlet/edit":
				showEditForm(request, response);
				break;
			case "/inventoryservlet/update":
				updateProduct(request, response);
				break;
			case "/inventoryservlet/dashboard":
				listProduct(request, response);
				break;
			case "/inventoryservlet/insert":
				addNewProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<product> products = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				String pn = rs.getString("prodname");
				String pq = rs.getString("prodquantity");
				products.add(new product(pn, pq));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("listProduct", products);
		request.getRequestDispatcher("/inventory.jsp").forward(request, response);
	}

	public void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String prodname = request.getParameter("prodname");
		product existingProd = new product("", "");
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
			preparedStatement.setString(1, prodname);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				prodname = rs.getString("prodname");
				String prodquantity = rs.getString("prodquantity");
				existingProd = new product(prodname, prodquantity);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("product", existingProd);
		request.getRequestDispatcher("/editproduct.jsp").forward(request, response);
	}

	public void updateProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String oriName = request.getParameter("oriName");
		String prodname = request.getParameter("prodname");
		String prodquantity = request.getParameter("prodquantity");

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL)) {
			statement.setString(1, prodname);
			statement.setString(2, prodquantity);
			statement.setString(3, oriName);
			statement.executeUpdate();
		}

		response.sendRedirect("http://localhost:8090/Assignmentpt2/inventoryservlet/dashboard");
	}

	public void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String prodname = request.getParameter("prodname");
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
			statement.setString(1, prodname);
			int i = statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:8090/Assignmentpt2/inventoryservlet/dashboard");
	}

	public void addNewProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher rd = null;
		rd = getServletContext().getRequestDispatcher("/AddProductServlet");
		rd.include(request, response);
	}

}