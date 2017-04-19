package drug_info;

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
 * Servlet implementation class drug_info
 */
@WebServlet("/drug_info")
public class drug_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public drug_info() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		try
		{
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
			//st.executeUpdate("use trial");
			String drug1=request.getParameter("drug1");
			//String n = System.getProperty("line.separator");
			if(drug1.matches(""))
			{
				out.println("Please give proper details");
			}
			String start_head="<font color='red'><b>";
			String end_head="</b></font>";
			//String start_info="<i>";
			//String end_info="</i>";
			ResultSet r=st.executeQuery("select * from drug_info where drug_info.name='"+drug1+"'");
			while(r.next())
			{
				out.print(start_head+"NAME:"+end_head+"<i>"+r.getString("name")+"</i><br><br>");
				out.print(start_head+"DESCRIPTION: "+end_head+"<i>"+r.getString("description")+"</i><br><br>");
				out.print(start_head+"INDICATION:"+end_head+"<i>"+r.getString("indication")+"</i><br><br>");
				out.print(start_head+"TOXICITY:"+end_head+"<i>"+r.getString("toxicity")+"</i><br><br>");
				out.print(start_head+"PRECAUTIONS:"+end_head+"<i>"+r.getString("food_interactions")+"</i><br><br>");
				
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
