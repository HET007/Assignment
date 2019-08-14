

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Crudopration
 */
@WebServlet("/Crudopration")
public class Crudopration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Crudopration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s=request.getParameter("crud");
		String empid=request.getParameter("empid");
		Connection con = null;
		PreparedStatement st = null;
		PrintWriter out = response.getWriter();
		try {
			Class.forName("org.sqlite.JDBC");
		    con= DriverManager.getConnection("jdbc:sqlite:C:/sqlite/dtproject2.db");
		    String sql;
		    switch(s) {
			
		    case "1":
					sql="select * from employe where empid='"+empid+"'";
					st=con.prepareStatement(sql);
					ResultSet r= st.executeQuery();
					if(!r.next()) {
					 String	sql2 ="insert into employe(empid) VALUES('"+empid+"')";
					 st=con.prepareStatement(sql2);
					 out.println("<html><head>assignment</head><script>alert('"+"ADDED SUCESSFULLY"+  "');</script><body></body></html>");
					 response.sendRedirect("index.html");
					}
					else{
						out.println("<html><head>assignment</head><script>alert('"+"ID ALREDY EXIST"+  "');</script><body></body></html>");
						response.sendRedirect("employe.html");
					}
					
				    break;
			
		    case "2":
		    		response.sendRedirect("update.html");
			        break;
			
		    case "3":
		    	String sql3="select * from employe where empid='"+empid+"'";
				st=con.prepareStatement(sql3);
			    r= st.executeQuery();
				if(r.next()) {
				sql="delete from employe WHERE empid='"+empid+"'";
				 st=con.prepareStatement(sql);
				 out.println("<html><head>assignment</head><script>alert('"+"DELETED SUCESSFULLY"+  "');</script><body></body></html>");
				 response.sendRedirect("index.html");
				 }else{
						out.println("<html><head>assignment</head><script>alert('"+"NOT EXIST"+  "');</script><body></body></html>");
						response.sendRedirect("employe.html");
					}
			    break;
			
		    
		    case "4":
		    	request.setAttribute("emp",empid);
		    	RequestDispatcher rd = request.getRequestDispatcher("/Viewemp");
		    	rd.forward(request,response);
			    break;
			}}
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
