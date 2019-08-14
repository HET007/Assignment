

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
 * Servlet implementation class UpdateEmp
 */
@WebServlet("/UpdateEmp")
public class UpdateEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String emp , empn;
		emp = request.getParameter("emp");
		empn = request.getParameter("empn");
		Connection con = null;
		PreparedStatement st = null;
		PrintWriter out = response.getWriter();
		try {
			Class.forName("org.sqlite.JDBC");
			con   = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/dtproject2.db");
			String sql="select * from employe where empid='"+emp+"'";
			st=con.prepareStatement(sql);
			ResultSet r= st.executeQuery();
			if(r.next()) {
				String sql2="update employe SET empid = '"+empn+"',WHERE empid='"+emp+"'";
				st=con.prepareStatement(sql);
				out.println("<html><head>assignment</head><script>alert('"+"Update Suceessfull"+  "');</script><body></body></html>");
				response.sendRedirect("index.html");
			}
			else {
		
				out.println("<html><head>assignment</head><script>alert('"+"Alredy Exist"+  "');</script><body></body></html>");
				response.sendRedirect("employe.html");
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
