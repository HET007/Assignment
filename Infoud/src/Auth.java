

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Auth
 */
@WebServlet("/Auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user , pass;
		user = request.getParameter("user");
		pass = request.getParameter("pass");
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con   = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/dtproject2.db");
			String sql="select * from admin where user='"+user+"'and password='"+pass+"'";
			st=con.prepareStatement(sql);
			ResultSet r= st.executeQuery();
			if(r.next()) {
				response.sendRedirect("employe.html");
			}
			else {
				PrintWriter out = response.getWriter();
				out.println("<html><head>assignment</head><script>alert('"+"Wrong id password"+  "');</script><body></body></html>");
				response.sendRedirect("index.html");
			}
		
		}
		catch(Exception e) {
			System.out.println("Exception" +e.getMessage());
		}
		finally {
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	}

}
