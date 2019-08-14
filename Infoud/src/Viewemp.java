

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
 * Servlet implementation class Viewemp
 */
@WebServlet("/Viewemp")
public class Viewemp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Viewemp() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String emp = (String)request.getAttribute("emp");
		Connection con = null;
		PreparedStatement st = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con   = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/dtproject2.db");
			String sql="select * from employe where empid='"+emp+"'";
			st=con.prepareStatement(sql);
			ResultSet r= st.executeQuery();
			if(r.next()) {
				String sql2="select * from attendance where id='"+emp+"'";
				st=con.prepareStatement(sql2);
				r= st.executeQuery();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>" + 
				"<head>" + 
				"	<title>assignment</title>" + 
				"</head>" + 
				"<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tem.css\">" + 
				"<body><div class=\"main\"><h3 style='"+"margin-left=-190%;"+"'>EMPLOYE_ID :'"+r.getString(1)+"'</h3>" + 
				"<h4>TOTAL ATTENDANCE'"+r.getString(2)+"'</h4></body>");
		}else {
			out.println("<html><head>assignment</head><script>alert('"+"NOT EXIST"+  "');</script><body></body></html>");
			response.sendRedirect("index.html");
		}
		
	}catch(Exception e) {
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

