

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
 * Servlet implementation class Salary
 */
@WebServlet("/Salary")
public class Salary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Salary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Connection con = null;
		PreparedStatement st = null;
		String emp = request.getParameter("user");
		int salary =10000,temp;
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
				temp=Integer.parseInt(r.getString(2));
				for(int i=temp;i<31;i++)
				{
					salary = salary-323;
				}
				out.println("<!DOCTYPE html>\r\n" + 
						"<html>" + 
						"<head>" + 
						"	<title>assignment</title>" + 
						"</head>" + "<script"+
						"function key(){\r\n" + 
						"	window.location = \"http://localhost:8080/Infoud/\";\r\n" + 
						"	return;\r\n" + 
						"\r\n" + 
						"}\r\n" + 
						"</script><link rel=\"stylesheet\" type=\"text/css\" href=\"css/tem.css\">" + 
						"<body><form name=\"xyz\" action=\"index.html\" method=\"POST\"><div class=\"main\"><h3 style='"+"margin-left=-190%;"+"'>EMPLOYE_Salary :"+salary+"</h3>" + 
						"<button name=\"b2\"  onclick=\"key();\"\">Logout</button></body></html>");
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
	}}
