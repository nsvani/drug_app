package first_aid_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class first_aid_1
 */
@WebServlet("/first_aid_1")
public class first_aid_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public first_aid_1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			String[] symptoms=new String[300];
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/drugbank","root","vani0410");
			Statement st=c.createStatement();
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			//out.print("hello");
			ResultSet r=st.executeQuery("select distinct ind from otc ");
			int i=0;
			while(r.next())
			{
				symptoms[i]=r.getString("ind");
				out.println(symptoms[i]+";");
				System.out.println(symptoms[i]);
				i++;
			}
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
